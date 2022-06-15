package dao.interfaces;

import entity.Student;

import java.util.List;

public interface IDaoStudent {

    List<Student> findAll();

    int createStudent(String FirstName,
                      String LastName,
                      String Email,
                      String Pass);

    Student findStudentById(Long IdStudent);

    int updateStudentById(Long IdStudent, Student newStudent);

    int deleteStudentById(Long IdStudent);



}
