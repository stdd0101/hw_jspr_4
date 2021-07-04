package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public class PostRepository {
  private int id = 1;
  private Map<Integer, Post> map = new HashMap<>();
  public List<Post> all() {return Collections.emptyList();}
  public Optional<Post> getById(long id) {return Optional.empty();}
  public synchronized Post save (Post post) {
    id++;
    map.put(id, post);
    return post;
  }
  public synchronized void resolveById (long id) {

  }

}
