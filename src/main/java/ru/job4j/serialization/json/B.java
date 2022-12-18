package ru.job4j.serialization.json;

import org.json.JSONObject;

/**
 * При преобразовании объектов в json-строку возможно рекурсивное зацикливание, когда объект A содержит ссылку на объект B,
 * а он в свою очередь ссылается на первоначальный объект A.
 * При выполнении будут осуществляться переходы по ссылке и сериализация до возникновения исключения StackOverflowError.
 * Чтобы избежать исключения нужно разорвать цепочку, это делается в момент перехода по ссылке на объект, который уже сериализован.
 * В библиотеке JSON-Java (org.json) для этого существует аннотация @JSONPropertyIgnore (КЛАСС А)
 */
public class B {
    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.setB(b);
        b.setA(a);
        System.out.println(new JSONObject(b));

    }
}
