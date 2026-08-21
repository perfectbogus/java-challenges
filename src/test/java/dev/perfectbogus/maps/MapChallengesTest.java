package dev.perfectbogus.maps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MapChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — getOrDefault()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            Map<String, Integer> result = MapChallenges.challenge1(
                    List.of("apple","banana","apple","cherry","banana","apple"),
                    List.of("apple","grape","banana","fig"));

            assertEquals(3, result.get("apple"));
            assertEquals(0, result.get("grape"));  // never seen → 0
            assertEquals(2, result.get("banana"));
            assertEquals(0, result.get("fig"));    // never seen → 0
        }

        @Test
        void basicCase1_2() {
            Map<String, Integer> result = MapChallenges.challenge1_2(
                    List.of("apple","banana","apple","cherry","banana","apple"),
                    List.of("apple","grape","banana","fig"));

            assertEquals(3, result.get("apple"));
            assertEquals(0, result.get("grape"));  // never seen → 0
            assertEquals(2, result.get("banana"));
            assertEquals(0, result.get("fig"));    // never seen → 0
        }

        @Test
        void queryOrderPreserved() {
            Map<String, Integer> result = MapChallenges.challenge1(
                    List.of("a","b","a"),
                    List.of("c","b","a","d"));

            List<String> keys = new ArrayList<>(result.keySet());
            // LinkedHashMap → insertion order = query order
            assertEquals("c", keys.get(0));
            assertEquals("b", keys.get(1));
            assertEquals("a", keys.get(2));
            assertEquals("d", keys.get(3));
        }

        @Test
        void allQueriesMissing() {
            Map<String, Integer> result = MapChallenges.challenge1(
                    List.of("apple","banana"),
                    List.of("grape","mango"));

            assertEquals(0, result.get("grape"));
            assertEquals(0, result.get("mango"));
        }

        @Test
        void emptyWords() {
            Map<String, Integer> result = MapChallenges.challenge1(
                    List.of(),
                    List.of("apple","banana"));

            assertEquals(0, result.get("apple"));
            assertEquals(0, result.get("banana"));
        }

        @Test
        void emptyQueries() {
            Map<String, Integer> result = MapChallenges.challenge1(
                    List.of("apple","apple"),
                    List.of());

            assertTrue(result.isEmpty());
        }

        @Test
        void nullWords() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge1(null, List.of("a")));
        }

        @Test
        void nullQueries() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge1(List.of("a"), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — putIfAbsent()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<Character, Integer> result = MapChallenges.challenge2("programming");

            assertEquals(0, result.get('p'));
            assertEquals(1, result.get('r')); // first 'r' at index 1, not 6!
            assertEquals(2, result.get('o'));
            assertEquals(3, result.get('g')); // first 'g' at index 3, not 9!
            assertEquals(5, result.get('a'));
            assertEquals(6, result.get('m')); // first 'm' at index 5, not 6!
            assertEquals(8, result.get('i'));
            assertEquals(9, result.get('n'));
        }

        @Test
        void basicCase2_2() {
            Map<Character, Integer> result = MapChallenges.challenge2_2("programming");

            assertEquals(0, result.get('p'));
            assertEquals(1, result.get('r')); // first 'r' at index 1, not 6!
            assertEquals(2, result.get('o'));
            assertEquals(3, result.get('g')); // first 'g' at index 3, not 9!
            assertEquals(5, result.get('a'));
            assertEquals(6, result.get('m')); // first 'm' at index 5, not 6!
            assertEquals(8, result.get('i'));
            assertEquals(9, result.get('n'));
        }

        @Test
        void noDuplicates() {
            Map<Character, Integer> result = MapChallenges.challenge2("abc");

            assertEquals(0, result.get('a'));
            assertEquals(1, result.get('b'));
            assertEquals(2, result.get('c'));
            assertEquals(3, result.size());
        }

        @Test
        void allSameChar() {
            Map<Character, Integer> result = MapChallenges.challenge2("aaaa");

            assertEquals(1, result.size());
            assertEquals(0, result.get('a')); // first occurrence = 0!
        }

        @Test
        void singleChar() {
            Map<Character, Integer> result = MapChallenges.challenge2("x");
            assertEquals(0, result.get('x'));
        }

        @Test
        void emptyString() {
            assertTrue(MapChallenges.challenge2("").isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — merge()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            Map<String, Integer> cart1 = new HashMap<>(Map.of("apple",3,"banana",2,"cherry",1));
            Map<String, Integer> cart2 = new HashMap<>(Map.of("banana",4,"cherry",2,"date",5));

            Map<String, Integer> result = MapChallenges.challenge3(cart1, cart2);

            assertEquals(4, result.size());
            assertEquals(3, result.get("apple"));  // only cart1
            assertEquals(6, result.get("banana")); // 2+4
            assertEquals(3, result.get("cherry")); // 1+2
            assertEquals(5, result.get("date"));   // only cart2
        }

        @Test
        void noCommonItems() {
            Map<String, Integer> cart1 = new HashMap<>(Map.of("apple",3,"banana",2));
            Map<String, Integer> cart2 = new HashMap<>(Map.of("cherry",1,"date",5));

            Map<String, Integer> result = MapChallenges.challenge3(cart1, cart2);

            assertEquals(4, result.size());
            assertEquals(3, result.get("apple"));
            assertEquals(2, result.get("banana"));
            assertEquals(1, result.get("cherry"));
            assertEquals(5, result.get("date"));
        }

        @Test
        void allCommonItems() {
            Map<String, Integer> cart1 = new HashMap<>(Map.of("a",1,"b",2));
            Map<String, Integer> cart2 = new HashMap<>(Map.of("a",3,"b",4));

            Map<String, Integer> result = MapChallenges.challenge3(cart1, cart2);

            assertEquals(4, result.get("a")); // 1+3
            assertEquals(6, result.get("b")); // 2+4
        }

        @Test
        void emptyCart1() {
            Map<String, Integer> result = MapChallenges.challenge3(
                    new HashMap<>(),
                    new HashMap<>(Map.of("apple",3)));

            assertEquals(3, result.get("apple"));
        }

        @Test
        void emptyCart2() {
            Map<String, Integer> result = MapChallenges.challenge3(
                    new HashMap<>(Map.of("apple",3)),
                    new HashMap<>());

            assertEquals(3, result.get("apple"));
        }

        @Test
        void originalMapsNotModified() {
            Map<String, Integer> cart1 = new HashMap<>(Map.of("apple",3));
            Map<String, Integer> cart2 = new HashMap<>(Map.of("apple",5));

            MapChallenges.challenge3(cart1, cart2);

            assertEquals(3, cart1.get("apple")); // unchanged!
            assertEquals(5, cart2.get("apple")); // unchanged!
        }

        @Test
        void nullCart1() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge3(null, new HashMap<>()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — replaceAll()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            Map<String, Double> prices = new HashMap<>(Map.of(
                    "Phone",  999.0,
                    "Shirt",   49.0,
                    "Laptop", 1299.0,
                    "Book",    15.0,
                    "Cable",    9.99
            ));
            Map<String, Double> result = MapChallenges.challenge4(prices);

            assertEquals(899.10,  result.get("Phone"),  0.01); // *0.90
            assertEquals(51.45,   result.get("Shirt"),  0.01); // *1.05
            assertEquals(1169.10, result.get("Laptop"), 0.01); // *0.90
            assertEquals(15.75,   result.get("Book"),   0.01); // *1.05
            assertEquals(10.49,   result.get("Cable"),  0.01); // *1.05
        }

        @Test
        void exactBoundary100IsIncreased() {
            Map<String, Double> prices = new HashMap<>(Map.of("item", 100.0));
            Map<String, Double> result = MapChallenges.challenge4(prices);

            // price = 100.0 → NOT > 100 → increase 5%
            assertEquals(105.0, result.get("item"), 0.01);
        }

        @Test
        void exactBoundary100PlusOneIsDiscounted() {
            Map<String, Double> prices = new HashMap<>(Map.of("item", 100.01));
            Map<String, Double> result = MapChallenges.challenge4(prices);

            // price > 100 → discount 10%
            assertEquals(90.009, result.get("item"), 0.01);
        }

        @Test
        void modifiesOriginalMap() {
            Map<String, Double> prices = new HashMap<>(Map.of("a", 200.0));
            Map<String, Double> result = MapChallenges.challenge4(prices);

            // replaceAll modifies IN PLACE → same reference!
            assertSame(prices, result);
        }

        @Test
        void emptyMap() {
            assertTrue(MapChallenges.challenge4(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — forEach()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            Map<String, List<String>> deptMap = new HashMap<>(Map.of(
                    "Engineering", new ArrayList<>(List.of("Carol","Alice","Eve")),
                    "Marketing",   new ArrayList<>(List.of("Bob","Diana")),
                    "HR",          new ArrayList<>(List.of("Frank"))
            ));
            String result = MapChallenges.challenge5(deptMap);

            String[] lines = result.split("\n");
            assertEquals(3, lines.length);
            // Departments sorted alpha: Engineering, HR, Marketing
            assertEquals("Engineering (3 employees): Alice, Carol, Eve", lines[0]);
            assertEquals("HR (1 employees): Frank",                       lines[1]);
            assertEquals("Marketing (2 employees): Bob, Diana",           lines[2]);
        }

        @Test
        void singleDepartment() {
            Map<String, List<String>> deptMap = new HashMap<>(Map.of(
                    "Engineering", new ArrayList<>(List.of("Bob","Alice"))
            ));
            String result = MapChallenges.challenge5(deptMap);

            assertEquals("Engineering (2 employees): Alice, Bob", result);
        }

        @Test
        void employeesSortedAlpha() {
            Map<String, List<String>> deptMap = new HashMap<>(Map.of(
                    "HR", new ArrayList<>(List.of("Zara","Alice","Mia"))
            ));
            String result = MapChallenges.challenge5(deptMap);

            assertTrue(result.contains("Alice, Mia, Zara"));
        }

        @Test
        void departmentsSortedAlpha() {
            Map<String, List<String>> deptMap = new HashMap<>(Map.of(
                    "Zebra", new ArrayList<>(List.of("A")),
                    "Alpha", new ArrayList<>(List.of("B"))
            ));
            String result = MapChallenges.challenge5(deptMap);

            assertTrue(result.startsWith("Alpha"));
        }

        @Test
        void emptyMap() {
            assertEquals("", MapChallenges.challenge5(new HashMap<>()));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — computeIfAbsent()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            List<String[]> edges = List.of(
                    new String[]{"A","B"},
                    new String[]{"A","C"},
                    new String[]{"B","C"},
                    new String[]{"C","A"},
                    new String[]{"B","D"}
            );
            Map<String, List<String>> result = MapChallenges.challenge6(edges);

            assertEquals(3, result.size());
            assertEquals(List.of("B","C"), result.get("A"));
            assertEquals(List.of("C","D"), result.get("B"));
            assertEquals(List.of("A"),     result.get("C"));
        }

        @Test
        void singleEdge() {
            List<String[]> edges = new ArrayList<>();
            edges.add(new String[]{"X","Y"});
            Map<String, List<String>> result = MapChallenges.challenge6(edges);

            assertEquals(1, result.size());
            assertEquals(List.of("Y"), result.get("X"));
        }

        @Test
        void multipleEdgesFromSameNode() {
            List<String[]> edges = List.of(
                    new String[]{"A","Z"},
                    new String[]{"A","B"},
                    new String[]{"A","M"}
            );
            Map<String, List<String>> result = MapChallenges.challenge6(edges);

            // neighbors sorted alpha: B,M,Z
            assertEquals(List.of("B","M","Z"), result.get("A"));
        }

        @Test
        void emptyEdges() {
            assertTrue(MapChallenges.challenge6(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — computeIfPresent()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            Map<String, Double> salaries = new HashMap<>(Map.of(
                    "Alice", 80000.0,
                    "Bob",   60000.0,
                    "Carol", 75000.0
            ));
            List<String[]> raises = List.of(
                    new String[]{"Alice","10"},
                    new String[]{"Diana","20"}, // Diana doesn't exist → no new entry!
                    new String[]{"Bob","5"},
                    new String[]{"Carol","15"}
            );
            Map<String, Double> result = MapChallenges.challenge7(salaries, raises);

            assertEquals(3,        result.size());       // Diana NOT added!
            assertEquals(88000.0,  result.get("Alice"),  0.01);
            assertEquals(63000.0,  result.get("Bob"),    0.01);
            assertEquals(86250.0,  result.get("Carol"),  0.01);
            assertFalse(result.containsKey("Diana"));    // key absent → stays absent!
        }

        @Test
        void absentKeyNotAdded() {
            Map<String, Double> salaries = new HashMap<>(Map.of("Alice", 80000.0));
            List<String[]> raises = new ArrayList<>();
            raises.add(new String[]{"Bob","10"});

            Map<String, Double> result = MapChallenges.challenge7(salaries, raises);

            assertFalse(result.containsKey("Bob")); // not added by computeIfPresent!
            assertEquals(1, result.size());
        }

        @Test
        void zeroPercentRaise() {
            Map<String, Double> salaries = new HashMap<>(Map.of("Alice", 80000.0));
            List<String[]> raises = new ArrayList<>();
            raises.add(new String[]{"Alice","0"});

            Map<String, Double> result = MapChallenges.challenge7(salaries, raises);
            assertEquals(80000.0, result.get("Alice"), 0.01); // unchanged
        }

        @Test
        void emptyRaises() {
            Map<String, Double> salaries = new HashMap<>(Map.of("Alice", 80000.0));
            Map<String, Double> result = MapChallenges.challenge7(salaries, List.of());

            assertEquals(80000.0, result.get("Alice"), 0.01); // unchanged
        }

        @Test
        void nullSalaries() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge7(null, List.of()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — compute()
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            Map<String, Integer> result = MapChallenges.challenge8(List.of(
                    "LOGIN:Alice","LOGIN:Bob","LOGIN:Alice","LOGOUT:Bob",
                    "LOGIN:Carol","LOGOUT:Alice","LOGIN:Bob","LOGOUT:Bob"
            ));

            assertEquals(2, result.size());
            assertEquals(1, result.get("Alice")); // 2 logins, 1 logout = 1 active
            assertEquals(1, result.get("Carol")); // 1 login, 0 logouts = 1 active
            assertFalse(result.containsKey("Bob")); // 2 logins, 2 logouts = removed!
        }

        @Test
        void multipleLoginSameUser() {
            Map<String, Integer> result = MapChallenges.challenge8(List.of(
                    "LOGIN:Alice","LOGIN:Alice","LOGIN:Alice"
            ));

            assertEquals(3, result.get("Alice")); // 3 active sessions
        }

        @Test
        void loginThenLogout() {
            Map<String, Integer> result = MapChallenges.challenge8(List.of(
                    "LOGIN:Alice","LOGOUT:Alice"
            ));

            assertFalse(result.containsKey("Alice")); // count=0 → REMOVED!
        }

        @Test
        void noEvents() {
            assertTrue(MapChallenges.challenge8(List.of()).isEmpty());
        }

        @Test
        void onlyLogins() {
            Map<String, Integer> result = MapChallenges.challenge8(List.of(
                    "LOGIN:Alice","LOGIN:Bob"
            ));

            assertEquals(1, result.get("Alice"));
            assertEquals(1, result.get("Bob"));
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — remove(key, value)
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            Map<String, Integer> inventory = new HashMap<>(Map.of(
                    "apple", 5, "banana", 3, "cherry", 8, "date", 2));
            List<String[]> requests = List.of(
                    new String[]{"banana","3"},  // 3=3 → REMOVED
                    new String[]{"apple", "4"},  // 5≠4 → kept
                    new String[]{"cherry","8"},  // 8=8 → REMOVED
                    new String[]{"date",  "1"}   // 2≠1 → kept
            );
            Map<String, Integer> removed = MapChallenges.challenge9(inventory, requests);

            // Removed entries
            assertEquals(2, removed.size());
            assertEquals(3, removed.get("banana"));
            assertEquals(8, removed.get("cherry"));

            // Remaining in inventory
            assertTrue(inventory.containsKey("apple"));   // not removed
            assertTrue(inventory.containsKey("date"));    // not removed
            assertFalse(inventory.containsKey("banana")); // removed!
            assertFalse(inventory.containsKey("cherry")); // removed!
        }

        @Test
        void noneRemoved() {
            Map<String, Integer> inventory = new HashMap<>(Map.of("apple",5));
            List<String[]> requests = new ArrayList<>(); // 5≠3
            requests.add(new String[]{"apple","3"});

            Map<String, Integer> removed = MapChallenges.challenge9(inventory, requests);

            assertTrue(removed.isEmpty());
            assertTrue(inventory.containsKey("apple")); // still there!
        }

        @Test
        void allRemoved() {
            Map<String, Integer> inventory = new HashMap<>(Map.of("apple",5,"banana",3));
            List<String[]> requests = List.of(
                    new String[]{"apple","5"},
                    new String[]{"banana","3"}
            );
            Map<String, Integer> removed = MapChallenges.challenge9(inventory, requests);

            assertEquals(2, removed.size());
            assertTrue(inventory.isEmpty());
        }

        @Test
        void absentKeyRequest() {
            Map<String, Integer> inventory = new HashMap<>(Map.of("apple",5));
            List<String[]> requests = new ArrayList<>(); // grape absent
            requests.add(new String[]{"grape","5"});

            Map<String, Integer> removed = MapChallenges.challenge9(inventory, requests);

            assertTrue(removed.isEmpty());
        }

        @Test
        void nullInventory() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge9(null, List.of()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Full Map Operations: Transaction Summary
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        private List<String> transactions;

        @BeforeEach
        void setUp() {
            transactions = List.of(
                    "DEPOSIT:ACC001:1000.0",
                    "WITHDRAWAL:ACC001:200.0",
                    "DEPOSIT:ACC002:500.0",
                    "DEPOSIT:ACC001:300.0",
                    "WITHDRAWAL:ACC002:700.0",
                    "DEPOSIT:ACC002:400.0"
            );
        }

        @Test
        void basicCase() {
            Map<String, MapChallenges.AccountSummary> result =
                    MapChallenges.challenge10(transactions);

            assertEquals(2, result.size());

            MapChallenges.AccountSummary acc1 = result.get("ACC001");
            assertEquals(3,      acc1.transactionCount());
            assertEquals(1300.0, acc1.totalDeposits(),      0.01);
            assertEquals(200.0,  acc1.totalWithdrawals(),   0.01);
            assertEquals(1100.0, acc1.netBalance(),         0.01);
            assertEquals(1000.0, acc1.largestTransaction(), 0.01);
            assertEquals("HEALTHY", acc1.status());

            MapChallenges.AccountSummary acc2 = result.get("ACC002");
            assertEquals(3,    acc2.transactionCount());
            assertEquals(900.0,acc2.totalDeposits(),      0.01);
            assertEquals(700.0,acc2.totalWithdrawals(),   0.01);
            assertEquals(200.0,acc2.netBalance(),         0.01);
            assertEquals(700.0,acc2.largestTransaction(), 0.01);
            assertEquals("HEALTHY", acc2.status());
        }

        @Test
        void overdrawnAccount() {
            Map<String, MapChallenges.AccountSummary> result =
                    MapChallenges.challenge10(List.of(
                            "DEPOSIT:ACC001:100.0",
                            "WITHDRAWAL:ACC001:300.0"
                    ));

            assertEquals("OVERDRAWN", result.get("ACC001").status());
            assertEquals(-200.0, result.get("ACC001").netBalance(), 0.01);
        }

        @Test
        void zeroBalanceAccount() {
            Map<String, MapChallenges.AccountSummary> result =
                    MapChallenges.challenge10(List.of(
                            "DEPOSIT:ACC001:500.0",
                            "WITHDRAWAL:ACC001:500.0"
                    ));

            assertEquals("ZERO",  result.get("ACC001").status());
            assertEquals(0.0,     result.get("ACC001").netBalance(), 0.01);
        }

        @Test
        void largestTransactionTracked() {
            Map<String, MapChallenges.AccountSummary> result =
                    MapChallenges.challenge10(List.of(
                            "DEPOSIT:ACC001:100.0",
                            "WITHDRAWAL:ACC001:500.0",
                            "DEPOSIT:ACC001:200.0"
                    ));

            assertEquals(500.0, result.get("ACC001").largestTransaction(), 0.01);
        }

        @Test
        void singleTransaction() {
            Map<String, MapChallenges.AccountSummary> result =
                    MapChallenges.challenge10(List.of("DEPOSIT:ACC001:1000.0"));

            MapChallenges.AccountSummary acc = result.get("ACC001");
            assertEquals(1,      acc.transactionCount());
            assertEquals(1000.0, acc.totalDeposits(),      0.01);
            assertEquals(0.0,    acc.totalWithdrawals(),   0.01);
            assertEquals(1000.0, acc.netBalance(),         0.01);
            assertEquals(1000.0, acc.largestTransaction(), 0.01);
            assertEquals("HEALTHY", acc.status());
        }

        @Test
        void emptyTransactions() {
            assertTrue(MapChallenges.challenge10(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges.challenge10(null));
        }
    }
}