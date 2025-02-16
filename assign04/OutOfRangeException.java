package assign04;
/**
* This class represents an exception to the normal flow of a program when a
* numerical value is out of range for a data type.
*
* NOTE: The warning about serialized runtime is suppressed because it is
* beyond the scope of CS 2420. Do not suppress warnings in your
* programs unless you have been given explicit guidance/permission to
* so by the course staff!
*
* @author CS 2420 course staff
* @version January 30, 2025
*/
@SuppressWarnings("serial")
public class OutOfRangeException extends RuntimeException {
    public OutOfRangeException(String dataTypeName) {
        super("The value is too large for the " + dataTypeName + " datatype.");
}
}
