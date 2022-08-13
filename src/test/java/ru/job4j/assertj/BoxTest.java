package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEqualTo("Tetrahedron")
                .isNotEqualTo("Cube")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEqualTo("Sphere")
                .isNotEqualTo("Cube")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(6, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEqualTo("Tetrahedron")
                .isNotEqualTo("Sphere")
                .isEqualTo("Cube");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(-1, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEqualTo("Tetrahedron")
                .isNotEqualTo("Sphere")
                .isNotEqualTo("Cube")
                .isEqualTo("Unknown object");
    }

    @Test
    void isThisUnknownObjectWhenEdgeMinusOne() {
        Box box = new Box(4, -1);
        String name = box.whatsThis();
        System.out.println(name);
        assertThat(name).isNotNull()
                .isNotEqualTo("Tetrahedron")
                .isNotEqualTo("Sphere")
                .isNotEqualTo("Cube")
                .isEqualTo("Unknown object");
    }

    @Test
    void isExist() {
        Box box = new Box(6, 2);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(5, 2);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void whenAreaVertexIsDefault() {
        Box box = new Box(5, 2);
        double result = box.getArea();
        assertThat(result).isNotNull()
                .isEqualTo(0);
    }

    @Test
    void whenAreaVertexIs0() {
        Box box = new Box(0, 2);
        double result = box.getArea();
        assertThat(result).isEqualTo(50.26d, withPrecision(0.006d))
                .isGreaterThan(49.25d)
                .isLessThan(51.26d);
    }

    @Test
    void whenAreaVertexIs4() {
        Box box = new Box(4, 2);
        double result = box.getArea();
        assertThat(result).isEqualTo(6.928d, withPrecision(0.006d))
                .isGreaterThan(5.25d)
                .isLessThan(7.26d);
    }

    @Test
    void whenAreaVertexIs6() {
        Box box = new Box(6, 2);
        double result = box.getArea();
        System.out.println(result);
        assertThat(result).isEqualTo(24.0d, withPrecision(0.006d))
                .isGreaterThan(5.25d)
                .isLessThan(27.26d);
    }



}