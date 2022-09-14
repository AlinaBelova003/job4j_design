package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenProperties() {
        String path = "./data/app.properties.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("username")).isEqualTo("Alina");
    }

    @Test
    void whenKayWithoutValue() {
        String path = "./data/kayWithoutValue.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Alina");
    }

    @Test
    void whenIsEmptyProperties() {
        String path = "./data/isEmpty.properties.txt";
        Config config = new Config(path);
        config.load();
    }

    @Test
    void whenKayAndValueAnd1() {
        String path = "./data/kayValue=1.txt";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Alina=1");
    }

}