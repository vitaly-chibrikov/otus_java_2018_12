package ru.otus.user;

import java.util.Optional;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean authenticate(String name, String password) {
        return Optional.ofNullable(userDao.findByName(name))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
