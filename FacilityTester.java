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
public class FacilityTester {

	private Facility emptyFacility, verySmallFacility, smallFacility;
	private Facility largeFacility;
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
		
		emptyFacility = new Facility();
		
		verySmallFacility = new Facility();
		verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", uHID1, 1010101, date1));
		verySmallFacility.addPatient(new CurrentPatient("Drew", "Hall", uHID2, 3232323, date2));
		verySmallFacility.addPatient(new CurrentPatient("Riley", "Nguyen", uHID3, 9879876, date3));
		
		smallFacility = new Facility();
		smallFacility.addAll(readFromFile("src/assign02/small_patient_list.txt"));
		
		// Extend this tester to add more tests for the facilities above, 
		// as well as to create and test larger facilities.
		// (HINT: For a larger facility, use the helpers at the end of this file to
		//        generate names, IDs, and dates.)

		// How to test on largeFacility if we are having random names and id? do we just check numbers?
		largeFacility = new Facility();
		String[] firstNames = generateNames(1000);
		String[] lastNames = generateNames(1000);
		UHealthID[] UHealthIDs = generateUHIDs(1000);
		GregorianCalendar[] dates = generateDates(1000);
		Random random = new Random();
		for (int i = 1; i <= 1000; i++) {
			int physician = 1000000 + random.nextInt(9000000);
			largeFacility.addPatient(new CurrentPatient(firstNames[i], lastNames[i], UHealthIDs[i], physician, dates[i]));
		}
	}
	
	// Empty Facility tests --------------------------------------------------------

	@Test
	public void testEmptyLookupUHID() {
		assertNull(emptyFacility.lookupByUHID(uHID1));
	}
	
	@Test
	public void testEmptyLookupPhysician() {
		ArrayList<CurrentPatient> patients = emptyFacility.lookupByPhysician(1010101);
		assertEquals(0, patients.size());
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
	}
	
	@Test
	public void testEmptyGetRecentPatients() {
		ArrayList<CurrentPatient> patients = emptyFacility.getRecentPatients(date3);
		assertEquals(0, patients.size());
	}

	// Very small facility tests ---------------------------------------------------

	@Test
	public void testVerySmallLookupUHID() {
		Patient expected = new Patient("Drew", "Hall", new UHealthID("BCBC-2323"));
		CurrentPatient actual = verySmallFacility.lookupByUHID(new UHealthID("BCBC-2323"));
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerySmallLookupPhysicianCount() {
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(1, actualPatients.size());
	}
	
	@Test
	public void testVerySmallLookupPhysicianPatient() {
		Patient expectedPatient = new Patient("Riley", "Nguyen", new UHealthID("HRHR-7654"));
		ArrayList<CurrentPatient> actualPatients = verySmallFacility.lookupByPhysician(9879876);
		assertEquals(expectedPatient, actualPatients.get(0));
	}
	
	@Test
	public void testVerySmallAddNewPatient() {
		assertTrue(verySmallFacility.addPatient(new CurrentPatient("Jane", "Doe", new UHealthID("BBBB-2222"), 1010101, date1)));		
	}
	
	@Test
	public void testVerySmallUpdatePhysician() {
		verySmallFacility.lookupByUHID(uHID1).updatePhysician(9090909);
		CurrentPatient patient = verySmallFacility.lookupByUHID(uHID1);
		assertEquals(9090909, patient.getPhysician());
	}
	
	// Small facility tests -------------------------------------------------------------------------

	/**
	 * Testing how many current patient that is newer than a given date
	 */
	@Test
	public void testSmallGetRecentPatients() {
		ArrayList<CurrentPatient> actual = smallFacility.getRecentPatients(new GregorianCalendar(2020, 0, 0));
		assertEquals(2, actual.size());
	}
	
	// Add more tests for small facility

	/**
	 * Testing adding new patient to small facility list
	 */
	@Test
	public void testAddOnePatientToSmallFacility() {
		// Testing to add an existed patient
		CurrentPatient newPatient1 = new CurrentPatient("Blake", "Bird", new UHealthID("JHSD-7483"), 0000000, new GregorianCalendar(2000,2,3));
		assertFalse(smallFacility.addPatient(newPatient1));

		// Testing to add a new patient
		CurrentPatient newPatient2 = new CurrentPatient("David", "Chen", new UHealthID("CDVD-2003"), 1213800, new GregorianCalendar(2003,9,29));
		assertTrue(smallFacility.addPatient(newPatient2));
	}

	/**
	 * Testing size after adding a list of new patients to a list of current patients
	 */
	@Test //Needed help
	public void testAddAllPatientsToSmallFacility() {
		// Adding verySmallFacility patients into smallFacilitye
		ArrayList<CurrentPatient> smallFacilityAllPatients = smallFacility.getRecentPatients(new GregorianCalendar(200, 0, 0));
		ArrayList<CurrentPatient> verySmallFacilityALlPatients = verySmallFacility.getRecentPatients(new GregorianCalendar(200, 0, 0));

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
		ArrayList<CurrentPatient> allPatients = smallFacility.lookupByPhysician(1111111);
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
		ArrayList<CurrentPatient> allPatients = smallFacility.lookupByPhysician(1111111);
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
		ArrayList<CurrentPatient> recentPatientsExpected = new ArrayList<>();
		ArrayList<CurrentPatient> recentPatientsActual = smallFacility.getRecentPatients(new GregorianCalendar(2015, 0, 0));
		recentPatientsExpected.add(new CurrentPatient("John", "Fuller", new UHealthID("PNRB-0953"), 1234123, new GregorianCalendar(2018, 9, 4)));
		recentPatientsExpected.add(new CurrentPatient("Mia", "Nakamoto", new UHealthID("NRUT-4467"), 1234123, new GregorianCalendar(2023, 3, 3)));
		recentPatientsExpected.add(new CurrentPatient("Jin", "Young", new UHealthID("QWYU-0303"),  6786786 , new GregorianCalendar(2017 , 2, 2)));
		recentPatientsExpected.add(new CurrentPatient("Jordan", "Jones", new UHealthID("AEHK-3524"),  6786786 , new GregorianCalendar(2019 , 9, 19)));
		recentPatientsExpected.add(new CurrentPatient("Abdul", "Alcada", new UHealthID("ITER-7777"),  7777777 , new GregorianCalendar(2017 , 7, 7)));
		recentPatientsExpected.add(new CurrentPatient("Chang-Hau", "Hsu", new UHealthID("MOON-3769"),  9999999 , new GregorianCalendar(2022 , 6, 6)));
		assertEquals(recentPatientsExpected, recentPatientsActual);

		// Retrieve all the patients before 2017.2.2 should not include 2017.2.2 patients
		ArrayList<CurrentPatient> recentPatientsExpectedAfter2017 = new ArrayList<>();
		ArrayList<CurrentPatient> recentPatientsActualAfter2017 = smallFacility.getRecentPatients(new GregorianCalendar(2017, 2, 2));
		recentPatientsExpectedAfter2017.add(new CurrentPatient("John", "Fuller", new UHealthID("PNRB-0953"), 1234123, new GregorianCalendar(2018, 9, 4)));
		recentPatientsExpectedAfter2017.add(new CurrentPatient("Mia", "Nakamoto", new UHealthID("NRUT-4467"), 1234123, new GregorianCalendar(2023, 3, 3)));
		recentPatientsExpectedAfter2017.add(new CurrentPatient("Jordan", "Jones", new UHealthID("AEHK-3524"),  6786786 , new GregorianCalendar(2019 , 9, 19)));
		recentPatientsExpectedAfter2017.add(new CurrentPatient("Abdul", "Alcada", new UHealthID("ITER-7777"),  7777777 , new GregorianCalendar(2017 , 7, 7)));
		recentPatientsExpectedAfter2017.add(new CurrentPatient("Chang-Hau", "Hsu", new UHealthID("MOON-3769"),  9999999 , new GregorianCalendar(2022 , 6, 6)));
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
	}

	/**
	 * Testing setting the physician of a patient with the given UHealthID.
	 */
	@Test
	public void testSetPhysicianSmallFacility() {
		// Setting a physician to an existed patient
		smallFacility.setPhysician(new UHealthID("PNRB-0953"), 7777777);
		ArrayList<CurrentPatient> existPatientsExpected = smallFacility.lookupByPhysician(7777777);
		ArrayList<CurrentPatient> existPatientsActual = new ArrayList<>();
		existPatientsActual.add(new CurrentPatient("John", "Fuller", new UHealthID("PNRB-0953"), 1234123, new GregorianCalendar(2018, 9, 4)));
		existPatientsActual.add(new CurrentPatient("Abdul", "Alcada", new UHealthID("ITER-7777"),  7777777 , new GregorianCalendar(2017 , 7, 7)));
		assertEquals(existPatientsExpected, existPatientsActual);

		// Setting a physician to a non-existing patient
		smallFacility.setPhysician(new UHealthID("NZZT-4460"), 9999999);
		ArrayList<CurrentPatient> nonExistPatientsExpected = smallFacility.lookupByPhysician(9999999);
		ArrayList<CurrentPatient> nonExistPatientsActual = new ArrayList<>();
		nonExistPatientsActual.add(new CurrentPatient("Chang-Hau", "Hsu", new UHealthID("MOON-3769"),  9999999 , new GregorianCalendar(2022 , 6, 6)));
		assertEquals(nonExistPatientsExpected, nonExistPatientsActual);
	}

	/**
	 * Testing setting the last visit date of a patient with the given UHealthID.
	 */
	@Test
	public void testSetLastVisitDaySmallFacility() {
		// Setting last day of visit for an existed patient
		smallFacility.setLastVisit(new UHealthID("PNRB-0953"), new GregorianCalendar(1910, 1,23));
		CurrentPatient lastDayActual = smallFacility.lookupByUHID(new UHealthID("PNRB-0953"));
		CurrentPatient lastDayExpected = new CurrentPatient("John", "Fuller", new UHealthID("PNRB-0953"), 1234123, new GregorianCalendar(1910, 1,23));
		assertEquals(lastDayExpected, lastDayActual);

		// Setting last visit day for a non-existed patient
		smallFacility.setLastVisit(new UHealthID("PPPP-0000"), new GregorianCalendar(2022, 6,6));
		ArrayList<CurrentPatient> lastDayOf2022Acutal = smallFacility.getRecentPatients(new GregorianCalendar(2022, 6, 6));
		ArrayList<CurrentPatient> lastDayOf2022Expected = new ArrayList<>();
		lastDayOf2022Expected.add(new CurrentPatient("Mia", "Nakamoto", new UHealthID("NRUT-4467"), 1234123, new GregorianCalendar(2023, 3, 3)));
		assertEquals(lastDayOf2022Expected, lastDayOf2022Acutal);
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
	public ArrayList<CurrentPatient> readFromFile(String filename) {
		ArrayList<CurrentPatient> patients = new ArrayList<CurrentPatient>();
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
	private CurrentPatient parsePatient(String line, int lineNumber) throws ParseException {
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
		
		return new CurrentPatient(firstName, lastName, new UHealthID(patientIDString), 
								physician, lastVisit);
	}
}
