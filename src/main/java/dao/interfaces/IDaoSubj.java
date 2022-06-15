package dao.interfaces;

import entity.Subj;

import java.util.List;

public interface IDaoSubj {

    List<Subj> findAll();

    int createSubj(String SubName);

    Subj findSubjById(Long IdSubj);

    int updateSubjById(Long IdSubj, Subj newSubj);

    int deleteSubjById(Long IdSubj);

}
