package com.revature.registration.repositories;

public interface CrudRepository<T> {
    T findById(String id);
    T save(T newResource);
    boolean update(T updatedResource,String field,String newValue);
    boolean deleteById(String id);
}
