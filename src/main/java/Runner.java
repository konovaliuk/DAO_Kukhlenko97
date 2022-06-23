import connection.DataSource;
import dao.DAOFactory;
import dao.interfaces.IDaoSpecialty;
import dao.interfaces.IDaoStudent;
import dao.interfaces.IDaoSubj;
import dao.interfaces.IDaoUniver;
import entity.Specialty;
import entity.Student;
import entity.Subj;
import entity.Univer;

import java.sql.SQLException;
import java.util.List;

public class Runner {

    public static void main(String[] args){

        IDaoStudent daoStudent = DAOFactory.createDaoStudent();

        List<Student> allStudents = daoStudent.findAll();
        System.out.print("All students: \n");
        for (Student allStudent : allStudents) {
            System.out.format("%s\t%s\n", allStudent.getFirstName(), allStudent.getLastName());
        }


        System.out.print("\ncreateStudent() function: \n");
        int rs1 = daoStudent.createStudent("Jimmy", "Gold", "grego@gmail.com",
                "qwerty1234");
        System.out.format("Created students = %d\n", rs1);

        System.out.print("\nfindStudentById() function: \n");
        Student student = daoStudent.findStudentById(11L);
        System.out.format("%s\t%s\n", student.getFirstName(), student.getLastName());

        System.out.print("\ndeleteStudentById() function: \n");
        int rs3 = daoStudent.deleteStudentById(11L);
        System.out.format("Deleted students = %d\n", rs3);

        System.out.print(" \n");

        IDaoSubj daoSubj = DAOFactory.createDaoSubj();

        List<Subj> allSubjs = daoSubj.findAll();
        System.out.print("All Subjects: \n");
        for (Subj allSubj : allSubjs) {
            System.out.format("%s\n", allSubj.getSubName());
        }

        System.out.print(" \n");

        IDaoUniver daoUniver = DAOFactory.createDaoUniver();

        List<Univer> allUnivers = daoUniver.findAll();
        System.out.print("All universities: \n");
        for (Univer allUniver : allUnivers) {
            System.out.format("%s\t%s\n", allUniver.getUniverName(), allUniver.getCity());
        }

        System.out.print(" \n");

        IDaoSpecialty daoSpecialty = DAOFactory.createDaoSpecialty();

        List<Specialty> allSpecialtys = daoSpecialty.findAll();
        System.out.print("All specialtys: \n");
        for (Specialty allSpecialty : allSpecialtys) {
            System.out.format("%s\t%s\n", allSpecialty.getSpecialtyName(), allSpecialty.getSpecialtyCode());
        }

        try{
            DataSource.closeConnection();
            System.out.print("\nConnection was successfully closed\n");
        }
        catch (SQLException e){
            System.err.print(e.getMessage());
        }



    }

}
