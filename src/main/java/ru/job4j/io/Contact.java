package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Contact implements Serializable {
    /**
     * Поле serialVersionUID - уникальный идентификатор версии сериализованного класса.
     * Если в классе что-то меняется, то и оно автоматические меняется
     * Если значения при сериал. и десер. меняются, то вылазит исключение
     */
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.phone = phone;
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Contact{" + "zipCode="
                + zipCode + ", phone='"
                + phone + '\''
                + '}';
    }

    /**
     * Запись объекта во временный файл, который удалится системой
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            ObjectOutputStream oss = new ObjectOutputStream(fos);
            oss.writeObject(contact);
            System.out.println(contact);
        }
        /**
         * Чтение объекта из файла */
        try (FileInputStream input = new FileInputStream(tempFile)) {
            ObjectInputStream ois = new ObjectInputStream(input);
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.println(contactFromFile);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, phone);
    }
}
