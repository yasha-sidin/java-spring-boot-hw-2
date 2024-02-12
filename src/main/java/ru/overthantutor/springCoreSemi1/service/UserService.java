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
        return optional.orElse(List.of());
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

    public User deleteById(long id) {
        Optional<User> optional = userRepository.readData(id, User.class);
        if (optional.isPresent()) {
            userRepository.dropData(optional.get());
            return optional.get();
        } else {
            return null;
        }
    }
}
