package entity;

import java.io.Serializable;
import java.util.Objects;

public class Subj implements Serializable {

    private Long IdSubj;
    private String SubName;

    public Subj(){

    }

    public Subj(Long idSubj, String subName) {
        IdSubj = idSubj;
        SubName = subName;
    }

    public Long getIdSubj() {
        return IdSubj;
    }

    public void setIdSubj(Long idSubj) {
        IdSubj = idSubj;
    }

    public String getSubName() {
        return SubName;
    }

    public void setSubName(String subName) {
        SubName = subName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subj subj = (Subj) o;
        return Objects.equals(IdSubj, subj.IdSubj) && Objects.equals(SubName, subj.SubName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdSubj, SubName);
    }

    @Override
    public String toString() {
        return "Subj{" +
                "IdSubj=" + IdSubj +
                ", SubName='" + SubName + '\'' +
                '}';
    }
}
