package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.entities.UniversiteDTO;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UniversiteServiceImpl implements IUniversiteService{
@Autowired
    UniversiteRepository universiteRepository;

    public UniversiteServiceImpl() {
        // TODO Auto-generated constructor stub
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
        Universite de1 = universiteRepository.findUnivById(id);
        de1.setNomUniv(deDTO.getNomUniv());
        return universiteRepository.save(de1);
    }

  public Universite retrieveUniversite (Integer idUniversite){
Universite u = universiteRepository.findById(idUniversite).get();
return  u;
    }
    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

}
