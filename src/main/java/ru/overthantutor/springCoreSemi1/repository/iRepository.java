package ru.overthantutor.springCoreSemi1.repository;

import java.util.List;
import java.util.Optional;

/**
 * This interface provide abstract class which can help control data in any storage
 * @param <T> any data
 */
public interface iRepository<T> {
    /**
     * Insertion data
     * @param data any data (some params)
     * @return     true or false in dependence of execution insertion operation
     */
    boolean insertData(T... data);

    /**
     * Insertion data
     * @param data list of data
     * @return     true or false in dependence of execution insertion operation
     */
    boolean insertData(List<T> data);

    /**
     * Update data
     * @param data some data (some params)
     * @return     true or false in dependence of execution updating operation
     */
    boolean updateData(T... data);

    /**
     * Reading data by id
     * @param id    data id
     * @param clazz class type of T class
     * @return      Optional<T> with null if data doesn't exist or with data if it exists
     */
    Optional<T> readData(long id, Class<T> clazz);

    /**
     * Reading all data
     * @param clazz class type of T class
     * @return      Optional<T> with null if data doesn't exist or with list of data if it exists
     */
    Optional<List<T>> readAllData(Class<T> clazz);

    /**
     * Dropping data
     * @param data some data (some params)
     * @return     true or false in dependence of execution dropping operation
     */
    boolean dropData(T... data);
}
