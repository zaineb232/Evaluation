package ma.projet.test;

import ma.projet.classes.Categorie;
import ma.projet.classes.Produit;
import ma.projet.classes.Commande;
import ma.projet.classes.LigneCommandeProduit;
import ma.projet.service.ProduitService;
import ma.projet.service.CommandeService;
import ma.projet.service.LigneCommandeService;
import ma.projet.service.CategorieService;
import ma.projet.util.HibernateUtil;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(HibernateUtil.class);

        ProduitService produitService = context.getBean(ProduitService.class);
        CommandeService commandeService = context.getBean(CommandeService.class);
        LigneCommandeService ligneService = context.getBean(LigneCommandeService.class);
        CategorieService categorieService = context.getBean(CategorieService.class);

        System.out.println("=== INITIALISATION DES DONNÉES ===");


        // Création des catégories
        Categorie cat1 = new Categorie();
        cat1.setCode("C1");
        cat1.setLibelle("Catégorie 1");
        categorieService.create(cat1);

        Categorie cat2 = new Categorie();
        cat2.setCode("C2");
        cat2.setLibelle("Catégorie 2");
        categorieService.create(cat2);

        // Création des produits
        Produit p1 = new Produit();
        p1.setReference("ES12");
        p1.setPrix(120f);
        p1.setCategorie(cat1);
        produitService.create(p1);

        Produit p2 = new Produit();
        p2.setReference("ZR85");
        p2.setPrix(100f);
        p2.setCategorie(cat1);
        produitService.create(p2);

        Produit p3 = new Produit();
        p3.setReference("EE85");
        p3.setPrix(200f);
        p3.setCategorie(cat2);
        produitService.create(p3);

        // Création des commandes
        Commande c1 = new Commande();
        c1.setDate(LocalDate.of(2013, 3, 14)); 
        commandeService.create(c1);

        Commande c2 = new Commande();
        c2.setDate(LocalDate.of(2003, 6, 12));
        commandeService.create(c2);

        // Création des lignes de commande
        LigneCommandeProduit l1 = new LigneCommandeProduit();
        l1.setProduit(p1);
        l1.setCommande(c1);
        l1.setQuantite(7);
        ligneService.create(l1);

        LigneCommandeProduit l2 = new LigneCommandeProduit();
        l2.setProduit(p2);
        l2.setCommande(c1);
        l2.setQuantite(14);
        ligneService.create(l2);

        LigneCommandeProduit l3 = new LigneCommandeProduit();
        l3.setProduit(p3);
        l3.setCommande(c1);
        l3.setQuantite(5);
        ligneService.create(l3);



        System.out.println("\n=== TESTS de l'exercice 1===");

        // Test 1: Affichage formaté d'une commande 
        System.out.println("\n--- Test 1: Affichage  d'une commande ---");
        afficherCommandeFormattee(c1.getId(), produitService, commandeService);

        // Test 2: Produits par commande // Test 3: Produits par commande
        System.out.println("\n--- Test2: Produits par commande ---");
        produitService.findByCommande(c1.getId());

        // Test 3: Produits par catégorie
        System.out.println("\n--- Test 3: Produits par catégorie ---");
        List<Produit> produitsCat1 = produitService.findByCategorie(cat1);
        System.out.println("Catégorie: " + cat1.getLibelle());
        System.out.println("Référence\t\tPrix\t\tCatégorie");
        for (Produit p : produitsCat1) {
            System.out.printf("%s\t\t%.0f DH\t\t%s%n",
                    p.getReference(), p.getPrix(), p.getCategorie().getLibelle());
        }

        // Test 4: Produits commandés entre deux dates
        System.out.println("\n--- Test 4: Produits commandés entre 2013-03-01 et 2013-03-31 ---");
        LocalDate start = LocalDate.of(2013, 3, 1);
        LocalDate end = LocalDate.of(2013, 3, 31);
        produitService.findByDateCommande(start, end);

        // Test 5: Produits avec prix supérieur à un seuil
        System.out.println("\n--- Test 5: Produits prix > 100 DH ---");
        produitService.findProduitsPrixSuperieur(100f);

        context.close();
    }

    private static void afficherCommandeFormattee(int commandeId, ProduitService produitService, CommandeService commandeService) {
        Commande commande = commandeService.findById(commandeId);
        if (commande != null) {
            System.out.println("Commande : " + commande.getId());
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", java.util.Locale.FRENCH);
            String dateFormatee = commande.getDate().format(formatter);
            System.out.println("Date : " + dateFormatee);
            
            System.out.println("Liste des produits :");
            System.out.println("Référence\t\tPrix\t\tQuantité");
            
            produitService.findByCommande(commandeId);
        }
    }
}
