package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * This class contains tests for FacilityGeneric.
 *
 * @author Eric Heisler and Mi Zeng and Aiden MaxWell
 * @version Jan 22, 2024
 */
public class FacilityGenericTester {
	// Generic Facility
	private FacilityGeneric<Integer> uNIDFacility, emptyFacility, phase3Facility;
	private FacilityGeneric<UHealthID> uHIDFacility;
	private FacilityGeneric<String> nameFacility;
	// IDs, dates, names
	private UHealthID[] uHIDs1, uHIDs2;
	private GregorianCalendar[] dates;
	private String[] firstNames, lastNames, physicianNames;
	// For phase 3
	private UHealthID p3id1, p3id2, p3id3, p3id4;
	private GregorianCalendar p3date1, p3date2, p3date3, p3date4;

	// Don't change these numbers. It will affect some tests.
	int nPatients = 20;
	int nPhysicians = 8;

	// A private helper to generate UHIDs
	private UHealthID[] generateUHIDs(String prefix, int howMany) {
		UHealthID[] ids = new UHealthID[howMany];
		for (int i = 0; i < howMany; i++)
			ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i));
		return ids;
	}

	// A private helper to generate dates
	private GregorianCalendar[] generateDates(int howMany) {
		GregorianCalendar[] dates = new GregorianCalendar[howMany];
		for (int i = 0; i < howMany; i++)
			dates[i] = new GregorianCalendar(2000 + i%22, i%12, i%28);
		return dates;
	}

	// A private helper to generate names
	private String[] generateNames(int howMany, int a, int b) {
		String[] names = new String[howMany];
		for (int i = 0; i < howMany; i++)
			names[i] = (char)('A' + (i+a) % 26) + "" + (char)('a' + (b*i) % 26);
		return names;
	}

	public void setUp() throws Exception {

		uHIDs1 = generateUHIDs("PATS", nPatients); // for patients
		uHIDs2 = generateUHIDs("DOCS", nPhysicians); // for physicians

		dates = generateDates(nPatients);

		firstNames = generateNames(nPatients, 1, 2);
		lastNames = generateNames(nPatients, 5, 3);
		physicianNames = generateNames(nPhysicians, 10, 4);

		uNIDFacility = new FacilityGeneric<Integer>();
		uHIDFacility = new FacilityGeneric<UHealthID>();
		nameFacility = new FacilityGeneric<String>();
		emptyFacility = new FacilityGeneric<Integer>();
		phase3Facility = new FacilityGeneric<Integer>();

		for (int i = 0; i < nPatients; i++) {
			uNIDFacility.addPatient(new CurrentPatientGeneric<Integer>(
										firstNames[i], lastNames[i],
										uHIDs1[i], 1234567 + i%nPhysicians, dates[i]));
			uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(
										firstNames[i], lastNames[i],
										uHIDs1[i], uHIDs2[i%nPhysicians], dates[i]));
			nameFacility.addPatient(new CurrentPatientGeneric<String>(
										firstNames[i], lastNames[i],
										uHIDs1[i], physicianNames[i%nPhysicians], dates[i]));
		}

		p3id1 = new UHealthID("XXXX-1111");
 		p3id2 = new UHealthID("BBBB-1111");
 		p3id3 = new UHealthID("FFFF-1111");
 		p3id4 = new UHealthID("BBBB-2222");
 		p3date1 = new GregorianCalendar(2019, 1, 5);
 		p3date2 = new GregorianCalendar(2019, 1, 4);
 		p3date3 = new GregorianCalendar(2019, 1, 3);
 		p3date4 = new GregorianCalendar(2019, 1, 2);

		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("XXXX-1111"), 7, new GregorianCalendar(2019, 1, 5)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "B", new UHealthID("BBBB-1111"), 7, new GregorianCalendar(2019, 1, 4)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("A", "C", new UHealthID("FFFF-1111"), 7, new GregorianCalendar(2019, 1, 3)));
		phase3Facility.addPatient(new CurrentPatientGeneric<Integer>("R", "T", new UHealthID("BBBB-2222"), 7, new GregorianCalendar(2019, 1, 2)));

		// FILL IN -- Extend this tester to add more tests for the facilities above,
		// as well as to create and test other facilities.
	}

	// empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() throws Exception{
		setUp();
		assertNull(emptyFacility.lookupByUHID(uHIDs1[0]));
	}

	@Test
	public void testEmptyLookupPhysician() throws Exception{
		setUp();
		ArrayList<CurrentPatientGeneric> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
	}

	@Test
	public void testEmptySetVisit() throws Exception {
		// ensure no exceptions thrown
		setUp();
		emptyFacility.setLastVisit(uHIDs1[0], dates[3]);
	}

	@Test
	public void testEmptySetPhysician() throws Exception {
		// ensure no exceptions thrown
		setUp();
		emptyFacility.setPhysician(uHIDs1[0], 1010101);
	}

	@Test
	public void testEmptyGetInactivePatients() {
		ArrayList<CurrentPatientGeneric> patients = emptyFacility.getInactivePatients(dates[4]);
		assertEquals(0, patients.size());
	}

	// uNID Facility tests --------------------------------------------------------

	@Test
	public void testUNIDLookupPhysicianCount() throws Exception {
		setUp();
		ArrayList<CurrentPatientGeneric> actualPatients = uNIDFacility.lookupByPhysician(1234568);
		assertEquals(3, actualPatients.size());
	}

	@Test
	public void testUNIDLookupPhysicianPatient() throws Exception {
		setUp();
		Patient expectedPatient = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()));
		ArrayList<CurrentPatientGeneric> actualPatients = uNIDFacility.lookupByPhysician(1234568);
		assertEquals(expectedPatient, actualPatients.get(0));
	}

	// UHealthID facility tests ---------------------------------------------------

	@Test
	public void testUHIDLookupUHID() throws Exception {
		setUp();
		Patient expected = new Patient(firstNames[0], lastNames[0], new UHealthID(uHIDs1[0].toString()));
		CurrentPatientGeneric<UHealthID> actual = uHIDFacility.lookupByUHID(new UHealthID(uHIDs1[0].toString()));
		assertEquals(expected, actual);
	}

	@Test
	public void testUHIDLookupPhysicianCount() throws Exception {
		setUp();
		Patient expectedPatient = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()));
		ArrayList<CurrentPatientGeneric> actualPatients = uHIDFacility.lookupByPhysician(uHIDs2[1]);
		assertEquals(3, actualPatients.size());
	}

	@Test
	public void testUHIDAddDuplicatePatient() throws Exception {
		setUp();
		assertFalse(uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()), uHIDs2[1], dates[1])));
	}

	@Test
	public void testUHIDAddNewPatient() throws Exception {
		setUp();
		assertTrue(uHIDFacility.addPatient(new CurrentPatientGeneric<UHealthID>(firstNames[1], lastNames[1], new UHealthID("ZZZZ-9999"), uHIDs2[1], dates[1])));
	}

	@Test
	public void testUHIDUpdatePhysician() throws Exception {
		setUp();
		uHIDFacility.lookupByUHID(uHIDs1[2]).updatePhysician(uHIDs2[0]);
		CurrentPatientGeneric<UHealthID> patient = uHIDFacility.lookupByUHID(uHIDs1[2]);
		assertEquals(uHIDs2[0], patient.getPhysician());
	}

	// name facility tests -------------------------------------------------------------------------

	@Test
	public void testNameLookupPhysician() throws Exception {
		setUp();
		Patient expectedPatient1 = new Patient(firstNames[1], lastNames[1], new UHealthID(uHIDs1[1].toString()));
		Patient expectedPatient2 = new Patient(firstNames[9], lastNames[9], new UHealthID(uHIDs1[9].toString()));

		ArrayList<CurrentPatientGeneric> actualPatients = nameFacility.lookupByPhysician(physicianNames[1]);
		assertTrue(actualPatients.contains(expectedPatient1) && actualPatients.contains(expectedPatient2));
	}

	@Test
	public void testNameGetInactivePatients() throws Exception {
		setUp();
		ArrayList<CurrentPatientGeneric> actual = nameFacility.getInactivePatients(new GregorianCalendar(2010, 0, 0));
		assertEquals(10, actual.size());
	}

	@Test
	public void testNameGetPhysicianList() throws Exception {
		setUp();
		ArrayList<String> actual = nameFacility.getPhysicianList();
		assertEquals(8, actual.size());
	}

	// phase 3 tests ---------------------------------------------------------------------------

	@Test
 	public void testOrderedByUHIDCount() throws Exception {
		setUp();
 		ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getOrderedByUHealthID();
 		assertEquals(4, actual.size());
 	}

 	@Test
 	public void testOrderedByUHIDOrder() throws Exception {
 		setUp();
 		ArrayList<CurrentPatientGeneric<Integer>> actual = phase3Facility.getOrderedByUHealthID();
 		assertTrue(actual.get(3).equals(new CurrentPatientGeneric<Integer>("A", "B", p3id1, 7, p3date1)) &&
 				actual.get(0).equals(new CurrentPatientGeneric<Integer>("A", "B", p3id2, 7, p3date2)) &&
 				actual.get(2).equals(new CurrentPatientGeneric<Integer>("A", "C", p3id3, 7, p3date3)) &&
 				actual.get(1).equals(new CurrentPatientGeneric<Integer>("R", "T", p3id4, 7, p3date4)));
 	}
}
