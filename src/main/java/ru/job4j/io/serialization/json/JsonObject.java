package ru.job4j.io.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.io.serialization.json.taskjson.Animal;
import ru.job4j.io.serialization.json.taskjson.Tiger;

import java.util.ArrayList;
import java.util.List;

public class JsonObject {

    public static void main(String[] args) {
        final Tiger tiger = new Tiger(false, 5, "lion", new Animal("Cat"), new String[] {"Bangladesh", "Vietnam", "India"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isFly", tiger.isFly());
        jsonObject.put("age", tiger.getAge());
        jsonObject.put("name", tiger.getName());
        jsonObject.put("type", tiger.getType());
        jsonObject.put("location", tiger.getLocation());
        System.out.println(jsonObject);
        System.out.println(new JSONObject(tiger));

        /* JSONObject из json-строки строки */
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        System.out.println(jsonContact);

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);
        System.out.println(jsonStatuses);
    }

}
