package spittr.repository;

import java.util.List;
import spittr.domain.Spitter;

public interface SpitterRepository {
	
	
	Spitter save(Spitter spitter);
	
	Spitter findByUsername(String username);
	
	Spitter findOne(long id);
	
	List<Spitter> findAll();
	

}
