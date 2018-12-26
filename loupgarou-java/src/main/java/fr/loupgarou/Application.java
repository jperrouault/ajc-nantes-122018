package fr.loupgarou;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.loupgarou.dao.IDAOPartie;
import fr.loupgarou.dao.IDAOPersonnage;
import fr.loupgarou.dao.IDAOPouvoir;
import fr.loupgarou.dao.IDAOUtilisateur;
import fr.loupgarou.dao.jpa.DAOPartieJPA;
import fr.loupgarou.dao.jpa.DAOPersonnageJPA;
import fr.loupgarou.dao.jpa.DAOPouvoirJPA;
import fr.loupgarou.dao.jpa.DAOUtilisateurJPA;
import fr.loupgarou.exception.AccountLockedException;
import fr.loupgarou.exception.NonUniqueUsernameException;
import fr.loupgarou.exception.UsernameOrPasswordNotFoundException;
import fr.loupgarou.model.Joueur;
import fr.loupgarou.model.Partie;
import fr.loupgarou.model.Personnage;
import fr.loupgarou.model.Pouvoir;
import fr.loupgarou.model.Utilisateur;

public class Application {
	private static IDAOUtilisateur daoUtilisateur;
	private static IDAOPersonnage daoPersonnage;
	private static IDAOPouvoir daoPouvoir;
	private static IDAOPartie daoPartie;
	private static Utilisateur utilisateur;
	private static Scanner sc;

	/**
	 * Programme principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("LoupGarouPU");
		
		try {
			daoUtilisateur = new DAOUtilisateurJPA(emf);
			daoPersonnage = new DAOPersonnageJPA(emf);
			daoPouvoir = new DAOPouvoirJPA(emf);
			daoPartie = new DAOPartieJPA(emf);
			
			sc = new Scanner(System.in);
	
			connexion();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			sc.close();
			emf.close();
		}
	}

	/**
	 * Se connecter avec un nom d'utilisateur et un mot de passe (à saisir)
	 */
	public static void connexion() {
		System.out.print("Indiquer le nom d'utilisateur (touche entrer pour s'inscrire) : ");
		String username = sc.nextLine();
		
		if (username.equals("")) {
			inscription();
			return;
		}

		System.out.print("Indiquer le mot de passe : ");
		String password = sc.nextLine();

		try {
			utilisateur = daoUtilisateur.auth(username, password);
			menu();
		}

		catch (UsernameOrPasswordNotFoundException e) {
			System.out.println("MAUVAIS USERNAME OU PASSWORD !!");
		}

		catch (AccountLockedException e) {
			System.out.println("COMPTE BLOQUE ... SORRY !");
		}
	}

	/**
	 * S'inscrire (créer un nouveau compte utilisateur)
	 */
	public static void inscription() {
		Joueur myJoueur = new Joueur();
		
		System.out.print("Indiquer votre nom : ");
		myJoueur.setNom(sc.nextLine());
		
		System.out.print("Indiquer votre prénom : ");
		myJoueur.setPrenom(sc.nextLine());

		System.out.print("Indiquer votre pseudo : ");
		myJoueur.setPseudo(sc.nextLine());
		
		System.out.print("Indiquer le nom d'utilisateur : ");
		myJoueur.setUsername(sc.nextLine());

		System.out.print("Indiquer le mot de passe : ");
		myJoueur.setPassword(sc.nextLine());
		
		
		try {
			daoUtilisateur.save(myJoueur);
		}
		
		catch (NonUniqueUsernameException e) {
			System.out.println("Le nom d'utilisateur est déjà utilisé !");
		}
		
		connexion();
	}

	/**
	 * Affiche le menu et démarre les sous-programmes
	 */
	public static void menu() {
		int menu = 0;

		do {
			System.out.println("----------------------------------");
			System.out.println("1.	Voir les personnages");
			System.out.println("10.	Ajouter un personnage");
			System.out.println("11.	Modifier un personnage");
			System.out.println("12.	Supprimer un personnage");
			System.out.println("2.	Démarrer une nouvelle partie");
			System.out.println("20.	Lister les parties");
			System.out.println("3.	Se déconnecter");
			System.out.println("----------------------------------");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				showPersonnages();
				break;

			case 10:
				addPersonnage();
				break;

			case 11:
				editPersonnage();
				break;

			case 12:
				deletePersonnage();
				break;

			case 20:
				showParties();
				break;

			case 3:
				utilisateur = null;
				menu = 0;
				System.out.println("Bye!");
				break;
			}
		} while (menu != 0);
	}

	/**
	 * Affiche la liste des personnages
	 */
	public static void showPersonnages() {
		for (Personnage p : daoPersonnage.findAll()) {
			System.out.println(p.getId() + ". " + p.getLibelle());
		}
	}

	/**
	 * Affiche la liste des pouvoirs
	 */
	public static void showPouvoirs() {
		for (Pouvoir p : daoPouvoir.findAll()) {
			System.out.println(p.getId() + ". " + p.getLibelle());
		}
	}

	/**
	 * Ajouter un personnage
	 */
	public static void addPersonnage() {
		Personnage myPersonnage = new Personnage();

		System.out.println("Saisir le nom du personnage :");
		myPersonnage.setLibelle(sc.next());

		showPouvoirs();
		System.out.println("Choisir le pouvoir du personnage : ");
		myPersonnage.setPouvoir(new Pouvoir());
		myPersonnage.getPouvoir().setId(sc.nextInt());

		daoPersonnage.save(myPersonnage);
	}

	/**
	 * Modifier un personnage
	 */
	public static void editPersonnage() {
		showPersonnages();

		System.out.println("Choisir le personnage à modifier : ");
		Personnage myPersonnage = daoPersonnage.findById(sc.nextInt());

		System.out.println(String.format("Saisir le nom du personnage [%s]", myPersonnage.getLibelle()));
		myPersonnage.setLibelle(sc.next());

		daoPersonnage.save(myPersonnage);
	}

	/**
	 * Supprimer un personnage
	 */
	public static void deletePersonnage() {
		showPersonnages();

		System.out.println("Choisir le personnage à supprimer : ");
		daoPersonnage.deleteById(sc.nextInt());
	}

	/**
	 * Affiche la liste des parties
	 */
	public static void showParties() {
		for (Partie p : daoPartie.findAll()) {
			System.out.println(p.getId());
		}
	}
}
