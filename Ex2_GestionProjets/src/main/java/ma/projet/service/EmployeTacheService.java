package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeTacheService implements IDao<EmployeTache> {

    @Autowired
    private SessionFactory sessionFactory;

    // Méthode pour créer une nouvelle assignation employé-tâche
    @Override
    @Transactional
    public boolean create(EmployeTache employeTache) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employeTache);
        return true;
    }
    // Méthode pour mettre à jour une assignation employé-tâche
    @Override
    @Transactional
    public boolean update(EmployeTache employeTache) {
        sessionFactory.getCurrentSession().update(employeTache);
        return true;
    }
    // Méthode pour supprimer une assignation employé-tâche
    @Override
    @Transactional
    public boolean delete(EmployeTache employeTache) {
        sessionFactory.getCurrentSession().delete(employeTache);
        return true;
    }



    // Méthode pour récupérer la liste de toutes les assignations employé-tâche
    @Override
    @Transactional(readOnly = true)
    public List<EmployeTache> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from EmployeTache", EmployeTache.class)
                .list();
    }

    // Méthode pour rechercher une assignation employé-tâche par son identifiant
    @Override
    @Transactional(readOnly = true)
    public EmployeTache findById(int id) {
        return sessionFactory.getCurrentSession().get(EmployeTache.class, id);
    }



}

