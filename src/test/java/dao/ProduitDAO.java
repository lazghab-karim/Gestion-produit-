package dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.AgentDAO;
import domaine.Produit;

public class ProduitDAO {
    private static SessionFactory factory;

    public void Create(Produit Ag) {//create
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        session.persist(Ag); 
        
        transaction.commit();
        session.close();
        System.out.println("Produit ajouté avec succès !");
    }
    public Produit Read(int id) {//read
        Session session = factory.openSession();
        Produit Ag = session.get(Produit.class, id);
        session.close();
        return Ag;
    }
    public void Update(Produit Ag) {//update
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        session.update(Ag);
        
        transaction.commit();
        session.close();
        System.out.println("Produit mis à jour avec succès !");
    }
    public void Delete(int id) {//delete
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        Produit Ag= session.get(Produit.class, id);
        if (Ag!= null) {
            session.delete(Ag);
            System.out.println("Produit supprimé avec succès !");
        } else {
            System.out.println("Produit introuvable !");
        }
        
        transaction.commit();
        session.close();
    }


    public static void main(String[] args) {
    	ProduitDAO dao = new ProduitDAO();

        Scanner scanner = new Scanner(System.in);
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    
        while (true) {
            System.out.println("\n===== GESTION DES Produit =====");
            System.out.println("1. Ajouter un Produit");
            System.out.println("2. Consulter un Produit");
            System.out.println("3. Modifier un Produit");
            System.out.println("4. Supprimer un Produit");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    System.out.print("Entrez l'ID : ");
                    Produit Pd_ = new Produit();
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez le nom : ");
                    Pd_.setNom(scanner.nextLine());
                    dao.Create(Pd_);
                    break;

                case 2:
                    System.out.print("Entrez l'ID de l'Produit : ");
                    int idRecherche = scanner.nextInt();
                    Produit Ag = dao.Read(idRecherche);
                    if (Ag != null) {
                        System.out.println("Produit trouvé : " + Ag.getProduitId() + " - " + Ag.getNom());
                    } else {
                        System.out.println("Aucun Produit trouvé avec cet ID.");
                    }
                    break;

                case 3:
                    System.out.print("Entrez l'ID de l'Produit à modifier : ");
                    int idModif = scanner.nextInt();
                    scanner.nextLine();
                    Produit agentAModifier = dao.Read(idModif);
                    if (agentAModifier != null) {
                        System.out.print("Entrez le nouveau nom : ");
                        String nouveauNom = scanner.nextLine();
                        agentAModifier.setNom(nouveauNom);
                        dao.Update(agentAModifier);
                        System.out.println("Modification réussie !");
                    } else {
                        System.out.println("Produit introuvable.");
                    }
                    break;

                case 4:
                    System.out.print("Entrez l'ID de l'Produit à supprimer : ");
                    int idSuppr = scanner.nextInt();
                    dao.Delete(idSuppr);
                    break;

                case 5:
                    System.out.println("Fermeture du programme.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
    }
}
