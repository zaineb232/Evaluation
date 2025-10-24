package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.LigneCommandeProduit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LigneCommandeService implements IDao<LigneCommandeProduit> {

    @Autowired
    private SessionFactory sessionFactory;

    // Créer une nouvelle ligne de commande
    @Override
    @Transactional
    public boolean create(LigneCommandeProduit ligneCommande) {
        Session session = sessionFactory.getCurrentSession();
        session.save(ligneCommande);
        return true;
    }

    // Mettre à jour une ligne de commande
    @Override
    @Transactional
    public boolean update(LigneCommandeProduit ligneCommande) {
        sessionFactory.getCurrentSession().update(ligneCommande);
        return true;
    }
    // Supprimer une ligne de commande
    @Override
    @Transactional
    public boolean delete(LigneCommandeProduit ligneCommande) {
        sessionFactory.getCurrentSession().delete(ligneCommande);
        return true;
    }
    // Trouver une ligne de commande par son ID
    @Override
    @Transactional(readOnly = true)
    public LigneCommandeProduit findById(int id) {
        return sessionFactory.getCurrentSession().get(LigneCommandeProduit.class, id);
    }

    // Récupérer toutes les lignes de commande
    @Override
    @Transactional(readOnly = true)
    public List<LigneCommandeProduit> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from LigneCommandeProduit", LigneCommandeProduit.class)
                .list();
    }
}
