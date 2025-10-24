package ma.projet.service;

import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.IDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MariageService implements IDao<Mariage> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean create(Mariage mariage) {
        Session session = sessionFactory.getCurrentSession();
        session.save(mariage);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Mariage mariage) {
        sessionFactory.getCurrentSession().delete(mariage);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Mariage mariage) {
        sessionFactory.getCurrentSession().update(mariage);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Mariage findById(int id) {
        return sessionFactory.getCurrentSession().get(Mariage.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mariage> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Mariage", Mariage.class)
                .list();
    }



    @Transactional(readOnly = true)
    public void afficherMariagesAvecDetails(int hommeId) {
        Session session = sessionFactory.getCurrentSession();
        List<Mariage> mariages = session.createQuery(
                        "SELECT m FROM Mariage m " +
                                "JOIN FETCH m.femme " +
                                "WHERE m.homme.id = :hommeId " +
                                "ORDER BY m.dateDebut",
                        Mariage.class
                )
                .setParameter("hommeId", hommeId)
                .list();

        if (!mariages.isEmpty()) {
            Homme homme = mariages.get(0).getHomme();
            System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());

            // Séparer les mariages en cours et échoués
            List<Mariage> mariagesEnCours = new ArrayList<>();
            List<Mariage> mariagesEchoues = new ArrayList<>();

            for (Mariage m : mariages) {
                if (m.getDateFin() == null) {
                    mariagesEnCours.add(m);
                } else {
                    mariagesEchoues.add(m);
                }
            }

            // Afficher les mariages en cours
            if (!mariagesEnCours.isEmpty()) {
                System.out.println("Mariages En Cours :");
                int count = 1;
                for (Mariage m : mariagesEnCours) {
                    String dateDebut = m.getDateDebut() != null ? 
                        String.format("%02d/%02d/%04d", m.getDateDebut().getDayOfMonth(), 
                                     m.getDateDebut().getMonthValue(), m.getDateDebut().getYear()) : "N/A";
                    System.out.printf("%d. Femme : %-15s Date Début : %-12s    Nbr Enfants : %d%n",
                            count++,
                            m.getFemme().getNom() + " " + m.getFemme().getPrenom(),
                            dateDebut,
                            m.getNbrEnfant());
                }
            }

            // Afficher les mariages échoués
            if (!mariagesEchoues.isEmpty()) {
                System.out.println("\nMariages échoués :");
                int count = 1;
                for (Mariage m : mariagesEchoues) {
                    String dateDebut = m.getDateDebut() != null ? 
                        String.format("%02d/%02d/%04d", m.getDateDebut().getDayOfMonth(), 
                                     m.getDateDebut().getMonthValue(), m.getDateDebut().getYear()) : "N/A";
                    String dateFin = m.getDateFin() != null ? 
                        String.format("%02d/%02d/%04d", m.getDateFin().getDayOfMonth(), 
                                     m.getDateFin().getMonthValue(), m.getDateFin().getYear()) : "N/A";
                    System.out.printf("%d. Femme : %-15s Date Début : %-12s    Date Fin : %-12s    Nbr Enfants : %d%n",
                            count++,
                            m.getFemme().getNom() + " " + m.getFemme().getPrenom(),
                            dateDebut,
                            dateFin,
                            m.getNbrEnfant());
                }
            }

            if (mariagesEnCours.isEmpty() && mariagesEchoues.isEmpty()) {
                System.out.println("Cet homme n'a aucun mariage enregistré.");
            }
        } else {
            System.out.println("Cet homme n'a aucun mariage enregistré.");
        }
    }



}
