package pl.shelter.rest.model.accounts;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PersonalId implements Comparable<PersonalId> {

    private String id;

    private PersonalId(String id) {
        this.id = id;
    }

    public PersonalId() {

    }

    public static PersonalId valueOf(String number) {
        validateNumber(number);
        return new PersonalId(number);
    }

    public static PersonalId valueOf(Integer number) {
        return valueOf(String.valueOf(number));
    }

    private static void validateNumber(String number) {
        String nu = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonalId{" + "id=" + id + '}';
    }
    public String toDtoString(){return id;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonalId other = (PersonalId) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(PersonalId o) {
        return id.compareTo(o.id);
    }

}