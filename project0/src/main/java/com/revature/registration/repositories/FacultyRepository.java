package com.revature.registration.repositories;

import com.revature.registration.models.Faculty;

public class FacultyRepository implements CrudRepository<Faculty>{


    @Override
    public Faculty save(Faculty newResource) {
        return null;
    }

    @Override
    public Faculty findById(int id) {
        return null;
    }

    @Override
    public boolean update(Faculty updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
