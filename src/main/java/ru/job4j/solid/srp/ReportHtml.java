package ru.job4j.solid.srp;

import java.util.function.Predicate;

public class ReportHtml implements Report {

    private Store store;

    public ReportHtml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder res = new StringBuilder();
        res.append("<!DOCTYPE html>").append(System.lineSeparator());
        res.append("<html lang =\"ru\">").append(System.lineSeparator());
        res.append("<body>").append(System.lineSeparator());
        res.append("Name; Hired; Fired; Salary").append(System.lineSeparator());
        store.findBy(filter).forEach(emp -> {
            res.append(emp.getName()).append(";")
                    .append(emp.getHired()).append(";")
                    .append(emp.getFired()).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(System.lineSeparator());
        });
        res.append("</body>").append(System.lineSeparator());
        res.append("</html>").append(System.lineSeparator());
        return res.toString();
    }
}
