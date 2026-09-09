package dev.perfectbogus.maps;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MapChallenges2Test {

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 1 — Top N frequent words
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge1 {

        @Test
        void basicCase() {
            List<String> result = MapChallenges2.challenge1(
                    List.of("the cat sat on the mat",
                            "the cat in the hat",
                            "the cat sat"), 3);

            assertEquals(List.of("the","cat","sat"), result);
        }

        @Test
        void tiesBrokenAlphabetically() {
            // "on","mat","in","hat" all freq=1 → alpha: hat,in,mat,on
            List<String> result = MapChallenges2.challenge1(
                    List.of("the cat sat on the mat",
                            "the cat in the hat",
                            "the cat sat"), 5);

            assertEquals("the", result.get(0));
            assertEquals("cat", result.get(1));
            assertEquals("sat", result.get(2));
            assertEquals("hat", result.get(3)); // alpha: hat before in!
            assertEquals("in",  result.get(4));
        }

        @Test
        void singleSentence() {
            List<String> result = MapChallenges2.challenge1(
                    List.of("a b a c a b"), 2);

            assertEquals(List.of("a","b"), result);
        }

        @Test
        void nLargerThanUnique() {
            List<String> result = MapChallenges2.challenge1(
                    List.of("hello world"), 10);

            assertEquals(2, result.size());
            assertTrue(result.containsAll(List.of("hello","world")));
        }

        @Test
        void emptyList() {
            assertTrue(MapChallenges2.challenge1(List.of(), 3).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge1(null, 3));
        }

        @Test
        void invalidN() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge1(List.of("a"), 0));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 2 — Invert map to multimap
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge2 {

        @Test
        void basicCase() {
            Map<String, String> input = new LinkedHashMap<>();
            input.put("Alice", "Eng");
            input.put("Bob",   "Mkt");
            input.put("Carol", "Eng");
            input.put("Diana", "Mkt");

            Map<String, List<String>> result = MapChallenges2.challenge2(input);

            assertEquals(List.of("Alice","Carol"), result.get("Eng")); // sorted!
            assertEquals(List.of("Bob","Diana"),   result.get("Mkt"));
        }

        @Test
        void uniqueValues() {
            Map<String, Integer> input = Map.of("a", 1, "b", 2);
            Map<Integer, List<String>> result = MapChallenges2.challenge2(input);

            assertEquals(List.of("a"), result.get(1));
            assertEquals(List.of("b"), result.get(2));
        }

        @Test
        void allSameValue() {
            Map<String, String> input = Map.of("x","v","y","v","z","v");
            Map<String, List<String>> result = MapChallenges2.challenge2(input);

            assertEquals(1, result.size());
            assertEquals(3, result.get("v").size());
        }

        @Test
        void sortedAlphabetically() {
            Map<String, Integer> input = new LinkedHashMap<>();
            input.put("charlie", 1);
            input.put("alice",   1);
            input.put("bob",     1);

            Map<Integer, List<String>> result = MapChallenges2.challenge2(input);
            assertEquals(List.of("alice","bob","charlie"), result.get(1));
        }

        @Test
        void emptyMap() {
            assertTrue(MapChallenges2.challenge2(Map.<String, String>of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge2(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 3 — Anagram groups
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge3 {

        @Test
        void basicCase() {
            Map<String, List<String>> result = MapChallenges2.challenge3(
                    List.of("eat","tea","tan","ate","nat","bat"));

            assertEquals(List.of("ate","eat","tea"), result.get("aet")); // sorted!
            assertEquals(List.of("nat","tan"),        result.get("ant"));
            assertFalse(result.containsKey("abt")); // "bat" alone → excluded!
        }

        @Test
        void noAnagrams() {
            Map<String, List<String>> result = MapChallenges2.challenge3(
                    List.of("cat","dog","bird"));

            assertTrue(result.isEmpty()); // all singletons → excluded!
        }

        @Test
        void allAnagrams() {
            Map<String, List<String>> result = MapChallenges2.challenge3(
                    List.of("abc","bca","cab"));

            assertEquals(1, result.size());
            List<String> group = result.values().iterator().next();
            assertEquals(List.of("abc","bca","cab"), group);
        }

        @Test
        void wordsWithSameLettersDifferentGroups() {
            Map<String, List<String>> result = MapChallenges2.challenge3(
                    List.of("listen","silent","enlist","hello","world"));

            String key = String.valueOf("listen".chars().sorted()
                    .collect(StringBuilder::new,
                            StringBuilder::appendCodePoint, StringBuilder::append));
            assertEquals(3, result.get(key).size());
        }

        @Test
        void emptyList() {
            assertTrue(MapChallenges2.challenge3(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge3(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 4 — Map difference report
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge4 {

        @Test
        void basicCase() {
            Map<String, Integer> map1 = Map.of("a",1,"b",2,"c",3,"d",4);
            Map<String, Integer> map2 = Map.of("b",2,"c",99,"e",5);

            MapChallenges2.DiffReport<String> report =
                    MapChallenges2.challenge4(map1, map2);

            assertEquals(Set.of("a","d"), report.onlyInLeft());
            assertEquals(Set.of("e"),     report.onlyInRight());
            assertEquals(Set.of("c"),     report.different()); // c: 3≠99
        }

        @Test
        void identicalMaps() {
            Map<String, Integer> map = Map.of("a",1,"b",2);

            MapChallenges2.DiffReport<String> report =
                    MapChallenges2.challenge4(map, map);

            assertTrue(report.onlyInLeft().isEmpty());
            assertTrue(report.onlyInRight().isEmpty());
            assertTrue(report.different().isEmpty());
        }

        @Test
        void completelyDifferent() {
            Map<String, Integer> map1 = Map.of("a",1);
            Map<String, Integer> map2 = Map.of("b",2);

            MapChallenges2.DiffReport<String> report =
                    MapChallenges2.challenge4(map1, map2);

            assertEquals(Set.of("a"), report.onlyInLeft());
            assertEquals(Set.of("b"), report.onlyInRight());
            assertTrue(report.different().isEmpty());
        }

        @Test
        void sameKeysDifferentValues() {
            Map<String, Integer> map1 = Map.of("a",1,"b",2);
            Map<String, Integer> map2 = Map.of("a",9,"b",8);

            MapChallenges2.DiffReport<String> report =
                    MapChallenges2.challenge4(map1, map2);

            assertTrue(report.onlyInLeft().isEmpty());
            assertTrue(report.onlyInRight().isEmpty());
            assertEquals(Set.of("a","b"), report.different());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge4(null, Map.of()));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 5 — Two-level nested map
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge5 {

        @Test
        void basicCase() {
            List<MapChallenges2.Employee> employees = List.of(
                    new MapChallenges2.Employee("Alice", "Eng", 90000, 8),
                    new MapChallenges2.Employee("Bob",   "Eng", 70000, 3),
                    new MapChallenges2.Employee("Carol", "Mkt", 80000, 6),
                    new MapChallenges2.Employee("Diana", "Mkt", 60000, 2),
                    new MapChallenges2.Employee("Eve",   "Eng", 85000, 7)
            );
            Map<String, Map<String, Double>> result =
                    MapChallenges2.challenge5(employees);

            assertEquals(175000.0, result.get("Eng").get("SENIOR"), 0.01);
            assertEquals(70000.0,  result.get("Eng").get("JUNIOR"), 0.01);
            assertEquals(80000.0,  result.get("Mkt").get("SENIOR"), 0.01);
            assertEquals(60000.0,  result.get("Mkt").get("JUNIOR"), 0.01);
        }

        @Test
        void seniorThresholdIsExclusive() {
            // yearsOfExperience=5 → SENIOR (>= 5!)
            List<MapChallenges2.Employee> employees = List.of(
                    new MapChallenges2.Employee("A", "HR", 80000, 5),
                    new MapChallenges2.Employee("B", "HR", 60000, 4)
            );
            Map<String, Map<String, Double>> result =
                    MapChallenges2.challenge5(employees);

            assertEquals(80000.0, result.get("HR").get("SENIOR"), 0.01);
            assertEquals(60000.0, result.get("HR").get("JUNIOR"), 0.01);
        }

        @Test
        void singleEmployee() {
            List<MapChallenges2.Employee> employees = List.of(
                    new MapChallenges2.Employee("Alice", "Eng", 90000, 8));
            Map<String, Map<String, Double>> result =
                    MapChallenges2.challenge5(employees);

            assertEquals(90000.0, result.get("Eng").get("SENIOR"), 0.01);
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge5(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 6 — Element positions index
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge6 {

        @Test
        void basicStringCase() {
            Map<String, List<Integer>> result = MapChallenges2.challenge6(
                    List.of("a","b","a","c","b","a"));

            assertEquals(List.of(0,2,5), result.get("a"));
            assertEquals(List.of(1,4),   result.get("b"));
            assertEquals(List.of(3),      result.get("c"));
        }

        @Test
        void integerList() {
            Map<Integer, List<Integer>> result = MapChallenges2.challenge6(
                    List.of(1,2,3,2,1));

            assertEquals(List.of(0,4), result.get(1));
            assertEquals(List.of(1,3), result.get(2));
            assertEquals(List.of(2),   result.get(3));
        }

        @Test
        void allUnique() {
            Map<String, List<Integer>> result = MapChallenges2.challenge6(
                    List.of("x","y","z"));

            assertEquals(List.of(0), result.get("x"));
            assertEquals(List.of(1), result.get("y"));
            assertEquals(List.of(2), result.get("z"));
        }

        @Test
        void emptyList() {
            assertTrue(MapChallenges2.challenge6(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge6(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 7 — Running cumulative sum
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge7 {

        @Test
        void basicCase() {
            LinkedHashMap<String, Double> input = new LinkedHashMap<>();
            input.put("Jan", 100.0);
            input.put("Feb", 150.0);
            input.put("Mar", 200.0);
            input.put("Apr",  50.0);

            Map<String, Double> result = MapChallenges2.challenge7(input);

            List<Map.Entry<String, Double>> entries = new ArrayList<>(result.entrySet());
            assertEquals("Jan", entries.get(0).getKey());
            assertEquals(100.0, entries.get(0).getValue(), 0.01);
            assertEquals("Feb", entries.get(1).getKey());
            assertEquals(250.0, entries.get(1).getValue(), 0.01);
            assertEquals("Mar", entries.get(2).getKey());
            assertEquals(450.0, entries.get(2).getValue(), 0.01);
            assertEquals("Apr", entries.get(3).getKey());
            assertEquals(500.0, entries.get(3).getValue(), 0.01);
        }

        @Test
        void singleEntry() {
            LinkedHashMap<String, Double> input = new LinkedHashMap<>();
            input.put("only", 42.0);

            Map<String, Double> result = MapChallenges2.challenge7(input);
            assertEquals(42.0, result.get("only"), 0.01);
        }

        @Test
        void preservesOrder() {
            LinkedHashMap<String, Double> input = new LinkedHashMap<>();
            input.put("z", 1.0);
            input.put("a", 2.0);
            input.put("m", 3.0);

            List<String> keys = new ArrayList<>(MapChallenges2.challenge7(input).keySet());
            assertEquals(List.of("z","a","m"), keys); // insertion order!
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge7(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 8 — Frequency of frequencies
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge8 {

        @Test
        void basicStringCase() {
            Map<Integer, List<String>> result = MapChallenges2.challenge8(
                    List.of("apple","banana","apple","cherry","banana","apple"));

            assertEquals(List.of("apple"),  result.get(3));
            assertEquals(List.of("banana"), result.get(2));
            assertEquals(List.of("cherry"), result.get(1));
        }

        @Test
        void multipleElementsSameFrequency() {
            Map<Integer, List<Integer>> result = MapChallenges2.challenge8(
                    List.of(1,2,3,2,1,3,1));

            assertEquals(List.of(1),   result.get(3));
            assertEquals(List.of(2,3), result.get(2)); // sorted!
        }

        @Test
        void allSameFrequency() {
            Map<Integer, List<String>> result = MapChallenges2.challenge8(
                    List.of("a","b","c"));

            assertEquals(1, result.size());
            assertEquals(3, result.get(1).size());
        }

        @Test
        void emptyList() {
            assertTrue(MapChallenges2.challenge8(List.<String>of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge8(null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 9 — Merge maps with custom policy
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge9 {

        @Test
        void sumMerge() {
            Map<String, Integer> map1 = Map.of("a",10,"b",20,"c",30);
            Map<String, Integer> map2 = Map.of("b",5,"c",15,"d",25);

            Map<String, Integer> result =
                    MapChallenges2.challenge9(map1, map2, Integer::sum);

            assertEquals(10, result.get("a")); // only in map1
            assertEquals(25, result.get("b")); // 20+5
            assertEquals(45, result.get("c")); // 30+15
            assertEquals(25, result.get("d")); // only in map2
        }

        @Test
        void maxMerge() {
            Map<String, Integer> map1 = Map.of("x",10,"y",20);
            Map<String, Integer> map2 = Map.of("x",30,"y",5);

            Map<String, Integer> result =
                    MapChallenges2.challenge9(map1, map2, Math::max);

            assertEquals(30, result.get("x"));
            assertEquals(20, result.get("y"));
        }

        @Test
        void disjointMaps() {
            Map<String, Integer> map1 = Map.of("a",1);
            Map<String, Integer> map2 = Map.of("b",2);

            Map<String, Integer> result =
                    MapChallenges2.challenge9(map1, map2, Integer::sum);

            assertEquals(1, result.get("a"));
            assertEquals(2, result.get("b"));
        }

        @Test
        void emptyMaps() {
            Map<String, Integer> result = MapChallenges2.challenge9(
                    Map.of(), Map.of(), Integer::sum);
            assertTrue(result.isEmpty());
        }

        @Test
        void nullMap() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge9(null, Map.of(), Integer::sum));
        }

        @Test
        void nullMergeFunction() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge9(Map.of(), Map.of(), null));
        }
    }

    // ══════════════════════════════════════════════════════════════
    // CHALLENGE 10 — Word co-occurrence
    // ══════════════════════════════════════════════════════════════
    @Nested
    class Challenge10 {

        @Test
        void basicCase() {
            Map<String, Set<String>> result = MapChallenges2.challenge10(
                    List.of("hello world","hello java","java rocks"));

            assertEquals(Set.of("world","java"), result.get("hello"));
            assertEquals(Set.of("hello"),         result.get("world"));
            assertEquals(Set.of("hello","rocks"), result.get("java"));
            assertEquals(Set.of("java"),           result.get("rocks"));
        }

        @Test
        void singleSentence() {
            Map<String, Set<String>> result = MapChallenges2.challenge10(
                    List.of("a b c"));

            assertEquals(Set.of("b","c"), result.get("a"));
            assertEquals(Set.of("a","c"), result.get("b"));
            assertEquals(Set.of("a","b"), result.get("c"));
        }

        @Test
        void wordDoesNotCoOccurWithItself() {
            Map<String, Set<String>> result = MapChallenges2.challenge10(
                    List.of("hello world"));

            assertFalse(result.get("hello").contains("hello"));
            assertFalse(result.get("world").contains("world"));
        }

        @Test
        void sameWordInMultipleSentences() {
            Map<String, Set<String>> result = MapChallenges2.challenge10(
                    List.of("a b","a c","a d"));

            // "a" co-occurs with b, c, d
            assertEquals(Set.of("b","c","d"), result.get("a"));
        }

        @Test
        void emptyList() {
            assertTrue(MapChallenges2.challenge10(List.of()).isEmpty());
        }

        @Test
        void nullInput() {
            assertThrows(IllegalArgumentException.class,
                    () -> MapChallenges2.challenge10(null));
        }
    }
}