package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportHr implements Report {

    private Store store;

    public ReportHr(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder res = new StringBuilder();
        List<Employee> temp = store.findBy(filter);
        temp.sort((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()));
        res.append("Name;Salary;").append(System.lineSeparator());
        temp.forEach(emp -> {
            res.append(emp.getName()).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(System.lineSeparator());
        });
        return res.toString();
    }
}
