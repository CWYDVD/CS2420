package assign02;

import java.util.GregorianCalendar;

public class CurrentPatientGeneric<Type> extends Patient{
    Type physician;
    GregorianCalendar lastVisit;
    /**
     * Creates a new CurrentPatientGeneric, which is a patient that also stores the associated physician's ID and the date of the patient's last visit. The type of the physician's ID must be specified.
     * @param firstName First name of the patient
     * @param lastName Last name of the patient
     * @param uHealthID uHealthID of the patient
     * @param physician physician's ID
     * @param lastVisit The date of the patient's last visit
     */
    public CurrentPatientGeneric (String firstName, String lastName, UHealthID uHealthID, Type physician, GregorianCalendar lastVisit) {
        super(firstName, lastName, uHealthID);
        this.physician = physician;
        this.lastVisit = lastVisit;
    }
    /**
     * Return the ID of the patient's physician
     * @return The ID
     */
    public Type getPhysician() {
        return this.physician;
    }
    /**
     * Return the date of the patient's last visit
     * @return
     */
    public GregorianCalendar getLastVisit() {
        return this.lastVisit;
    }
    /**
     * Change the physician associated with this patient
     * @param newPhysician The ID of the new physician
     */
    public void updatePhysician(Type newPhysician) {
        this.physician = newPhysician;
    }

    /**
     * Change the date of the patient's last visit
     * @param date The new date
     */

    public void updateLastVisit(GregorianCalendar date) {
        this.lastVisit = date;
    }






}
