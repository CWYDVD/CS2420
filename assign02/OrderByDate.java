package assign02;

import java.util.Comparator;

/**
 * Comparator that defines an ordering among current patients using their date of last visit.
 * Tie break date with unique uHealthID.
 * 
 * @author Maxwell and David
 * @version January 23, 2025
 */
public class OrderByDate<Type> implements Comparator<CurrentPatientGeneric<Type>> {
	/**
	 * Returns a negative value if lhs (left-hand side) is less than rhs (right-hand side). 
	 * Returns a positive value if lhs is greater than rhs.
	 * Returns 0 if lhs and rhs are equal.
	 */
	public int compare(CurrentPatientGeneric<Type> lhs, CurrentPatientGeneric<Type> rhs) {
		if (lhs.getLastVisit().compareTo(rhs.getLastVisit()) != 0)
            return (lhs.getLastVisit().compareTo(rhs.getLastVisit()));
        else return lhs.getUHealthID().toString().compareTo(rhs.getUHealthID().toString());
	}
}
