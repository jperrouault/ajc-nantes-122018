package fr.codenames.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.codenames.model.Partie;

public interface IDAOPartie extends JpaRepository<Partie, Integer> {
	@Query("select p from Partie p left join p.participations pa where p.terminee = false group by p.id having count(pa) < 4")
	public List<Partie> findFree(Pageable page);
	
	public default Partie findFreeOne() {
		return this.findFree(PageRequest.of(0, 1)).get(0);
	}
}