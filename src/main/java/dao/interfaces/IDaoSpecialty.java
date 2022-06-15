package dao.interfaces;


import entity.Specialty;

import java.util.List;

public interface IDaoSpecialty {

    List<Specialty> findAll();

    int createSpecialty(String SpecialtyName,
                        String Industry,
                        String SpecialtyCode,
                        String IndustryCode);

    Specialty findSpecialtyById(Long IdSpecialty);

    int updateSpecialtyById(Long IdSpecialty, Specialty newSpecialty);

    int deleteSpecialtyById(Long IdSpecialty);

}
