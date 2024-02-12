package ru.overthantutor.springCoreSemi1.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class which provide User substance
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    /**
     * User id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    /**
     * User firstname
     */
    @Column(name = "firstname")
    private String firstname;
    /**
     * User lastname
     */
    @Column(name = "lastname")
    private String lastname;
}
