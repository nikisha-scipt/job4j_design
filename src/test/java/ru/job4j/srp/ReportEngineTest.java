package ru.job4j.srp;

import org.junit.Ignore;
import ru.job4j.solid.srp.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);

        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportHtml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report html = new ReportHtml(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append(System.lineSeparator())
                .append("<html lang =ru>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>")
                .append(System.lineSeparator());
        assertThat(html.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenReportSalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report salary = new ReportSalary(store, 45);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary() * 45).append(";")
                .append(System.lineSeparator());
        assertThat(salary.generate(em -> true), is(expect.toString()));
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
        StringBuilder expect = new StringBuilder();
        expect.append("Name;Salary;").append(System.lineSeparator());
        expect.append(worker2.getName()).append(";");
        expect.append(worker2.getSalary()).append(";");
        expect.append(System.lineSeparator());
        expect.append(worker1.getName()).append(";");
        expect.append(worker1.getSalary()).append(";");
        expect.append(System.lineSeparator());
        assertThat(hr.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenJsonReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        Report hr = new ReportJson(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"").append(worker1.getName())
                .append("\",\"").append("hired\":{\"year\":")
                .append(now.get(Calendar.YEAR)).append(",\"month\":")
                .append(now.get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":")
                .append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":")
                .append(now.get(Calendar.MINUTE))
                .append(",\"second\":")
                .append(now.get(Calendar.SECOND))
                .append("},\"fired\":")
                .append("{\"year\":")
                .append(now.get(Calendar.YEAR))
                .append(",\"month\":")
                .append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":")
                .append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":")
                .append(now.get(Calendar.MINUTE))
                .append(",\"second\":")
                .append(now.get(Calendar.SECOND))
                .append("},\"salary\":").append(worker1.getSalary()).append("}]");
        assertThat(hr.generate(em -> true), is(expect.toString()));
    }

    @Test
    @Ignore
    public void whenXmlReportSb() throws DatatypeConfigurationException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Danil", now, now, 100);
        store.add(worker);
        Report generator = new ReportXml(store);
        XMLGregorianCalendar date = DatatypeFactory
                .newInstance()
                .newXMLGregorianCalendar((GregorianCalendar) now);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append(System.lineSeparator())
                .append("<empList>").append(System.lineSeparator())
                .append("    <empList>").append(System.lineSeparator())
                .append("        <fired>").append(date).append("</fired>").append(System.lineSeparator())
                .append("        <hired>").append(date).append("</hired>").append(System.lineSeparator())
                .append("        <name>").append(worker.getName()).append("</name>").append(System.lineSeparator())
                .append("        <salary>").append(worker.getSalary()).append("</salary>").append(System.lineSeparator())
                .append("    </empList>").append(System.lineSeparator())
                .append("</empList>").append(System.lineSeparator());

        assertThat(generator.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenXmlReportString() throws DatatypeConfigurationException {
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