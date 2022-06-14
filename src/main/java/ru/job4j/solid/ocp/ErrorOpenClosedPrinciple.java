package ru.job4j.solid.ocp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;

public class ErrorOpenClosedPrinciple {

    /**
     * Класс Dog & Cat наследует состояние класса Animal из-за этого нарушение OCP, необходимо сделать класс Animal -> abstract и сделать абстрактный метод voice()
     */
    private static class Animal {
        /* some code... */
        public void voice() {
            System.out.println("Animal voice");
        }
    }

    private static class Dog extends Animal {
        @Override
        public void voice() {
            System.out.println("Dog voice");
        }
    }

    private static class Cat extends Animal {
        @Override
        public void voice() {
            System.out.println("Cat voice");
        }
    }

    /**
     * Данный метод нарушает принцип OCP, так как метод возвращает конкретную реализацию HashMap интерфейса Map, в дальнейшем мы не сможешь расширять наш код и гибко использовать данный метод, метод должен возвращать Map<>, так как взаимодействие должно быть через абстракции
     */
    private HashMap<Integer, String> getList() {
        HashMap<Integer, String> res = new HashMap<Integer, String>();
        res.put(1, "test");
        return res;
    }

    /**
     * Данный класс нарушает принцип OCP, так как не имеет возможности расширения, только изменения (создавать новые методы или изменять что-либо внутри класса и в дальнйшем могут возникнуть ошибки)
     * Класс должен имлементировать или наследоваться от абстрактного класса, где заранее прописаны методы, которые мы реализуем
     */
    private static class DataBase {

        private Connection connection;
        private Statement statement;
        private PreparedStatement preparedStatement;

        public DataBase(Connection connection, Statement statement, PreparedStatement preparedStatement) {
            this.connection = connection;
            this.statement = statement;
            this.preparedStatement = preparedStatement;
        }

        public void save() {
            /* save User in data base */
        }
    }


}
