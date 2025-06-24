/**
 * Manager for all Frames in the Application
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/7/2024
 */
public class GUIManager
{
	/**
	 * Makes a Frame visible
	 * @param frame		Passed in Frame Instance
	 */
	public void openFrame(MainFrame frame)
	{
		if (frame != null) {
			frame.openFrame();
		}
	}

	/**
	 * Makes a Frame Invisible
	 * @param frame		Passed in Frame Instance
	 */
	public void closeFrame(MainFrame frame)
	{
		if (frame != null) {
			frame.closeFrame();
		}
	}
}
