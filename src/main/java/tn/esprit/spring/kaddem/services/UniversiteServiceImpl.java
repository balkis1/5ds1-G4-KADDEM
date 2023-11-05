package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.entities.UniversiteDTO;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;


import java.util.List;

@Slf4j
@Service
public class UniversiteServiceImpl implements IUniversiteService{
    final
    UniversiteRepository universiteRepository;

    public UniversiteServiceImpl(UniversiteRepository universiteRepository) {
        this.universiteRepository = universiteRepository;
    }


    public   List<Universite> retrieveAllUniversites(){
return (List<Universite>) universiteRepository.findAll();
    }


    @Override
    public Universite addUniversite(UniversiteDTO univDTO) {
        return universiteRepository.save(new Universite(univDTO.getNomUniv()));
    }

    @Override
    public Universite updateUniversite(Integer id, UniversiteDTO deDTO) {
        Universite de1 = universiteRepository.findUniversiteByIdUniv(id);
        de1.setNomUniv(deDTO.getNomUniv());
        return universiteRepository.save(de1);
    }

  public Universite retrieveUniversite (Integer idUniversite){
       return universiteRepository.findById(idUniversite).orElse(null);
    }
    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

}
