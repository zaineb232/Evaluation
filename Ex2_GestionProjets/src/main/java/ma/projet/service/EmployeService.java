package ma.projet.service;

import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeService implements IDao<Employe> {

    @Autowired
    private SessionFactory sessionFactory;

    // Méthode pour créer un nouvel employé dans la base de données
    @Override
    @Transactional
    public boolean create(Employe employe) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employe);
        return true;
    }

  
    // Méthode pour mettre à jour les informations d'un employé existant
    @Override
    @Transactional
    public boolean update(Employe employe) {
        sessionFactory.getCurrentSession().update(employe);
        return true;
    }

        // Méthode pour récupérer la liste de tous les employés
        @Override
        @Transactional(readOnly = true)
        public List<Employe> findAll() {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Employe", Employe.class)
                    .list();
        }
    
    // Méthode pour rechercher un employé par son identifiant
    @Override
    @Transactional(readOnly = true)
    public Employe findById(int id) {
        return sessionFactory.getCurrentSession().get(Employe.class, id);
    }



    // Méthode pour afficher toutes les tâches assignées à un employé spécifique
    @Transactional(readOnly = true)
    public void afficherTachesParEmploye(int employeId) {
        Session session = sessionFactory.getCurrentSession();
        List<Tache> taches = session.createQuery(
                        "select e.tache from EmployeTache e where e.employe.id = :employeId",
                        Tache.class
                )
                .setParameter("employeId", employeId)
                .list();

        if (!taches.isEmpty()) {
            Employe employe = session.get(Employe.class, employeId);
            System.out.println("Employé : " + employe.getNom() + " " + employe.getPrenom());
            System.out.println("Liste des tâches :");

            System.out.printf(
                    "%-5s %-25s %-25s %-12s %-12s%n",
                    "Num", "Nom", "Projet", "Date Début", "Date Fin"
            );

            for (Tache t : taches) {
                String dateDebut = t.getDateDebut() != null ? t.getDateDebut().toString() : "N/A";
                String dateFin = t.getDateFin() != null ? t.getDateFin().toString() : "N/A";
                String nomProjet = t.getProjet() != null ? t.getProjet().getNom() : "N/A";
                System.out.printf(
                        "%-5d %-25s %-25s %-12s %-12s%n",
                        t.getId(), t.getNom(), nomProjet, dateDebut, dateFin
                );
            }
        } else {
            System.out.println("Aucune tâche assignée à cet employé.");
        }
    }

    // Méthode pour afficher tous les projets gérés par un employé (en tant que chef de projet)
    @Transactional(readOnly = true)
    public void afficherProjetsGeresParEmploye(int employeId) {
        Session session = sessionFactory.getCurrentSession();
        List<Projet> projets = session.createQuery(
                        "from Projet p where p.chef.id = :id",
                        Projet.class
                )
                .setParameter("id", employeId)
                .list();

        if (!projets.isEmpty()) {
            Employe employe = session.get(Employe.class, employeId);
            System.out.println("Employé : " + employe.getNom() + " " + employe.getPrenom());
            System.out.println("Liste des projets gérés :");

            System.out.printf(
                    "%-5s %-25s %-12s %-12s%n",
                    "ID", "Nom", "Date Début", "Date Fin"
            );


            for (Projet p : projets) {
                String dateDebut = p.getDateDebut() != null ? p.getDateDebut().toString() : "N/A";
                String dateFin = p.getDateFin() != null ? p.getDateFin().toString() : "N/A";

                System.out.printf(
                        "%-5d %-25s %-12s %-12s%n",
                        p.getId(), p.getNom(), dateDebut, dateFin
                );
            }
        } else {
            System.out.println("Cet employé ne gère aucun projet.");
        }
    }

      // Méthode pour supprimer un employé de la base de données
      @Override
      @Transactional
      public boolean delete(Employe employe) {
          sessionFactory.getCurrentSession().delete(employe);
          return true;
      }
  



}
