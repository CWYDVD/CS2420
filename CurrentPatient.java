package assign02;

import java.util.GregorianCalendar;

public class CurrentPatient extends Patient{
    int physician;
    GregorianCalendar lastVisit;
    public CurrentPatient(String firstName, String lastName, UHealthID uHealthID, int physician, GregorianCalendar lastVisit) {
        this.physician = physician;
        this.lastVisit = lastVisit;
        super(firstName, lastName, uHealthID);
    }

    
}
