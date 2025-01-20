package assign02;

import java.util.GregorianCalendar;

/**
 * This class represents a current patient and their physician, along with the date of last visit.
 *
 * @author Eric Heisler and Mi Zeng and Aiden Maxwell
 * @version Jan 22, 2024
 */

public class CurrentPatient extends Patient {

	private int physician;
	private GregorianCalendar lastVisit;
	
	
	/**
	 * Create a new CurrentPatient object
	 * @param firstName The patient's first name
	 * @param lastName The patient's last name
	 * @param uHealthID The patient's uHealthID
	 * @param physician The patient's physician's uNID
	 * @param lastVisit The date of the patient's last visit, as a GregorianCalendar object
	 */
	public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, int physician,
			GregorianCalendar lastVisit) {
		super(firstName, lastName, uHealthID);
		this.physician = physician;
		this.lastVisit = lastVisit;

	}
	/**
	 * This method returns the Physician object associated with this patient.
	 * @return the patient's physician
	 */
	public int getPhysician() {
		return physician;
	}
	/**
	 * This method returns the GregorianCalendar date of the patient's last visit
	 * @return the date of the last visit, as a GregorianCalendar object
	 */
	public GregorianCalendar getLastVisit() {
		return lastVisit;
	}
	/**
	 * This method changes the Physician associated with this patient
	 * @param newPhysician The new physician
	 */
	public void updatePhysician(int newPhysician) {
		
		this.physician = newPhysician;
	}
	/**
	 * This method changes the recorded date of the patient's last visit
	 * @param date The date, as a GregorianCalendar object
	 */
	public void updateLastVisit(GregorianCalendar date) {
		this.lastVisit = date;
	}

}
