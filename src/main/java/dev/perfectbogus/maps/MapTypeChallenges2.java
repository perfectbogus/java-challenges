package dev.perfectbogus.maps;

import java.util.*;
import java.util.stream.Collectors;

public class MapTypeChallenges2 {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — IdentityHashMap (Challenges 1–3)
    //
    // IdentityHashMap uses REFERENCE EQUALITY (==) instead of equals()!
    // → two keys are "equal" ONLY if they are the SAME OBJECT in memory!
    // → useful when you need to track object IDENTITY, not content!
    //
    // Example:
    //   String a = new String("hello");
    //   String b = new String("hello");
    //   a.equals(b) → true  (same content!)
    //   a == b       → false (different objects!)
    //
    //   HashMap:         a and b are the SAME key (equals)!
    //   IdentityHashMap: a and b are DIFFERENT keys (different refs)!
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — IdentityHashMap: count unique object references
    //
    // Given a list of String objects, count how many are
    // UNIQUE BY REFERENCE (not by content!).
    //
    // Use IdentityHashMap<String, Boolean> to track seen references.
    // put(obj, true) → if obj was NOT seen before (by reference)
    // Return count of unique references.
    //
    // Input:  s1=new String("hello"), s2=new String("hello"), s3=s1
    //   list=[s1, s2, s3, s1]
    //   s1 == s1 → same reference
    //   s1 == s2 → DIFFERENT references! (even though s1.equals(s2)!)
    //   s3 == s1 → same reference
    //   unique refs: s1/s3, s2 → count=2
    //
    // HashMap would count only 1 (equals!) → IdentityHashMap counts 2! ✓
    //
    // Throw IllegalArgumentException if list is null.
    // ─────────────────────────────────────────────────────────────
    public static int challenge1(List<String> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        Map<String, Integer> map = new IdentityHashMap<>();
        for (String s : list) {
            map.merge(s, 1, Integer::sum);
        }
        return map.size();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — IdentityHashMap: object processing tracker
    //
    // Given a list of objects, simulate a processor that marks each
    // object as "visited" using IdentityHashMap.
    // Process each object: if NOT visited by reference → mark visited
    //                      if already visited → skip.
    //
    // Return the count of objects ACTUALLY PROCESSED (unique refs only).
    //
    // record Item(String name, int value)
    //
    // Input:  item1=new Item("x",1), item2=new Item("x",1), item3=item1
    //   list=[item1, item2, item3, item2]
    //   item1 → not visited → process! count=1
    //   item2 → not visited → process! count=2 (different ref from item1!)
    //   item3 → item3==item1, already visited → skip!
    //   item2 → already visited → skip!
    //   processed=2
    //
    // HashMap would see item1.equals(item2) → only process 1!
    // IdentityHashMap sees different refs → processes BOTH! ✓
    //
    // Throw IllegalArgumentException if list is null.
    // ─────────────────────────────────────────────────────────────
    record Item(String name, int value) {}

    public static int challenge2(List<Item> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        Map<Item, Boolean> visited = new IdentityHashMap<>();
        for (Item i : list) {
            visited.computeIfAbsent(i, w -> Boolean.TRUE);
        }
        return visited.size();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — IdentityHashMap: detect aliased objects
    //
    // Given a list of objects, detect which ones are ALIASES
    // (different positions in the list pointing to the SAME reference).
    //
    // Return Map<String, List<Integer>> where:
    //   key   = System.identityHashCode(obj) as String (unique per ref!)
    //   value = list of indices that share that same reference
    //
    // Only include entries with 2 or more indices (actual aliases!).
    //
    // Input:  s1=new String("a"), s2=new String("b"), s3=s1
    //   list=[s1, s2, s3, s2]
    //   indices 0 and 2 share s1's reference → alias!
    //   indices 1 and 3 share s2's reference → alias!
    //   s1 and s2 are different references!
    //
    // Use IdentityHashMap<Object, List<Integer>> to group indices by ref.
    // Then filter to keep only groups with size >= 2.
    //
    // Throw IllegalArgumentException if list is null.
    // ─────────────────────────────────────────────────────────────
    public static Map<String, List<Integer>> challenge3(List<Object> list) {
        if (list == null) throw new IllegalArgumentException("List cannot be null");
        Map<Object, List<Integer>> map = new IdentityHashMap<>();
        int i = 0;
        for (Object o : list) {
            map.computeIfAbsent(o, w -> new ArrayList<>()).add(i++);
        }

        Map<String, List<Integer>> result = new HashMap<>();
        for (Map.Entry<Object, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) result.put(String.valueOf(entry.getKey()), entry.getValue());
        }

        return result;
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — EnumMap (Challenges 4–6)
    //
    // EnumMap is optimized for enum keys:
    // → internally uses an array indexed by enum ordinal!
    // → much faster than HashMap for enum keys!
    // → maintains ENUM DECLARATION ORDER (not insertion order)!
    // → no null keys allowed!
    //
    // enum Priority { LOW, MEDIUM, HIGH }
    //   → ordinals: LOW=0, MEDIUM=1, HIGH=2
    //   → EnumMap always iterates: LOW → MEDIUM → HIGH (declaration order!)
    // ══════════════════════════════════════════════════════════════════════

    enum Priority { LOW, MEDIUM, HIGH }

    enum Department { HR, ENGINEERING, MARKETING, FINANCE }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — EnumMap: task count per priority
    //
    // Given a list of tasks (each with a Priority enum),
    // count how many tasks exist per priority level.
    //
    // record Task(String name, Priority priority)
    //
    // Return EnumMap<Priority, Long> of priority → count.
    // EnumMap iterates in enum declaration order: LOW → MEDIUM → HIGH!
    //
    // Input:  [TaskA/HIGH, TaskB/LOW, TaskC/HIGH, TaskD/MEDIUM, TaskE/LOW]
    // Output: EnumMap{LOW=2, MEDIUM=1, HIGH=2}
    //         (in enum order: LOW first, HIGH last!)
    //
    // All priority levels must be present in result (count=0 if none)!
    //
    // Throw IllegalArgumentException if tasks is null.
    // ─────────────────────────────────────────────────────────────
    record Task(String name, Priority priority) {}

    public static EnumMap<Priority, Long> challenge4(List<Task> tasks) {
        if (tasks == null) throw new IllegalArgumentException("Tasks cannot be null");
        EnumMap<Priority, Long> map = new EnumMap<>(Priority.class);

        for (Priority p : Priority.values()) {
            map.put(p, 0L);
        }

        for (Task t : tasks) {
            map.merge(t.priority(), 1L, Long::sum);
        }

        return map;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — EnumMap: highest salary per department
    //
    // Given a list of employees with a Department enum,
    // find the HIGHEST salary in each department.
    //
    // record DeptEmployee(String name, Department department, double salary)
    //
    // Return EnumMap<Department, Double> of department → max salary.
    // Only include departments that have at least one employee!
    //
    // Input:  [Alice/ENGINEERING/90000, Bob/ENGINEERING/70000,
    //          Carol/MARKETING/80000,   Diana/HR/75000]
    // Output: EnumMap{HR=75000.0, ENGINEERING=90000.0, MARKETING=80000.0}
    //         (in enum order: HR first, ENGINEERING second, MARKETING third!)
    //
    // Use merge(dept, salary, Math::max) or compute pattern.
    //
    // Throw IllegalArgumentException if employees is null.
    // ─────────────────────────────────────────────────────────────
    record DeptEmployee(String name, Department department, double salary) {}

    public static EnumMap<Department, Double> challenge5(List<DeptEmployee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        return new EnumMap<>(Department.class);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — EnumMap: priority weighted score
    //
    // Given a list of tasks with scores, compute a WEIGHTED TOTAL
    // score using priority multipliers from an EnumMap config.
    //
    // Priority weights:
    //   LOW    → multiplier = 1
    //   MEDIUM → multiplier = 2
    //   HIGH   → multiplier = 3
    //
    // record ScoredTask(String name, Priority priority, int score)
    //
    // weighted score = score × multiplier
    //
    // Step 1: build EnumMap<Priority, Integer> of weights
    //         {LOW=1, MEDIUM=2, HIGH=3}
    //
    // Step 2: for each task → weightedScore = score × weights.get(priority)
    //
    // Return the TOTAL weighted score of all tasks.
    //
    // Input:  [Alpha/HIGH/10, Beta/LOW/20, Gamma/MEDIUM/15]
    //   Alpha: 10 × 3 = 30
    //   Beta:  20 × 1 = 20
    //   Gamma: 15 × 2 = 30
    //   total = 80
    // Output: 80
    //
    // Throw IllegalArgumentException if tasks is null.
    // ─────────────────────────────────────────────────────────────
    record ScoredTask(String name, Priority priority, int score) {}

    public static int challenge6(List<ScoredTask> tasks) {
        if (tasks == null) throw new IllegalArgumentException("Tasks cannot be null");
        return 0;
    }
}