package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    public void isThisSphere() {
        Box box = new Box(0, 12);
        String name = box.whatsThis();
        assertThat(name).
                isNotBlank()
                .isEqualTo("Sphere");
    }

    @Test
    public void isThisExist() {
        Box box = new Box(-1,  0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    public void whenNumberVertexIsNull() {
        Box box = new Box(0, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(0);
    }

    @Test
    public void whenVertexNegative() {
        Box box = new Box(-1, 10);
        boolean vertex = box.isExist();
        assertThat(vertex).isEqualTo(false);
    }

    @Test
    public void whenVertexNotExist() {
        Box box = new Box(4, 10);
        boolean vertex = box.isExist();
        assertThat(vertex).isEqualTo(true);
    }

    @Test
    public void whenTetrahedronIsTrue() {
        Box box = new Box(4, 15);
        double square = box.getArea();
        assertThat(square).isEqualTo(389.71d, withPrecision(0.006d))
                .isGreaterThan(388)
                .isLessThan(390);
    }

    @Test
    public void whenCudeTrue() {
        Box box = new Box(8, 15);
        double square = box.getArea();
        assertThat(square).isEqualTo(1350.0d, withPrecision(0.003d));
    }
}