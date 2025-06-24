import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Class that allows for an instance of a Slider Frame
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/7/2024
 */
public class SliderFrame extends MainFrame implements ChangeListener
{
	private JSlider mpgSlider;
	private JSlider horsepowerSlider;
	private JSlider accelerationSlider;
	private JButton menuButton;

	private JTextField mpgField;
	private JTextField horsepowerField;
	private JTextField accelerationField;
	private JTextArea resultsArea;

	private ArrayList<Car> dinoco = new ArrayList<>();
	private GUIManager guiManager;
	private MenuFrame menuFrame;

	private boolean debugMode = false;
	private Logger logger = Logger.getLogger(SliderFrame.class.getName());

	/**
	 * Constructor that defines Styling and attributes
	 *
	 * @param guiManager		Gui Manager
	 * @param MenuFrame		Menu Frame
	 * @param debugMode		Whether INFO logs get printed
	 */
	public SliderFrame(GUIManager guiManager, MenuFrame menuFrame, boolean debugMode)
	{
		this.debugMode = debugMode;
		this.guiManager = guiManager;
		this.menuFrame = menuFrame;

		GridBagConstraints layoutConst;
		JScrollPane scrollPane;

		setTitle("Performance Filters");

		initializeSliders();
		initializeTextFields();

		menuButton = new JButton("Back to Menu");
		menuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				guiManager.closeFrame(SliderFrame.this);
				guiManager.openFrame(menuFrame);
				if (debugMode) {
					logger.log(Level.INFO, "Returned to Menu");
				}
			}
		});

		resultsArea = new JTextArea(20, 40);
		resultsArea.setEditable(false);
		scrollPane = new JScrollPane(resultsArea);

		setLayout(new GridBagLayout());

		addLabelAndComponents("MPG (minimum):", mpgSlider, mpgField, 0);
		addLabelAndComponents("Horsepower (minimum):", horsepowerSlider, horsepowerField, 1);
		addLabelAndComponents("Acceleration (maximum seconds):", accelerationSlider, accelerationField, 2);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 10, 10, 10);
		layoutConst.fill = GridBagConstraints.BOTH;
		layoutConst.gridx = 0;
		layoutConst.gridy = 3;
		layoutConst.gridwidth = 3;
		layoutConst.weightx = 1.0;
		layoutConst.weighty = 1.0;
		add(scrollPane, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 10, 10, 10);
		layoutConst.anchor = GridBagConstraints.LINE_END;
		layoutConst.gridx = 2;
		layoutConst.gridy = 4;
		add(menuButton, layoutConst);

		pack();
		if(debugMode) {
			logger.log(Level.INFO, "SliderFrame initialized successfully");
		}
	}

	/**
	 * Initializes the Sliders and their positions
	 */
	private void initializeSliders()
	{
		mpgSlider = createSlider(10, 50, 20);
		horsepowerSlider = createSlider(50, 250, 100);
		accelerationSlider = createSlider(8, 25, 15);
	}

	/**
	 * Initializes the Texts in each Field
	 */
	private void initializeTextFields()
	{
		mpgField = createTextField();
		horsepowerField = createTextField();
		accelerationField = createTextField();
	}

	/**
	 * Creates a Slider with a fixed style
	 * @param min
	 * @param max
	 * @param initial
	 */
	private JSlider createSlider(int min, int max, int initial)
	{
		JSlider slider = new JSlider(min, max, initial);
		slider.addChangeListener(this);
		slider.setMajorTickSpacing((max - min) / 5);
		slider.setMinorTickSpacing((max - min) / 10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		return slider;
	}

	/**
	 * Creates a Textfield for user input
	 */
	private JTextField createTextField()
	{
		JTextField field = new JTextField(10);
		field.setEditable(false);
		return field;
	}

	/**
	 *
	 */
	private void addLabelAndComponents(String labelText, JSlider slider, JTextField field, int yPos)
	{
		GridBagConstraints layoutConst = new GridBagConstraints();
		JLabel label = new JLabel(labelText);

		layoutConst.insets = new Insets(10, 10, 5, 5);
		layoutConst.anchor = GridBagConstraints.LINE_END;
		layoutConst.gridx = 0;
		layoutConst.gridy = yPos;
		add(label, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 5, 5, 10);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 1;
		layoutConst.gridy = yPos;
		layoutConst.weightx = 0.2;
		add(field, layoutConst);

		layoutConst = new GridBagConstraints();
		layoutConst.insets = new Insets(10, 5, 5, 10);
		layoutConst.fill = GridBagConstraints.HORIZONTAL;
		layoutConst.gridx = 2;
		layoutConst.gridy = yPos;
		layoutConst.weightx = 0.8;
		add(slider, layoutConst);
	}

	/**
	 * Handles when the a ChangeEvent Occurs
	 * @param event		The event that pushes to update the data displayed
	 */
	@Override
	public void stateChanged(ChangeEvent event)
	{
		updateTextFields();
		filterCars();
	}

	/**
	 * Updates the value of the Sliders displayed
	 */
	private void updateTextFields()
	{
		mpgField.setText(String.valueOf(mpgSlider.getValue()));
		horsepowerField.setText(String.valueOf(horsepowerSlider.getValue()));
		accelerationField.setText(String.valueOf(accelerationSlider.getValue()));
	}

	/**
	 * Filters the Cars by the Values of the Drag
	 */
	private void filterCars()
	{
		ArrayList<Car> filteredCars = new ArrayList<>();

		double carMpg = 0;
		double carHorsepower = 0;
		double carAcceleration = 0;

		for (Car car : dinoco) {
			try {
				carMpg = Double.parseDouble(car.getMpg());
				carHorsepower = Double.parseDouble(car.getHorsepower());
				carAcceleration = Double.parseDouble(car.getAcceleration());

				if (carMpg >= mpgSlider.getValue() &&
						carHorsepower >= horsepowerSlider.getValue() &&
						carAcceleration <= accelerationSlider.getValue())
				{
					filteredCars.add(car);
				}
			} catch (NumberFormatException e) {
				if (debugMode) {
					logger.log(Level.WARNING,
						  "Error parsing car values");
				}
			}
		}

		updateResultsArea(filteredCars);
	}

	/**
	 * Updates the Result Contents with only valid data
	 * @param fileredCars		Valid Data to be Displayed
	 */
	private void updateResultsArea(ArrayList<Car> filteredCars)
	{
		resultsArea.setText("");
		resultsArea.append("MPG\tCylinders\tDisplacement\tHorsepower\t" +
				"Weight\tAcceleration\tModel-Year\tOrigin\tCar-Name\n");

		for (Car car : filteredCars) {
			resultsArea.append(car.toString());
		}
	}

	/**
	 * Updates The Car List with an ArrayList
	 * @param inDinoco	passed in ArrayList
	 */
	public void updateCarList(ArrayList<Car> inDinoco)
	{
		dinoco.clear();

		for(Car car: inDinoco) {
			dinoco.add(car);
		}

		filterCars();

		if (debugMode) {
			logger.log(Level.INFO, "Car list updated in SliderFrame");
		}
	}
}
