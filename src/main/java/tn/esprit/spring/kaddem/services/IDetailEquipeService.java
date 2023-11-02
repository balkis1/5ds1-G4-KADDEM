package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.DetailEquipeDTO;

import java.util.List;

public interface IDetailEquipeService {

     List<DetailEquipe> retrieveAllDetailEquipes();

     DetailEquipe updateDetailEquipe (Integer detailEquipeId,DetailEquipeDTO  deDTO);

      DetailEquipe addDetailEquipe (DetailEquipeDTO de);

     DetailEquipe retrieveDetailEquipe (Integer  idDetailEquipe);

    void removeDetailEquipe(Integer idDetailEquipe);

}

