package ru.overthantutor.springCoreSemi1.repository;

import org.springframework.stereotype.Repository;
import ru.overthantutor.springCoreSemi1.model.User;

/**
 * Spring repository which extends DatabaseRepository and keeps users
 */
@Repository
public class UserRepository extends DatabaseRepository<User> {

}
