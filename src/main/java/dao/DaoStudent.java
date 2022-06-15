package dao;

import connection.DataSource;
import dao.interfaces.IDaoStudent;
import entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoStudent implements IDaoStudent {

    private final static String columnIdStudent = "IdStudent";
    private final static String columnFirstName = "FirstName";
    private final static String columnLastName = "LastName";
    private final static String columnEmail = "Email";
    private final static String columnPass = "Pass";

    private static final String SQLSelect = "SELECT * FROM `student`";
    private static final String SQLInsert = "INSERT INTO `student`(" +
            columnFirstName+
            ","+columnLastName+
            ","+columnEmail+
            ","+columnPass+
            ") VALUES (?, ? ,?, ?)";

    private static final String SQLSelectWhere = "SELECT * FROM `student` WHERE IdStudent = ?";
    private static final String SQLSelectByLastName = "SELECT * FROM `student` WHERE " +columnLastName + "= ?";
    private static final String SQLUpdate = "UPDATE `student` SET " +
            columnFirstName+ "= ?, "+
            columnLastName+"= ?, "+
            columnEmail+"= ?, "+
            columnPass+"= ?, "+
            " WHERE IdStudent = ?";

    private static final String SQLDelete = "DELETE FROM `student` WHERE IdStudent = ?";

    private static final DataSource dataSource = DataSource.getInstance();

    public Student getStudent(ResultSet rs) throws SQLException {
        Long IdStudent = rs.getLong(columnIdStudent);
        String FirstName = rs.getString(columnFirstName);
        String LastName = rs.getString(columnLastName);
        String Email = rs.getString(columnEmail);
        String Pass = rs.getString(columnPass);

        return new Student(IdStudent, FirstName, LastName, Email, Pass);
    }

    public PreparedStatement setStudentParams(PreparedStatement prStm,
                                              String FirstName,
                                              String LastName,
                                              String Email,
                                              String Pass) throws SQLException {

        prStm.setString(1, FirstName);
        prStm.setString(2, LastName);
        prStm.setString(3, Email);
        prStm.setString(4, Pass);

        return prStm;

    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<Student>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                students.add(getStudent(rs));
            }

        }
        catch(SQLException e){
            System.err.print(e.getMessage()+"\n");
        }
        finally{
            if(rs != null){
                try {
                    rs.close();
                }
                catch(SQLException e){
                    System.err.print(e.getMessage()+"\n");
                }
            }
            else{
                System.out.print("Student table is empty.\n");
            }
        }
        return students;
    }

    @Override
    public int createStudent( String FirstName,
                              String LastName,
                              String Email,
                              String Pass) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setStudentParams(prStm, FirstName, LastName, Email, Pass);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
    }

    @Override
    public Student findStudentById(Long IdStudent) {

        Student student = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setLong(1, IdStudent);
            rs = prStm.executeQuery();

            rs.next();
            student = getStudent(rs);

        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs != null){
                try {
                    rs.close();
                }
                catch(SQLException e){
                    System.err.print(e.getMessage() + "\n");
                }
            }
            else{
                System.out.print("There is no student with that id.\n");
            }
        }

        return student;
    }

    @Override
    public int updateStudentById(Long IdStudent, Student newStudent) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setStudentParams(prStm, newStudent.getFirstName(), newStudent.getLastName(), newStudent.getEmail(),
                    newStudent.getPass());
            prStm.setLong(5, IdStudent);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no student with that id.\n");
            }
        }
        return rs;
    }

    @Override
    public int deleteStudentById(Long IdStudent) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLDelete)){

            prStm.setLong(1, IdStudent);
            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        return rs;
    }


}
