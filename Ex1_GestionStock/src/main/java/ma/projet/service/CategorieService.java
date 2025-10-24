package ma.projet.service;

import ma.projet.dao.IDao;
import ma.projet.classes.Categorie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class CategorieService implements IDao<Categorie> {

    @Autowired
    private SessionFactory sessionFactory;

    // Créer une nouvelle catégorie
    @Override
    @Transactional
    public boolean create(Categorie categorie) {
        Session session = sessionFactory.getCurrentSession();
        session.save(categorie);
        return true;
    }
    // Mettre à jour une catégorie
    @Override
    @Transactional
    public boolean update(Categorie categorie) {
        sessionFactory.getCurrentSession().update(categorie);
        return true;
    }
    // Supprimer une catégorie
    @Override
    @Transactional
    public boolean delete(Categorie categorie) {
        sessionFactory.getCurrentSession().delete(categorie);
        return true;
    }

    // Trouver une catégorie par son ID
    @Override
    @Transactional(readOnly = true)
    public Categorie findById(int id) {
        return sessionFactory.getCurrentSession().get(Categorie.class, id);
    }

    // Récupérer toutes les catégories
    @Override
    @Transactional(readOnly = true)
    public List<Categorie> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Categorie", Categorie.class)
                .list();
    }
}
