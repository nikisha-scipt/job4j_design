package ru.job4j.solid.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportXml implements Report {

    private final Store store;

    public ReportXml(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = null;
        try {
            List<Employee> employeeList = store.findBy(filter);
            JAXBContext context = JAXBContext.newInstance(EmpList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            xml = "";
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new EmpList(employeeList), writer);
                xml = writer.getBuffer().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }


    @XmlRootElement(name = "empList")
    private static class EmpList {

        private List<Employee> empList;

        public EmpList() {
        }

        public EmpList(List<Employee> empList) {
            this.empList = empList;
        }

        public List<Employee> getEmpList() {
            return empList;
        }

        public void setEmpList(List<Employee> empList) {
            this.empList = empList;
        }

    }
}
