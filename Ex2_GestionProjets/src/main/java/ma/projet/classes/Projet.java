package ma.projet.classes;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
  @author zaineb
 
 */
@Entity
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    //Liste des tâches appartenant à ce projet
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tache> taches = new ArrayList<>();

    // Chef de projet responsable de ce projet
    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Employe chef;

   
    public Projet() {
    }

  
    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

  
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

 
    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

   //Retourne la liste des tâches du projet

    public List<Tache> getTaches() {
        return taches;
    }

    //Définit la liste des tâches du projet
    
    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }
}
