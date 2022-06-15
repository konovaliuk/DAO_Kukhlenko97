package dao;

import dao.interfaces.IDaoSpecialty;
import dao.interfaces.IDaoStudent;
import dao.interfaces.IDaoSubj;
import dao.interfaces.IDaoUniver;

public class DAOFactory {

    public static IDaoStudent createDaoStudent() {return new DaoStudent();}

    public static IDaoSubj createDaoSubj() {return new DaoSubj();}

    public static IDaoUniver createDaoUniver() {return new DaoUniver();}

    public static IDaoSpecialty createDaoSpecialty() {return new DaoSpecialty();}

}
