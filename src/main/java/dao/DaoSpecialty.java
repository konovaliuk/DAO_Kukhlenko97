package dao;

import connection.DataSource;
import dao.interfaces.IDaoSpecialty;
import entity.Specialty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoSpecialty implements IDaoSpecialty {

    private final static String columnIdSpecialty = "IdSpecialty";
    private final static String columnSpecialtyName = "SpecialtyName";
    private final static String columnIndustry = "Industry";
    private final static String columnSpecialtyCode = "SpecialtyCode";
    private final static String columnIndustryCode = "IndustryCode";

    private static final String SQLSelect = "SELECT * FROM `specialty`";
    private static final String SQLInsert = "INSERT INTO `specialty`(" +
            columnSpecialtyName+
            ","+columnIndustry+
            ","+columnSpecialtyCode+
            ","+columnIndustryCode+
            ") VALUES (?, ? ,?, ?)";

    private static final String SQLSelectWhere = "SELECT * FROM `specialty` WHERE IdSpecialty = ?";
    private static final String SQLUpdate = "UPDATE `specialty` SET " +
            columnSpecialtyName+ "= ?, "+
            columnIndustry+"= ?, "+
            columnSpecialtyCode+"= ?, "+
            columnIndustryCode+"= ?, "+
            " WHERE IdSpecialty = ?";

    private static final String SQLDelete = "DELETE FROM `specialty` WHERE IdSpecialty = ?";

    private static final DataSource dataSource = DataSource.getInstance();

    public Specialty getSpecialty(ResultSet rs) throws SQLException {
        Long IdSpecialty = rs.getLong(columnIdSpecialty);
        String SpecialtyName = rs.getString(columnSpecialtyName);
        String Industry = rs.getString(columnIndustry);
        String SpecialtyCode = rs.getString(columnSpecialtyCode);
        String IndustryCode = rs.getString(columnIndustryCode);

        return new Specialty(IdSpecialty, SpecialtyName, Industry, SpecialtyCode, IndustryCode);
    }

    public PreparedStatement setSpecialtyParams(PreparedStatement prStm,
                                                String SpecialtyName,
                                                String Industry,
                                                String SpecialtyCode,
                                                String IndustryCode) throws SQLException {

        prStm.setString(1, SpecialtyName);
        prStm.setString(2, Industry);
        prStm.setString(3, SpecialtyCode);
        prStm.setString(4, IndustryCode);

        return prStm;

    }

    @Override
    public List<Specialty> findAll() {
        List<Specialty> specialtys = new ArrayList<Specialty>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                specialtys.add(getSpecialty(rs));
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
                System.out.print("Specialty table is empty.\n");
            }
        }
        return specialtys;
    }

    @Override
    public int createSpecialty( String SpecialtyName,
                                String Industry,
                                String SpecialtyCode,
                                String IndustryCode) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setSpecialtyParams(prStm, SpecialtyName, Industry, SpecialtyCode, IndustryCode);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
    }

    @Override
    public Specialty findSpecialtyById(Long IdSpecialty) {

        Specialty specialty = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setLong(1, IdSpecialty);
            rs = prStm.executeQuery();

            rs.next();
            specialty = getSpecialty(rs);

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
                System.out.print("There is no specialty with that id.\n");
            }
        }

        return specialty;
    }

    @Override
    public int updateSpecialtyById(Long IdSpecialty, Specialty newSpecialty) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setSpecialtyParams(prStm, newSpecialty.getSpecialtyName(), newSpecialty.getIndustry(), newSpecialty.getSpecialtyCode(),
                    newSpecialty.getIndustryCode());
            prStm.setLong(5, IdSpecialty);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no specialty with that id.\n");
            }
        }
        return rs;
    }

    @Override
    public int deleteSpecialtyById(Long IdSpecialty) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLDelete)){

            prStm.setLong(1, IdSpecialty);
            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        return rs;
    }

}
