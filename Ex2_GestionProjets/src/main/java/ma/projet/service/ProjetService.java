package ma.projet.service;

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
public class ProjetService implements IDao<Projet> {

    @Autowired
    private SessionFactory sessionFactory;

    // Méthode pour créer un nouveau projet dans la base de données
    @Override
    @Transactional
    public boolean create(Projet projet) {
        Session session = sessionFactory.getCurrentSession();
        session.save(projet);
        return true;
    }


    // Méthode pour mettre à jour les informations d'un projet existant
    @Override
    @Transactional
    public boolean update(Projet projet) {
        sessionFactory.getCurrentSession().update(projet);
        return true;
    }

    // Méthode pour supprimer un projet de la base de données
    @Override
    @Transactional
    public boolean delete(Projet projet) {
        sessionFactory.getCurrentSession().delete(projet);
        return true;
    }


    // Méthode pour récupérer la liste de tous les projets
    @Override
    @Transactional(readOnly = true)
    public List<Projet> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Projet", Projet.class)
                .list();
    }

    // Méthode pour rechercher un projet par son identifiant
    @Override
    @Transactional(readOnly = true)
    public Projet findById(int id) {
        return sessionFactory.getCurrentSession().get(Projet.class, id);
    }
    
    // Méthode pour afficher toutes les tâches planifiées (non terminées) d'un projet
    @Transactional(readOnly = true)
    public void afficherTachesPlanifieesParProjet(int projetId) {
        Session session = sessionFactory.getCurrentSession();
        List<Tache> taches = session.createQuery(
                        "from Tache t where t.projet.id = :id and t.dateFin is null",
                        Tache.class
                )
                .setParameter("id", projetId)
                .list();

        if (!taches.isEmpty()) {
            Projet projet = taches.get(0).getProjet();
            System.out.println("Projet : " + projet.getId() + "\tNom : " + projet.getNom() +
                    "\tDate début : " + (projet.getDateDebut() != null ? projet.getDateDebut() : "N/A"));
            System.out.println("Liste des tâches planifiées :");
            System.out.printf(
                    "%-5s %-25s %-12s %-12s%n",
                    "Num", "Nom", "Date Début", "Date Fin"
            );

            for (Tache t : taches) {
                String dateDebut = t.getDateDebut() != null ? t.getDateDebut().toString() : "N/A";
                String dateFin = t.getDateFin() != null ? t.getDateFin().toString() : "N/A";
                System.out.printf(
                        "%-5s %-25s %-12s %-12s%n",
                        t.getId(), t.getNom(), dateDebut, dateFin
                );

            }
        } else {
            System.out.println("Pas de taches planifiées.");
        }
    }

    // Méthode pour afficher toutes les tâches réalisées (terminées) d'un projet
    @Transactional(readOnly = true)
    public void afficherTachesRealiseesParProjet(int projetId) {
        Session session = sessionFactory.getCurrentSession();
        List<Tache> taches = session.createQuery(
                        "from Tache t where t.projet.id = :id and t.dateFin is not null",
                        Tache.class
                )
                .setParameter("id", projetId)
                .list();

        if (!taches.isEmpty()) {
            Projet projet = taches.get(0).getProjet();
            System.out.println("Projet : " + projet.getId() + "\tNom : " + projet.getNom() +
                    "\tDate début : " + (projet.getDateDebut() != null ? projet.getDateDebut() : "N/A"));
            System.out.println("Liste des tâches réalisées :");


            System.out.printf(
                    "%-5s %-25s %-12s %-12s%n",
                    "Num", "Nom", "Date Début", "Date Fin"
            );

            for (Tache t : taches) {
                String dateDebut = t.getDateDebut() != null ? t.getDateDebut().toString() : "N/A";
                String dateFin = t.getDateFin() != null ? t.getDateFin().toString() : "N/A";

                System.out.printf(
                        "%-5s %-25s %-12s %-12s%n",
                        t.getId(), t.getNom(), dateDebut, dateFin
                );
            }
        } else {
            System.out.println("Aucune tâche réalisée .");
        }
    }




}
