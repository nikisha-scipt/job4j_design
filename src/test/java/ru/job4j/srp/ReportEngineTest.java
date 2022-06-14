package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        String expect = "Name; Hired; Fired; Salary;"
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() + ";"
                + System.lineSeparator();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenReportHtml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report html = new ReportHtml(store);
        String expect = "<!DOCTYPE html>" + System.lineSeparator()
               + "<html lang =\"ru\">" + System.lineSeparator()
               + "<body>" + System.lineSeparator()
               + "Name; Hired; Fired; Salary" + System.lineSeparator()
               + worker.getName() + ";"
               + worker.getHired() + ";"
               + worker.getFired() + ";"
               + worker.getSalary() + ";"
               + System.lineSeparator()
                + "</body>" + System.lineSeparator()
               + "</html>" + System.lineSeparator();
        assertThat(html.generate(em -> true), is(expect));
    }

    @Test
    public void whenReportSalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report salary = new ReportSalary(store, 45);
        String expect = "Name; Hired; Fired; Salary;"
                + worker.getName() + ";"
                + worker.getHired() + ";"
                + worker.getFired() + ";"
                + worker.getSalary() * 45 + ";"
                + System.lineSeparator();
        assertThat(salary.generate(em -> true), is(expect));
    }

    @Test
    public void whenReportHr() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Anton", now, now, 150);
        store.add(worker1);
        store.add(worker2);
        Report hr = new ReportHr(store);
        String expect = "Name;Salary;" + System.lineSeparator()
                + worker2.getName() + ";"
                + worker2.getSalary() + ";"
                + System.lineSeparator()
                + worker1.getName() + ";"
                + worker1.getSalary() + ";"
                + System.lineSeparator();
        assertThat(hr.generate(em -> true), is(expect));
    }

}