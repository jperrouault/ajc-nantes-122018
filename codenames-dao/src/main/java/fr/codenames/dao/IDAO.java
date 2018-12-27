package fr.codenames.dao;

import java.util.List;

public interface IDAO<T> {
	/**
	 * Rechercher toutes les entit�s
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * Rechercher une entit� par son identifiant
	 * @param id
	 * @return
	 */
	public T findById(int id);
	
	/**
	 * Sauvegarder une entit� (cr�ation et modification)
	 * @param entity
	 * @return
	 */
	public T save(T entity);
	
	/**
	 * Supprimer une entit�
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * Supprimer une entit� par son identifiant
	 * @param id
	 */
	public void deleteById(int id);
}