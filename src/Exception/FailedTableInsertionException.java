/**
 * Exception for when there is a failure to update the contents
 * of an SQL Database
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/7/2024
 */
public class FailedTableInsertionException extends Exception
{
	/**
	 * Constructor that passes a custom error message to Exception
	 * @param message	Custom Error Message
	 */
	public FailedTableInsertionException(String message) {
		super(message);
	}
}
