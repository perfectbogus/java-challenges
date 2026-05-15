package dev.perfectbogus.performance.finalkeyword;

public class FinalKeyword {

    public static void main(String[] args) {
        final Customer c = new Customer("John");
        System.out.println(c.getName());
        System.out.println(c.getName());
    }

    private static class Customer {
        private String name;

        Customer(String name) {
            this.name = name;
        }

        public String getName() {
            String tmp = this.name;
            this.name = "XXX";
            return tmp;
        }
    }
}
