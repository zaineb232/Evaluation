package ma.projet.test;

import ma.projet.service.EmployeService;
import ma.projet.service.EmployeTacheService;
import ma.projet.service.ProjetService;
import ma.projet.service.TacheService;
import ma.projet.classes.Employe;
import ma.projet.classes.Projet;
import ma.projet.classes.Tache;
import ma.projet.classes.EmployeTache;
import ma.projet.util.HibernateUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateUtil.class);

        EmployeService employeService = context.getBean(EmployeService.class);
        EmployeTacheService employeTacheService = context.getBean(EmployeTacheService.class);
        ProjetService projetService = context.getBean(ProjetService.class);
        TacheService tacheService = context.getBean(TacheService.class);

        // Création des employés
        Employe emp1 = new Employe();
        emp1.setNom("BAANNI");
        emp1.setPrenom("Zaineb");
        employeService.create(emp1);

        Employe emp2 = new Employe();
        emp2.setNom("BAANNI");
        emp2.setPrenom("Noura");
        employeService.create(emp2);

        // Création des projets
        Projet proj1 = new Projet();
        proj1.setNom("Application E-Commerce");
        proj1.setChef(emp1);
        proj1.setDateDebut(LocalDate.of(2030, 2, 10));
        proj1.setDateFin(LocalDate.of(2030, 10, 02));
        projetService.create(proj1);

        Projet proj2 = new Projet();
        proj2.setNom("Système de Gestion RH");
        proj2.setChef(emp2);
        proj2.setDateDebut(LocalDate.of(2030, 5, 11));
        proj2.setDateFin(LocalDate.of(2030, 7, 30));
        projetService.create(proj2);

        // Création des tâches planifiées
        Tache t1 = new Tache();
        t1.setNom("Développement Frontend React");
        t1.setProjet(proj1);
        t1.setPrix(1200f);
        t1.setDateDebut(LocalDate.of(2030, 10, 22));
        t1.setDateFin(null);
        tacheService.create(t1);

        Tache t2 = new Tache();
        t2.setNom("Tests Unitaires");
        t2.setProjet(proj2);
        t2.setPrix(600f);
        t2.setDateDebut(LocalDate.of(2030, 12, 9));
        t2.setDateFin(null);
        tacheService.create(t2);

        // Création des tâches réalisées
        Tache t3 = new Tache();
        t3.setNom("Analyse Fonctionnelle");
        t3.setProjet(proj1);
        t3.setPrix(1800f);
        t3.setDateDebut(LocalDate.of(2030, 1, 10));
        t3.setDateFin(LocalDate.of(2030, 4, 1));
        tacheService.create(t3);

        Tache t4 = new Tache();
        t4.setNom("Conception Base de Données");
        t4.setProjet(proj1);
        t4.setPrix(2500f);
        t4.setDateDebut(LocalDate.of(2030, 3, 2));
        t4.setDateFin(LocalDate.of(2030, 5, 5));
        tacheService.create(t4);

        // Assignation des tâches aux employés
        EmployeTache et1 = new EmployeTache();
        et1.setEmploye(emp1);
        et1.setTache(t1);
        employeTacheService.create(et1);

        EmployeTache et2 = new EmployeTache();
        et2.setEmploye(emp1);
        et2.setTache(t3);
        employeTacheService.create(et2);

        EmployeTache et3 = new EmployeTache();
        et3.setEmploye(emp2);
        et3.setTache(t2);
        employeTacheService.create(et3);

        EmployeTache et4 = new EmployeTache();
        et4.setEmploye(emp1);
        et4.setTache(t4);
        employeTacheService.create(et4);

        System.out.println("        TEST DE L'EXERCICE 2");
        System.out.println("________________________________________________");

        
        System.out.println("\n📋 TEST 1: Tâches assignées à un employé");
        System.out.println("Employé: " + emp1.getPrenom() + " " + emp1.getNom());
        System.out.println("________________________________________________");
        employeService.afficherTachesParEmploye(emp1.getId());

        System.out.println("\n👨‍💼 TEST 2: Projets gérés par un employé");
        System.out.println("Employé: " + emp1.getPrenom() + " " + emp1.getNom());
        System.out.println("________________________________________________");
        employeService.afficherProjetsGeresParEmploye(emp1.getId());

        System.out.println("\n📅 TEST 3: Tâches planifiées d'un projet");
        System.out.println("Projet: " + proj1.getNom());
        System.out.println("________________________________________________");
        projetService.afficherTachesPlanifieesParProjet(proj1.getId());

        System.out.println("\n✅ TEST 4: Tâches réalisées d'un projet");
        System.out.println("Projet: " + proj1.getNom());
        System.out.println("________________________________________________");
        projetService.afficherTachesRealiseesParProjet(proj1.getId());

        System.out.println("\n💰 TEST 5: Tâches coûteuses (> 1000 DH)");
        System.out.println("________________________________________________");
        tacheService.afficherTachesPrixSuperieurA1000();

        System.out.println("\n📊 TEST 6: Tâches réalisées dans une période");
        LocalDate debut = LocalDate.of(2030, 3, 1);
        LocalDate fin = LocalDate.of(2030, 5, 31);
        System.out.println("Période: " + debut + " à " + fin);
        System.out.println("________________________________________________");
        tacheService.afficherTachesEntreDates(debut, fin);

        context.close();
    }
}