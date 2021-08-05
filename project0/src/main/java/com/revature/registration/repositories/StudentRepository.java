package com.revature.registration.repositories;

import com.revature.registration.models.Student;

public class StudentRepository implements CrudRepository<Student>{
    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public Student save(Student newResource) {
        return null;
    }

    @Override
    public boolean update(Student updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
