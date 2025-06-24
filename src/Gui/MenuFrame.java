import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Frame that provides navigation between different Frames
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/7/2024
 */
public class MenuFrame extends MainFrame implements ActionListener
{
	private JButton searchButton;
	private JButton sliderButton;
	private JButton updateButton;
	private JButton exitButton;

	private SearchFrame searchFrame;
	private SliderFrame sliderFrame;

	private GUIManager guiManager;
	private Database database;

	private boolean debugMode = false;
	private Logger logger = Logger.getLogger(MenuFrame.class.getName());

	/**
	 * Constructor that defines the attributes of the Menu Frame
	 *
	 * @param guiManager  The application's GUI manager
	 * @param searchFrame The search frame instance
	 * @param sliderFrame The slider frame instance
	 * @param database    The database instance
	 */
	public MenuFrame(GUIManager guiManager, SearchFrame searchFrame,
			SliderFrame sliderFrame, Database database,
			boolean debugMode)
	{
		this.debugMode = debugMode;
		this.guiManager = guiManager;
		this.searchFrame = searchFrame;
		this.sliderFrame = sliderFrame;
		this.database = database;

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());
		contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(51, 153, 255));
		titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

		JLabel titleLabel = new JLabel("Dinoco Database");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
		titleLabel.setForeground(Color.WHITE);
		titlePanel.add(titleLabel);

		searchButton = createStyledButton("Search Database");
		sliderButton = createStyledButton("Performance Search");
		updateButton = createStyledButton("Update Database");
		exitButton = createStyledButton("Exit");

		GridBagConstraints layoutConst = new GridBagConstraints();

		layoutConst.insets = new Insets(0, 0, 30, 0);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		contentPanel.add(titlePanel, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(0, 0, 15, 0);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		contentPanel.add(searchButton, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(0, 0, 15, 0);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 0;
		layoutConst.gridy = 2;
		contentPanel.add(sliderButton, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(0, 0, 15, 0);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 0;
		layoutConst.gridy = 3;
		contentPanel.add(updateButton, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(20, 0, 0, 0);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 0;
		layoutConst.gridy = 4;
		contentPanel.add(exitButton, layoutConst);

		add(contentPanel);

		pack();
		setLocationRelativeTo(null);

		if (debugMode) {
			logger.log(Level.INFO, "MenuFrame initialized successfully");
		}
	}


	/**
	 * Setter for Search Frame
	 * @param searchFrame	The Search Frame
	 */
	public void setSearchFrame(SearchFrame searchFrame)
	{
		this.searchFrame = searchFrame;
	}

	/**
	 * Setter for sliderFrame
	 * @param sliderFrame	The Slider Frame
	 */
	public void setSliderFrame(SliderFrame sliderFrame)
	{
		this.sliderFrame = sliderFrame;
	}

	/**
	 * Creates a styled button
	 * @param The button text
	 * @return T styled JButton
	 */
	private JButton createStyledButton(String text)
	{
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(200, 40));
		button.setFont(new Font("Arial", Font.PLAIN, 14));
		button.setFocusPainted(false);
		button.addActionListener(this);
		return button;
	}

	/**
	 * Handles button clicks for navigation and database operations
	 * @param event The action event from the button
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == searchButton) {
			guiManager.closeFrame(this);
			guiManager.closeFrame(sliderFrame);
			guiManager.openFrame(searchFrame);
			if (debugMode) {
				logger.log(Level.INFO, "Opened Search Frame");
			}
		} else if (event.getSource() == sliderButton) {
			guiManager.closeFrame(this);
			guiManager.closeFrame(searchFrame);
			guiManager.openFrame(sliderFrame);
			if (debugMode) {
				logger.log(Level.INFO, "Opened Slider Frame");
			}
		} else if (event.getSource() == updateButton) {
			database.updateDatabase();
			searchFrame.updateCarList(database.downloadDatabase());
			sliderFrame.updateCarList(database.downloadDatabase());
			if (debugMode) {
				logger.log(Level.INFO, "Database updated");
			}
		} else if (event.getSource() == exitButton) {
			database.closeConnection();
			System.exit(0);
		} else {
			if (debugMode) {
				logger.log(Level.WARNING,
						"Unsensible Action Source Selection");
			}
		}
	}
}
