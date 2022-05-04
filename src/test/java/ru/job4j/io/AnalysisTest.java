package ru.job4j.io;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import java.io.*;


public class AnalysisTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private Analysis analysis;

    @Before
    public void init() {
        analysis = new Analysis();
    }


    @Test
    public void whenHowLongErrorGoOne() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("500 10:59:01");
            writer.println("400 11:01:02");
            writer.println("200 11:02:02");
        }
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(stringBuilder::append);
        }
        assertThat(stringBuilder.toString(), is("Результат:10:57:01;11:02:02;"));
    }

    @Test
    public void whenHowLongErrorGoTwo() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("200 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
        }
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(stringBuilder::append);
        }
        assertThat(stringBuilder.toString(), is("Результат:10:57:01;10:59:01;11:01:02;11:02:02;"));
    }

}