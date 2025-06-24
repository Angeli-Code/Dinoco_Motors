import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database Class to hold an instance of a Database connection and also
 * interact with it.
 *
 * @author Angelo Laberinto
 * @version 1.0
 * @since 12/6/2024
 */
public class Database
{
	/* TODO: How do I securely store information like this? Google... at some point */
	private static final String databaseID = "jdbc:mysql://localhost/Dinoco";
	private static final String userName = "javaman";
	private static final String userPwd = "args";

	private Connection connection;

	private Logger logger = Logger.getLogger(Database.class.getName());
	private boolean debugMode = false;

	/**
	 * Constructor that attempts to establish a connection to an SQL
	 * database. This also includes whether or not debugging information
	 * will be logged to the console
	 *
	 * @param debugMode	Whether or not debug info gets logged
	 */
	public Database(boolean debugMode)
	{
		this.debugMode = debugMode;

		try {
			connection = DriverManager.getConnection(databaseID,
								 userName,
								 userPwd);

			if (connection.isValid(1) && debugMode) {
				logger.log(Level.INFO,
					  "SQL Connection Established");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
				   "Connection to Database Unsuccessful",
				   e);
		}
	}

	/**
	 * Updates the SQL Database with the contents of a TSV File
	 */
	public void updateDatabase()
	{
		Parser parser = new Parser(debugMode);
		ArrayList<Car> dinoco = new ArrayList<>();
		dinoco = parser.parseTSV();
		PreparedStatement prepStmt;
		int rowsAffected = 0;
		int affectedTotal = 0;

		clearDatabase();

		String sqlQuery = "INSERT INTO Cars"
				+ "(mpg,cylinders,displacement,horsepower,"
				+ "weight,acceleration,modelYear,origin,"
				+ "carname) "
				+ "Values (?,?,?,?,?,?,?,?,?)";

		try {
			prepStmt = connection.prepareStatement(sqlQuery);

			/* TODO: Learn how to use batches */
			for(Car car: dinoco) {
				prepStmt.setString(1,car.getMpg());
				prepStmt.setString(2,car.getCylinders());
				prepStmt.setString(3,car.getDisplacement());
				prepStmt.setString(4,car.getHorsepower());
				prepStmt.setString(5,car.getWeight());
				prepStmt.setString(6,car.getAcceleration());
				prepStmt.setString(7,car.getModelYear());
				prepStmt.setString(8,car.getOrigin());
				prepStmt.setString(9,car.getCarName());

				rowsAffected = prepStmt.executeUpdate();

				if (rowsAffected == 1) {
					affectedTotal++;
				}
			}

			if (rowsAffected == 0) {
				throw new FailedTableInsertionException(
						"Table Updating Failure");
			} else {
				if (debugMode) {
					logger.log(Level.INFO,
						"SQL Table Updated Successfully");
				}
			}
		} catch (FailedTableInsertionException e) {
			if (debugMode) {
				logger.log(Level.WARNING,
					"Failed to Update Table with new data",
					e);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
				   "Problem with SQL Connection",
				   e);
		}
	}

	/**
	 * Clears the Cars Table in the SQL Database before it gets
	 * updated with new data
	 */
	public void clearDatabase()
	{
		String clearQuery = "DELETE From Cars";
		PreparedStatement prepStmt;

		try {
			prepStmt = connection.prepareStatement(clearQuery);
			int rowsAffected = prepStmt.executeUpdate();

			if (rowsAffected == 0) {
				throw new FailedTableClearException(
						"Error Clearing Table");
			} else {
				if (debugMode) {
					logger.log(Level.INFO,
						  "SQL Table Cleared. Ready for Updating");
				}
			}

			prepStmt.close();

			if (prepStmt != null) {
				throw new FailedClosePrepStatementException(
						"Failed to close Prepared Statement");
			}

		} catch (FailedTableClearException e) {
			logger.log(Level.SEVERE,
					"Failed to clear SQL Table",
					e);
		} catch (FailedClosePrepStatementException e) {
			if (debugMode) {
				logger.log(Level.WARNING,
					  "Failed to close Prepared Statement");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
					"Problem with SQL Connection",
					e);
		}
	}

	/**
	 * Downloads the Contents of the the SQL Database into
	 * an ArrayList
	 *
	 * @return An ArrayList with the Updated SQL Data
	 */
	public ArrayList<Car> downloadDatabase()
	{
		ArrayList<Car> downloadedList = new ArrayList<>();
		PreparedStatement prepStmt;
		ResultSet rSet;
		ResultSetMetaData metaData;

		final int TEMPSIZE = 9;
		String tmpData[] = new String[TEMPSIZE];

		String sqlQuery = "SELECT * FROM Cars";

		Car car;

		try {
			prepStmt = connection.prepareStatement(sqlQuery);
			rSet = prepStmt.executeQuery();
			metaData = rSet.getMetaData();
			int colCount = metaData.getColumnCount();

			while (rSet.next()) {
				for (int i = 1; i <= colCount;i++) {
					tmpData[i-1] = rSet.getString(i);
				}

				car = new Car(tmpData[0],
						  tmpData[1],
						  tmpData[2],
						  tmpData[3],
						  tmpData[4],
						  tmpData[5],
						  tmpData[6],
						  tmpData[7],
						  tmpData[8]);
				downloadedList.add(car);

			}
			prepStmt.close();
			if (prepStmt != null) {
				throw new FailedClosePrepStatementException(
						"Failed to close Prepared Statement");
			}
		} catch (FailedClosePrepStatementException e) {
			if (debugMode) {
				logger.log(Level.WARNING,
					"Failed to close Prepared Statement");
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
				  "Error with SQL Connection",
				  e);
		}
		return downloadedList;
	}

	/**
	 * Attempts to close the connection of a Database Instance
	 */
	public void closeConnection()
	{
		try {
			connection.close();

			if (connection.isValid(0)) {
				throw new FailedSQLDisconnectException(
						"Error Disconnecting from SQL");
			} else {
				if (debugMode) {
					logger.log(Level.INFO,
						   "Connection Closed Successfully");
				}
			}

		} catch (FailedSQLDisconnectException e) {
			logger.log(Level.SEVERE,
				   "Unsucessful SQL Disconnection",
				   e);
		} catch (SQLException e) {
			logger.log(Level.SEVERE,
				   "Problems with SQL Connection",
				   e);
		}
	}
}
