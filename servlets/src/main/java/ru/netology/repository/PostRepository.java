package ru.netology.repository;
import ru.netology.model.Post;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Stub
public class PostRepository {
    private AtomicInteger id = new AtomicInteger(1);
    private Map<Long, Post> map = new ConcurrentHashMap<>();

    public List<Post> all() {
        List<Post> posts = new ArrayList<Post>(map.values());
        return posts;
    }

    public Optional<Post> getById(long id) {
        return Optional.empty();
    }

//    public Post save(Post post) {
//        if (map.containsKey(post.getId())) {
//            return map.put(post.getId(), post);
//        } else {
//            long newId = id.incrementAndGet();
//            map.put(newId, post);
//            return post;
//        }
//    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long newId = id.incrementAndGet();
            post.setId(newId);
            map.put(newId, post);
            return post;
        } else {
            return map.put(post.getId(), post);
        }
    }

    public void delete(Post post) {
        map.remove(post.getId());
    }
}
