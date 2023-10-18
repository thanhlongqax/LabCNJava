package org.example.Repository;

import org.example.Models.*;
public interface UserRepository {
    public boolean add(User user);
    public User findByEmail(String email);
}