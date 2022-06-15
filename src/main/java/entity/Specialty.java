package entity;

import java.io.Serializable;
import java.util.Objects;

public class Specialty implements Serializable {

    private Long IdSpecialty;
    private String SpecialtyName;
    private String Industry;
    private String SpecialtyCode;
    private String IndustryCode;

    public Specialty(){}

    public Specialty(Long idSpecialty, String specialtyName, String industry, String specialtyCode, String industryCode) {
        IdSpecialty = idSpecialty;
        SpecialtyName = specialtyName;
        Industry = industry;
        SpecialtyCode = specialtyCode;
        IndustryCode = industryCode;
    }

    public Long getIdSpecialty() {
        return IdSpecialty;
    }

    public void setIdSpecialty(Long idSpecialty) {
        IdSpecialty = idSpecialty;
    }

    public String getSpecialtyName() {
        return SpecialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        SpecialtyName = specialtyName;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }

    public String getSpecialtyCode() {
        return SpecialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        SpecialtyCode = specialtyCode;
    }

    public String getIndustryCode() {
        return IndustryCode;
    }

    public void setIndustryCode(String industryCode) {
        IndustryCode = industryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        return Objects.equals(IdSpecialty, specialty.IdSpecialty) && Objects.equals(SpecialtyName, specialty.SpecialtyName) && Objects.equals(Industry, specialty.Industry) && Objects.equals(SpecialtyCode, specialty.SpecialtyCode) && Objects.equals(IndustryCode, specialty.IndustryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdSpecialty, SpecialtyName, Industry, SpecialtyCode, IndustryCode);
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "IdSpecialty=" + IdSpecialty +
                ", SpecialtyName='" + SpecialtyName + '\'' +
                ", Industry='" + Industry + '\'' +
                ", SpecialtyCode='" + SpecialtyCode + '\'' +
                ", IndustryCode='" + IndustryCode + '\'' +
                '}';
    }
}
