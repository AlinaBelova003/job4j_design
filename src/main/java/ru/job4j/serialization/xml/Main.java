package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Contact;
import ru.job4j.serialization.json.Person;

import javax.xml.bind.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException {
        Person person = new Person(false, 30, new Contact("11-111"), new String[] {"Worker", "Married"});
        /**
         * JAXBContext предоставляет клиенту точку входа в API JAXB. По умолчанию JAXB не форматирует XML-документ.
         * Чтобы JAXB сериализовал выходные данные -Marshaller. Метод маршала использует объект и выходной файл для хранения сгенерированного XML в качестве параметров.
         * Устанавливаем флаг для читабельного вывода XML в JAXB
         */
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
