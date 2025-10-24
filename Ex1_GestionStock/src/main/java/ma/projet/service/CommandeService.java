package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Commande;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class CommandeService implements IDao<Commande> {

    @Autowired
    private SessionFactory sessionFactory;

    // Créer une nouvelle commande
    @Override
    @Transactional
    public boolean create(Commande commande) {
        Session session = sessionFactory.getCurrentSession();
        session.save(commande);
        return true;
    }
    // Mettre à jour une commande
    @Override
    @Transactional
    public boolean update(Commande commande) {
        sessionFactory.getCurrentSession().update(commande);
        return true;
    }

    // Supprimer une commande
    @Override
    @Transactional
    public boolean delete(Commande commande) {
        sessionFactory.getCurrentSession().delete(commande);
        return true;
    }
    
    // Trouver une commande par son ID
    @Override
    @Transactional(readOnly = true)
    public Commande findById(int id) {
        return sessionFactory.getCurrentSession().get(Commande.class, id);
    }

    // Récupérer toutes les commandes
    @Override
    @Transactional(readOnly = true)
    public List<Commande> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Commande", Commande.class)
                .list();
    }
}
