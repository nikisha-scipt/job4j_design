package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void checkValidationNotContainsEquality() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> {
            nameLoad.parse("test", "test2");
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("test")
                .hasMessageContaining("this name: test does not contain the symbol \"=\"");
    }

    @Test
    void checkValidationNotContainsKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> {
            nameLoad.parse("=", "test2");
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: = does not contain a key");
    }

    @Test
    void checkValidationNotContainsValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> {
            nameLoad.parse("d=", "t=12");
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: d= does not contain a value");
    }

}