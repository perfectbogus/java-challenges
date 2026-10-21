package dev.perfectbogus.collectors;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CollectorsFunctionsChallenge {

    // Used by most of the challenges. In the examples a product is written as name(category, price, stock), for
    // example Pen(Office, 2, 100). The examples use these products:
    //     shop = [Pen(Office, 2, 100), Desk(Home, 150, 5), Clip(Office, 1, 500)]
    public record Product(String name, String category, int price, int stock) {
    }

    // CHALLENGE 1
    // Returns the different categories of the products, without repeated ones. The order does not matter.
    // Example: distinctCategories(shop) returns {Office, Home}
    //          distinctCategories([])   returns {}
    public static Set<String> distinctCategories(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 2
    // Returns the names of the products in the order of the list, in a list that cannot be modified: calling add or
    // remove on the result throws an UnsupportedOperationException.
    // Example: namesUnmodifiable(shop) returns [Pen, Desk, Clip]
    //          namesUnmodifiable([])   returns []
    public static List<String> namesUnmodifiable(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 3
    // Returns a String with the first character of each word, in order, with nothing between them. Every word has at
    // least one character.
    // Example: initials([apple, banana, cherry]) returns "abc"
    //          initials([Kiwi])                   returns "K"
    //          initials([])                       returns ""
    public static String initials(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns how many products have a price greater than or equal to minPrice.
    // Example: countAtLeast(shop, 2)   returns 2
    //          countAtLeast(shop, 1)   returns 3
    //          countAtLeast(shop, 151) returns 0
    public static long countAtLeast(List<Product> products, int minPrice) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns the sum of the stock of all the products. An empty list gives 0.
    // Example: totalStock(shop) returns 605
    //          totalStock([])   returns 0
    public static int totalStock(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns the average price of the products, as a decimal number. An empty list gives 0.0.
    // Example: averagePrice(shop)                              returns 51.0
    //          averagePrice([Pen(Office, 1, 5), Cup(Home, 2, 5)]) returns 1.5
    //          averagePrice([])                                returns 0.0
    public static double averagePrice(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Returns the product with the lowest price, inside an Optional. If two products have the lowest price, the one
    // that comes first in the list is returned. An empty list gives Optional.empty().
    // Example: cheapest(shop)                                   returns Optional[Clip(Office, 1, 500)]
    //          cheapest([Pen(Office, 2, 5), Cup(Home, 2, 7)])   returns Optional[Pen(Office, 2, 5)]
    //          cheapest([])                                     returns Optional.empty
    public static Optional<Product> cheapest(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns the names of the products in a TreeSet: without repeated names and in alphabetical order (the normal
    // order of Strings).
    // Example: sortedUniqueNames([Pen(Office, 2, 100), Desk(Home, 150, 5), Pen(Home, 3, 10)]) returns [Desk, Pen]
    //          sortedUniqueNames([])                                                          returns []
    public static TreeSet<String> sortedUniqueNames(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns a map from the name of each product to its price. Every product has a different name.
    // Example: priceByName(shop) returns {Pen=2, Desk=150, Clip=1}
    //          priceByName([])   returns {}
    public static Map<String, Integer> priceByName(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns a map from the first character of a word to the sum of the lengths of all the words that start with
    // that character. The words can repeat, and uppercase and lowercase characters are different. Every word has at
    // least one character.
    // Example: totalLengthByInitial([apple, avocado, banana]) returns {a=12, b=6}
    //          totalLengthByInitial([ox, Ox, ox])             returns {o=4, O=2}
    //          totalLengthByInitial([])                       returns {}
    public static Map<Character, Integer> totalLengthByInitial(List<String> words) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns how many products each category has.
    // Example: countByCategory(shop) returns {Office=2, Home=1}
    //          countByCategory([])   returns {}
    public static Map<String, Long> countByCategory(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 12
    // Returns, for each category, the names of its products in the order they have in the list.
    // Example: namesByCategory(shop) returns {Office=[Pen, Clip], Home=[Desk]}
    //          namesByCategory([])   returns {}
    public static Map<String, List<String>> namesByCategory(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 13
    // Returns, for each category, the sum of the stock of its products. The result is a TreeMap, so its categories
    // are in alphabetical order (the normal order of Strings).
    // Example: totalStockByCategorySorted(shop) returns {Home=5, Office=600}   (Home comes first)
    //          totalStockByCategorySorted([])   returns {}
    public static TreeMap<String, Integer> totalStockByCategorySorted(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 14
    // Returns, for each category, the average price of its products.
    // Example: averagePriceByCategory(shop) returns {Office=1.5, Home=150.0}
    //          averagePriceByCategory([])   returns {}
    public static Map<String, Double> averagePriceByCategory(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 15
    // Splits the products by price. The key true has the number of products whose price is greater than or equal to
    // limit, and the key false has the number of products with a lower price. Both keys are always in the result,
    // even when their number is 0.
    // Example: countByPriceLimit(shop, 2)    returns {false=1, true=2}
    //          countByPriceLimit(shop, 1000) returns {false=3, true=0}
    //          countByPriceLimit([], 5)      returns {false=0, true=0}
    public static Map<Boolean, Long> countByPriceLimit(List<Product> products, int limit) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 16
    // Returns the statistics of the prices of the products: how many there are, their sum, the lowest price, the
    // highest price and the average. For an empty list the count and the sum are 0.
    // Example: priceStatistics(shop) has count 3, sum 153, min 1, max 150 and average 51.0
    public static IntSummaryStatistics priceStatistics(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 17
    // Returns the value of the inventory: for each product, its price multiplied by its stock, and all of them added
    // together. An empty list gives 0.
    // Example: inventoryValue(shop) returns 1450   (2 * 100 + 150 * 5 + 1 * 500)
    //          inventoryValue([])   returns 0
    public static int inventoryValue(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 18
    // Returns, for each category, the names of the products that have stock (a stock greater than 0), in the order of
    // the list. A category that has products but none of them has stock is still in the result, with an empty list.
    // Example: inStockNamesByCategory([Pen(Office, 2, 100), Desk(Home, 150, 0), Lamp(Home, 30, 4)])
    //              returns {Office=[Pen], Home=[Lamp]}
    //          inStockNamesByCategory([Pen(Office, 2, 100), Desk(Home, 150, 0)])
    //              returns {Office=[Pen], Home=[]}
    public static Map<String, List<String>> inStockNamesByCategory(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 19
    // Returns, for each category, the name of its product with the highest price. If two products of a category have
    // the highest price, the one that comes first in the list is used.
    // Example: priciestNameByCategory(shop) returns {Office=Pen, Home=Desk}
    //          priciestNameByCategory([Pen(Office, 2, 1), Cup(Office, 2, 9)]) returns {Office=Pen}
    //          priciestNameByCategory([]) returns {}
    public static Map<String, String> priciestNameByCategory(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 20
    // Returns the lowest and the highest price of the products as a String "lowest-highest". An empty list gives
    // "none".
    // Example: priceRange(shop)                    returns "1-150"
    //          priceRange([Pen(Office, 2, 100)])   returns "2-2"
    //          priceRange([])                      returns "none"
    public static String priceRange(List<Product> products) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
