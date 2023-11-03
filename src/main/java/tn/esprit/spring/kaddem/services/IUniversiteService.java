package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.Universite;

import java.util.List;
import java.util.Set;

public interface IUniversiteService {
   public List<Universite> retrieveAllUniversites();

    Universite addUniversite (Universite  u);

    Universite updateUniversite (Universite  u);

    Universite retrieveUniversite (Integer idUniversite);

    public  void deleteUniversite(Integer idUniversite);






}
