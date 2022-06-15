package dao.interfaces;

import entity.Univer;

import java.util.List;

public interface IDaoUniver {

    List<Univer> findAll();

    int createUniver(String UniverName,
                     String City,
                     String Website);

    Univer findUniverById(Long IdUniver);

    int updateUniverById(Long IdUniver, Univer newUniver);

    int deleteUniverById(Long IdUniver);

}
