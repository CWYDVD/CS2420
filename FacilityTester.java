package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lab01.DiffUtil;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * This class contains tests for Facility.
 *
 * @author Eric Heisler and Mi Zeng and Aiden MaxWell
 * @version Jan 22, 2024
 */
public class FacilityTester {

	private Facility emptyFacility, verySmallFacility, smallFacility, largeFacility, largeEmptyFacility;
	private UHealthID uHID1, uHID2, uHID3;
	private GregorianCalendar date1, date2, date3;

	//@BeforeEach   ---- System problem
	// void setUp() throws Exception {
	
	public void setUp() throws Exception{

		uHID1 = new UHealthID("AAAA-1111");
		uHID2 = new UHealthID("BCBC-2323");
		uHID3 = new UHealthID("HRHR-7654");

		date1 = new GregorianCalendar(2023, 0, 1);
		date2 = new GregorianCalendar(2023, 3, 17);
		date3 = new GregorianCalendar(2022, 8, 21);

		emptyFacility = new Facility();

		verySmallFacility = new Facility();
		verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2));
		verySmallFacility.addPatient(new CurrentPatient("Riley", "Nguyen", uHID3, 9879876, date3));

		smallFacility = new Facility();
		smallFacility.addAll("src/assign02/small_patient_list.txt");
		
		//Facility[] facilities = new Facility[1000];
		largeFacility = new Facility();
		Random random = new Random();
		for (int i = 0; i < 10; i ++) {
			for (int j = 0; j < 100; j++)
				largeFacility.addPatient(new CurrentPatient(generateName(), generateName(), new UHealthID(generateUHealthID()), i, new GregorianCalendar(1970 + random.nextInt(54), random.nextInt(11), random.nextInt(29))));
		}
		
	}
	
	private static String generateName() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		String name = "";
		for(int i=random.nextInt(9) + 1; i > 0; i--) {
			name += alphabet.charAt(random.nextInt(25));
		}
		return name;
	}
	
	private static String generateUHealthID() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		String UID = "";
		for(int i = 0; i < 4; i++) {
			UID += alphabet.charAt(random.nextInt(25));
		}
		UID += "-";
		for(int i = 0; i < 4; i++) {
			UID += random.nextInt(9);
		}
		return UID;
	}
	// Empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() throws Exception {
		setUp();
		assertNull(emptyFacility.lookupByUHID(uHID1));
	}

	@Test
	public void testEmptyLookupPhysician() throws Exception {
		setUp();
		ArrayList<CurrentPatient> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptySetVisit() throws Exception {
		// ensure no exceptions thrown
		setUp();
		emptyFacility.setLastVisit(uHID2, date3);
	}

	@Test
	public void testEmptySetPhysician() throws Exception {
		// ensure no exceptions thrown
		setUp();
		emptyFacility.setPhysician(uHID2, 1010101);
	}

	@Test
	public void testEmptyGetInactivePatients() throws Exception {
		setUp();
		ArrayList<CurrentPatient> patients = emptyFacility.getInactivePatients(date3);
		assertEquals(0, patients.size());
	}

	// Very small facility tests ---------------------------------------------------

	@Test
	public void testVerySmallLookupUHID() throws Exception {
		setUp();
		Patient expected = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
		CurrentPatient actual = verySmallFacility.lookupByUHID(new UHealthID("BCBC-2323"));
		assertEquals(expected, actual);
	}

	@Test
	public void testVerySmallLookupPhysicianCount() throws Exception {
		setUp();
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(1, actualPatients.size());
	}

	@Test
	public void testVerySmallLookupPhysicianPatient() throws Exception {
		setUp();
		Patient expectedPatient = new Patient("Riley", "Nguyen", new UHealthID("HRHR-7654"));
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(expectedPatient, actualPatients.get(0));
	}

	@Test
	public void testVerySmallAddNewPatient() throws Exception {
		setUp();
		assertTrue(verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", new UHealthID("BBBB-2222"), 1010101, date1)));
	}

	@Test
	public void testVerySmallUpdatePhysician() throws Exception {
		setUp();
		verySmallFacility.lookupByUHID(uHID1).updatePhysician(9090909);
		CurrentPatient patient = verySmallFacility.lookupByUHID(uHID1);
		assertEquals(9090909, patient.getPhysician());
	}

	// Small facility tests -------------------------------------------------------------------------

	@Test
	public void testSmallLookupPhysicianCount() throws Exception {
		setUp();
		ArrayList<CurrentPatient> actualPatients = smallFacility.lookupByPhysician(8888888);
		assertEquals(2, actualPatients.size());
	}

	@Test
	public void testSmallLookupPhysicianPatient() throws Exception {
		setUp();
		Patient expectedPatient1 = new Patient("Kennedy", "Miller", new UHealthID("QRST-3456"));
		Patient expectedPatient2 = new Patient("Taylor", "Miller", new UHealthID("UVWX-7890"));

		ArrayList<CurrentPatient> actualPatients = smallFacility.lookupByPhysician(8888888);
		assertTrue(actualPatients.contains(expectedPatient1) && actualPatients.contains(expectedPatient2));
	}

	@Test
	public void testSmallGetInactivePatients() throws Exception {
		setUp();
		ArrayList<CurrentPatient> actual = smallFacility.getInactivePatients(new GregorianCalendar(2020, 0, 0));
		assertEquals(9, actual.size());
	}

	@Test
	public void testSmallGetPhysicianList() throws Exception {
		setUp();
		ArrayList<Integer> actual = smallFacility.getPhysicianList();
		assertEquals(7, actual.size());
	}
	
	// Larger Facility tests  (add by Mi Zeng and Aiden Maxwell) -------------------
	@Test
	public void testLargeGetPhysicianList() throws Exception {
		setUp();
		ArrayList<Integer> actual = largeFacility.getPhysicianList();
		assertEquals(10, actual.size());
		
	}
	
//	@Test
//	public void testLargeEmpty() throws Exception {
//		setUp();
//		Facility[] facilities = new Facility[1000];
//		
//		
//	}

		
		
}
