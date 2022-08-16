package ru.job4j.gcdemo.leak;

import ru.job4j.gcdemo.leak.model.Post;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PostStore {

    private final Map<Integer, Post> posts = new HashMap<>();
    private int id;

    public Post add(Post post) {
        post.setId(id++);
        posts.put(id, post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }
}
