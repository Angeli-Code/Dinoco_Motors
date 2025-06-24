/**
 * Exception for when an SQL Connection fails to close
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/7/2024
 */
public class FailedSQLDisconnectException extends Exception
{
	/**
	 * Constructor that passes a custom error message to
	 * Exception Constructor
	 * @param message	Custom Error Message
	 */
	public FailedSQLDisconnectException(String message)
	{
		super(message);
	}
}
