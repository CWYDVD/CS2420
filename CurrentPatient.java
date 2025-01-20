package assign02;

import java.util.GregorianCalendar;

public class CurrentPatient extends Patient{
    int physician;
    GregorianCalendar lastVisit;
    /**
     * Creates a new CurrentPatient, which is a patient that also stores the associated physician's ID and the date of the patient's last visit.
     * @param firstName First name of the patient
     * @param lastName Last name of the patient
     * @param uHealthID uHealthID of the patient
     * @param physician physician's ID
     * @param lastVisit 
     */
    public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, int physician, GregorianCalendar lastVisit) {
        super(firstName, lastName, uHealthID);
        this.physician = physician;
        this.lastVisit = lastVisit;
    }

    public int getPhysician() {
        return (this.physician);
    }

    public GregorianCalendar getLastVisit() {
        return (this.lastVisit);
    }

    public void updatePhysician(int newPhysician) {
        this.physician = newPhysician;
    }

    

    public void updateLastVisit(GregorianCalendar date) {
        this.lastVisit = date;
    }






}
