package entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String name;
    private int age;
    private List<Consommation> consommations;

    public User() {
    }

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.consommations = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Consommation> getConsommations() {
        return consommations;
    }

    public void setConsommations(List<Consommation> consommations) {
        this.consommations = consommations;
    }

}
