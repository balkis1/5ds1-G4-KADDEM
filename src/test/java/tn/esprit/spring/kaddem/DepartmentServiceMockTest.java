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


        // Define the behavior of the mocked departementService
        when(departementService.addDepartement(any(Departement.class))).thenReturn(department);
        Departement result = departementService.addDepartement(department);

        // Verify that the mocked service was called with the correct arguments
        verify(departementService, times(1)).addDepartement(any(Departement.class));

        // Check if the result matches the expected values
        assertEquals("Computer Science" , result.getNomDepart());
    }
    @Test
    public void testRetrieveAllDepartments() {
        =
        when(departementService.retrieveAllDepartements()).thenReturn(Arrays.asList(new Departement(), new Departement()));
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
        when(departementService.updateDepartement(any(Departement.class))).thenReturn(department);
        Departement result = departementService.updateDepartement(department);
        verify(departementService, times(1)).updateDepartement(any(Departement.class));
        assertEquals("Computer Science" , result.getNomDepart());}

    @Test
    public void testRemoveDepartment() {
        doNothing().when(departementService).deleteDepartement(123);
        departementService.deleteDepartement(123);
        verify(departementService, times(1)).deleteDepartement(123);
    }}
