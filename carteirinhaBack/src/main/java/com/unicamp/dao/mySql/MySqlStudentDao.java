package com.unicamp.dao.mySql;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.unicamp.dao.StudentDao;
import com.unicamp.entity.Student;

/*
@Repository
public class MySqlStudentDao implements StudentDao {
    private static Map<Integer, Student> students;

    @Override
    public Collection<Student> getAllStudents() {
        return this.students.values();
    }

    @Override
    public Student getStudentById(Integer id) {
        return this.students.get(id);
    }

    @Override
    public void removeStudentById(Integer id) {
        this.students.remove(id);
    }

    @Override
    public void insertStudentToDb(Student student) {
        this.students.put(student.getRa(), student);
    }

    @Override
    public <S extends Student> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Student> Iterable<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Student> findById(Long id) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterable<Student> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<Student> findAllById(Iterable<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Student entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll(Iterable<? extends Student> entities) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }
}
*/