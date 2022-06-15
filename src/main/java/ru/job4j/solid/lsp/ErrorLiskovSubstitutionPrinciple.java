package ru.job4j.solid.lsp;

public class ErrorLiskovSubstitutionPrinciple {

    /**
     * Нарушает принцип LSP, так как в классе-наследнике необходимо усиления предусловий
     */
    private static class Man {

        protected int countHead;
        protected String type;

        public Man(int countHead) {
            this.countHead = countHead;
        }

        public void checkMan() {
            if (countHead != 1) {
                throw new IllegalArgumentException("It is not man");
            } else {
                this.type = "Man";
            }
        }

    }

    private static class Orc extends Man {

        public Orc(int countHead) {
            super(countHead);
        }

        public void checkOrc() {
            /* if (countHead != 2) {
                throw new IllegalArgumentException("It is not Orc");
            } else {
                type = "Orc";
            } */
        }
    }

    /**
     * Нарушен прицнип LPS, поскольку в классе-наследнике BadStudent опущена проверка на компетенцию студента, что дает ему возможность не посещать заниятия и быть компетентным учеником
     */
    private static class Clazz {

        int begin;
        int hours;
        String name;

        public Clazz(int begin, int hours, String name) {
            this.begin = begin;
            this.hours = hours;
            this.name = name;
        }

        private static class Student {

            String name;
            int hourBeginStudy;
            int avgGrade;
            boolean competence;
            Clazz math;

            public Student(String name, int hourBeginStudy, int avgGrade, Clazz math) {
                this.name = name;
                this.hourBeginStudy = hourBeginStudy;
                this.avgGrade = avgGrade;
                this.math = math;
            }

            public void checkStudy() {
                competence = hourBeginStudy == math.begin;
                if (!competence) {
                    throw new IllegalArgumentException("Student is not cool!");
                } else {
                    System.out.println("Student is cool!");
                }
            }
        }

        private static class BadStudent extends Student {

            public BadStudent(String name, int hourBeginStudy, int avgGrade, Clazz math) {
                super(name, hourBeginStudy, avgGrade, math);
            }

            @Override
            public void checkStudy() {
                System.out.println("Student is cool!");
            }
        }
    }

    /**
     * Нарушение принципа LPS, так как в классе-наследнике badCar ослаблена постусловие,значит машина с небольшой мощностью будет мощной
     */
    private static class Transport {
        int power;

        public Transport(int power) {
            this.power = power;
        }

        private static class Car extends Transport {
            int power = 20;

            public Car(int power) {
                super(power);
            }

            public void check() {
                if (power < 10) {
                    throw new IllegalArgumentException("It is a little car");
                }
                System.out.println("Strong car!");
            }
        }

        private static class BadCar extends Car {
            int power = 8;

            public BadCar(int power) {
                super(power);
            }

            public void check() {
                if (power < 5) {
                    throw new IllegalArgumentException("It is a little car");
                }
                System.out.println("Strong car!");
            }
        }
    }

}
