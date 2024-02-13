package ru.overthantutor.springCoreSemi1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.overthantutor.springCoreSemi1.model.User;
import ru.overthantutor.springCoreSemi1.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring service class which is manipulating users
 */
@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Reading all data from database repository
     * @return List of user if they exist or null if it is not true
     */
    public List<User> readAll() {
        Optional<List<User>> optional = userRepository.readAllData(User.class);
        if (optional.isPresent()) {
            if (optional.get().isEmpty()) return null;
        }
        return optional.orElse(null);
    }

    /**
     * Saving user into repository
     * @param user saving user
     * @return     saving user if operation is successful or null if it is not true
     */
    public User saveUser(User user) {
        if (userRepository.insertData(user)) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * Deleting user by id
     * @param id user id
     * @return   deleted user or null if it is not exist
     */
    public User deleteById(long id) {
        Optional<User> optional = userRepository.readData(id, User.class);
        if (optional.isPresent()) {
            userRepository.dropData(optional.get());
            return optional.get();
        } else {
            return null;
        }
    }

    /**
     * Updating user
     * @param user user with updated data
     * @return     updated user or null if it is not exists
     */
    public User updateUser(User user) {
        Optional<User> optional = userRepository.readData(user.getId(), User.class);
        if (optional.isPresent()) {
            userRepository.updateData(user);
            return user;
        } else {
            return null;
        }
    }

    /**
     * Getting user by id
     * @param id user id
     * @return   user or null if it is not exist
     */
    public User getUserById(long id) {
        return userRepository.readData(id, User.class).orElse(null);
    }
}
