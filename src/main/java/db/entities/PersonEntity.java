package db.entities;

import org.bson.types.ObjectId;

import javax.persistence.Entity;
import javax.persistence.Table;

public class PersonEntity {

    private ObjectId id;
    private String firstName;
    private String lastName;
    private String email;

    public PersonEntity() {
        // Needed by Jackson deserialization
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonEntity(ObjectId id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public ObjectId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
