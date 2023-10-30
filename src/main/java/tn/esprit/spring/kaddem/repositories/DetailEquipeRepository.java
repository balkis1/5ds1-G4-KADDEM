package tn.esprit.spring.kaddem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.DetailEquipe;

import java.util.Date;
import java.util.List;

@Repository
public interface DetailEquipeRepository extends CrudRepository<DetailEquipe, Integer> {

}
