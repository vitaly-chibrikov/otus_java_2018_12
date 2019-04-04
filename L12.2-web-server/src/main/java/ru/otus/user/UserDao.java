package ru.otus.user;

public interface UserDao {

    User save(User user);

    User findByName(String name);

}