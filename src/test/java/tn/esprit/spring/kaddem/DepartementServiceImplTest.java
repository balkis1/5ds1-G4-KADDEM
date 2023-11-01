package tn.esprit.spring.kaddem;
import lombok.extern.slf4j.Slf4j;
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
    void testAddDepartment(){
        Departement department = new Departement();
        department.setIdDepart(123); // Set the department ID to 123
        department.setNomDepart("Computer Science"); // Set the department name
        Departement result = departementService.addDepartement(department);

    assertNotNull(result.getIdDepart());
    assertEquals("Computer Science" , result.getNomDepart());



    }



}
