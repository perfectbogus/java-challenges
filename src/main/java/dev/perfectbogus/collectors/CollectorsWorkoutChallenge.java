package dev.perfectbogus.collectors;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorsWorkoutChallenge {

    // An order has the name of the customer, the city where it was made and its amount. In the examples below, an
    // order is written like this: ana(Lima, 100). This record is already written, do not change it.
    public record Order(String customer, String city, int amount) {
    }

    // CHALLENGE 1
    // Returns the cities of the orders without repeats, in the same order in which each city appears for the first
    // time in the list. An empty list gives an empty set.
    // Example: firstSeenCities([ana(Lima, 100), bob(Quito, 50), cy(Lima, 30)])  returns [Lima, Quito]
    //          firstSeenCities([bob(Quito, 50), ana(Lima, 100)])                 returns [Quito, Lima]
    //          firstSeenCities([])                                               returns []
    public static LinkedHashSet<String> firstSeenCities(List<Order> orders) {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for (Order o : orders) {
            set.add(o.city());
        }
        return set;
    }

    public static LinkedHashSet<String> firstSeenCitiesStream(List<Order> orders) {
        return orders.stream().map(Order::city).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    // CHALLENGE 2
    // Returns the sum of the amounts of all the orders. Each amount is zero or greater, and the sum can be bigger
    // than an int. An empty list gives 0.
    // Example: totalAmount([ana(Lima, 100), bob(Quito, 50)])                        returns 150
    //          totalAmount([ana(Lima, 2000000000), bob(Lima, 2000000000)])          returns 4000000000
    //          totalAmount([])                                                      returns 0
    public static long totalAmount(List<Order> orders) {
        return orders.stream().collect(Collectors.summingLong(Order::amount));
    }

    public static long totalAmountMapped(List<Order> orders) {
        return orders.stream().mapToLong(Order::amount).sum();
    }

    // CHALLENGE 3
    // Returns a map from each city to the number of orders made in that city. The entries of the map are in the same
    // order in which each city appears for the first time in the list. An empty list gives an empty map.
    // Example: orderCountsByCityInOrder([ana(Lima, 1), bob(Quito, 2), cy(Lima, 3)])  returns {Lima=2, Quito=1}
    //          orderCountsByCityInOrder([bob(Quito, 2), ana(Lima, 1)])                returns {Quito=1, Lima=1}
    //          orderCountsByCityInOrder([])                                           returns {}
    public static LinkedHashMap<String, Long> orderCountsByCityInOrder(List<Order> orders) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 4
    // Returns the greatest amount of all the orders. Each amount is zero or greater. If the list is empty, the
    // result is -1.
    // Example: greatestAmount([ana(Lima, 100), bob(Quito, 250), cy(Lima, 30)])  returns 250
    //          greatestAmount([ana(Lima, 0)])                                   returns 0
    //          greatestAmount([])                                               returns -1
    public static int greatestAmount(List<Order> orders) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 5
    // Returns a map from each city to another map, which goes from each customer who made orders in that city to the
    // sum of the amounts of that customer's orders in that city. Only the cities and the customers that appear are in
    // the maps. The sums fit in an int. An empty list gives an empty map.
    // Example: customerTotalsByCity([ana(Lima, 100), bob(Lima, 50), ana(Lima, 20), ana(Quito, 5)])
    //                                                      returns {Lima={ana=120, bob=50}, Quito={ana=5}}
    //          customerTotalsByCity([ana(Rome, 7)])        returns {Rome={ana=7}}
    //          customerTotalsByCity([])                    returns {}
    public static Map<String, Map<String, Integer>> customerTotalsByCity(List<Order> orders) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 6
    // Returns a map from each city to a text with the names of the customers of its orders, in the same order they
    // have in the list, separated by a comma and a space (", "). A customer who made two orders in the same city
    // appears twice. An empty list gives an empty map.
    // Example: customersByCityText([ana(Lima, 1), bob(Quito, 2), cy(Lima, 3)])  returns {Lima=ana, cy, Quito=bob}
    //          customersByCityText([ana(Lima, 1), ana(Lima, 2)])                 returns {Lima=ana, ana}
    //          customersByCityText([])                                           returns {}
    public static Map<String, String> customersByCityText(List<Order> orders) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 7
    // Splits the orders in two groups: the key true has the orders whose amount is "limit" or more, and the key false
    // has the rest. Returns a map with the sum of the amounts of each group. The map always has both keys, and a
    // group without orders has a sum of 0. The sums fit in an int.
    // Example: totalsAboveAndBelow([ana(Lima, 100), bob(Quito, 50), cy(Lima, 200)], 100)  returns {false=50, true=300}
    //          totalsAboveAndBelow([ana(Lima, 10)], 100)                                  returns {false=10, true=0}
    //          totalsAboveAndBelow([], 5)                                                 returns {false=0, true=0}
    public static Map<Boolean, Integer> totalsAboveAndBelow(List<Order> orders, int limit) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 8
    // Returns the city that has the most orders. If two or more cities have the same (highest) number of orders, the
    // one that appears first in the list is chosen. If the list is empty, the result is the text "none".
    // Example: cityWithMostOrders([ana(Lima, 1), bob(Quito, 2), cy(Quito, 3)])  returns "Quito"
    //          cityWithMostOrders([ana(Lima, 1), bob(Quito, 2)])                returns "Lima"
    //          cityWithMostOrders([])                                           returns "none"
    public static String cityWithMostOrders(List<Order> orders) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 9
    // Returns a Collector that can be used with stream.collect(...) on a stream of orders, and that gives the number of
    // orders whose amount is "limit" or more. For an empty stream, the result is 0. Each call to this method returns a
    // collector that can be used on its own.
    // Example: with orders = [ana(Lima, 100), bob(Quito, 50), cy(Lima, 300)]
    //          orders.stream().collect(bigOrderCounter(100))  returns 2
    //          orders.stream().collect(bigOrderCounter(301))  returns 0
    //          Stream.<Order>empty().collect(bigOrderCounter(0))  returns 0
    public static Collector<Order, ?, Long> bigOrderCounter(int limit) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 10
    // Returns a map from each customer to the city of the last order of that customer in the list. Only the customers
    // that appear are in the map. An empty list gives an empty map.
    // Example: lastCityByCustomer([ana(Lima, 1), bob(Quito, 2), ana(Rome, 3)])  returns {ana=Rome, bob=Quito}
    //          lastCityByCustomer([ana(Lima, 1), ana(Lima, 2)])                 returns {ana=Lima}
    //          lastCityByCustomer([])                                           returns {}
    public static Map<String, String> lastCityByCustomer(List<Order> orders) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    // CHALLENGE 11
    // Returns a text with the sum of the amounts of each city, written as city=total and separated by a comma and a
    // space (", "), in the same order in which each city appears for the first time in the list. The sums fit in an
    // int. An empty list gives an empty text.
    // Example: cityTotalsText([ana(Lima, 100), bob(Quito, 50), cy(Lima, 30)])  returns "Lima=130, Quito=50"
    //          cityTotalsText([bob(Quito, 50), ana(Lima, 1)])                  returns "Quito=50, Lima=1"
    //          cityTotalsText([])                                              returns ""
    public static String cityTotalsText(List<Order> orders) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
