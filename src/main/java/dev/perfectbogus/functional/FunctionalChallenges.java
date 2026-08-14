package dev.perfectbogus.functional;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class FunctionalChallenges {

    record Employee(String name, String department, double salary) {}

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–6)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — 🟢 EASY
    // Function Basics — apply a Function<String, String> to transform each word.
    //
    // Given a list of strings, use a Function to:
    // → capitalize the FIRST letter
    // → lowercase ALL other letters
    //
    // Input:  ["hELLO", "wORLD", "jAVA", "fUNCTIONAL"]
    // Output: ["Hello", "World", "Java", "Functional"]
    //
    // Constraints:
    // → Define a Function<String, String> named 'capitalize'
    // → Apply it using stream().map(capitalize)
    // → Do NOT inline the function — declare it as a variable first!
    //
    // Hint:
    // Function<String, String> capitalize = s ->
    //     s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge1(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO — declare Function<String, String> capitalize, then apply with map()
        Function<String, String> capitalize =
                w -> w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase();

        return words.stream().map(capitalize).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — 🟢 EASY
    // Predicate Combinators — use and() / or() / negate() to filter employees.
    //
    // Given a list of employees, return only those who are:
    // → in "Engineering" department AND salary > 80000
    // OR
    // → in "Marketing" department AND salary > 65000
    //
    // Input:
    //   Alice  Engineering 95000  ← Engineering AND > 80000 ✓
    //   Bob    Marketing   60000  ← Marketing but NOT > 65000 ✗
    //   Carol  Engineering 75000  ← Engineering but NOT > 80000 ✗
    //   Diana  Marketing   70000  ← Marketing AND > 65000 ✓
    //   Eve    HR          90000  ← neither dept ✗
    //
    // Output: [Alice, Diana]
    //
    // Constraints:
    // → Define separate named Predicates — do NOT inline everything!
    // → Combine with .and() and .or()
    //
    // Hint:
    // Predicate<Employee> isEngineering = e -> e.department().equals("Engineering");
    // Predicate<Employee> highSalaryEng = isEngineering.and(e -> e.salary() > 80000);
    // Predicate<Employee> isMarketing   = ...
    // Predicate<Employee> highSalaryMkt = ...
    // Predicate<Employee> combined      = highSalaryEng.or(highSalaryMkt);
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge2(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO — define Predicates, combine with and()/or(), filter stream
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — 🟢 EASY
    // Consumer andThen() — chain two Consumers to perform two actions.
    //
    // Given a list of employees:
    // → Consumer 1: add employee name to a 'logged' list
    // → Consumer 2: add employee to 'highEarners' list if salary > 80000
    // Chain both consumers with andThen() and apply with forEach()
    //
    // Input:  [Alice/Eng/95000, Bob/Mkt/60000, Carol/Eng/85000]
    // logged:      ["Alice", "Bob", "Carol"]   ← all names
    // highEarners: [Alice, Carol]              ← salary > 80000
    //
    // Constraints:
    // → Declare Consumer<Employee> logName and Consumer<Employee> trackHighEarner
    // → Chain with andThen()
    // → Apply with employees.forEach(combined)
    //
    // Hint:
    // Consumer<Employee> logName = e -> logged.add(e.name());
    // Consumer<Employee> trackHighEarner = e -> { if(e.salary() > 80000) highEarners.add(e); };
    // Consumer<Employee> combined = logName.andThen(trackHighEarner);
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Object> challenge3(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        List<String>   logged      = new ArrayList<>();
        List<Employee> highEarners = new ArrayList<>();
        // TODO — define two Consumers, chain with andThen(), apply with forEach()
        return Map.of("logged", logged, "highEarners", highEarners);
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — 🟢 EASY
    // Supplier<T> — use a Supplier to provide a default value.
    //
    // Given a list of employees filtered by department,
    // if NO employee is found in that department, use a Supplier
    // to return a default "placeholder" Employee instead.
    //
    // Input:  employees=[Alice/Eng/95000, Bob/Mkt/60000], department="HR"
    // → HR has no employees → return Supplier default:
    //   Employee("Unknown", "HR", 0.0)
    //
    // Input:  employees=[Alice/Eng/95000, Bob/Mkt/60000], department="Engineering"
    // → Engineering has Alice → return [Alice]
    //
    // Constraints:
    // → Declare Supplier<Employee> defaultEmployee = () -> new Employee("Unknown", dept, 0.0)
    // → If stream finds nothing, use supplier to return singleton list
    //
    // Hint:
    // Supplier<Employee> defaultEmployee = () -> new Employee("Unknown", department, 0.0);
    // List<Employee> found = employees.stream().filter(e -> ...).toList();
    // return found.isEmpty() ? List.of(defaultEmployee.get()) : found;
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge4(List<Employee> employees, String department) {
        if (employees == null)  throw new IllegalArgumentException("Employees cannot be null");
        if (department == null) throw new IllegalArgumentException("Department cannot be null");
        // TODO — define Supplier<Employee> defaultEmployee, use if no results found
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — 🟢 EASY
    // UnaryOperator<T> — use with List.replaceAll() to normalize strings.
    //
    // Given a list of messy strings, apply a UnaryOperator<String> to:
    // → trim whitespace
    // → replace all internal multiple spaces with single space
    // → lowercase everything
    //
    // Input:  ["  Hello   World  ", "JAVA   IS   FUN", "  functional  "]
    // Output: ["hello world", "java is fun", "functional"]
    //
    // Constraints:
    // → Declare UnaryOperator<String> normalize
    // → Use words.replaceAll(normalize) to apply in-place
    // → Return the modified list
    //
    // Hint:
    // UnaryOperator<String> normalize = s -> s.trim().replaceAll("\\s+", " ").toLowerCase();
    // words.replaceAll(normalize);
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge5(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO — declare UnaryOperator<String> normalize, apply with replaceAll()
        return words;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — 🟢 EASY
    // Function Composition — build a pipeline with andThen()
    //
    // Build a string processing pipeline using Function.andThen():
    // Step 1 → trim whitespace
    // Step 2 → convert to uppercase
    // Step 3 → replace spaces with underscores
    // Step 4 → add prefix "KEY_" and suffix "_END"
    //
    // Apply the composed pipeline to each word in the list.
    //
    // Input:  ["  hello world  ", "  java  is  fun  ", "  test  "]
    // Output: ["KEY_HELLO_WORLD_END", "KEY_JAVA__IS__FUN_END", "KEY_TEST_END"]
    //
    // Constraints:
    // → Declare each step as a separate Function<String, String>
    // → Compose ALL steps with .andThen() into one pipeline function
    // → Apply pipeline using stream().map(pipeline)
    //
    // Hint:
    // Function<String, String> trim    = String::trim;
    // Function<String, String> upper   = String::toUpperCase;
    // Function<String, String> noSpace = s -> s.replace(" ", "_");
    // Function<String, String> wrap    = s -> "KEY_" + s + "_END";
    // Function<String, String> pipeline = trim.andThen(upper).andThen(noSpace).andThen(wrap);
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge6(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO — declare 4 separate Functions, compose with andThen(), apply with map()
        return new ArrayList<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 7–9)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — 🟡 MEDIUM
    // Higher-Order Functions — function that takes AND returns functions.
    //
    // Implement a SALARY ADJUSTER FACTORY — a method that RETURNS a
    // Function<Employee, Employee> based on the given parameters:
    //
    // Part A — createRaiseFunction(double percentage)
    //   Returns a Function that gives every employee a salary raise by percentage
    //   e.g. createRaiseFunction(0.10) → Function that raises salary by 10%
    //
    // Part B — createDeptFilter(String dept, Function<Employee, Employee> fn)
    //   Returns a Function that applies fn ONLY to employees in the given department,
    //   leaving others unchanged.
    //   e.g. createDeptFilter("Engineering", raiseBy10%) → only Engineering gets raise
    //
    // Input:  [Alice/Eng/100000, Bob/Mkt/60000, Carol/Eng/80000]
    //   raiseFor10pct = createRaiseFunction(0.10)
    //   engOnlyRaise  = createDeptFilter("Engineering", raiseFor10pct)
    //
    // Apply engOnlyRaise to each employee:
    // Output: [Alice/Eng/110000, Bob/Mkt/60000, Carol/Eng/88000]
    //          ↑ raised 10%       ↑ unchanged    ↑ raised 10%
    //
    // Hint:
    // public static Function<Employee, Employee> createRaiseFunction(double pct) {
    //     return e -> new Employee(e.name(), e.department(), e.salary() * (1 + pct));
    // }
    // public static Function<Employee, Employee> createDeptFilter(String dept, Function<Employee,Employee> fn) {
    //     return e -> e.department().equals(dept) ? fn.apply(e) : e;
    // }
    // ─────────────────────────────────────────────────────────────
    public static Function<Employee, Employee> createRaiseFunction(double percentage) {
        // TODO — return a Function that raises salary by percentage
        return Function.identity();
    }

    public static Function<Employee, Employee> createDeptFilter(String dept, Function<Employee, Employee> fn) {
        // TODO — return a Function that applies fn only to employees in dept
        return Function.identity();
    }

    public static List<Employee> challenge7(List<Employee> employees, String dept, double percentage) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO — use createRaiseFunction + createDeptFilter, apply with map()
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — 🟡 MEDIUM
    // BinaryOperator + reduce() — aggregate data without loops.
    //
    // Given a list of employees, use BinaryOperator<Employee> inside
    // stream().reduce() to find:
    //
    // Part A — findHighestPaid(List<Employee>) → Employee with highest salary
    // Part B — findLowestPaid(List<Employee>)  → Employee with lowest salary
    // Part C — findMostExperienced(List<Employee>, Map<String,Integer> yearsMap)
    //          → Employee whose name maps to highest years in the given Map
    //
    // Constraints:
    // → Use BinaryOperator<Employee> in each method
    // → Use stream().reduce(binaryOperator) — no sorting, no max/min collector!
    // → Return Optional<Employee>.orElseThrow() result
    //
    // Hint:
    // BinaryOperator<Employee> keepHighest = (a, b) -> a.salary() >= b.salary() ? a : b;
    // return employees.stream().reduce(keepHighest).orElseThrow();
    // ─────────────────────────────────────────────────────────────
    public static Employee findHighestPaid(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) throw new IllegalArgumentException("Employees cannot be null or empty");
        // TODO — declare BinaryOperator<Employee> keepHighest, use with reduce()
        return null;
    }

    public static Employee findLowestPaid(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) throw new IllegalArgumentException("Employees cannot be null or empty");
        // TODO — declare BinaryOperator<Employee> keepLowest, use with reduce()
        return null;
    }

    public static Employee findMostExperienced(List<Employee> employees, Map<String, Integer> yearsMap) {
        if (employees == null || employees.isEmpty()) throw new IllegalArgumentException("Employees cannot be null or empty");
        if (yearsMap == null) throw new IllegalArgumentException("YearsMap cannot be null");
        // TODO — declare BinaryOperator<Employee> keepMostExp using yearsMap.get(name)
        return null;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — 🟡 MEDIUM
    // BiFunction<T,U,R> — combine two inputs into one output.
    //
    // Given two maps (each department → list of employees):
    // → map1: {"Engineering": [Alice, Carol], "Marketing": [Bob]}
    // → map2: {"Engineering": [Eve],          "HR": [Frank]}
    //
    // Use a BiFunction<Map, Map, Map> to MERGE them into one map:
    // → if dept exists in BOTH maps → combine their employee lists
    // → if dept exists in ONE map   → keep that list
    //
    // Output: {
    //   "Engineering" → [Alice, Carol, Eve]
    //   "Marketing"   → [Bob]
    //   "HR"          → [Frank]
    // }
    //
    // Constraints:
    // → Declare BiFunction<Map<String, List<Employee>>, Map<String, List<Employee>>,
    //                      Map<String, List<Employee>>> mergeByDept
    // → Inside: use Stream.concat() to merge both lists when key exists in both
    // → Apply: mergeByDept.apply(map1, map2)
    //
    // Hint:
    // BiFunction<Map<String,List<Employee>>, Map<String,List<Employee>>,
    //            Map<String,List<Employee>>> merge = (m1, m2) -> {
    //     Map<String, List<Employee>> result = new HashMap<>(m1);
    //     m2.forEach((dept, list) -> result.merge(dept, list, (l1, l2) ->
    //         Stream.concat(l1.stream(), l2.stream()).toList()));
    //     return result;
    // };
    // ─────────────────────────────────────────────────────────────
    public static Map<String, List<Employee>> challenge9(
            Map<String, List<Employee>> map1, Map<String, List<Employee>> map2) {
        if (map1 == null) throw new IllegalArgumentException("Map1 cannot be null");
        if (map2 == null) throw new IllegalArgumentException("Map2 cannot be null");
        // TODO — declare BiFunction mergeByDept, apply it
        return new HashMap<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION C — HARD (Challenge 10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — 🔴 HARD
    // Dynamic Function Pipeline — chain arbitrary transformations.
    //
    // Given:
    // → A list of employees
    // → A list of Function<Employee, Employee> transformations to apply IN ORDER
    // → A Predicate<Employee> to filter AFTER transformations
    // → A BiFunction<String, Double, String> to format the final result per employee
    //
    // Steps:
    // 1. Chain ALL transformations into ONE Function using reduce() + andThen()
    //    If no transformations → use Function.identity()
    // 2. Apply the chained function to each employee using map()
    // 3. Filter with the given Predicate
    // 4. Map each surviving employee to a String using the BiFunction:
    //    biFunction.apply(employee.name(), employee.salary())
    // 5. Collect results into a sorted List<String>
    //
    // Example:
    //   employees     = [Alice/Eng/100000, Bob/Mkt/50000, Carol/Eng/80000]
    //   transforms    = [raise10pct, deptBonus20pctEngOnly]
    //   filterAfter   = e -> e.salary() > 100000
    //   format        = (name, salary) -> name + ": $" + (long) salary
    //
    //   After raise10pct:       [Alice/110000, Bob/55000, Carol/88000]
    //   After deptBonus20%Eng:  [Alice/132000, Bob/55000, Carol/105600]
    //   After filter > 100000:  [Alice/132000, Carol/105600]
    //   After format:           ["Alice: $132000", "Carol: $105600"]
    //   After sort:             ["Alice: $132000", "Carol: $105600"]
    //
    // Constraints:
    // → Chain transforms with: transforms.stream().reduce(Function.identity(), Function::andThen)
    // → Do NOT use a loop — use stream pipeline throughout!
    // → Apply BiFunction with: biFunction.apply(e.name(), e.salary())
    //
    // Hint:
    // Function<Employee, Employee> pipeline = transforms.stream()
    //     .reduce(Function.identity(), Function::andThen);
    //
    // return employees.stream()
    //     .map(pipeline)
    //     .filter(filterAfter)
    //     .map(e -> formatter.apply(e.name(), e.salary()))
    //     .sorted()
    //     .toList();
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge10(
            List<Employee> employees,
            List<Function<Employee, Employee>> transforms,
            Predicate<Employee> filterAfter,
            BiFunction<String, Double, String> formatter) {
        if (employees  == null) throw new IllegalArgumentException("Employees cannot be null");
        if (transforms == null) throw new IllegalArgumentException("Transforms cannot be null");
        if (filterAfter == null) throw new IllegalArgumentException("Filter cannot be null");
        if (formatter   == null) throw new IllegalArgumentException("Formatter cannot be null");
        // TODO
        // Step 1 — chain all transforms: transforms.stream().reduce(Function.identity(), Function::andThen)
        // Step 2 — apply pipeline with map()
        // Step 3 — filter with filterAfter
        // Step 4 — format with formatter.apply(name, salary)
        // Step 5 — sorted().toList()
        return new ArrayList<>();
    }
}