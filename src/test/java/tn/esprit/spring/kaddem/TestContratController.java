package tn.esprit.spring.kaddem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.services.IContratService;
import tn.esprit.spring.kaddem.services.IEtudiantService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/contracts")
public class TestContratController {
    @Autowired
    private IContratService contratService;

    @Autowired
    private IEtudiantService etudiantService;

    @PostMapping("/add")
    public Contrat addContract(@RequestBody Contrat contrat) {
        return contratService.addContrat(contrat);
    }

    @DeleteMapping("/remove/{id}")
    public void removeContract(@PathVariable Integer id) {
        contratService.removeContrat(id);
    }

    @PostMapping("/assign/{contractId}/{etudiantId}")
    public Contrat assignContractToEtudiant(@PathVariable Integer contractId, @PathVariable Integer idDepartement) {
        Contrat contrat = contratService.retrieveContrat(contractId);
        Etudiant etudiant = (Etudiant) etudiantService.getEtudiantsByDepartement(idDepartement);

        if (contrat != null && etudiant != null) {
            contrat.setEtudiant(etudiant);
            return contratService.updateContrat(contrat);
        } else {
            // Handle not found scenarios or validation errors
            return null;
        }
    }

    @GetMapping("/updateStatus")
    public void retrieveAndUpdateStatusContrat() {
        contratService.retrieveAndUpdateStatusContrat();
    }

    @GetMapping("/revenue")
    public float getChiffreAffaireEntreDeuxDates(@RequestParam Date startDate, @RequestParam Date endDate) throws ParseException {
        return contratService.getChiffreAffaireEntreDeuxDates(startDate, endDate);
    }

    @GetMapping("/all")
    public List<Contrat> getAllContracts() {
        return contratService.retrieveAllContrats();
    }
}
