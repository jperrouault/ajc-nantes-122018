package fr.loupgarou.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import fr.loupgarou.dao.IDAOUtilisateur;
import fr.loupgarou.exception.AccountLockedException;
import fr.loupgarou.exception.UsernameOrPasswordNotFoundException;
import fr.loupgarou.model.Joueur;
import fr.loupgarou.model.TypeUtilisateur;
import fr.loupgarou.model.Utilisateur;

public class DAOUtilisateurJPA implements IDAOUtilisateur {
	private EntityManager em;
	
	
	public DAOUtilisateurJPA(EntityManagerFactory emf) {
		this.em = emf.createEntityManager();
	}
	
	
	@Override
	public List<Utilisateur> findAll() {
		return em
				.createQuery("select u from Utilisateur u", Utilisateur.class)
				.getResultList();
	}

	@Override
	public Utilisateur findById(int id) {
		return em.find(Utilisateur.class, id);
	}

	@Override
	public Utilisateur save(Utilisateur entity) {
		//On démarre la transaction
		em.getTransaction().begin();
		
		if (entity.getId() == 0) {
			em.persist(entity);
		}
		
		else {
			entity = em.merge(entity);
		}
		
		//On commit la transaction
		em.getTransaction().commit();
		
		return entity;
	}

	@Override
	public void delete(Utilisateur entity) {
		em.getTransaction().begin();
		em.remove(em.merge(entity));
		em.getTransaction().commit();
	}
	
	@Override
	public void deleteById(int id) {
//		Utilisateur myPartie = new Utilisateur();
//		myPartie.setId(id);
//		this.delete(myPartie);
	}


	@Override
	public Utilisateur auth(String username, String password) throws UsernameOrPasswordNotFoundException, AccountLockedException {
		try {
			Utilisateur myUtilisateur = em
					.createQuery("select u from Utilisateur u where u.username = :username and u.password = :password", Utilisateur.class)
					.setParameter("username", username)
					.setParameter("password", password)
					.getSingleResult();
			
			
			if (myUtilisateur.getType() == TypeUtilisateur.JOUEUR) {
				if (((Joueur)myUtilisateur).isBanni()) {
					throw new AccountLockedException();
				}
			}
			
			return myUtilisateur;
		}
		
		catch (NoResultException ex) {
			throw new UsernameOrPasswordNotFoundException();
		}
	}
}