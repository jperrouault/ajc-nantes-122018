package fr.formation.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOJPA {
	protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("SurfyPU");
	protected EntityManager em = emf.createEntityManager();

	public void close() {
		em.close();
		emf.close();
	}
}
