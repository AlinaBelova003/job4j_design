package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

/**
 * Работа c JAXB - способ записи объектов в xml
 *  xml обязательно должен иметь корневой тег, в котором все и будет располагаться.
 * @XmlRootElement. нужно ставить над сущностью, которая будет корневой в нашем случае это Person
 * Мы можем указать как мы хотим читать/писать объект.
 * @XmlAccessorType - читать информацию будем напрямую через поля(можно еще через геттеры и сеттеры)
 * Для того чтобы поле считалось атрибутом нужно поставить  @XmlAttribute, по умолчанию поле парсится как тег
 */
@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)

public class Person {
    @XmlAttribute
    private boolean sex;
    private  int age;
    private Contact contact;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private  String[] status;

    public Person() {

    }

    public Person(boolean sex, int age, Contact contact, String[] status) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", status=" + Arrays.toString(status)
                + '}';
    }
}
