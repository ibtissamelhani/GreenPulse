package entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String cin;
    private String name;
    private int age;
    private List<Consumption> consumptions;

    public User() {
    }

    public User(String cin,String name, int age) {
        this.cin = cin;
        this.name = name;
        this.age = age;
        this.consumptions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

    public List<Consumption> getConsumptions() {
        return consumptions;
    }

    public void setConsumptions(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", consumptions=" + consumptions +
                '}';
    }
}
