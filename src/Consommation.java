import java.time.LocalDate;

public class Consommation {

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Float totale;

    public Consommation(LocalDate dateDebut, LocalDate dateFin, Float totale) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.totale = totale;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Float getTotale() {
        return totale;
    }

    public void setTotale(Float totale) {
        this.totale = totale;
    }
}
