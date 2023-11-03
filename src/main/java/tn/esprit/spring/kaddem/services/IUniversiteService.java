package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.entities.UniversiteDTO;

import java.util.List;
import java.util.Set;

public interface IUniversiteService {
   public List<Universite> retrieveAllUniversites();

    Universite addUniversite(UniversiteDTO univDTO) ;
    Universite updateUniversite(Integer id, UniversiteDTO deDTO);

    Universite retrieveUniversite (Integer idUniversite);

    public  void deleteUniversite(Integer idUniversite);






}
