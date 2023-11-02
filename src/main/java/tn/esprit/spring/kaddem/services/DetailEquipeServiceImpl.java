package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.DetailEquipeDTO;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;

import java.util.List;

@Slf4j
@Service
public class DetailEquipeServiceImpl implements IDetailEquipeService {
	@Autowired
	DetailEquipeRepository detailEquipeRepository;

	@Override
	public List<DetailEquipe> retrieveAllDetailEquipes() {
		return (List<DetailEquipe>) detailEquipeRepository.findAll();

	}

	@Override
	public DetailEquipe updateDetailEquipe(Integer detailEquipeId, DetailEquipeDTO deDTO) {
		DetailEquipe de1 = detailEquipeRepository.findDetailEquipeByIdDetailEquipe(detailEquipeId);
		de1.setThematique(deDTO.getThematique());
		de1.setSalle(deDTO.getSalle());
		return detailEquipeRepository.save(de1);
	}

	@Override
	public DetailEquipe addDetailEquipe(DetailEquipeDTO detailEquipeDTO) {
		return detailEquipeRepository.save(new DetailEquipe(detailEquipeDTO.getSalle(), detailEquipeDTO.getThematique()));
	}

	@Override
	public DetailEquipe retrieveDetailEquipe(Integer idDetailEquipe) {
		return detailEquipeRepository.findById(idDetailEquipe).orElse(null);
	}

	@Override
	public void removeDetailEquipe(Integer idDetailEquipe) {
		DetailEquipe c= this.retrieveDetailEquipe(idDetailEquipe);
		detailEquipeRepository.delete(c);
	}
}
