package ma.projet.test;

import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.HibernateUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateUtil.class);

        FemmeService femmeService = context.getBean(FemmeService.class);
        HommeService hommeService = context.getBean(HommeService.class);
        MariageService mariageService = context.getBean(MariageService.class);


        // Création de 10 femmes
        Femme f1 = new Femme(); f1.setCin("F001"); f1.setNom("Amina"); f1.setPrenom("Benali"); f1.setDateNaissance(LocalDate.of(1965, 3, 12));
        Femme f2 = new Femme(); f2.setCin("F002"); f2.setNom("Khadija"); f2.setPrenom("Alaoui"); f2.setDateNaissance(LocalDate.of(1970, 7, 5));
        Femme f3 = new Femme(); f3.setCin("F003"); f3.setNom("Zineb"); f3.setPrenom("Hassani"); f3.setDateNaissance(LocalDate.of(1980, 11, 4));
        Femme f4 = new Femme(); f4.setCin("F004"); f4.setNom("Fatima"); f4.setPrenom("Idrissi"); f4.setDateNaissance(LocalDate.of(1968, 9, 3));
        Femme f5 = new Femme(); f5.setCin("F005"); f5.setNom("Aicha"); f5.setPrenom("Bennani"); f5.setDateNaissance(LocalDate.of(1975, 6, 10));
        Femme f6 = new Femme(); f6.setCin("F006"); f6.setNom("Salima"); f6.setPrenom("Rami"); f6.setDateNaissance(LocalDate.of(1972, 5, 15));
        Femme f7 = new Femme(); f7.setCin("F007"); f7.setNom("Amal"); f7.setPrenom("Ali"); f7.setDateNaissance(LocalDate.of(1978, 8, 22));
        Femme f8 = new Femme(); f8.setCin("F008"); f8.setNom("Wafa"); f8.setPrenom("Alaoui"); f8.setDateNaissance(LocalDate.of(1982, 12, 10));
        Femme f9 = new Femme(); f9.setCin("F009"); f9.setNom("Karima"); f9.setPrenom("Alami"); f9.setDateNaissance(LocalDate.of(1960, 4, 18));
        Femme f10 = new Femme(); f10.setCin("F010"); f10.setNom("Naima"); f10.setPrenom("Benali"); f10.setDateNaissance(LocalDate.of(1976, 9, 30));

        Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10).forEach(femmeService::create);


        // Création de 5 hommes
        Homme h1 = new Homme(); h1.setCin("H001"); h1.setNom("Ahmed"); h1.setPrenom("Benali");
        Homme h2 = new Homme(); h2.setCin("H002"); h2.setNom("Mohamed"); h2.setPrenom("Alaoui");
        Homme h3 = new Homme(); h3.setCin("H003"); h3.setNom("Hassan"); h3.setPrenom("Idrissi");
        Homme h4 = new Homme(); h4.setCin("H004"); h4.setNom("Omar"); h4.setPrenom("Bennani");
        Homme h5 = new Homme(); h5.setCin("H005"); h5.setNom("Youssef"); h5.setPrenom("Hassani");

        Arrays.asList(h1, h2, h3, h4, h5).forEach(hommeService::create);


        // Mariages pour Ahmed Benali (h1) 
        Mariage m1 = new Mariage(); m1.setHomme(h1); m1.setFemme(f6); m1.setDateDebut(LocalDate.of(1990,9,3)); m1.setNbrEnfant(4); // Salima Rami - en cours
        Mariage m2 = new Mariage(); m2.setHomme(h1); m2.setFemme(f7); m2.setDateDebut(LocalDate.of(1995,9,3)); m2.setNbrEnfant(2); // Amal Ali - en cours
        Mariage m3 = new Mariage(); m3.setHomme(h1); m3.setFemme(f8); m3.setDateDebut(LocalDate.of(2000,11,4)); m3.setNbrEnfant(3); // Wafa Alaoui - en cours
        Mariage m4 = new Mariage(); m4.setHomme(h1); m4.setFemme(f9); m4.setDateDebut(LocalDate.of(1989,9,3)); m4.setDateFin(LocalDate.of(1990,9,3)); m4.setNbrEnfant(0); // Karima Alami - échoué

        // Mariages pour Mohamed Alaoui (h2) - marié à 4 femmes
        Mariage m5 = new Mariage(); m5.setHomme(h2); m5.setFemme(f1); m5.setDateDebut(LocalDate.of(1992,5,10)); m5.setNbrEnfant(1);
        Mariage m6 = new Mariage(); m6.setHomme(h2); m6.setFemme(f2); m6.setDateDebut(LocalDate.of(1996,7,12)); m6.setNbrEnfant(2);
        Mariage m7 = new Mariage(); m7.setHomme(h2); m7.setFemme(f3); m7.setDateDebut(LocalDate.of(2000,3,5)); m7.setNbrEnfant(1);
        Mariage m8 = new Mariage(); m8.setHomme(h2); m8.setFemme(f4); m8.setDateDebut(LocalDate.of(2003,1,20)); m8.setNbrEnfant(2);

        // Mariages pour Hassan Idrissi (h3)
        Mariage m9 = new Mariage(); m9.setHomme(h3); m9.setFemme(f5); m9.setDateDebut(LocalDate.of(2007,4,1)); m9.setNbrEnfant(1);
        Mariage m10 = new Mariage(); m10.setHomme(h3); m10.setFemme(f10); m10.setDateDebut(LocalDate.of(2020,6,22)); m10.setNbrEnfant(0);

        // Mariages pour Omar Bennani (h4) - marié à 4 femmes 
        Mariage m11 = new Mariage(); m11.setHomme(h4); m11.setFemme(f1); m11.setDateDebut(LocalDate.of(2005,1,15)); m11.setNbrEnfant(2);
        Mariage m12 = new Mariage(); m12.setHomme(h4); m12.setFemme(f2); m12.setDateDebut(LocalDate.of(2008,3,20)); m12.setNbrEnfant(1);
        Mariage m13 = new Mariage(); m13.setHomme(h4); m13.setFemme(f3); m13.setDateDebut(LocalDate.of(2010,6,10)); m13.setNbrEnfant(3);
        Mariage m14 = new Mariage(); m14.setHomme(h4); m14.setFemme(f4); m14.setDateDebut(LocalDate.of(2012,9,5)); m14.setNbrEnfant(1);

        // Mariage pour Youssef Hassani (h5)
        Mariage m15 = new Mariage(); m15.setHomme(h5); m15.setFemme(f5); m15.setDateDebut(LocalDate.of(2015,2,14)); m15.setNbrEnfant(2);

        Arrays.asList(m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15).forEach(mariageService::create);


        System.out.println("\n--- Liste des Femmes ---");
        femmeService.findAll().forEach(f -> System.out.printf("%-5s %-25s %-25s %-12s%n",
                f.getCin(), f.getNom(), f.getPrenom(), f.getDateNaissance()));


        Femme plusAgee = femmeService.findAll().stream()
                .min((fA, fB) -> fA.getDateNaissance().compareTo(fB.getDateNaissance()))
                .orElse(null);
        System.out.println("\nFemme la plus âgée : " + (plusAgee != null ? plusAgee.getCin() + " " + plusAgee.getNom() + " " + plusAgee.getPrenom() : "N/A"));


        System.out.println("\n--- Épouses de l'homme " + h1.getNom() + " ---");
        hommeService.afficherEpousesEntreDates(h1.getId(), LocalDate.of(1999,3,20), LocalDate.of(2025,6,10));


        System.out.println("\n--- Nombre d'enfants de " + f1.getNom() + " entre 2000 et 2025 ---");
        int nbEnfants = femmeService.nombreEnfantsEntreDates(f1.getId(), LocalDate.of(1999,4,1), LocalDate.of(2025,9,13));
        System.out.println("Nombre d'enfants : " + nbEnfants);


        System.out.println("\n--- Femmes mariées deux fois ou plus ---");
        femmeService.femmesMarieesAuMoinsDeuxFois().forEach(f -> System.out.printf("%-5s %-25s %-25s%n", f.getCin(), f.getNom(), f.getPrenom()));


        System.out.println("\n--- Hommes mariés à 4 femmes entre 1999 et 2007 ---");
        hommeService.afficherNombreHommesAvecQuatreFemmes(LocalDate.of(2000,5,1), LocalDate.of(2013,9,5));


        System.out.println("\n--- Mariages de l'homme " + h1.getNom() + " avec détails ---");
        mariageService.afficherMariagesAvecDetails(h1.getId());

        context.close();
    }
}
