package dev.perfectbogus.sorting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class SortingComparatorChallenges {

    record Employee(String name, String department, double salary) {}

    // ══════════════════════════════════════════════════════════════════════
    // SECTION A — EASY (Challenges 1–5)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 1
    // Sort strings by their LAST CHARACTER ascending,
    // then by LENGTH ascending for ties,
    // then alphabetically ascending for remaining ties.
    //
    // Input:  ["banana","apple","grape","fig","mango","date","lime"]
    //
    // Last chars: banana→'a', apple→'e', grape→'e', fig→'g', mango→'o', date→'e', lime→'e'
    //
    // last='a': banana
    // last='e': apple(5),grape(5),date(4),lime(4) → len ASC: date(4),lime(4),apple(5),grape(5)
    //            len=4 tie: date<lime alpha
    //            len=5 tie: apple<grape alpha
    // last='g': fig
    // last='o': mango
    //
    // Output: ["banana","date","lime","apple","grape","fig","mango"]
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge1(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        Comparator<String> byLastChar = Comparator.comparingInt(w -> w.charAt(w.length() - 1));
        Comparator<String> byLength = Comparator.comparingInt(String::length);
        Comparator<String> byAlpha = Comparator.naturalOrder();
        words.sort(byLastChar.thenComparing(byLength).thenComparing(byAlpha));
        return words;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 2
    // Sort Map<String, Integer> entries by their COMBINED SCORE ascending,
    // then by key alphabetically ascending for ties.
    // Combined score = key.length() * value
    //
    // Input:  {"cat"=4, "elephant"=1, "dog"=3, "ant"=5, "bear"=2}
    //
    // Scores:
    //   cat      → 3*4=12
    //   elephant → 8*1=8
    //   dog      → 3*3=9
    //   ant      → 3*5=15
    //   bear     → 4*2=8
    //
    // score=8:  bear(4*2), elephant(8*1) → key alpha: bear, elephant
    // score=9:  dog
    // score=12: cat
    // score=15: ant
    //
    // Output: [(bear,2),(elephant,1),(dog,3),(cat,4),(ant,5)]
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, Integer>> challenge2(Map<String, Integer> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO
        Comparator<Map.Entry<String, Integer>> byCombined = Comparator.comparingInt( e -> {
            int length = e.getKey().length();
            int val = e.getValue();
            return length*val;
        });
        Comparator<Map.Entry<String, Integer>> byAlpha = Map.Entry.comparingByKey();
        return map.entrySet().stream().sorted(byCombined.thenComparing(byAlpha)).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 3
    // Sort a 2D array by the PRODUCT of first and last element ASC,
    // then by first element ASC for ties,
    // then by last element ASC for remaining ties.
    //
    // Input:  [[3,2],[1,6],[2,3],[4,1],[1,4]]
    //
    // Products (first * last):
    //   [3,2] → 3*2=6
    //   [1,6] → 1*6=6
    //   [2,3] → 2*3=6
    //   [4,1] → 4*1=4
    //   [1,4] → 1*4=4
    //
    // product=4: [4,1] first=4, [1,4] first=1 → first ASC: [1,4],[4,1]
    // product=6: [1,6] first=1, [2,3] first=2, [3,2] first=3 → first ASC
    //
    // Output: [[1,4],[4,1],[1,6],[2,3],[3,2]]
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge3(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        Comparator<int[]> byProduct = Comparator.comparingInt(row -> row[0] * row[1]);
        Comparator<int[]> byFirstElem = Comparator.comparingInt(row -> row[0]);
        Comparator<int[]> byLastElem = Comparator.comparingInt(row -> row[1]);

        Arrays.sort(matrix, byProduct.thenComparing(byFirstElem).thenComparing(byLastElem));

        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 4
    // Sort employees by NAME LENGTH descending,
    // then by NAME alphabetically ascending for ties,
    // then by SALARY descending for remaining ties.
    //
    // record Employee(String name, String department, double salary)
    //
    // Input:
    //   ("Alice",   "Engineering", 95000)  name len=5
    //   ("Bob",     "Marketing",   60000)  name len=3
    //   ("Charlie", "Engineering", 85000)  name len=7
    //   ("Diana",   "HR",          70000)  name len=5
    //   ("Eve",     "Marketing",   90000)  name len=3
    //
    // len=7: Charlie
    // len=5: Alice(95000), Diana(70000) → name alpha: Alice,Diana
    // len=3: Bob(60000), Eve(90000) → name alpha: Bob,Eve
    //
    // Output: [Charlie, Alice, Diana, Bob, Eve]
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge4(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        Comparator<Employee> byNameLengthDesc = Comparator.<Employee>comparingInt(e -> e.name().length()).reversed();
        Comparator<Employee> byNameAlpha = Comparator.comparing(Employee::name);
        Comparator<Employee> bySalaryDesc = Comparator.comparingDouble(Employee::salary).reversed();

        employees.sort(byNameLengthDesc.thenComparing(byNameAlpha).thenComparing(bySalaryDesc));

        return employees;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 5
    // Sort integers by NUMBER OF DIGITS ascending,
    // then by VALUE ascending for ties.
    //
    // Input:  [350, 7, 42, 1000, 5, 98, 6543, 3]
    //
    // Digit counts:
    //   350  → 3 digits
    //   7    → 1 digit
    //   42   → 2 digits
    //   1000 → 4 digits
    //   5    → 1 digit
    //   98   → 2 digits
    //   6543 → 4 digits
    //   3    → 1 digit
    //
    // digits=1: 3,5,7    → value ASC: 3,5,7
    // digits=2: 42,98    → value ASC: 42,98
    // digits=3: 350
    // digits=4: 1000,6543 → value ASC
    //
    // Output: [3,5,7,42,98,350,1000,6543]
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> challenge5(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException("Numbers cannot be null");
        // TODO
        Comparator<Integer> byNDigits = Comparator.comparingInt(i -> Character.charCount(i));
        Comparator<Integer> byValue = Comparator.naturalOrder();

        numbers.sort(byNDigits.thenComparing(byValue));

        return numbers;
    }

    // ══════════════════════════════════════════════════════════════════════
    // SECTION B — MEDIUM (Challenges 6–10)
    // ══════════════════════════════════════════════════════════════════════

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 6
    // Sort products by PROFIT MARGIN descending,
    // then by NAME ascending for ties.
    // Profit margin = (price - cost) / price * 100  (as percentage)
    //
    // record Product(String name, double price, double cost)
    //
    // Input:
    //   ("Phone",  999.0, 600.0)  margin = (999-600)/999*100 = 39.94%
    //   ("Shirt",  49.0,  10.0)   margin = (49-10)/49*100   = 79.59%
    //   ("Laptop", 1299.0, 900.0) margin = (1299-900)/1299*100 = 30.72%
    //   ("Book",   29.0,  5.0)    margin = (29-5)/29*100    = 82.76%
    //   ("Cable",  9.99,  2.0)    margin = (9.99-2)/9.99*100 = 79.98%
    //
    // margin DESC: Book(82.76%), Cable(79.98%), Shirt(79.59%), Phone(39.94%), Laptop(30.72%)
    //
    // Output: [Book, Cable, Shirt, Phone, Laptop]
    // ─────────────────────────────────────────────────────────────
    record Product(String name, double price, double cost) {}

    public static List<Product> challenge6(List<Product> products) {
        if (products == null) throw new IllegalArgumentException("Products cannot be null");
        // TODO
        Comparator<Product> byProfitMarginDesc = Comparator.<Product>comparingDouble(p ->
                (p.price() - p.cost()) / (p.price() * 100))
                .reversed();
        Comparator<Product> byName = Comparator.comparing(Product::name);

        products.sort(byProfitMarginDesc.thenComparing(byName));

        return products;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 7
    // Sort Map<String, List<String>> entries by AVERAGE WORD LENGTH
    // of the list DESC, then by LIST SIZE ASC for ties,
    // then by KEY alphabetically ASC for remaining ties.
    //
    // Average word length = sum of word lengths / number of words
    //
    // Input:
    //   "team1" → ["java","stream","collect"]      avg=(4+6+7)/3=5.67
    //   "team2" → ["go","python"]                  avg=(2+6)/2=4.0
    //   "team3" → ["javascript","typescript"]      avg=(10+10)/2=10.0
    //   "team4" → ["c","cpp","rust","java"]        avg=(1+3+4+4)/4=3.0
    //   "team5" → ["kotlin","scala"]               avg=(6+5)/2=5.5
    //
    // avg DESC: team3(10.0),team1(5.67),team5(5.5),team2(4.0),team4(3.0)
    //
    // Output: [(team3,...),(team1,...),(team5,...),(team2,...),(team4,...)]
    // ─────────────────────────────────────────────────────────────
    public static List<Map.Entry<String, List<String>>> challenge7(Map<String, List<String>> map) {
        if (map == null) throw new IllegalArgumentException("Map cannot be null");
        // TODO
        Comparator<Map.Entry<String, List<String>>> byAvgWordLengthDesc =
                Map.Entry.<String, List<String>>comparingByValue(Comparator.comparingDouble(list ->
                        list.stream().mapToDouble(String::length).average().orElse(0.0))).reversed();

        Comparator<Map.Entry<String, List<String>>> byListSize = Map.Entry.comparingByValue(Comparator.comparingInt(List::size));
        Comparator<Map.Entry<String, List<String>>> byKeyAlpha = Map.Entry.comparingByKey(Comparator.naturalOrder());

        return map.entrySet().stream().sorted(byAvgWordLengthDesc.thenComparing(byListSize).thenComparing(byKeyAlpha)).toList();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 8
    // Sort 2D array by the COUNT OF PRIME NUMBERS in each row DESC,
    // then by ROW SUM ASC for ties,
    // then by FIRST ELEMENT ASC for remaining ties.
    //
    // Primes: 2,3,5,7,11,13,17,19,23...
    // A number is prime if it has exactly 2 divisors: 1 and itself.
    //
    // Input:  [[4,6,8],[2,3,5],[7,11,4],[1,9,15],[2,7,11]]
    //
    // Prime counts:
    //   [4,6,8]  → 0 primes              sum=18
    //   [2,3,5]  → 3 primes (2,3,5)      sum=10
    //   [7,11,4] → 2 primes (7,11)       sum=22
    //   [1,9,15] → 0 primes              sum=25
    //   [2,7,11] → 3 primes (2,7,11)     sum=20
    //
    // primes=3: [2,3,5]=10, [2,7,11]=20 → sum ASC: [2,3,5],[2,7,11]
    // primes=2: [7,11,4]=22
    // primes=0: [4,6,8]=18, [1,9,15]=25 → sum ASC: [4,6,8],[1,9,15]
    //
    // Output: [[2,3,5],[2,7,11],[7,11,4],[4,6,8],[1,9,15]]
    // ─────────────────────────────────────────────────────────────
    public static int[][] challenge8(int[][] matrix) {
        if (matrix == null) throw new IllegalArgumentException("Matrix cannot be null");
        // TODO
        return matrix;
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 9
    // Sort employees by their DEPARTMENT HEADCOUNT descending
    // (department with most employees comes first),
    // then by SALARY descending within same headcount,
    // then by NAME ascending for remaining ties.
    //
    // record Employee(String name, String department, double salary)
    //
    // Input:
    //   ("Alice",  "Engineering", 95000)
    //   ("Bob",    "Marketing",   60000)
    //   ("Carol",  "Engineering", 85000)
    //   ("Diana",  "Marketing",   70000)
    //   ("Eve",    "Engineering", 92000)
    //   ("Frank",  "HR",          75000)
    //
    // Headcounts: Engineering=3, Marketing=2, HR=1
    //
    // headcount=3 (Engineering): Alice(95000),Eve(92000),Carol(85000) → salary DESC
    // headcount=2 (Marketing):   Diana(70000),Bob(60000)              → salary DESC
    // headcount=1 (HR):          Frank(75000)
    //
    // Output: [Alice, Eve, Carol, Diana, Bob, Frank]
    // ─────────────────────────────────────────────────────────────
    public static List<Employee> challenge9(List<Employee> employees) {
        if (employees == null) throw new IllegalArgumentException("Employees cannot be null");
        // TODO
        return new ArrayList<>();
    }

    // ─────────────────────────────────────────────────────────────
    // CHALLENGE 10
    // Sort words by their VOWEL RATIO descending
    // (vowels / total characters),
    // then by LENGTH ascending for ties,
    // then alphabetically ascending for remaining ties.
    // Vowels = a, e, i, o, u (lowercase)
    //
    // Input:  ["rhythm","queue","hello","aeiou","cat","beautiful","gym"]
    //
    // Vowel ratios:
    //   rhythm    → 0/6 = 0.000
    //   queue     → 4/5 = 0.800  (u,e,u,e)
    //   hello     → 2/5 = 0.400  (e,o)
    //   aeiou     → 5/5 = 1.000
    //   cat       → 1/3 = 0.333
    //   beautiful → 5/9 = 0.556  (e,a,u,i,u)
    //   gym       → 0/3 = 0.000
    //
    // ratio=1.000: aeiou
    // ratio=0.800: queue
    // ratio=0.556: beautiful
    // ratio=0.400: hello
    // ratio=0.333: cat
    // ratio=0.000: gym(3),rhythm(6) → len ASC: gym,rhythm
    //
    // Output: ["aeiou","queue","beautiful","hello","cat","gym","rhythm"]
    // ─────────────────────────────────────────────────────────────
    public static List<String> challenge10(List<String> words) {
        if (words == null) throw new IllegalArgumentException("Words cannot be null");
        // TODO
        return new ArrayList<>();
    }
}