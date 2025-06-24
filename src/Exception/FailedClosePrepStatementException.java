/**
 * Exception for when a PreparedStatement fails to close
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/6/2024
 */
public class FailedClosePrepStatementException extends Exception
{
	/**
	 * Constructor that passes a String to Exception
	 *
	 * @param message	Custom Error Message
	 */
	public FailedClosePrepStatementException(String message)
	{
		super(message);
	}
}
