package dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dao.ProduitDAO;
import domaine.Agent;

public class AgentDAO {
    private static SessionFactory factory;

    public void Create(Agent Ag) {//create
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        session.persist(Ag); 
        
        transaction.commit();
        session.close();
        System.out.println("Agent ajouté avec succès !");
    }
    public Agent Read(int id) {//read
        Session session = factory.openSession();
        Agent Ag = session.get(Agent.class, id);
        session.close();
        return Ag;
    }
    public void Update(Agent Ag) {//update
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        session.update(Ag);
        
        transaction.commit();
        session.close();
        System.out.println("Agents mis à jour avec succès !");
    }
    public void Delete(int id) {//delete
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        
        Agent Ag= session.get(Agent.class, id);
        if (Ag!= null) {
            session.delete(Ag);
            System.out.println("Agent supprimé avec succès !");
        } else {
            System.out.println("Agent introuvable !");
        }
        
        transaction.commit();
        session.close();
    }


    public static void main(String[] args) {
    	AgentDAO dao = new AgentDAO();

        Scanner scanner = new Scanner(System.in);
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        factory = cfg.buildSessionFactory();
    
        while (true) {
            System.out.println("\n===== GESTION DES AGENTS =====");
            System.out.println("1. Ajouter un agent");
            System.out.println("2. Consulter un agent");
            System.out.println("3. Modifier un agent");
            System.out.println("4. Supprimer un agent");
            System.out.println("5. Quitter");
            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine();
            switch (choix) {
                case 1:
                    System.out.print("Entrez l'ID : ");
                    Agent Ag_ = new Agent();
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez le nom : ");
                    Ag_.setNom(scanner.nextLine());
                    System.out.print("Entrez le prenom : ");
                    Ag_.setPrenom(scanner.nextLine());
                    System.out.print("Entrez l'email : ");
                    Ag_.setEmail(scanner.nextLine());
                    dao.Create(Ag_);
                    break;

                case 2:
                    System.out.print("Entrez l'ID de l'agent : ");
                    int idRecherche = scanner.nextInt();
                    Agent Ag = dao.Read(idRecherche);
                    if (Ag != null) {
                        System.out.println("Agent trouvé : " + Ag.getAgentId() + " - " + Ag.getNom());
                    } else {
                        System.out.println("Aucun agent trouvé avec cet ID.");
                    }
                    break;

                case 3:
                    System.out.print("Entrez l'ID de l'agent à modifier : ");
                    int idModif = scanner.nextInt();
                    scanner.nextLine();
                    Agent agentAModifier = dao.Read(idModif);
                    if (agentAModifier != null) {
                        System.out.print("Entrez le nouveau nom : ");
                        String nouveauNom = scanner.nextLine();
                        agentAModifier.setNom(nouveauNom);
                        dao.Update(agentAModifier);
                        System.out.println("Modification réussie !");
                    } else {
                        System.out.println("agent introuvable.");
                    }
                    break;

                case 4:
                    System.out.print("Entrez l'ID de l'agent à supprimer : ");
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
