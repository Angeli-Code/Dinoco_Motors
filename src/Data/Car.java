/**
 * Class for Car Objects.
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/6/2024
 */
public class Car
{
	private String mpg;
	private String cylinders;
	private String displacement;
	private String horsepower;
	private String weight;
	private String acceleration;
	private String modelYear;
	private String origin;
	private String carName;

	/**
	 * Constructor that sets all fields to "Not Set"
	 */
	public Car()
	{
		mpg = "Not Set";
		cylinders = "Not Set";
		displacement = "Not Set";
		horsepower = "Not Set";
		weight = "Not Set";
		acceleration = "Not Set";
		modelYear = "Not Set";
		origin = "Not Set";
		carName = "Not Set";
	}

	/**
	 * Constructor that sets all fields to passed in parameters
	 *
	 * @param mpg		Car's Miles Per Gallon
	 * @param cylinders	Car's Number of Cylinders
	 * @param displacement	Car's Displacment
	 * @param horsepower	Car's Horsepower
	 * @param weight	Car's Weight
	 * @param acceleration	Car's Acceleration
	 * @param modelYear	Car's Model Year
	 * @param origin	Car's Origin
	 * @param carName	Car's Car Name
	 */
	public Car(String mpg, String cylinders, String displacement,
		   String horsepower, String weight, String acceleration,
		   String modelYear, String origin, String carName)
	{
		this.mpg = mpg;
		this.cylinders = cylinders;
		this.displacement = displacement;
		this.horsepower = horsepower;
		this.weight = weight;
		this.acceleration = acceleration;
		this.modelYear = modelYear;
		this.origin = origin;
		this.carName = carName;
	}

	/**
	 * Sets MPG
	 * @param mpg	Car's Miles Per Gallon
	 */
	public void setMpg(String mpg)
	{
		this.mpg = mpg;
	}

	/**
	 * Sets Cylinders
	 * @param cylinders	Car's Number of Cylinders
	 */
	public void setCylinders(String cylinders)
	{
		this.cylinders = cylinders;
	}

	/**
	 * Sets Displacement
	 * @param displacement	Car's Displacement
	 */
	public void setDisplacement(String displacement)
	{
		this.displacement = displacement;
	}

	/**
	 * Sets Horsepower
	 * @param horsepower	Car's Horsepower
	 */
	public void setHorsepower(String horsepower)
	{
		this.horsepower = horsepower;
	}

	/**
	 * Sets Weight
	 * @param weight	Car's Weight
	 */
	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	/**
	 * Sets Acceleration
	 * @param acceleration	Car's Acceleration
	 */
	public void setAcceleration(String acceleration)
	{
		this.acceleration = acceleration;
	}

	/**
	 * Sets Model Year
	 * @param modelYear	Car's Model Year
	 */
	public void setModelYear(String modelYear)
	{
		this.modelYear = modelYear;
	}

	/**
	 * Sets Origin
	 * @param origin	Car's Origin
	 */
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}

	/**
	 * Sets Car Name
	 * @param carName	Car's Name
	 */
	public void setCarName(String carName)
	{
		this.carName = carName;
	}

	/**
	 * Getter for MPG
	 * @return The Car's MPG
	 */
	public String getMpg()
	{
		return mpg;
	}

	/**
	 * Getter for Cylinders
	 * @return The Car's Cylinders
	 */
	public String getCylinders()
	{
		return cylinders;
	}

	/**
	 * Getter for Displacement
	 * @return The Car's Displacement
	 */
	public String getDisplacement()
	{
		return displacement;
	}

	/**
	 * Getter for Horsepower
	 * @return The Car's Horsepower
	 */
	public String getHorsepower()
	{
		return horsepower;
	}

	/**
	 * Getter for Weight
	 * @return The Car's Weight
	 */
	public String getWeight()
	{
		return weight;
	}

	/**
	 * Getter for Acceleration
	 * @return The Car's Acceleration
	 */
	public String getAcceleration()
	{
		return acceleration;
	}

	/**
	 * Getter for Model Year
	 * @return The Car's Model Year
	 */
	public String getModelYear()
	{
		return modelYear;
	}

	/**
	 * Getter for Origin
	 * @return The Car's Origin
	 */
	public String getOrigin()
	{
		return origin;
	}

	/**
	 * Getter for Car Name
	 * @return The Car's Name
	 */
	public String getCarName()
	{
		return carName;
	}

	/**
	 * Formatted String containing the data of this Car instance
	 * @return Formatted Car Data String
	 */
	@Override
	public String toString()
	{
		return mpg
			+ "\t" + cylinders
			+ "\t" + displacement
			+ "\t" + horsepower
			+ "\t" + weight
			+ "\t" + acceleration
			+ "\t" + modelYear
			+ "\t" + origin
			+ "\t" + carName + "\n";
	}
}
