package entity;

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable {

    private Long IdStudent;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Pass;

    public Student() {

    }

    public Student(Long idStudent, String firstName, String lastName, String email, String pass) {
        IdStudent = idStudent;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Pass = pass;
    }

    public Long getIdStudent() {
        return IdStudent;
    }

    public void setIdStudent(Long idStudent) {
        IdStudent = idStudent;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(IdStudent, student.IdStudent) && Objects.equals(FirstName, student.FirstName) && Objects.equals(LastName, student.LastName) && Objects.equals(Email, student.Email) && Objects.equals(Pass, student.Pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdStudent, FirstName, LastName, Email, Pass);
    }

    @Override
    public String toString() {
        return "Student{" +
                "IdStudent=" + IdStudent +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Pass='" + Pass + '\'' +
                '}';
    }
}
