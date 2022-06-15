package dao;

import connection.DataSource;
import dao.interfaces.IDaoSubj;
import entity.Subj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoSubj implements IDaoSubj {

    private final static String columnIdSubj = "IdSubj";
    private final static String columnSubName = "SubName";

    private static final String SQLSelect = "SELECT * FROM `subj`";
    private static final String SQLInsert = "INSERT INTO `subj`(" +
            columnSubName +
            ") VALUES (?)";

    private static final String SQLSelectWhere = "SELECT * FROM `subj` WHERE IdSubj = ?";
    private static final String SQLUpdate = "UPDATE `student` SET " +
            columnSubName + "= ?, "+
            " WHERE IdStudent = ?";

    private static final String SQLDelete = "DELETE FROM `subj` WHERE IdSubj = ?";

    private static final DataSource dataSource = DataSource.getInstance();

    public Subj getSubj(ResultSet rs) throws SQLException {
        Long IdSubj = rs.getLong(columnIdSubj);
        String SubName = rs.getString(columnSubName);

        return new Subj(IdSubj, SubName);
    }

    public PreparedStatement setSubjParams(PreparedStatement prStm,
                                           String SubName) throws SQLException {

        prStm.setString(1, SubName);

        return prStm;

    }

    @Override
    public List<Subj> findAll() {
        List<Subj> subjs = new ArrayList<Subj>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                subjs.add(getSubj(rs));
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
                System.out.print("Subject table is empty.\n");
            }
        }
        return subjs;
    }

    @Override
    public int createSubj( String SubName) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setSubjParams(prStm, SubName);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
    }

    @Override
    public Subj findSubjById(Long IdSubj) {

        Subj subj = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setLong(1, IdSubj);
            rs = prStm.executeQuery();

            rs.next();
            subj = getSubj(rs);

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
                System.out.print("There is no subject with that id.\n");
            }
        }

        return subj;
    }

    @Override
    public int updateSubjById(Long IdSubj, Subj newSubj) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setSubjParams(prStm, newSubj.getSubName());
            prStm.setLong(2, IdSubj);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no subject with that id.\n");
            }
        }
        return rs;
    }

    @Override
    public int deleteSubjById(Long IdSubj) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLDelete)){

            prStm.setLong(1, IdSubj);
            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        return rs;
    }

}
