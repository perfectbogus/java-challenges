package dev.perfectbogus.maps;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class MapChallenges {

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–5)
    // Main functions covered: getOrDefault, putIfAbsent, merge, replaceAll, forEach
    // ══════════════════════════════════════════════════════════════════════


    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1 — getOrDefault()
    //
    // Given a list of words, build a frequency map.
    // Then given a list of QUERIES, return how many times each
    // queried word appears (0 if it was never seen).
    //
    // Use getOrDefault() for ALL lookups — never use containsKey()!
    //
    // Input:  words=["apple","banana","apple","cherry","banana","apple"]
    //         queries=["apple","grape","banana","fig"]
    // Output: Map{"apple"=3, "grape"=0, "banana"=2, "fig"=0}
    //              ↑ appears 3x  ↑ never seen → 0
    //
    // Also use getOrDefault() to BUILD the frequency map:
    // map.put(word, map.getOrDefault(word, 0) + 1)
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge1(List<String> words, List<String> queries) {
        if (words   == null) throw new IllegalArgumentException("Words cannot be null");
        if (queries == null) throw new IllegalArgumentException("Queries cannot be null");
        // TODO — use getOrDefault() to build frequency map
        //        use getOrDefault() to answer each query

        // build freq map
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }

        // answer each query
        Map<String, Integer> result = new LinkedHashMap<>();
        for (String q : queries) {
            result.put(q, freq.getOrDefault(q, 0));
        }

        return result;
    }

    public static Map<String, Integer> challenge1_2(List<String> words, List<String> queries) {
        if (words   == null) throw new IllegalArgumentException("Words cannot be null");
        if (queries == null) throw new IllegalArgumentException("Queries cannot be null");
        // TODO — use getOrDefault() to build frequency map
        //        use getOrDefault() to answer each query
        Set<String> set = new HashSet<>(queries);

        // build freq map
        Map<String, Long> freq = words.stream().filter(set::contains).collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));

        return queries.stream().collect(Collectors.toMap(
                Function.identity(),
                q -> freq.getOrDefault(q, 0L).intValue(),
                (e1, e2) -> e1,
                LinkedHashMap::new
        ));
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2 — putIfAbsent()
    //
    // Given a string, use putIfAbsent() to track the FIRST OCCURRENCE
    // index of each character. putIfAbsent() only sets the value if
    // the key is NOT already present — perfect for "first occurrence"!
    //
    // Return Map<Character, Integer> (char → first index in string)
    //
    // Input:  "programming"
    //   p=0, r=1, o=2, g=3, a=4, m=5, i=7, n=8
    //   (second 'r' at index 6 → putIfAbsent keeps index 1!)
    //   (second 'g' at index 9 → putIfAbsent keeps index 3!)
    //   (second 'm' at index 6 → putIfAbsent keeps index 5!)
    //
    // Output: {p=0, r=1, o=2, g=3, a=4, m=5, i=7, n=8}
    //
    // ⚠️ Must use putIfAbsent() — NOT containsKey() or put()!
    // ─────────────────────────────────────────────────────────────
    public static Map<Character, Integer> challenge2(String s) {
        if (s == null) throw new IllegalArgumentException("Input cannot be null");
        // TODO — iterate with index, use putIfAbsent(char, index)
        return new LinkedHashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3 — merge()
    //
    // Given TWO shopping cart maps (item → quantity), COMBINE them
    // into one map by SUMMING quantities for items in both carts.
    // Items only in one cart keep their quantity.
    //
    // Use merge() for ALL insertions — NOT putIfAbsent() or put()!
    // merge(key, value, remappingFunction)
    // → if key absent:  put(key, value)
    // → if key present: put(key, remappingFunction(existing, value))
    //
    // Input:  cart1 = {apple=3, banana=2, cherry=1}
    //         cart2 = {banana=4, cherry=2, date=5}
    // Output: {apple=3, banana=6, cherry=3, date=5}
    //          ↑only c1  ↑ 2+4     ↑ 1+2    ↑only c2
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge3(
            Map<String, Integer> cart1, Map<String, Integer> cart2) {
        if (cart1 == null) throw new IllegalArgumentException("Cart1 cannot be null");
        if (cart2 == null) throw new IllegalArgumentException("Cart2 cannot be null");
        // TODO — start with new HashMap<>(cart1)
        //        then use merge() for each entry in cart2
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4 — replaceAll()
    //
    // Given a map of product → price, apply a PRICE ADJUSTMENT:
    // → If price > 100.0 → apply 10% DISCOUNT  (price * 0.90)
    // → If price <= 100.0 → apply 5% INCREASE  (price * 1.05)
    //
    // Use replaceAll((key, value) -> newValue) to update ALL entries
    // in place. Return the modified map.
    //
    // replaceAll() replaces each value with the result of the function!
    //
    // Input:  {Phone=999.0, Shirt=49.0, Laptop=1299.0, Book=15.0, Cable=9.99}
    // Output: {Phone=899.1, Shirt=51.45, Laptop=1169.1, Book=15.75, Cable=10.49}
    //          ↑ *0.90       ↑ *1.05     ↑ *0.90          ↑ *1.05   ↑ *1.05
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Double> challenge4(Map<String, Double> prices) {
        if (prices == null) throw new IllegalArgumentException("Prices cannot be null");
        // TODO — use prices.replaceAll((product, price) -> ...)
        return prices;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5 — forEach()
    //
    // Given a map of department → list of employee names,
    // use forEach() to build a formatted report string.
    // Each department appears on its own line, sorted alphabetically,
    // with its employee count and names sorted alphabetically.
    //
    // Format per line: "DEPT_NAME (N employees): name1, name2, name3"
    //
    // Use forEach() to iterate — NOT entrySet().stream()!
    // Use TreeMap to ensure alphabetical department order.
    //
    // Input:  {
    //   "Engineering" → ["Carol","Alice","Eve"]
    //   "Marketing"   → ["Bob","Diana"]
    //   "HR"          → ["Frank"]
    // }
    // Output:
    // "Engineering (3 employees): Alice, Carol, Eve\n
    //  HR (1 employees): Frank\n
    //  Marketing (2 employees): Bob, Diana"
    // ─────────────────────────────────────────────────────────────
    public static String challenge5(Map<String, List<String>> deptMap) {
        if (deptMap == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO — convert to TreeMap (sorted keys), use forEach() to build report
        //        sort each employee list alphabetically
        //        join lines with "\n"
        return "";
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 6–9)
    // Main functions covered: computeIfAbsent, computeIfPresent, compute, remove(k,v)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6 — computeIfAbsent()
    //
    // Given a list of edges [from, to] representing a DIRECTED GRAPH,
    // build an adjacency list using computeIfAbsent().
    //
    // computeIfAbsent(key, mappingFunction)
    // → if key absent:  compute value from key, put it, return it
    // → if key present: return existing value (no change!)
    // → perfect for "get or create" pattern!
    //
    // Return Map<String, List<String>> (node → list of neighbors)
    // Neighbors sorted alphabetically per node.
    //
    // Input:  edges=[["A","B"],["A","C"],["B","C"],["C","A"],["B","D"]]
    // Output: {
    //   "A" → ["B","C"]
    //   "B" → ["C","D"]
    //   "C" → ["A"]
    // }
    //
    // ⚠️ Use computeIfAbsent() — NOT putIfAbsent() or containsKey()!
    // computeIfAbsent(node, k -> new ArrayList<>()).add(neighbor)
    // ─────────────────────────────────────────────────────────────
    public static Map<String, List<String>> challenge6(List<String[]> edges) {
        if (edges == null) throw new IllegalArgumentException("Edges cannot be null");
        // TODO — use computeIfAbsent(node, k -> new ArrayList<>()).add(neighbor)
        //        sort each neighbor list at the end
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7 — computeIfPresent()
    //
    // Given a map of employee → salary and a list of RAISE REQUESTS
    // as [employeeName, raisePercentage], apply the raise ONLY if
    // the employee EXISTS in the map.
    //
    // computeIfPresent(key, remappingFunction)
    // → if key PRESENT: update value with remappingFunction(key, existingValue)
    // → if key ABSENT:  do NOTHING (no new entry created!)
    // → perfect for "update only if exists" pattern!
    //
    // If computeIfPresent returns null → entry is REMOVED!
    // (don't return null in this challenge)
    //
    // Input:  salaries = {"Alice"=80000.0, "Bob"=60000.0, "Carol"=75000.0}
    //         raises   = [["Alice","10"],["Diana","20"],["Bob","5"],["Carol","15"]]
    //         (Diana doesn't exist → no new entry added!)
    //
    // Output: {"Alice"=88000.0, "Bob"=63000.0, "Carol"=86250.0}
    //          ↑ +10%            ↑ +5%           ↑ +15%
    //          Diana → NOT added (computeIfPresent does nothing for absent keys!)
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Double> challenge7(
            Map<String, Double> salaries, List<String[]> raises) {
        if (salaries == null) throw new IllegalArgumentException("Salaries cannot be null");
        if (raises   == null) throw new IllegalArgumentException("Raises cannot be null");
        // TODO — for each [name, pct]: use computeIfPresent(name, (k, v) -> v * (1 + pct/100))
        return salaries;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8 — compute()
    //
    // Given a stream of login/logout events, track ACTIVE SESSIONS
    // using compute().
    //
    // compute(key, remappingFunction(key, existingValueOrNull))
    // → if key absent:  existingValue = null → compute new value
    // → if key present: existingValue = current → compute updated value
    // → if function returns null → REMOVE the key!
    // → perfect for "create, update, or delete" in one call!
    //
    // Events are strings: "LOGIN:user" or "LOGOUT:user"
    // → LOGIN:  increment session count (or set to 1 if first login)
    // → LOGOUT: decrement session count (remove entry if count reaches 0!)
    //
    // Return final Map<String, Integer> of users with ACTIVE sessions only.
    // (users with 0 sessions should NOT appear in the map)
    //
    // Input:  ["LOGIN:Alice","LOGIN:Bob","LOGIN:Alice","LOGOUT:Bob",
    //          "LOGIN:Carol","LOGOUT:Alice","LOGIN:Bob","LOGOUT:Bob"]
    //
    // Trace:
    //   LOGIN:Alice  → {Alice=1}
    //   LOGIN:Bob    → {Alice=1, Bob=1}
    //   LOGIN:Alice  → {Alice=2, Bob=1}
    //   LOGOUT:Bob   → {Alice=2} (Bob=0 → REMOVED!)
    //   LOGIN:Carol  → {Alice=2, Carol=1}
    //   LOGOUT:Alice → {Alice=1, Carol=1}
    //   LOGIN:Bob    → {Alice=1, Carol=1, Bob=1}
    //   LOGOUT:Bob   → {Alice=1, Carol=1} (Bob=0 → REMOVED again!)
    //
    // Output: {"Alice"=1, "Carol"=1}
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge8(List<String> events) {
        if (events == null) throw new IllegalArgumentException("Events cannot be null");
        // TODO — for each event:
        //   LOGIN:  map.compute(user, (k, v) -> v == null ? 1 : v + 1)
        //   LOGOUT: map.compute(user, (k, v) -> (v == null || v <= 1) ? null : v - 1)
        //           returning null removes the entry!
        return new HashMap<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9 — remove(key, value) — Conditional Remove
    //
    // remove(key, value) only removes the entry if the key maps
    // to EXACTLY that value! Returns true if removed, false if not.
    //
    // Given a map of item → stock count and a list of RESERVATION REQUESTS
    // [item, reservedStock], remove the item from inventory ONLY if
    // the current stock exactly matches the reserved amount
    // (meaning it's fully reserved — none left!).
    //
    // Return a map of removed entries (items that were fully reserved).
    //
    // Input:  inventory = {apple=5, banana=3, cherry=8, date=2}
    //         requests  = [["banana","3"],["apple","4"],["cherry","8"],["date","1"]]
    //
    // Process:
    //   remove("banana", 3) → stock=3 = 3 → REMOVED! ✓
    //   remove("apple",  4) → stock=5 ≠ 4 → NOT removed ✗
    //   remove("cherry", 8) → stock=8 = 8 → REMOVED! ✓
    //   remove("date",   1) → stock=2 ≠ 1 → NOT removed ✗
    //
    // Return: {"banana"=3, "cherry"=8}  (items fully reserved and removed)
    //
    // ⚠️ Must use map.remove(key, value) — not map.get() + map.remove(key)!
    // ─────────────────────────────────────────────────────────────
    public static Map<String, Integer> challenge9(
            Map<String, Integer> inventory, List<String[]> requests) {
        if (inventory == null) throw new IllegalArgumentException("Inventory cannot be null");
        if (requests  == null) throw new IllegalArgumentException("Requests cannot be null");
        // TODO — for each [item, amount]:
        //        int qty = Integer.parseInt(amount)
        //        if (inventory.remove(item, qty)) → add to removed map
        return new HashMap<>();
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION C — HARD (Challenge 10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10 — Full Map Operations Combined
    //
    // Process a list of bank transactions and build a comprehensive
    // account summary using merge(), compute(), computeIfAbsent(), and getOrDefault().
    //
    // Each transaction: "TYPE:ACCOUNT:AMOUNT"
    //   TYPE    = DEPOSIT or WITHDRAWAL
    //   ACCOUNT = account identifier (e.g. "ACC001")
    //   AMOUNT  = positive double
    //
    // For each account build an AccountSummary:
    //   record AccountSummary(
    //       int    transactionCount,
    //       double totalDeposits,
    //       double totalWithdrawals,
    //       double netBalance,          // deposits - withdrawals
    //       double largestTransaction,  // largest single amount (any type)
    //       String status               // "HEALTHY" if balance>0, "OVERDRAWN" if balance<0, "ZERO" if =0
    //   )
    //
    // Input:
    //   ["DEPOSIT:ACC001:1000.0","WITHDRAWAL:ACC001:200.0",
    //    "DEPOSIT:ACC002:500.0", "DEPOSIT:ACC001:300.0",
    //    "WITHDRAWAL:ACC002:700.0","DEPOSIT:ACC002:400.0"]
    //
    // ACC001: deposits=1300, withdrawals=200, net=1100, count=3, largest=1000, HEALTHY
    // ACC002: deposits=900,  withdrawals=700, net=200,  count=3, largest=700,  HEALTHY
    //
    // Hint:
    // Step 1 — use computeIfAbsent to initialise intermediate data per account:
    //          Map<String, double[]> data = new HashMap<>()
    //          double[] = [count, deposits, withdrawals, largest]
    //          data.computeIfAbsent(account, k -> new double[4])
    //
    // Step 2 — for each transaction update the array:
    //          double[] stats = data.computeIfAbsent(account, k -> new double[4])
    //          stats[0]++ (count)
    //          if DEPOSIT:    stats[1] += amount
    //          if WITHDRAWAL: stats[2] += amount
    //          stats[3] = Math.max(stats[3], amount) (largest)
    //
    // Step 3 — convert data map to AccountSummary map:
    //          data.entrySet().stream().collect(toMap(key, entry -> buildSummary(stats)))
    //          net = deposits - withdrawals
    //          status = net > 0 ? "HEALTHY" : net < 0 ? "OVERDRAWN" : "ZERO"
    // ─────────────────────────────────────────────────────────────
    record AccountSummary(int transactionCount, double totalDeposits,
                          double totalWithdrawals, double netBalance,
                          double largestTransaction, String status) {}

    public static Map<String, AccountSummary> challenge10(List<String> transactions) {
        if (transactions == null) throw new IllegalArgumentException("Transactions cannot be null");
        // TODO
        return new HashMap<>();
    }
}