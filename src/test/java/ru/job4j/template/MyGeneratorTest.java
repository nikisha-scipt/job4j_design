package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.solid.template.Generator;
import ru.job4j.solid.template.MyGenerator;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MyGeneratorTest {

    @Ignore
    @Test
    public void whenNameIsPetr() {
        String template = "I am a/an ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        Generator generator = new MyGenerator();
        String result = generator.produce(template, map);
        String expected = "I am a/an Petr Arsentev. Who are you?.";
        assertThat(result, is(expected));
    }

    @Ignore
    @Test()
    public void whenNameIsDanil() {
        String template = "Hello, my name is ${name}. I am ${age} years old.";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Danil");
        map.put("age", "26");
        Generator generator = new MyGenerator();
        String result = generator.produce(template, map);
        String expected = "Hello, my name is Danil. I am 26 years old.";
        assertThat(result, is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenIllegalArgException() {
        String template = "Hello, my name is ${name}. I am ${age} years old. I live in ${town}.";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Danil");
        map.put("age", "26");
        Generator generator = new MyGenerator();
        String result = generator.produce(template, map);
        String expected = "Hello, my name is Danil. I am 26 years old.I live in Moscow";
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenKeyNotSame() {
        String template = "Hello, my name is ${name}. I am ${age} years old. I live in ${town}.";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Danil");
        map.put("TEST", "26");
        map.put("town", "Moscow");
        Generator generator = new MyGenerator();
        String result = generator.produce(template, map);
        String expected = "Hello, my name is Danil. I am 26 years old.I live in Moscow";
    }

}