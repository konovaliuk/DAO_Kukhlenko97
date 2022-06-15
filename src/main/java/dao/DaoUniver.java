package dao;

import connection.DataSource;
import dao.interfaces.IDaoUniver;
import entity.Univer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUniver implements IDaoUniver {

    private final static String columnIdUniver = "IdUniver";
    private final static String columnUniverName = "UniverName";
    private final static String columnCity = "City";
    private final static String columnWebsite = "Website";

    private static final String SQLSelect = "SELECT * FROM `univer`";
    private static final String SQLInsert = "INSERT INTO `univer`(" +
            columnUniverName+
            ","+columnCity+
            ","+columnWebsite+
            ") VALUES (?, ? ,?)";

    private static final String SQLSelectWhere = "SELECT * FROM `univer` WHERE IdUniver = ?";
    private static final String SQLUpdate = "UPDATE `univer` SET " +
            columnUniverName+ "= ?, "+
            columnCity+"= ?, "+
            columnWebsite+"= ?, "+
            " WHERE IdUniver = ?";

    private static final String SQLDelete = "DELETE FROM `univer` WHERE IdUniver = ?";

    private static final DataSource dataSource = DataSource.getInstance();

    public Univer getUniver(ResultSet rs) throws SQLException {
        Long IdUniver = rs.getLong(columnIdUniver);
        String UniverName = rs.getString(columnUniverName);
        String City = rs.getString(columnCity);
        String Website = rs.getString(columnWebsite);

        return new Univer(IdUniver, UniverName, City, Website);
    }

    public PreparedStatement setUniverParams(PreparedStatement prStm,
                                             String UniverName,
                                             String City,
                                             String Website) throws SQLException {

        prStm.setString(1, UniverName);
        prStm.setString(2, City);
        prStm.setString(3, Website);

        return prStm;

    }

    @Override
    public List<Univer> findAll() {
        List<Univer> univers = new ArrayList<Univer>();
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelect)) {

            rs = prStm.executeQuery();
            while(rs.next()){
                univers.add(getUniver(rs));
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
                System.out.print("University table is empty.\n");
            }
        }
        return univers;
    }

    @Override
    public int createUniver( String UniverName,
                             String City,
                             String Website) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLInsert)) {

            setUniverParams(prStm, UniverName, City, Website);
            rs = prStm.executeUpdate();
        }
        catch(SQLException e){
            System.err.print(e.getMessage() + "\n");
        }

        return rs;
    }

    @Override
    public Univer findUniverById(Long IdUniver) {

        Univer univer = null;
        ResultSet rs = null;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLSelectWhere)) {

            prStm.setLong(1, IdUniver);
            rs = prStm.executeQuery();

            rs.next();
            univer = getUniver(rs);

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
                System.out.print("There is no university with that id.\n");
            }
        }

        return univer;
    }

    @Override
    public int updateUniverById(Long IdUniver, Univer newUniver) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLUpdate)){

            setUniverParams(prStm, newUniver.getUniverName(), newUniver.getCity(), newUniver.getWebsite());
            prStm.setLong(5, IdUniver);

            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        finally{
            if(rs == 0){
                System.out.print("There is no university with that id.\n");
            }
        }
        return rs;
    }

    @Override
    public int deleteUniverById(Long IdUniver) {

        int rs = 0;
        try(Connection conn = DataSource.getConnection();
            PreparedStatement prStm = conn.prepareStatement(SQLDelete)){

            prStm.setLong(1, IdUniver);
            rs = prStm.executeUpdate();
        }
        catch (SQLException e){
            System.err.print(e.getMessage() + "\n");
        }
        return rs;
    }

}
