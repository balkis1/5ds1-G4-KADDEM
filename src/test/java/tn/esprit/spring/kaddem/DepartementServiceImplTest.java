package tn.esprit.spring.kaddem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class DepartementServiceImplTest {
    @Autowired
    private IDepartementService departementService;


    @Test
    @Order(1)
    void testAddDepartment(){
        Departement department = new Departement();
        department.setIdDepart(123); // Set the department ID to 123
        department.setNomDepart("Computer Science"); // Set the department name
        Departement result = departementService.addDepartement(department);

    assertNotNull(result.getIdDepart());
    assertEquals("Computer Science" , result.getNomDepart());
    }

    @Test
    @Order(2)
    void testUpdateDepartment() {
        // Create a department to update
        Integer existingDepartementId=123;

        Departement existingDepartement =departementService.retrieveDepartement(existingDepartementId);


        // Update the department name
        existingDepartement.setNomDepart("New Computer Science");
        Departement updatedDepartment = departementService.updateDepartement(existingDepartement);
         Departement retriveUpdatedDepartement = departementService.retrieveDepartement(existingDepartementId);

         assertEquals("UpdatedName", retriveUpdatedDepartement.getNomDepart());
    }

    @Test
    @Order(3)
    void testRetrieveDepartment() {
        // Create a department
        Integer existingDepartementId = 123;

        // Retrieve the department by ID
        Departement retrievedDepartment = departementService.retrieveDepartement(existingDepartementId);

        assertEquals("Computer Science", retrievedDepartment.getNomDepart());
    }

    @Test
    @Order(4)
    void testDeleteDepartment() {

        Integer existingDepartementId= 123;
        departementService.deleteDepartement(existingDepartementId);
        Departement deletedDepartement = departementService.retrieveDepartement(existingDepartementId);
        assertNull(deletedDepartement);

    }
}




