package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Danil nikishin";
        int age = 26;
        LOG.debug("User info name: {}, age: {}", name, age);

        byte one = 1;
        short two = 2;
        int three = 3;
        long four = 4;
        float five = 5.0f;
        double six = 6.0;
        char seven = 'g';
        boolean eight = true;
        LOG.debug("byte: {}, short: {}, int: {}, long: {}, float: {}, double: {}, char: {}, boolean: {}", one, two, three, four, five, six, seven, eight);

        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }

    }

}
