package com.revature.registration.repositories;

public interface CrudRepository<T> {
    T findById(int id);
    T save(T newResource);
    boolean update(T updatedResource);
    boolean deleteById(int id);
}
