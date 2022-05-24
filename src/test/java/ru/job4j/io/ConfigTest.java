package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./src/main/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
    }


    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithMoreValue() {
        String path = "./data/testMoreValue.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"), is("value"));
    }

    @Test
    public void whenValueHave14() {
        String path = "./data/testProperties.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key"), is("value=14"));
    }

}