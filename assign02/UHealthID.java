package assign02;

/**
 * This class represents a unique UHealthID, which has a four letter
 * prefix and four number suffix, such as ABCD-0123.
 * 
 * @author CS 2420 course staff
 * @version January 16, 2025
 */
public class UHealthID {
	
	private String prefix;
	private String suffix;
	
	/**
	 * Parses the given string to create a UHealthID.
	 * This assumes the format AAAA-0000 where "A" could be any English 
	 * letter and "0" could be any numerical digit.
	 * 
	 * @param uHID - the given string, representing a UHealthID
	 * @throws IllegalArgumentException if the uHID is not a valid ID string
	 */
	public UHealthID(String uHID) {
		// Remove the '-' and some other likely extra characters
		uHID = uHID.replaceAll("-|\\s|\\.|\\(|\\)", "");
		
		if(uHID.length() != 8) {			
			throw new IllegalArgumentException("Invalid UHealthID: " + uHID);
		}
		for(int i = 0; i < 4; i++) {
			if(!Character.isLetter(uHID.charAt(i))) {
				throw new IllegalArgumentException("Invalid UHealthID: " + uHID);
			}
		}
		for(int i = 4; i < 8; i++) {
			if(!Character.isDigit(uHID.charAt(i))) {
				throw new IllegalArgumentException("Invalid UHealthID: " + uHID);
			}
		}
		
		prefix = uHID.substring(0, 4).toUpperCase();
		suffix = uHID.substring(4, 8);
	}

	/**
	 * Two patient IDs are considered equal if they have the same prefix and suffix.
	 * 
	 * @param other - the object being compared with this UHealth ID
	 * @return true if the other object is a UHealthID with equal representation, 
	 *         false otherwise
	 */
	public boolean equals(Object other) {
		if(!(other instanceof UHealthID)) {
			return false;
		}		
		return toString().equals(other.toString());
	}
	
	/**
	 * Returns a textual representation of this UHealth ID.
	 * This is prefix + '-' + suffix.
	 * 
	 * @return a textual representation of this UHealth ID
	 */
	public String toString() {
		return prefix + "-" + suffix;
	}
}
