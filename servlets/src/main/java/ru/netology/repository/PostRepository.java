package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Stub
public class PostRepository {
  private AtomicInteger id = new AtomicInteger(1);
  private Map<Integer, Post> map = new ConcurrentHashMap<>();
  public List<Post> all() {return Collections.emptyList();}
  public Optional<Post> getById(long id) {return Optional.empty();}
  public Post save (Post post) {
    int newId = id.incrementAndGet();
    map.put(newId, post);
    return post;
  }
  public void resolveById (long id) {
//
  }
  public Post update(Post post, String content) {
    post.setContent(content);
    map.put((int) post.getId(), post);
    return post;
  }
  public void delete(Post post) {
    map.remove((int) post.getId());
  }
}
