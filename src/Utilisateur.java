import java.util.ArrayList;
import java.util.List;

public class Utilisateur {

    private String id;
    private String nom;
    private int age;
    private List<Consommation> consommations;

    public Utilisateur(String id, String nom, int age) {
        this.id = id;
        this.nom = nom;
        this.age = age;
        this.consommations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
