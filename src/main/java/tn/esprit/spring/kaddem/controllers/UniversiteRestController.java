package tn.esprit.spring.kaddem.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.entities.UniversiteDTO;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("/universite")
public class UniversiteRestController {
	final
	IUniversiteService universiteService;

	public UniversiteRestController(IUniversiteService universiteService) {
		this.universiteService = universiteService;
	}

	// http://localhost:8089/Kaddem/universite/retrieve-all-universites
	@GetMapping("/retrieve-all-universites")
	public List<Universite> getUniversites() {
		return universiteService.retrieveAllUniversites();

	}
	// http://localhost:8089/Kaddem/universite/retrieve-universite/8
	@GetMapping("/retrieve-universite/{universite-id}")
	public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
		return universiteService.retrieveUniversite(universiteId);
	}

	// http://localhost:8089/Kaddem/universite/add-universite
	@PostMapping("/add-universite")
	public ResponseEntity<Universite> addUniversite(@RequestBody UniversiteDTO univDTO) {
		return new ResponseEntity<>(universiteService.addUniversite(univDTO) , HttpStatus.CREATED);
	}



	// http://localhost:8089/Kaddem/universite/remove-universite/1
	@DeleteMapping("/remove-universite/{universite-id}")
	public void removeUniversite(@PathVariable("universite-id") Integer universiteId) {
		universiteService.deleteUniversite(universiteId);
	}


	// http://localhost:8089/Kaddem/universite/update-universite
	@PutMapping("/update-universite/{id}")
	public  ResponseEntity<Universite> updateUniversite(@PathVariable("id") Integer univId,
													   @RequestBody UniversiteDTO univDTO) {
		return new ResponseEntity<>(universiteService.updateUniversite(univId,univDTO) , HttpStatus.ACCEPTED);
	}




}


