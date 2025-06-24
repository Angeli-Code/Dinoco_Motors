import javax.swing.JFrame;
import java.awt.*;

/**
 * Parent Class for all Frames in application
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/7/2024
 */
public class MainFrame extends JFrame
{
	/**
	 * Constructor that sets the attributes of the Frame but sets
	 * the visbility to false
	 */
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1700,1100));
		setResizable(true);
		setUndecorated(false);

		pack();

		setLocationRelativeTo(null);
		setVisible(false);
	}

	/**
	 * Hides the Frame
	 */
	public void closeFrame()
	{
		setVisible(false);
	}

	/**
	 * Shows the Frame
	 */
	public void openFrame()
	{
		setVisible(true);
	}
}
