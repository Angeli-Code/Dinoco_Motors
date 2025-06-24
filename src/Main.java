import java.util.ArrayList;

/**
 * Application that allows you to view the Dinoco Database
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/7/2024
 */
public class Main
{
	public static void main(String[] args)
	{
		boolean debugMode = false;
		debugMode = checkDebugMode(args);

		Database db = new Database(debugMode);
		ArrayList<Car> dataList = new ArrayList<>();
		dataList = db.downloadDatabase();

		GUIManager guiMan = new GUIManager();
		MenuFrame menuFrame = new MenuFrame(guiMan,
						    null,
						    null,
						    db,
						    debugMode);
		SearchFrame searchFrame = new SearchFrame(guiMan,
							  menuFrame,
							  debugMode);
		SliderFrame sliderFrame = new SliderFrame(guiMan,
							  menuFrame,
							  debugMode);
		menuFrame.setSearchFrame(searchFrame);
		menuFrame.setSliderFrame(sliderFrame);
		searchFrame.updateCarList(dataList);
		sliderFrame.updateCarList(dataList);
		guiMan.openFrame(menuFrame);
	}

	/**
	 * Checks if the user ran the program in Debug Mode by checking
	 * String[] args passed to main method
	 *
	 * @param args		The args passed when ran from terminal
	 * @return		boolean for if they enabled debug mode
	 */
	public static boolean checkDebugMode(String[] args) {
		if (args.length == 0) {
			return false;
		}

		if (args[0].equals("--debug")) {
			return true;
		} else {
			return false;
		}
	}
}
