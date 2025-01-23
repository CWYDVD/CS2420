package assign02;

import java.util.Comparator;

/**
 * Comparator that defines an ordering among current patients using their names.
 * Tie break first name with last name, then uHealthID.
 * 
 * @author Maxwell and David
 * @version January 23, 2025
 */
public class OrderByName<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	/**
	 * Returns a negative value if lhs (left-hand side) is less than rhs (right-hand side). 
	 * Returns a positive value if lhs is greater than rhs.
	 * Returns 0 if lhs and rhs are equal.
	 */
	public int compare(CurrentPatientGeneric<Type> lhs, CurrentPatientGeneric<Type> rhs) {
		if (lhs.getFirstName().compareTo(rhs.getFirstName()) != 0) 
            return (lhs.getFirstName().compareTo(rhs.getFirstName()));
        else if (lhs.getLastName().compareTo(rhs.getLastName()) != 0)
            return (lhs.getLastName().compareTo(rhs.getLastName()));
        else return lhs.getUHealthID().toString().compareTo(rhs.getUHealthID().toString());
	}
}