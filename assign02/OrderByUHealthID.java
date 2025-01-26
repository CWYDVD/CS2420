package assign02;

import java.util.Comparator;

/**
 * Comparator that defines an ordering among current patients using their uHealthIDs.
 * uHealthIDs are guaranteed to be unique, making a tie-breaker unnecessary.
 * 
 * @author CS 2420 course staff
 * @version January 16, 2025
 */
public class OrderByUHealthID<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	/**
	 * Returns a negative value if lhs (left-hand side) is less than rhs (right-hand side). 
	 * Returns a positive value if lhs is greater than rhs.
	 * Returns 0 if lhs and rhs are equal.
	 */
	public int compare(CurrentPatientGeneric<Type> lhs, CurrentPatientGeneric<Type> rhs) {
		return lhs.getUHealthID().toString().compareTo(rhs.getUHealthID().toString());
	}
}
