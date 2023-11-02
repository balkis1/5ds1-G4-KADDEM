package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.entities.DetailEquipeDTO;
import tn.esprit.spring.kaddem.services.IDetailEquipeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/DetailEquipe")
public class DetailEquipeRestController {


	@Autowired
	IDetailEquipeService iDetailEquipeService;

	// http://localhost:8089/Kaddem/DetailEquipe/retrieve-all-DetailEquipes
	@GetMapping("/retrieve-all-DetailEquipes")
	public List<DetailEquipe> getDetailEquipes() {
		return iDetailEquipeService.retrieveAllDetailEquipes();
	}


	// http://localhost:8089/Kaddem/DetailEquipe/retrieve-DetailEquipe/8
	@GetMapping("/retrieve-DetailEquipe/{DetailEquipe-id}")
	public DetailEquipe retrieveDetailEquipe(@PathVariable("DetailEquipe-id") Integer DetailEquipeId) {
		return iDetailEquipeService.retrieveDetailEquipe(DetailEquipeId);
	}



	// http://localhost:8089/Kaddem/DetailEquipe/add-DetailEquipe
	@PostMapping("/add-DetailEquipe")
	public ResponseEntity<DetailEquipe> addDetailEquipe(@RequestBody DetailEquipeDTO DeDTO) {
		return new ResponseEntity<>(iDetailEquipeService.addDetailEquipe(DeDTO) , HttpStatus.CREATED);
	}



	// http://localhost:8089/Kaddem/contrat/remove-contrat/1
	@DeleteMapping("/remove-DetailEquipe/{DetailEquipe-id}")
	public void removeDetailEquipe(@PathVariable("DetailEquipe-id") Integer DetailEquipeId) {
		iDetailEquipeService.removeDetailEquipe(DetailEquipeId);
	}



	// http://localhost:8089/Kaddem/DetailEquipe/update-DetailEquipe
	@PutMapping("/update-DetailEquipe/{DetailEquipe-id}")
	public  ResponseEntity<DetailEquipe> updateContrat(@PathVariable("DetailEquipe-id") Integer DetailEquipeId,
									  @RequestBody DetailEquipeDTO DeDTO) {
		return new ResponseEntity<>(iDetailEquipeService.updateDetailEquipe(DetailEquipeId,DeDTO) , HttpStatus.ACCEPTED);
	}
}


