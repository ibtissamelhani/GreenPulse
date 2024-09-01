package entities;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String name;
    private int age;
    private List<Consumption> consumptions;

    public User() {
    }

    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.consumptions = new ArrayList<>();
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

    public List<Consumption> getConsommations() {
        return consumptions;
    }

    public void setConsommations(List<Consumption> consumptions) {
        this.consumptions = consumptions;
    }

    public void addConsumption(Consumption consumption) {
        if (consumption != null) {
            this.consumptions.add(consumption);
        } else {
            System.out.println("Cannot add null consumption.");
        }
    }

}
