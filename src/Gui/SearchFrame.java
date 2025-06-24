import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * A Frame for the user to search a car name and see the results displayed
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/8/2024
 */
public class SearchFrame extends MainFrame implements ActionListener
{
	private JTextField carNameField;
	private JButton searchButton;
	private JButton menuButton;
	private JTextArea resultsArea;
	private ArrayList<Car> dinoco = new ArrayList<>();
	private GUIManager guiManager;
	private MenuFrame menuFrame;

	private boolean debugMode = false;
	private Logger logger = Logger.getLogger(SearchFrame.class.getName());

	/**
	 * Constructor that creates a SearchFrame Instance
	 * @param guiManager	Manager so that the MenuButton can return to Menu
	 * @param menuFrame	The Menu Frame to show when MenuButton is clicked
	 */
	public SearchFrame(GUIManager guiManager, MenuFrame menuFrame,
			   boolean debugMode)
	{
		this.debugMode = debugMode;
		this.guiManager = guiManager;
		this.menuFrame = menuFrame;

		GridBagConstraints layoutConst;
		JLabel carNameLabel;
		JScrollPane scrollPane;

		setTitle("Car Search");

		carNameLabel = new JLabel("Enter Car Name:");
		searchButton = new JButton("Search");
		menuButton = new JButton("Back to Menu");
		searchButton.addActionListener(this);
		menuButton.addActionListener(this);
		carNameField = new JTextField(20);
		resultsArea = new JTextArea(20, 40);
		resultsArea.setEditable(false);
		scrollPane = new JScrollPane(resultsArea);

		setLayout(new GridBagLayout());

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 10, 5, 1);
		layoutConst.anchor = GridBagConstraints.LINE_END;
		layoutConst.gridx = 0;
		layoutConst.gridy = 0;
		add(carNameLabel, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 1, 5, 10);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 1;
		layoutConst.gridy = 0;
		layoutConst.weightx = 0.8;
		add(carNameField, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 1, 5, 10);
		layoutConst.gridx = 2;
		layoutConst.gridy = 0;
		add(searchButton, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 10, 10, 10);
		layoutConst.fill = GridBagConstraints.BOTH;
		layoutConst.gridx = 0;
		layoutConst.gridy = 1;
		layoutConst.gridwidth = 3;
		layoutConst.weightx = 1.0;
		layoutConst.weighty = 1.0;
		add(scrollPane, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 10, 10, 10);
		layoutConst.anchor = GridBagConstraints.LINE_END;
		layoutConst.gridx = 2;
		layoutConst.gridy = 2;
		add(menuButton, layoutConst);

		pack();
	}

	/**
	 * Updates The List of cars displayed in the JTextField
	 *
	 * @param inDinoco	Passed in Array of Car Objects
	 */
	public void updateCarList(ArrayList<Car> inDinoco)
	{
		resultsArea.setText("");

		resultsArea.append("MPG\t"
				  + "Cylinders\t"
				  + "Displacment\t"
				  + "Horsepower\t"
				  + "Weight\t"
				  + "Acceleration\t"
				  + "Model-Year\t"
				  + "Origin\t"
				  + "Car-Name\n");

		for (Car car : inDinoco) {
			resultsArea.append(car.toString());
			dinoco.add(car);
		}

		if (debugMode) {
			logger.log(Level.INFO,
				  "Successfully Updated Search Frame Data");
		}
	}

	/**
	 * Compares the contents of all Car Objects carName and adds all
	 * Cars that contain the user entered query to a new Array of Cars
	 *
	 * @param query		The User Entered Query
	 */
	private void searchSort(String query)
	{
		ArrayList<Car> newDinoco = new ArrayList<>();

		for (Car car: dinoco) {
			if (car.getCarName().toLowerCase().contains(query.toLowerCase())) {
				newDinoco.add(car);
			}
		}

		updateCarList(newDinoco);
	}

	/**
	 * Chooses what to do based on which button was pressed
	 * @param event		Button Clicked
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == searchButton) {
			String carName = carNameField.getText();
			searchSort(carName);
			if (debugMode) {
				logger.log(Level.INFO, "Search Triggered");
			}
		}
		else if (event.getSource() == menuButton) {
			guiManager.closeFrame(this);
			guiManager.openFrame(menuFrame);
			if (debugMode) {
				logger.log(Level.INFO, "Returned to Menu");
			}
		}
	}
}
