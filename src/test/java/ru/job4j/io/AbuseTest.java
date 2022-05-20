package ru.job4j.io;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;

public class AbuseTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void drop() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.println("hello foolish dude");
            writer.println("java job4j php");
        }
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish", "php"));
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(sb::append);
        }
        assertThat(sb.toString(), is("hello dude java job4j "));
    }

}