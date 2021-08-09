package com.revature.registration.repositories;

public class CourseRepository implements CrudRepository{
    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public Object save(Object newResource) {
        return null;
    }

    @Override
    public boolean update(Object updatedResource, String field, String newValue) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
