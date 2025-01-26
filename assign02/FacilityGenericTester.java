package assign02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

/**
 * This class contains tests for Facility.
 * 
 * @author CS 2420 course staff and Maxwell and David
 * @version January 20, 2025
 */
public class FacilityGenericTester {

	private FacilityGeneric<Integer> emptyFacility, verySmallFacility, smallFacility;
	private FacilityGeneric<Integer> largeFacility;
	private FacilityGeneric<String> emptyFacility2, verySmallFacility2;
	private UHealthID uHID1, uHID2, uHID3;
	private GregorianCalendar date1, date2, date3;
	
	@BeforeEach
	public void setUp() throws Exception {
		
		uHID1 = new UHealthID("AAAA-1111");
		uHID2 = new UHealthID("BCBC-2323");
		uHID3 = new UHealthID("HRHR-7654");
		
		date1 = new GregorianCalendar(2023, 0, 1);
		date2 = new GregorianCalendar(2023, 3, 17);
		date3 = new GregorianCalendar(2022, 8, 21);
		
		emptyFacility = new FacilityGeneric<>();
		emptyFacility2 = new FacilityGeneric<>();
		
		verySmallFacility = new FacilityGeneric<>();
		verySmallFacility.addPatient(new CurrentPatientGeneric<Integer>("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatientGeneric<Integer>("Drew", "Hall", uHID2, 3232323, date3));
		verySmallFacility.addPatient(new CurrentPatientGeneric<Integer>("Riley", "Nguyen", uHID3, 9879876, date2));verySmallFacility2 = new FacilityGeneric<>();
		verySmallFacility2.addPatient(new CurrentPatientGeneric<String>("Jane", "Doe", uHID1, "name1", date1));
		verySmallFacility2.addPatient(new CurrentPatientGeneric<String>("Drew", "Hall", uHID2, "name2", date2));
		verySmallFacility2.addPatient(new CurrentPatientGeneric<String>("Riley", "Nguyen", uHID3, "name3", date3));
		
		smallFacility = new FacilityGeneric<>();
		smallFacility.addAll(readFromFile("src/assign02/small_patient_list.txt"));
		
		// Extend this tester to add more tests for the facilities above, 
		// as well as to create and test larger facilities.
		// (HINT: For a larger facility, use the helpers at the end of this file to
		//        generate names, IDs, and dates.)

		// How to test on largeFacility if we are having random names and id? do we just check numbers?
		largeFacility = new FacilityGeneric<Integer>();
		String[] firstNames = generateNames(1000);
		String[] lastNames = generateNames(1000);
		UHealthID[] UHealthIDs = generateUHIDs(1000);
		GregorianCalendar[] dates = generateDates(1000);
		Random random = new Random();
		for (int i = 1; i <= 1000; i++) {
			int physician = 1000000 + random.nextInt(9000000);
			largeFacility.addPatient(new CurrentPatientGeneric<Integer>(firstNames[i], lastNames[i], UHealthIDs[i], physician, dates[i]));
		}
	}
	
	// Empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookupByUHID(uHID1));
		assertNull(emptyFacility2.lookupByUHID(uHID1));
	}
	
	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
		ArrayList<CurrentPatientGeneric<String>> patients2 = emptyFacility2.lookupByPhysician("string");
		assertEquals(0, patients2.size());
	}
	
	@Test
	public void testEmptySetVisit() {
		// ensure no exceptions thrown
		emptyFacility.setLastVisit(uHID2, date3);
	}

	@Test
	public void testEmptySetPhysician() {
		// ensure no exceptions thrown
		emptyFacility.setPhysician(uHID2, 1010101);
		emptyFacility2.setPhysician(uHID2, "string");
	}
	
	@Test
	public void testEmptyGetRecentPatients() {
		ArrayList<CurrentPatientGeneric<Integer>> patients = emptyFacility.getRecentPatients(date3);
		assertEquals(0, patients.size());
		ArrayList<CurrentPatientGeneric<String>> patients2 = emptyFacility2.getRecentPatients(date3);
		assertEquals(0, patients2.size());
	}

