package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Stub
@Repository
public class PostRepository {
    private AtomicInteger id = new AtomicInteger(1);
    private Map<Long, Post> map = new ConcurrentHashMap<>();

    //public List<Post> all() {return Collections.emptyList();}

    public List<Post> all() {
        List<Post> posts = new ArrayList<Post>(map.values());
        return posts;
    }

    public Optional<Post> getById(long id) {
        return Optional.empty();
    }

    public Post save(Post post) {
        if (map.containsKey(post.getId())) {
            return map.put(post.getId(), post);
        } else {
            long newId = id.incrementAndGet();
            map.put(newId, post);
            return post;
        }
    }
    public void delete(Post post) {
        map.remove(post.getId());
    }
}
