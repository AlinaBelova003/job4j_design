package ru.job4j.serialization.json;

public class Contact {
    public Contact(String phone) {
        this.phone = phone;
    }

    private final String phone;

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}