	// Very small facility tests ---------------------------------------------------

	@Test
	public void testVerySmallLookupUHID() {
		Patient expected = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
		CurrentPatientGeneric<Integer> actual = verySmallFacility.lookupByUHID(new UHealthID("BCBC-2323"));
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerySmallLookupPhysicianCount() {
		ArrayList<CurrentPatientGeneric<Integer>> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(1, actualPatients.size());

		ArrayList<CurrentPatientGeneric<String>> actualPatients2 = verySmallFacility2.lookupByPhysician("name1");
		assertEquals(1, actualPatients2.size());
	}
	
	@Test
	public void testVerySmallLookupPhysicianPatient() {
		Patient expectedPatient = new Patient("Riley", "Nguyen", new UHealthID("HRHR-7654"));
		ArrayList<CurrentPatientGeneric<Integer>> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(expectedPatient, actualPatients.get(0));

		Patient expectedPatient2 = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
		ArrayList<CurrentPatientGeneric<String>> actualPatients2 = verySmallFacility2.lookupByPhysician("name2");
		assertEquals(expectedPatient2, actualPatients2.get(0));
	}
	
	@Test
	public void testVerySmallAddNewPatient() {
		assertTrue(verySmallFacility.addPatient(new CurrentPatientGeneric<Integer>("Jane", "Doe", new UHealthID("BBBB-2222"), 1010101, date1)));

		assertTrue(verySmallFacility2.addPatient(new CurrentPatientGeneric<String>("Jane", "Doe", new UHealthID("BBBB-2222"), "name5", date1)));
	}
	
	@Test
	public void testVerySmallUpdatePhysician() {
		verySmallFacility.lookupByUHID(uHID1).updatePhysician(9090909);
		CurrentPatientGeneric<Integer> patient = verySmallFacility.lookupByUHID(uHID1);
		assertEquals(9090909, patient.getPhysician());

		verySmallFacility2.lookupByUHID(uHID1).updatePhysician("name4");
		CurrentPatientGeneric<String> patient2 = verySmallFacility2.lookupByUHID(uHID1);
		assertEquals("name4", patient2.getPhysician());
	}
	
	// Small facility tests -------------------------------------------------------------------------

	/**
	 * Testing how many current patient that is newer than a given date
	 */
	@Test
	public void testSmallGetRecentPatients() {
		ArrayList<CurrentPatientGeneric<Integer>> actual = smallFacility.getRecentPatients(new GregorianCalendar(2020, 0, 0));
		assertEquals(2, actual.size());
	}
	
	// Add more tests for small facility

	/**
	 * Testing adding new patient to small facility list
	 */
	@Test
	public void testAddOnePatientToSmallFacility() {
		// Testing to add an existed patient
		CurrentPatientGeneric<Integer> newPatient1 = new CurrentPatientGeneric<Integer>("Blake", "Bird", new UHealthID("JHSD-7483"), 0000000, new GregorianCalendar(2000,2,3));
		assertFalse(smallFacility.addPatient(newPatient1));

		// Testing to add a new patient
		CurrentPatientGeneric<Integer> newPatient2 = new CurrentPatientGeneric<Integer>("David", "Chen", new UHealthID("CDVD-2003"), 1213800, new GregorianCalendar(2003,9,29));
		assertTrue(smallFacility.addPatient(newPatient2));
	}

	/**
	 * Testing size after adding a list of new patients to a list of current patients
	 */
	@Test //Needed help
	public void testAddAllPatientsToSmallFacility() {
		// Adding verySmallFacility patients into smallFacilitye
		ArrayList<CurrentPatientGeneric<Integer>> smallFacilityAllPatients = smallFacility.getRecentPatients(new GregorianCalendar(200, 0, 0));
		ArrayList<CurrentPatientGeneric<Integer>> verySmallFacilityALlPatients = verySmallFacility.getRecentPatients(new GregorianCalendar(200, 0, 0));

		verySmallFacilityALlPatients.addAll(smallFacilityAllPatients); // why wont work?
		int count = verySmallFacilityALlPatients.size();

		assertEquals(14, count);
	}
	/**
	 * Testing the contant of patient list after adding it into the list
	 */
//	@Test

	/**
	 * Testing retrieving the patient with given UHealthID
	 */
	@Test
	public void testSmallFacilityLookUpByUHID() {
		// If the patient is in the system
		Patient expectExist = new Patient("Samantha", "Schooner", new UHealthID("OUDC-6143"));
		Patient actualExist = smallFacility.lookupByUHID(new UHealthID("OUDC-6143"));
		assertEquals(expectExist, actualExist);

		// If the patient is not in the system
		Patient expectNotExist = new Patient("David", "Chen", new UHealthID("OUDC-2003"));
		Patient actualNotExist = smallFacility.lookupByUHID(new UHealthID("OUDC-2003"));
		assertEquals(null, actualNotExist);
	}

	/**
	 * Testing retrieving the numbers patient with give physician
	 */
	@Test
	public void testSmallFacilityLookupByPhysicianCount() {
		// Check the physician's list of patients of all time
		ArrayList<CurrentPatientGeneric<Integer>> allPatients = smallFacility.lookupByPhysician(1111111);
		assertEquals(2, allPatients.size());

		// Check the physician's list of patients after removed all of patients
		allPatients.remove(0);
		allPatients.remove(0);
		assertEquals(0, allPatients.size());
	}

	/**
	 * Testing retrieving the name of patients with give physician
	 */
	@Test
	public void testSmallFacilityLookupByPhysician() {
		// Look up original patient list
		Patient patient1 = new Patient("Samantha", "Schooner", new UHealthID("OUDC-6143"));
		Patient patient2 = new Patient("Amy", "Gilmer", new UHealthID("VBIU-1616"));
		ArrayList<CurrentPatientGeneric<Integer>> allPatients = smallFacility.lookupByPhysician(1111111);
		// First to check the size of list
		assertEquals(2, allPatients.size());
		// Second to check the content of the list
		assertEquals(patient1, allPatients.get(0));
		assertEquals(patient2, allPatients.get(1));
		// Check the patient list after remove one
		allPatients.remove(0);
		assertEquals(patient2, allPatients.get(0));
	}

	/**
	 * Testing retrieving the patient list after a certain date (size and content)
	 */
	@Test
	public void testSmallFacilityGetRecentPatients() {
		// Retrieve all the patients before 2015
		ArrayList<CurrentPatientGeneric<Integer>> recentPatientsExpected = new ArrayList<>();
		ArrayList<CurrentPatientGeneric<Integer>> recentPatientsActual = smallFacility.getRecentPatients(new GregorianCalendar(2015, 0, 0));
		recentPatientsExpected.add(new CurrentPatientGeneric<Integer>("John", "Fuller", new UHealthID("PNRB-0953"), 1234123, new GregorianCalendar(2018, 9, 4)));
		recentPatientsExpected.add(new CurrentPatientGeneric<Integer>("Mia", "Nakamoto", new UHealthID("NRUT-4467"), 1234123, new GregorianCalendar(2023, 3, 3)));
		recentPatientsExpected.add(new CurrentPatientGeneric<Integer>("Jin", "Young", new UHealthID("QWYU-0303"),  6786786 , new GregorianCalendar(2017 , 2, 2)));
		recentPatientsExpected.add(new CurrentPatientGeneric<Integer>("Jordan", "Jones", new UHealthID("AEHK-3524"),  6786786 , new GregorianCalendar(2019 , 9, 19)));
		recentPatientsExpected.add(new CurrentPatientGeneric<Integer>("Abdul", "Alcada", new UHealthID("ITER-7777"),  7777777 , new GregorianCalendar(2017 , 7, 7)));
		recentPatientsExpected.add(new CurrentPatientGeneric<Integer>("Chang-Hau", "Hsu", new UHealthID("MOON-3769"),  9999999 , new GregorianCalendar(2022 , 6, 6)));
		assertEquals(recentPatientsExpected, recentPatientsActual);

		// Retrieve all the patients before 2017.2.2 should not include 2017.2.2 patients
		ArrayList<CurrentPatientGeneric<Integer>> recentPatientsExpectedAfter2017 = new ArrayList<>();
		ArrayList<CurrentPatientGeneric<Integer>> recentPatientsActualAfter2017 = smallFacility.getRecentPatients(new GregorianCalendar(2017, 2, 2));
		recentPatientsExpectedAfter2017.add(new CurrentPatientGeneric<Integer>("John", "Fuller", new UHealthID("PNRB-0953"), 1234123, new GregorianCalendar(2018, 9, 4)));
		recentPatientsExpectedAfter2017.add(new CurrentPatientGeneric<Integer>("Mia", "Nakamoto", new UHealthID("NRUT-4467"), 1234123, new GregorianCalendar(2023, 3, 3)));
		recentPatientsExpectedAfter2017.add(new CurrentPatientGeneric<Integer>("Jordan", "Jones", new UHealthID("AEHK-3524"),  6786786 , new GregorianCalendar(2019 , 9, 19)));
		recentPatientsExpectedAfter2017.add(new CurrentPatientGeneric<Integer>("Abdul", "Alcada", new UHealthID("ITER-7777"),  7777777 , new GregorianCalendar(2017 , 7, 7)));
		recentPatientsExpectedAfter2017.add(new CurrentPatientGeneric<Integer>("Chang-Hau", "Hsu", new UHealthID("MOON-3769"),  9999999 , new GregorianCalendar(2022 , 6, 6)));
		assertEquals(recentPatientsExpectedAfter2017, recentPatientsActualAfter2017);
	}

	/**
	 * Testing retrieving a list of physicians assigned to patients at small facility
	 */
	@Test
	public void testGetPhysicianListSmallFacility() {
		ArrayList<Integer> physiciansExpected = new ArrayList<>();
		ArrayList<Integer> physiciansActual = smallFacility.getPhysicianList();
		physiciansExpected.add(0000000);
		physiciansExpected.add(1111111);
		physiciansExpected.add(1234123);
		physiciansExpected.add(8888888);
		physiciansExpected.add(6786786);
		physiciansExpected.add(7777777);
		physiciansExpected.add(9999999);

		Collections.sort(physiciansExpected);
		Collections.sort(physiciansActual);
		assertEquals(physiciansExpected, physiciansActual);

		ArrayList<String> physiciansExpected2 = new ArrayList<>();
		ArrayList<String> physiciansActual2 = verySmallFacility2.getPhysicianList();
		physiciansExpected2.add("name1");
		physiciansExpected2.add("name2");
		physiciansExpected2.add("name3");
		physiciansExpected2.add("name4");

		Collections.sort(physiciansExpected2);
		Collections.sort(physiciansActual2);
		assertEquals(physiciansExpected2, physiciansActual2);
	}

	/**
	 * Testing setting the physician of a patient with the given UHealthID.
	 */
	@Test
	public void testSetPhysicianSmallFacility() {
		// Setting a physician to an existed patient
		smallFacility.setPhysician(new UHealthID("PNRB-0953"), 7777777);
		ArrayList<CurrentPatientGeneric<Integer>> existPatientsExpected = smallFacility.lookupByPhysician(7777777);
		ArrayList<CurrentPatientGeneric<Integer>> existPatientsActual = new ArrayList<>();
		existPatientsActual.add(new CurrentPatientGeneric<Integer>("John", "Fuller", new UHealthID("PNRB-0953"), 1234123, new GregorianCalendar(2018, 9, 4)));
		existPatientsActual.add(new CurrentPatientGeneric<Integer>("Abdul", "Alcada", new UHealthID("ITER-7777"),  7777777 , new GregorianCalendar(2017 , 7, 7)));
		assertEquals(existPatientsExpected, existPatientsActual);

		// Setting a physician to a non-existing patient
		smallFacility.setPhysician(new UHealthID("NZZT-4460"), 9999999);
		ArrayList<CurrentPatientGeneric<Integer>> nonExistPatientsExpected = smallFacility.lookupByPhysician(9999999);
		ArrayList<CurrentPatientGeneric<Integer>> nonExistPatientsActual = new ArrayList<>();
		nonExistPatientsActual.add(new CurrentPatientGeneric<Integer>("Chang-Hau", "Hsu", new UHealthID("MOON-3769"),  9999999 , new GregorianCalendar(2022 , 6, 6)));
		assertEquals(nonExistPatientsExpected, nonExistPatientsActual);
	}

	/**
	 * Testing setting the last visit date of a patient with the given UHealthID.
	 */
	@Test
	public void testSetLastVisitDaySmallFacility() {
		// Setting last day of visit for an existed patient
		smallFacility.setLastVisit(new UHealthID("PNRB-0953"), new GregorianCalendar(1910, 1,23));
		CurrentPatientGeneric<Integer> lastDayActual = smallFacility.lookupByUHID(new UHealthID("PNRB-0953"));
		CurrentPatient lastDayExpected = new CurrentPatient("John", "Fuller", new UHealthID("PNRB-0953"), 1234123, new GregorianCalendar(1910, 1,23));
		assertEquals(lastDayExpected, lastDayActual);

		// Setting last visit day for a non-existed patient
		smallFacility.setLastVisit(new UHealthID("PPPP-0000"), new GregorianCalendar(2022, 6,6));
		ArrayList<CurrentPatientGeneric<Integer>> lastDayOf2022Acutal = smallFacility.getRecentPatients(new GregorianCalendar(2022, 6, 6));
		ArrayList<CurrentPatientGeneric<Integer>> lastDayOf2022Expected = new ArrayList<>();
		lastDayOf2022Expected.add(new CurrentPatientGeneric<Integer>("Mia", "Nakamoto", new UHealthID("NRUT-4467"), 1234123, new GregorianCalendar(2023, 3, 3)));
		assertEquals(lastDayOf2022Expected, lastDayOf2022Acutal);
	}

	// Sorting tests ------------------------------------------------------------

	/*CurrentPatientGeneric<Integer>("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatientGeneric<Integer>("Drew", "Hall", uHID2, 3232323, date2));
		verySmallFacility.addPatient(new CurrentPatientGeneric<Integer>("Riley", "Nguyen", uHID3, 9879876, date3));*/

	@Test
	public void testOrderByUHealthID() {
		ArrayList<CurrentPatientGeneric<Integer>> ordered = verySmallFacility.getOrderedPatients(new OrderByUHealthID<Integer>());
		ArrayList<CurrentPatientGeneric<Integer>> expected = new ArrayList<>();
		expected.add(new CurrentPatientGeneric<Integer>("Jane", "Doe", uHID1, 1010101, date1));
		expected.add(new CurrentPatientGeneric<Integer>("Drew", "Hall", uHID2, 3232323, date3));
		expected.add(new CurrentPatientGeneric<Integer>("Riley", "Nguyen", uHID3, 9879876, date2));
		for (int i = 0; i < 3; i++)
			assertEquals(expected.get(i), ordered.get(i));

		//Check behavior on large sizes
		ArrayList<CurrentPatientGeneric<Integer>> large_ordered = largeFacility.getOrderedPatients(new OrderByUHealthID<>());
		assertEquals(1000, large_ordered.size());
	}
		@Test
	public void testOrderByName() {
		ArrayList<CurrentPatientGeneric<Integer>> ordered = verySmallFacility.getOrderedPatients(new OrderByName<Integer>());
		ArrayList<CurrentPatientGeneric<Integer>> expected = new ArrayList<>();
		expected.add(new CurrentPatientGeneric<Integer>("Drew", "Hall", uHID2, 3232323, date3));
		expected.add(new CurrentPatientGeneric<Integer>("Jane", "Doe", uHID1, 1010101, date1));
		expected.add(new CurrentPatientGeneric<Integer>("Riley", "Nguyen", uHID3, 9879876, date2));
		for (int i = 0; i < 3; i++)
			assertEquals(expected.get(i), ordered.get(i));
		
		//Test for ties
		FacilityGeneric<Integer> uncreative = new FacilityGeneric<>();

		uncreative.addPatient(new CurrentPatientGeneric<Integer>("Drew", "Two", uHID1, 101010, date1));
		uncreative.addPatient(new CurrentPatientGeneric<Integer>("Drew", "One", uHID3, 3232323, date3));
		uncreative.addPatient(new CurrentPatientGeneric<Integer>("Drew", "Two", uHID2, 212121, date2));
		ArrayList<CurrentPatientGeneric<Integer>> ordered2 = uncreative.getOrderedPatients(new OrderByName<Integer>());
		ArrayList<CurrentPatientGeneric<Integer>> expected2 = new ArrayList<>();
		expected.add(new CurrentPatientGeneric<Integer>("Drew", "One", uHID3, 3232323, date3));
		expected.add(new CurrentPatientGeneric<Integer>("Drew", "Two", uHID1, 1010101, date1));
		expected.add(new CurrentPatientGeneric<Integer>("Drew", "Two", uHID2, 212121, date2));
		for (int i = 0; i < 3; i++)
			assertEquals(expected2.get(i), ordered2.get(i));
	}
	@Test
	public void testOrderByDate() {
		ArrayList<CurrentPatientGeneric<Integer>> ordered = verySmallFacility.getOrderedPatients(new OrderByDate<Integer>());
		ArrayList<CurrentPatientGeneric<Integer>> expected = new ArrayList<>();
		expected.add(new CurrentPatientGeneric<Integer>("Jane", "Doe", uHID1, 1010101, date1));
		expected.add(new CurrentPatientGeneric<Integer>("Riley", "Nguyen", uHID3, 9879876, date2));
		expected.add(new CurrentPatientGeneric<Integer>("Drew", "Hall", uHID2, 3232323, date3));
		for (int i = 0; i < 3; i++)
			assertEquals(expected.get(i), ordered.get(i));

		//Ties
		FacilityGeneric<Integer> busy = new FacilityGeneric<>();

		busy.addPatient(new CurrentPatientGeneric<Integer>("Third", "Entry", uHID2, 212121, date2));
		busy.addPatient(new CurrentPatientGeneric<Integer>("First", "Entry", uHID3, 101010, date1));
		busy.addPatient(new CurrentPatientGeneric<Integer>("Second", "Entry", uHID1, 3232323, date2));
		ArrayList<CurrentPatientGeneric<Integer>> ordered2 = busy.getOrderedPatients(new OrderByName<Integer>());
		ArrayList<CurrentPatientGeneric<Integer>> expected2 = new ArrayList<>();
		expected.add(new CurrentPatientGeneric<Integer>("First", "Entry", uHID3, 101010, date1));
		expected.add(new CurrentPatientGeneric<Integer>("Second", "Entry", uHID1, 3232323, date2));
		expected.add(new CurrentPatientGeneric<Integer>("Third", "Entry", uHID2, 212121, date2));
		for (int i = 0; i < 3; i++)
			assertEquals(expected2.get(i), ordered2.get(i));
	}


	// Helper methods ------------------------------------------------------------

	/**
	 * Generates unique UHealthIDs (valid for up to 260,000 IDs).
	 * 
	 * @param howMany - IDs to make
	 * @return an array of UHealthIDs
	 */
	private UHealthID[] generateUHIDs(int howMany) {
		UHealthID[] ids = new UHealthID[howMany];
		for(int i = 0; i < howMany; i++) {
			String prefix = "JKL" + (char)('A' + (i / 10000) % 26);
			ids[i] = new UHealthID(prefix + "-" + String.format("%04d", i % 10000));
		}
		return ids;
	}
	
	/**
	 * Generates dates.
	 * 
	 * @param howMany - dates to generate
	 * @return an array of dates
	 */
	private GregorianCalendar[] generateDates(int howMany) {
		GregorianCalendar[] dates = new GregorianCalendar[howMany];
		for(int i = 0; i < howMany; i++)
			dates[i] = new GregorianCalendar(2000 + i%22, i%12, i%28);
		return dates;
	}
	
	/**
	 * Generate names.
	 * 
	 * @param howMany - names to generate
	 * @return an array of names
	 */
	private String[] generateNames(int howMany) {
		String[] names = new String[howMany];
		Random rng = new Random();
		for(int i = 0; i < howMany; i++)
			names[i] = "" + (char)('A' + rng.nextInt(26)) + (char)('a' + rng.nextInt(26))
					   + (char)('a' + rng.nextInt(26)) + (char)('a' + rng.nextInt(26));
		return names;
	}
	
	/**
	 * Adds the patients specified by the input file to a list.
	 * 
	 * Assumes a strict file format:
	 * (each line) FirstName LastName ABCD-0123 u0123456 2023 05 16
	 *     
	 * Also assumes there are no duplicate patients in the file.
	 * 
	 * @param filename - full or relative path to file containing patient data
	 */
	public ArrayList<CurrentPatientGeneric<Integer>> readFromFile(String filename) {
		ArrayList<CurrentPatientGeneric<Integer>> patients = new ArrayList<CurrentPatientGeneric<Integer>>();
		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNumber = 0;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				lineNumber++;
				patients.add(parsePatient(line, lineNumber));
			}
			fileIn.close();
		}
		catch(FileNotFoundException e) {
			System.err.println(e.getMessage() + "  Patient file couldn't be opened.");
		}
		catch(ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Not all patients added to list.");
		}
		return patients;
	}
	
	/**
	 * Parses the information about a patient from file.
	 * 
	 * @param line - string to be parsed
	 * @param lineNumber - line number in file, used for error reporting (see above)
	 * @return the Patient constructed from the information
	 * @throws ParseException if file containing line is not properly formatted (see above)
	 */
	private CurrentPatientGeneric<Integer> parsePatient(String line, int lineNumber) throws ParseException {
		Scanner lineIn = new Scanner(line);
		lineIn.useDelimiter(" ");

		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("First name", lineNumber);
		}
		String firstName = lineIn.next();

		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("Last name", lineNumber);
		}
		String lastName = lineIn.next();

		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("UHealth ID", lineNumber);
		}
		String patientIDString = lineIn.next();

		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("physician", lineNumber);
		}
		String physicianString = lineIn.next();
		int physician = Integer.parseInt(physicianString.substring(1, 8));
		
		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("year of last visit", lineNumber);
		}
		String yearString = lineIn.next();
		int year = Integer.parseInt(yearString);
		
		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("month of last visit", lineNumber);
		}
		String monthString = lineIn.next();
		int month = Integer.parseInt(monthString);
		
		if(!lineIn.hasNext()) {
			lineIn.close();
			throw new ParseException("day of last visit", lineNumber);
		}
		String dayString = lineIn.next();
		int day = Integer.parseInt(dayString);
		
		GregorianCalendar lastVisit = new GregorianCalendar(year, month, day);
		
		lineIn.close();
		
		return new CurrentPatientGeneric<Integer>(firstName, lastName, new UHealthID(patientIDString), 
								physician, lastVisit);
	}
}
