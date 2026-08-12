package dev.perfectbogus.comparators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CollectionConversionChallengesTest {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — List<String> → Map<Character, List<String>>
    //               Group by first letter, values sorted alphabetically
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            Map<Character, List<String>> result = CollectionConversionChallenges.challenge1(
                    List.of("banana","apple","avocado","blueberry","cherry","apricot"));

            assertEquals(3, result.size());
            assertEquals(List.of("apple","apricot","avocado"), result.get('a'));
            assertEquals(List.of("banana","blueberry"),        result.get('b'));
            assertEquals(List.of("cherry"),                    result.get('c'));
        }

        @Test
        void singleLetter() {
            Map<Character, List<String>> result = CollectionConversionChallenges.challenge1(
                    List.of("ant","ape","ark"));

            assertEquals(1, result.size());
            assertEquals(List.of("ant","ape","ark"), result.get('a'));
        }

        @Test
        void singleWord() {
            Map<Character, List<String>> result = CollectionConversionChallenges.challenge1(List.of("hello"));
            assertEquals(List.of("hello"), result.get('h'));
        }

        @Test
        void allDifferentFirstLetters() {
            Map<Character, List<String>> result = CollectionConversionChallenges.challenge1(
                    List.of("cherry","apple","banana"));

            assertEquals(3, result.size());
            assertEquals(List.of("apple"),  result.get('a'));
            assertEquals(List.of("banana"), result.get('b'));
            assertEquals(List.of("cherry"), result.get('c'));
        }

        @Test
        void sortedAlphabeticallyWithinGroup() {
            Map<Character, List<String>> result = CollectionConversionChallenges.challenge1(
                    List.of("zebra","zoo","zap","zip"));

            List<String> zGroup = result.get('z');
            assertEquals(List.of("zap","zebra","zip","zoo"), zGroup);
        }

        @Test
        void emptyList() {
            assertTrue(CollectionConversionChallenges.challenge1(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectionConversionChallenges.challenge1(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Map<String, List<Integer>> → Map<String, Integer>
    //               Sum the list of values per key
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        private Map<String, List<Integer>> salaries;

        @BeforeEach
        void setUp() {
            salaries = new HashMap<>(Map.of(
                    "Engineering", List.of(95000, 85000, 92000),
                    "Marketing",   List.of(60000, 70000),
                    "HR",          List.of(55000, 58000)
            ));
        }

        @Test
        void basicCase() {
            Map<String, Integer> result = CollectionConversionChallenges.challenge2(salaries);

            assertEquals(272000, result.get("Engineering"));
            assertEquals(130000, result.get("Marketing"));
            assertEquals(113000, result.get("HR"));
        }

        @Test
        void singleDepartment() {
            Map<String, List<Integer>> single = new HashMap<>(Map.of("HR", List.of(50000, 60000)));
            Map<String, Integer> result = CollectionConversionChallenges.challenge2(single);

            assertEquals(1, result.size());
            assertEquals(110000, result.get("HR"));
        }

        @Test
        void singleSalaryPerDepartment() {
            Map<String, List<Integer>> single = new HashMap<>(Map.of(
                    "Engineering", List.of(95000),
                    "Marketing",   List.of(60000)
            ));
            Map<String, Integer> result = CollectionConversionChallenges.challenge2(single);

            assertEquals(95000, result.get("Engineering"));
            assertEquals(60000, result.get("Marketing"));
        }

        @Test
        void emptyMap() {
            assertTrue(CollectionConversionChallenges.challenge2(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectionConversionChallenges.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — List<Employee> → Map<String, Employee>
    //               Index by name, keep highest salary on duplicate
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCaseWithDuplicates() {
            List<CollectionConversionChallenges.Employee> employees = List.of(
                    new CollectionConversionChallenges.Employee("Alice", "Engineering", 95000),
                    new CollectionConversionChallenges.Employee("Bob",   "Marketing",   60000),
                    new CollectionConversionChallenges.Employee("Alice", "HR",          85000),
                    new CollectionConversionChallenges.Employee("Bob",   "Engineering", 75000)
            );
            Map<String, CollectionConversionChallenges.Employee> result =
                    CollectionConversionChallenges.challenge3(employees);

            assertEquals(2, result.size());
            assertEquals(95000, result.get("Alice").salary(), 0.01); // higher wins
            assertEquals(75000, result.get("Bob").salary(),   0.01); // higher wins
            assertEquals("Engineering", result.get("Alice").department());
            assertEquals("Engineering", result.get("Bob").department());
        }

        @Test
        void noDuplicates() {
            List<CollectionConversionChallenges.Employee> employees = List.of(
                    new CollectionConversionChallenges.Employee("Alice", "Engineering", 95000),
                    new CollectionConversionChallenges.Employee("Bob",   "Marketing",   60000)
            );
            Map<String, CollectionConversionChallenges.Employee> result =
                    CollectionConversionChallenges.challenge3(employees);

            assertEquals(2, result.size());
            assertEquals(95000, result.get("Alice").salary(), 0.01);
            assertEquals(60000, result.get("Bob").salary(),   0.01);
        }

        @Test
        void allSameName() {
            List<CollectionConversionChallenges.Employee> employees = List.of(
                    new CollectionConversionChallenges.Employee("Alice", "Engineering", 95000),
                    new CollectionConversionChallenges.Employee("Alice", "HR",          75000),
                    new CollectionConversionChallenges.Employee("Alice", "Marketing",   85000)
            );
            Map<String, CollectionConversionChallenges.Employee> result =
                    CollectionConversionChallenges.challenge3(employees);

            assertEquals(1, result.size());
            assertEquals(95000, result.get("Alice").salary(), 0.01); // highest!
        }

        @Test
        void emptyList() {
            assertTrue(CollectionConversionChallenges.challenge3(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectionConversionChallenges.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Map<String, Integer> → List<String>
    //               Expand each word COUNT times, sorted alphabetically
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            Map<String, Integer> input = new HashMap<>(Map.of(
                    "apple",  3,
                    "banana", 1,
                    "cherry", 2
            ));
            List<String> result = CollectionConversionChallenges.challenge4(input);

            assertEquals(6, result.size());
            assertEquals(List.of("apple","apple","apple","banana","cherry","cherry"), result);
        }

        @Test
        void singleWord() {
            Map<String, Integer> input = new HashMap<>(Map.of("hello", 3));
            List<String> result = CollectionConversionChallenges.challenge4(input);

            assertEquals(List.of("hello","hello","hello"), result);
        }

        @Test
        void countOfOne() {
            Map<String, Integer> input = new HashMap<>(Map.of("apple", 1, "banana", 1));
            List<String> result = CollectionConversionChallenges.challenge4(input);

            assertEquals(2, result.size());
            assertEquals(List.of("apple","banana"), result);
        }

        @Test
        void sortedCorrectly() {
            Map<String, Integer> input = new HashMap<>(Map.of("zebra", 2, "ant", 2));
            List<String> result = CollectionConversionChallenges.challenge4(input);

            assertEquals(List.of("ant","ant","zebra","zebra"), result);
        }

        @Test
        void emptyMap() {
            assertTrue(CollectionConversionChallenges.challenge4(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectionConversionChallenges.challenge4(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — List<String> → LinkedHashMap<String, Long>
    //               Frequency map preserving first occurrence order
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            LinkedHashMap<String, Long> result = CollectionConversionChallenges.challenge5(
                    List.of("apple","banana","apple","cherry","banana","apple"));

            // Check values
            assertEquals(3L, result.get("apple"));
            assertEquals(2L, result.get("banana"));
            assertEquals(1L, result.get("cherry"));

            // Check insertion order (first occurrence)
            List<String> keys = new ArrayList<>(result.keySet());
            assertEquals("apple",  keys.get(0)); // first seen
            assertEquals("banana", keys.get(1)); // second seen
            assertEquals("cherry", keys.get(2)); // third seen
        }

        @Test
        void noDuplicates() {
            LinkedHashMap<String, Long> result = CollectionConversionChallenges.challenge5(
                    List.of("apple","banana","cherry"));

            assertEquals(3, result.size());
            result.values().forEach(v -> assertEquals(1L, v));

            List<String> keys = new ArrayList<>(result.keySet());
            assertEquals("apple",  keys.get(0));
            assertEquals("banana", keys.get(1));
            assertEquals("cherry", keys.get(2));
        }

        @Test
        void allSameWord() {
            LinkedHashMap<String, Long> result = CollectionConversionChallenges.challenge5(
                    List.of("apple","apple","apple"));

            assertEquals(1, result.size());
            assertEquals(3L, result.get("apple"));
        }

        @Test
        void orderPreserved() {
            LinkedHashMap<String, Long> result = CollectionConversionChallenges.challenge5(
                    List.of("c","a","b","c","a"));

            List<String> keys = new ArrayList<>(result.keySet());
            assertEquals("c", keys.get(0)); // c seen first
            assertEquals("a", keys.get(1)); // a seen second
            assertEquals("b", keys.get(2)); // b seen third
        }

        @Test
        void emptyList() {
            assertTrue(CollectionConversionChallenges.challenge5(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectionConversionChallenges.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Map<String, Map<String, Integer>> → Map<String, Integer>
    //               Flatten nested map into single map
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicCase() {
            Map<String, Map<String, Integer>> nested = new HashMap<>(Map.of(
                    "Europe",   new HashMap<>(Map.of("France", 67_000_000, "Germany", 83_000_000)),
                    "Asia",     new HashMap<>(Map.of("Japan",  125_000_000, "China", 1_400_000_000)),
                    "Americas", new HashMap<>(Map.of("Brazil", 215_000_000))
            ));
            Map<String, Integer> result = CollectionConversionChallenges.challenge6(nested);

            assertEquals(5, result.size());
            assertEquals(67_000_000,    result.get("France"));
            assertEquals(83_000_000,    result.get("Germany"));
            assertEquals(125_000_000,   result.get("Japan"));
            assertEquals(1_400_000_000, result.get("China"));
            assertEquals(215_000_000,   result.get("Brazil"));
        }

        @Test
        void singleContinent() {
            Map<String, Map<String, Integer>> nested = new HashMap<>(Map.of(
                    "Europe", new HashMap<>(Map.of("France", 67_000_000))
            ));
            Map<String, Integer> result = CollectionConversionChallenges.challenge6(nested);

            assertEquals(1, result.size());
            assertEquals(67_000_000, result.get("France"));
        }

        @Test
        void multipleContinentsSingleCountry() {
            Map<String, Map<String, Integer>> nested = new HashMap<>(Map.of(
                    "A", new HashMap<>(Map.of("CountryA", 100)),
                    "B", new HashMap<>(Map.of("CountryB", 200)),
                    "C", new HashMap<>(Map.of("CountryC", 300))
            ));
            Map<String, Integer> result = CollectionConversionChallenges.challenge6(nested);

            assertEquals(3, result.size());
            assertEquals(100, result.get("CountryA"));
            assertEquals(200, result.get("CountryB"));
            assertEquals(300, result.get("CountryC"));
        }

        @Test
        void emptyNestedMap() {
            Map<String, Map<String, Integer>> nested = new HashMap<>(Map.of(
                    "Europe", new HashMap<>()
            ));
            assertTrue(CollectionConversionChallenges.challenge6(nested).isEmpty());
        }

        @Test
        void emptyOuterMap() {
            assertTrue(CollectionConversionChallenges.challenge6(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectionConversionChallenges.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Map<String, Integer> → Map<Integer, List<String>>
    //               Invert map: value → list of keys, sorted alpha
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            Map<String, Integer> input = new HashMap<>(Map.of(
                    "apple",      3,
                    "banana",     1,
                    "cherry",     3,
                    "date",       2,
                    "elderberry", 1
            ));
            Map<Integer, List<String>> result = CollectionConversionChallenges.challenge7(input);

            assertEquals(3, result.size());
            assertEquals(List.of("apple","cherry"),           result.get(3)); // sorted alpha
            assertEquals(List.of("date"),                     result.get(2));
            assertEquals(List.of("banana","elderberry"),      result.get(1)); // sorted alpha
        }

        @Test
        void allUniqueValues() {
            Map<String, Integer> input = new HashMap<>(Map.of(
                    "apple",  1,
                    "banana", 2,
                    "cherry", 3
            ));
            Map<Integer, List<String>> result = CollectionConversionChallenges.challenge7(input);

            assertEquals(3, result.size());
            assertEquals(List.of("apple"),  result.get(1));
            assertEquals(List.of("banana"), result.get(2));
            assertEquals(List.of("cherry"), result.get(3));
        }

        @Test
        void allSameValue() {
            Map<String, Integer> input = new HashMap<>(Map.of(
                    "cat", 5,
                    "ant", 5,
                    "bat", 5
            ));
            Map<Integer, List<String>> result = CollectionConversionChallenges.challenge7(input);

            assertEquals(1, result.size());
            assertEquals(List.of("ant","bat","cat"), result.get(5)); // sorted alpha
        }

        @Test
        void singleEntry() {
            Map<String, Integer> input = new HashMap<>(Map.of("apple", 3));
            Map<Integer, List<String>> result = CollectionConversionChallenges.challenge7(input);

            assertEquals(1, result.size());
            assertEquals(List.of("apple"), result.get(3));
        }

        @Test
        void emptyMap() {
            assertTrue(CollectionConversionChallenges.challenge7(new HashMap<>()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectionConversionChallenges.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — List<String> → TreeMap<Integer, List<String>>
    //               Group by word length, TreeMap sorts keys ASC
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicCase() {
            TreeMap<Integer, List<String>> result = CollectionConversionChallenges.challenge8(
                    List.of("fig","banana","kiwi","apple","plum","date","cherry"));

            assertEquals(4, result.size());

            // Keys sorted ASC (TreeMap!)
            assertEquals(3, result.firstKey());
            assertEquals(6, result.lastKey());

            assertEquals(List.of("fig"),             result.get(3));
            assertEquals(List.of("date","kiwi","plum"), result.get(4)); // sorted alpha
            assertEquals(List.of("apple"),           result.get(5));
            assertEquals(List.of("banana","cherry"), result.get(6)); // sorted alpha
        }

        @Test
        void keysAreSortedAscending() {
            TreeMap<Integer, List<String>> result = CollectionConversionChallenges.challenge8(
                    List.of("elephant","cat","hi","a","dog","banana"));

            List<Integer> keys = new ArrayList<>(result.keySet());
            for (int i = 0; i < keys.size() - 1; i++) {
                assertTrue(keys.get(i) < keys.get(i + 1));
            }
        }

        @Test
        void allSameLength() {
            TreeMap<Integer, List<String>> result = CollectionConversionChallenges.challenge8(
                    List.of("cat","dog","ant"));

            assertEquals(1, result.size());
            assertEquals(List.of("ant","cat","dog"), result.get(3)); // sorted alpha
        }

        @Test
        void singleWord() {
            TreeMap<Integer, List<String>> result = CollectionConversionChallenges.challenge8(List.of("hello"));
            assertEquals(1, result.size());
            assertEquals(List.of("hello"), result.get(5));
        }

        @Test
        void valuesAreSortedAlphabetically() {
            TreeMap<Integer, List<String>> result = CollectionConversionChallenges.challenge8(
                    List.of("zebra","apple","mango"));

            // All length 5 → one group, sorted alpha
            List<String> group = result.get(5);
            assertEquals(List.of("apple","mango","zebra"), group);
        }

        @Test
        void emptyList() {
            assertTrue(CollectionConversionChallenges.challenge8(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectionConversionChallenges.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Two Map<String,Integer> → Merged Map<String,Integer>
    //               Keys in both → sum values, keys in one → keep value
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void basicCase() {
            Map<String, Integer> map1 = new HashMap<>(Map.of("apple", 3, "banana", 2, "cherry", 5));
            Map<String, Integer> map2 = new HashMap<>(Map.of("banana", 4, "cherry", 1, "date",   7));

            Map<String, Integer> result = CollectionConversionChallenges.challenge9(map1, map2);

            assertEquals(4, result.size());
            assertEquals(3, result.get("apple"));  // only map1
            assertEquals(6, result.get("banana")); // 2+4
            assertEquals(6, result.get("cherry")); // 5+1
            assertEquals(7, result.get("date"));   // only map2
        }

        @Test
        void noCommonKeys() {
            Map<String, Integer> map1 = new HashMap<>(Map.of("apple", 1, "banana", 2));
            Map<String, Integer> map2 = new HashMap<>(Map.of("cherry", 3, "date", 4));

            Map<String, Integer> result = CollectionConversionChallenges.challenge9(map1, map2);

            assertEquals(4, result.size());
            assertEquals(1, result.get("apple"));
            assertEquals(2, result.get("banana"));
            assertEquals(3, result.get("cherry"));
            assertEquals(4, result.get("date"));
        }

        @Test
        void allCommonKeys() {
            Map<String, Integer> map1 = new HashMap<>(Map.of("a", 1, "b", 2));
            Map<String, Integer> map2 = new HashMap<>(Map.of("a", 3, "b", 4));

            Map<String, Integer> result = CollectionConversionChallenges.challenge9(map1, map2);

            assertEquals(2, result.size());
            assertEquals(4, result.get("a")); // 1+3
            assertEquals(6, result.get("b")); // 2+4
        }

        @Test
        void emptyMap1() {
            Map<String, Integer> map1 = new HashMap<>();
            Map<String, Integer> map2 = new HashMap<>(Map.of("apple", 5));

            Map<String, Integer> result = CollectionConversionChallenges.challenge9(map1, map2);
            assertEquals(1, result.size());
            assertEquals(5, result.get("apple"));
        }

        @Test
        void emptyMap2() {
            Map<String, Integer> map1 = new HashMap<>(Map.of("apple", 5));
            Map<String, Integer> map2 = new HashMap<>();

            Map<String, Integer> result = CollectionConversionChallenges.challenge9(map1, map2);
            assertEquals(1, result.size());
            assertEquals(5, result.get("apple"));
        }

        @Test
        void bothEmpty() {
            assertTrue(CollectionConversionChallenges.challenge9(new HashMap<>(), new HashMap<>()).isEmpty());
        }

        @Test
        void originalMapsNotModified() {
            Map<String, Integer> map1 = new HashMap<>(Map.of("apple", 3));
            Map<String, Integer> map2 = new HashMap<>(Map.of("apple", 5));

            CollectionConversionChallenges.challenge9(map1, map2);

            // Original maps must stay unchanged!
            assertEquals(3, map1.get("apple"));
            assertEquals(5, map2.get("apple"));
        }

        @Test
        void nullMap1() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectionConversionChallenges.challenge9(null, new HashMap<>()));
        }

        @Test
        void nullMap2() {
            assertThrows(IllegalArgumentException.class,
                    () -> CollectionConversionChallenges.challenge9(new HashMap<>(), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — List<String> → Map<String, Map<Character, Long>>
    //                Each word → character frequency map
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() {
            Map<String, Map<Character, Long>> result = CollectionConversionChallenges.challenge10(
                    List.of("hello","world"));

            assertEquals(2, result.size());

            Map<Character, Long> helloFreq = result.get("hello");
            assertEquals(1L, helloFreq.get('h'));
            assertEquals(1L, helloFreq.get('e'));
            assertEquals(2L, helloFreq.get('l')); // 'l' appears twice!
            assertEquals(1L, helloFreq.get('o'));
            assertEquals(4, helloFreq.size());

            Map<Character, Long> worldFreq = result.get("world");
            assertEquals(1L, worldFreq.get('w'));
            assertEquals(1L, worldFreq.get('o'));
            assertEquals(1L, worldFreq.get('r'));
            assertEquals(1L, worldFreq.get('l'));
            assertEquals(1L, worldFreq.get('d'));
            assertEquals(5, worldFreq.size());
        }

        @Test
        void allSameChar() {
            Map<String, Map<Character, Long>> result = CollectionConversionChallenges.challenge10(List.of("aaa"));

            Map<Character, Long> freq = result.get("aaa");
            assertEquals(1, freq.size());
            assertEquals(3L, freq.get('a'));
        }

        @Test
        void singleChar() {
            Map<String, Map<Character, Long>> result = CollectionConversionChallenges.challenge10(List.of("a"));

            Map<Character, Long> freq = result.get("a");
            assertEquals(1, freq.size());
            assertEquals(1L, freq.get('a'));
        }

        @Test
        void multipleWords() {
            Map<String, Map<Character, Long>> result = CollectionConversionChallenges.challenge10(
                    List.of("cat","dog","cat"));

            // Two unique words (cat appears twice but map key is unique!)
            // If duplicates → last one wins OR merge → depends on toMap merge function
            // Challenge assumes unique words for simplicity
            assertTrue(result.containsKey("cat"));
            assertTrue(result.containsKey("dog"));
        }

        @Test
        void allUniqueChars() {
            Map<String, Map<Character, Long>> result = CollectionConversionChallenges.challenge10(List.of("abc"));

            Map<Character, Long> freq = result.get("abc");
            assertEquals(3, freq.size());
            assertEquals(1L, freq.get('a'));
            assertEquals(1L, freq.get('b'));
            assertEquals(1L, freq.get('c'));
        }

        @Test
        void emptyList() {
            assertTrue(CollectionConversionChallenges.challenge10(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class, () -> CollectionConversionChallenges.challenge10(null));
        }
    }
}