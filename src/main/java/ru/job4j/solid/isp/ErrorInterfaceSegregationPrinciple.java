package ru.job4j.solid.isp;

public class ErrorInterfaceSegregationPrinciple {

    /**
     * Данный интерфейс с нарушением принципа ISP, так как имеет метод вывода данных и никоим образом не влияет на саму реализацию List
     */
    interface List<T> {
        boolean add(T value);
        boolean contains(T value);
        boolean remove(int index);
        int indexOf(T value);

        void out();
    }

    /**
     * Нарушение принципа ISP -> при создании отдельного надводного необходимо будет так же реализовывать создание и других конструкций
     */
    interface Ship {
        void createShip();
        void createSubmarine();
        void boat();
    }

    /**
     * Нарушение принципа ISP -> слишком много методов в интерфейсе, некорректное выделение абтсракций
     */
    interface House {
        void createDoor();
        void createElevator();
        List<?> createFlat();
        void createWindow();
        void createBasement();
        void createRoof();
        void createHood();
        void createArea();
    }

}
