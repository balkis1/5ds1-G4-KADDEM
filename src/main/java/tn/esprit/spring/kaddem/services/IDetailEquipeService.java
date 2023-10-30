package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.DetailEquipe;
import java.util.Date;
import java.util.List;

public interface IDetailEquipeService {

     List<DetailEquipe> retrieveAllDetailEquipes();

     DetailEquipe updateDetailEquipe (DetailEquipe  de);

      DetailEquipe addDetailEquipe (DetailEquipe de);

     DetailEquipe retrieveDetailEquipe (Integer  idDetailEquipe);

    void removeDetailEquipe(Integer idDetailEquipe);

}

