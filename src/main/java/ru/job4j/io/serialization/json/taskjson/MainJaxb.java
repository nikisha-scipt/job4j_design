package ru.job4j.io.serialization.json.taskjson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class MainJaxb {

    public static void main(String[] args) throws JAXBException, IOException {
        final Tiger tiger = new Tiger(false, 5, "lion", new Animal("Cat"), new String[] {"Bangladesh", "Vietnam", "India"});
        JAXBContext context = JAXBContext.newInstance(Tiger.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(tiger, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Tiger result = (Tiger) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }

}
