package ma.projet.classes;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
  @author zaineb
 
 */
@Entity
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String telephone;

 //Liste des projets dont cet employé est le chef
    @OneToMany(mappedBy = "chef")
    private List<Projet> projetsGeres = new ArrayList<>();
//Liste des tâches assignées à cet employé    
    @OneToMany(mappedBy = "employe")
    private List<EmployeTache> employeTache = new ArrayList<>();


    public Employe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
