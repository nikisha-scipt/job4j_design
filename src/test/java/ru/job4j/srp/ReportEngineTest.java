package ru.job4j.srp;

import org.junit.Test;
import ru.job4j.solid.srp.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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

    @Test
    public void whenJsonReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        Report hr = new ReportJson(store);
        String expect = "[{\"name\":\""
                + worker1.getName()
                + "\",\""
                + "hired\":{\"year\":"
                + now.get(Calendar.YEAR)
                + ",\"month\":"
                + now.get(Calendar.MONTH)
                + ",\"dayOfMonth\":"
                + now.get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":"
                + now.get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":"
                + now.get(Calendar.MINUTE)
                + ",\"second\":"
                + now.get(Calendar.SECOND)
                + "},\"fired\":"
                + "{\"year\":"
                + now.get(Calendar.YEAR)
                + ",\"month\":"
                + now.get(Calendar.MONTH)
                + ",\"dayOfMonth\":"
                + now.get(Calendar.DAY_OF_MONTH)
                + ",\"hourOfDay\":"
                + now.get(Calendar.HOUR_OF_DAY)
                + ",\"minute\":"
                + now.get(Calendar.MINUTE)
                + ",\"second\":"
                + now.get(Calendar.SECOND)
                + "},\"salary\":"
                + worker1.getSalary() + "}]";
        assertThat(hr.generate(em -> true), is(expect));
    }

    @Test
    public void whenXmlReport() throws DatatypeConfigurationException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Danil", now, now, 100);
        store.add(worker);
        Report generator = new ReportXml(store);
        XMLGregorianCalendar date = DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar((GregorianCalendar) now);
        String except = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<empList>\n"
                + "    <empList>\n"
                + "        <fired>" + date + "</fired>\n"
                + "        <hired>" + date + "</hired>\n"
                + "        <name>" + worker.getName() + "</name>\n"
                + "        <salary>" + worker.getSalary() + "</salary>\n"
                + "    </empList>\n"
                + "</empList>\n";
        assertThat(generator.generate(em -> true), is(except));
    }

}