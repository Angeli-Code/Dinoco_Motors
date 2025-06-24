/**
 * Exception for when an SQL Database Table fails to clear
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/7/2024
 */
public class FailedTableClearException extends Exception
{
	/**
	 * Construct that passes a custom message to Exception
	 * @param message	Custom Message
	 */
	public FailedTableClearException(String message)
	{
		super(message);
	}
}
