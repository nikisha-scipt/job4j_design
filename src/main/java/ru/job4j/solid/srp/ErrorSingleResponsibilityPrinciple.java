package ru.job4j.solid.srp;

public class ErrorSingleResponsibilityPrinciple {

    /**
     * Явное нарушение принципа SRP - класс Math имеет одиин арифметический метод, который выполняет одновременно все операции.
     */
    private static class Math {

        public String operation(int x, int y) {
            String className = Math.class.getSimpleName();
            int sum = x + y;
            int sub = x - y;
            int mult = x * y;
            int div = x / y;
            return String.format("%s have single method and return math operation%n"
                    + "sum:%d%n "
                    + "sub:%d%n "
                    + "mult:%d%n "
                    + "div:%d%n",
                    className, sum, sub, mult, div);
        }
    }

    /**
     * Нарушения принципа SRP - класс MyList должен создавать коллекцию, метод out предназначен для другой цели (выводит на экран, но никак не влиять на коллекцию, по сравнению с другими методами)
     */
    interface MyList<T> {

        boolean add(T value);

        boolean delete(int index);

        int size();

        T findValue(int index);

        T update(int index, T value);

        void out();

    }

    /**
     * класс Product имеет метод - добавления себя в БД, нарушение налицо
     */
    private static class Product {

        private int price;
        private String title;

        public Product(int price, String title) {
            this.price = price;
            this.title = title;
        }

        @Override
        public String toString() {
            return "Product{" + "price=" + price + ", title='" + title + '\'' + '}';
        }

        public void addAtDataBase(Product this) {
        }
    }

}
