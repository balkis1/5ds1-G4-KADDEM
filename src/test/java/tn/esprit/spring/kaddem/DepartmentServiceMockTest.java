package tn.esprit.spring.kaddem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
public class DepartmentServiceMockTest {
    @Mock
    private IDepartementService departementService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }
    @Test
    public void testAddDepartment(){
        Departement department = new Departement();
        department.setIdDepart(123); // Set the department ID to 123
        department.setNomDepart("Computer Science"); // Set the department name
        /*Set<Etudiant> etudiants = new HashSet<>(); // Create a set of etudiants
        etudiants.add(etudiant); // Add the previously created etudiant to the set
        department.setEtudiants(etudiants); // Set the etudiants for the department*/
        //Departement result = departementService.addDepartement(department);

        // Define the behavior of the mocked etudiantService
        when(departementService.addDepartement(any(Departement.class))).thenReturn(department);
        // Test the service that depends on the mocked etudiantService
        Departement result = departementService.addDepartement(department);

        // Verify that the mocked service was called with the correct arguments
        verify(departementService, times(1)).addDepartement(any(Departement.class));

        // Check if the result matches the expected values
        assertEquals("Computer Science" , result.getNomDepart());
    }
    @Test
    public void testRetrieveAllDepartments() {
        // Define the behavior of the mocked etudiantService
        when(departementService.retrieveAllDepartements()).thenReturn(Arrays.asList(new Departement(), new Departement()));
        // Test the service that depends on the mocked etudiantService
        List<Departement> result = departementService.retrieveAllDepartements();

        // Verify that the mocked service was called
        verify(departementService, times(1)).retrieveAllDepartements();

        assertEquals(2, result.size());
    }
    @Test
    public void testUpdateDepartment() {
        // Create a "Department" object with specific attributes
        Departement department = new Departement();

        department.setIdDepart(123); // Set the department ID to 123
        department.setNomDepart("Computer Science");
        // Define the behavior of the mocked etudiantService
        when(departementService.updateDepartement(any(Departement.class))).thenReturn(department);

        // Test the service that depends on the mocked etudiantService
        Departement result = departementService.updateDepartement(department);
        // Verify that the mocked service was called with the correct arguments
        verify(departementService, times(1)).updateDepartement(any(Departement.class));
        // Check if the result matches the expected values
        // Check if the result matches the expected values
        assertEquals("Computer Science" , result.getNomDepart());}

    @Test
    public void testRemoveDepartment() {
        // Define the behavior of the mocked etudiantService
        doNothing().when(departementService).deleteDepartement(123);
// Test the service that depends on the mocked etudiantService
        departementService.deleteDepartement(123);
        // Verify that the mocked service was called with the correct arguments
        verify(departementService, times(1)).deleteDepartement(123);
    }}
