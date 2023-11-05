package tn.esprit.spring.kaddem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;


@RestController
    @RequestMapping("/api/departements")
    public class DepartementControllerTest {

        @Autowired
        private IDepartementService departementService;

        @PostMapping
        public Departement AddDepartment(@RequestBody Departement department) {
            return departementService.addDepartement(department);
        }

        @GetMapping("/{id}")
        public Departement RetrieveDepartment(@PathVariable Integer id) {
            return departementService.retrieveDepartement(id);
        }

        @PutMapping("/{id}")
        public Departement updateDepartment(@PathVariable Integer id, @RequestBody Departement department) {
            // Make sure the department ID in the URL matches the one in the request body
            if (!id.equals(department.getIdDepart())) {
                throw new IllegalArgumentException("Department ID in the URL does not match the request body.");
            }
            return departementService.updateDepartement(department);
        }

        @DeleteMapping("/{id}")
        public void deleteDepartment(@PathVariable Integer id) {
            departementService.deleteDepartement(id);
        }
    }


