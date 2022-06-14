package ru.job4j.solid.srp;

import java.util.function.Predicate;

public class ReportSalary implements Report {

    private Store store;
    private double matches;

    public ReportSalary(Store store, double matches) {
        this.store = store;
        this.matches = matches;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder res = new StringBuilder();
        res.append("Name; Hired; Fired; Salary;");
        store.findBy(filter).forEach(emp -> {
            double resSal = newSalary(emp);
            emp.setSalary(resSal);
            res.append(emp.getName()).append(";")
                    .append(emp.getHired()).append(";")
                    .append(emp.getFired()).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(System.lineSeparator());
        });
        return res.toString();
    }

    private double newSalary(Employee emp) {
        return matches * emp.getSalary();
    }


}
