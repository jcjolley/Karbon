/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ancestors;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jolley
 */
public class Person {
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    int id;
    private List<Person> children;
    private List<Person> parents;

    public Person() {
        firstname = "";
        lastname = "";
        birthday = LocalDate.now();
        id = 0;
    }
    
    
    public Person(String firstname, String lastname, LocalDate birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    public Person(String firstname, String lastname, LocalDate birthday, int id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.id = id;
    }

    public Person(String firstname, String lastname, LocalDate birthday, int id, List<Person> children, List<Person> parents) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.id = id;
        this.children = children;
        this.parents = parents;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void setChildren(List<Person> children) {
        this.children = children;
    }

    public List<Person> getParents() {
        return parents;
    }

    public void setParents(List<Person> parents) {
        this.parents = parents;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    
}
