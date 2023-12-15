package com.brainstation.usermicro.service;


import com.brainstation.usermicro.configuration.PropertiesConfig;
import com.brainstation.usermicro.entity.User;
import com.brainstation.usermicro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Optional<User> getUserById(Long id) {

        Optional<User> userData = userRepository.findById(id);

        sendKafkaMessage("get a user by id="+id);
        return userData;
    }

    @Override
    public User updateUser(User user) {

        sendKafkaMessage("update user");
       return userRepository.save(user);
    }

    @Override
    public User createUSer(User user) {

        sendKafkaMessage("creating user");
       User userData= userRepository.save(user);
       return userData;
    }

    @Override
    public void deleteUser(Long id) {

        sendKafkaMessage("delete user by id="+id);
            userRepository.deleteById(id);
    }

    public void sendKafkaMessage(String msg) {

        kafkaTemplate.send(propertiesConfig.getTopic(), msg);

    }
}
