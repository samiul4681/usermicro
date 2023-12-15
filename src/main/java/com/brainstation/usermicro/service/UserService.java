package com.brainstation.usermicro.service;

import com.brainstation.usermicro.entity.User;

import java.util.Optional;

public interface UserService {

    public Optional<User> getUserById(Long id);
    public User updateUser(User user);

    public User createUSer(User user);

    public void deleteUser(Long id);
}
