package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> bothPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        boolean outOrContinue = false;
        String user = null;
        while (!OUT.equals(user)) {
            user = getString();
            String bot = "Bot send a message: " +  bothPhrases.get((int) (Math.random() * bothPhrases.size()));
            outOrContinue = outOrContinue ? !CONTINUE.equals(user) : STOP.equals(user);
            if (!outOrContinue) {
                System.out.println(bot);
                log.add(user);
                log.add(bot);
            }
        }
        System.out.println(sendBye(bothPhrases));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> res = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                res.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8"), true))) {
            for (String elem : log) {
                writer.println(elem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sendBye(List<String> str) {
        String res = "";
        for (String elem : str) {
            if ("Bye!".equals(elem)) {
                res = elem;
                break;
            }
        }
        return res;
    }

    private String getString() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        return bufferedReader.readLine();
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("./garbage/logChatWithBot.txt", "./garbage/botAnswer.txt");
        cc.run();
    }

}
