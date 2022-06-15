package entity;

import java.io.Serializable;
import java.util.Objects;

public class Univer implements Serializable {

    private Long IdUniver;
    private String UniverName;
    private String City;
    private String Website;

    public Univer(){}

    public Univer(Long idUniver, String univerName, String city, String website) {
        IdUniver = idUniver;
        UniverName = univerName;
        City = city;
        Website = website;
    }

    public Long getIdUniver() {
        return IdUniver;
    }

    public void setIdUniver(Long idUniver) {
        IdUniver = idUniver;
    }

    public String getUniverName() {
        return UniverName;
    }

    public void setUniverName(String univerName) {
        UniverName = univerName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Univer univer = (Univer) o;
        return Objects.equals(IdUniver, univer.IdUniver) && Objects.equals(UniverName, univer.UniverName) && Objects.equals(City, univer.City) && Objects.equals(Website, univer.Website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IdUniver, UniverName, City, Website);
    }

    @Override
    public String toString() {
        return "Univer{" +
                "IdUniver=" + IdUniver +
                ", UniverName='" + UniverName + '\'' +
                ", City='" + City + '\'' +
                ", Website='" + Website + '\'' +
                '}';
    }
}
