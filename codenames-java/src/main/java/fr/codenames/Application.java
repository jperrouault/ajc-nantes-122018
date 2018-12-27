package fr.codenames;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.codenames.dao.IDAOCarte;
import fr.codenames.dao.IDAOPartie;
import fr.codenames.dao.IDAOUtilisateur;
import fr.codenames.dao.jpa.DAOCarteJPA;
import fr.codenames.dao.jpa.DAOPartieJPA;
import fr.codenames.dao.jpa.DAOUtilisateurJPA;
import fr.codenames.exception.AccountLockedException;
import fr.codenames.exception.UsernameOrPasswordNotFoundException;
import fr.codenames.model.Carte;
import fr.codenames.model.Joueur;
import fr.codenames.model.Partie;
import fr.codenames.model.Utilisateur;
import fr.codenames.exception.NonUniqueUsernameException;

public class Application {
	private static IDAOUtilisateur daoUtilisateur;
	private static IDAOCarte daoCarte;
	private static IDAOPartie daoPartie;
	private static Utilisateur utilisateur;
	private static Scanner sc;

	/**
	 * Programme principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeNamesPU");
		
		try {
			daoUtilisateur = new DAOUtilisateurJPA(emf);
			daoCarte = new DAOCarteJPA(emf);
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
	 * Se connecter avec un nom d'utilisateur et un mot de passe (� saisir)
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
	 * S'inscrire (cr�er un nouveau compte utilisateur)
	 */
	public static void inscription() {
		Joueur myJoueur = new Joueur();
		
		System.out.print("Indiquer votre nom : ");
		myJoueur.setNom(sc.nextLine());
		
		System.out.print("Indiquer votre pr�nom : ");
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
			System.out.println("Le nom d'utilisateur est d�j� utilis� !");
		}
		
		connexion();
	}

	/**
	 * Affiche le menu et d�marre les sous-programmes
	 */
	public static void menu() {
		int menu = 0;

		do {
			System.out.println("----------------------------------");
			System.out.println("1.	Voir les cartes");
			System.out.println("10.	Ajouter une carte");
			System.out.println("11.	Modifier une carte");
			System.out.println("12.	Supprimer une carte");
			System.out.println("2.	D�marrer une nouvelle partie");
			System.out.println("20.	Lister les parties");
			System.out.println("3.	Se d�connecter");
			System.out.println("----------------------------------");
			menu = sc.nextInt();

			switch (menu) {
			case 1:
				showCartes();
				break;

			case 10:
				addCarte();
				break;

			case 11:
				editCarte();
				break;

			case 12:
				deleteCarte();
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
	 * Affiche la liste des cartes
	 */
	public static void showCartes() {
		for (Carte c : daoCarte.findAll()) {
			System.out.println(c.getId() + ". " + c.getLibelle());
		}
	}

	/**
	 * Ajouter une carte
	 */
	public static void addCarte() {
		Carte myCarte = new Carte();

		System.out.println("Saisir le libell� de la carte :");
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Modifier une carte
	 */
	public static void editCarte() {
		showCartes();

		System.out.println("Choisir la carte � modifier : ");
		Carte myCarte = daoCarte.findById(sc.nextInt());

		System.out.println(String.format("Saisir le libell� de la carte [%s]", myCarte.getLibelle()));
		myCarte.setLibelle(sc.next());

		daoCarte.save(myCarte);
	}

	/**
	 * Supprimer une carte
	 */
	public static void deleteCarte() {
		showCartes();

		System.out.println("Choisir la carte � supprimer : ");
		daoCarte.deleteById(sc.nextInt());
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
