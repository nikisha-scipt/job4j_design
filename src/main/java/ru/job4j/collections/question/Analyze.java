package ru.job4j.collections.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int countChange = 0;
        int countDelete = 0;
        int countAdd = 0;
        Info res = new Info(countAdd, countChange, countDelete);
        Map<Integer, String> users = new HashMap<>();
        for (User cur : current) {
            users.put(cur.getId(), cur.getName());
        }
        for (User prev : previous) {
            if (!users.containsKey(prev.getId())) {
                res.setDeleted(++countDelete);
            } else if (!users.get(prev.getId()).equals(prev.getName())) {
                res.setChanged(++countChange);
            }
            users.put(prev.getId(), prev.getName());
        }
        res.setAdded(users.size() - previous.size());

        return res;
    }

}
