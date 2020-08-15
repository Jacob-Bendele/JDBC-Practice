import java.sql.*;

// Class to transfer results from responses back to the GUI.
class Results
{
	String type = null;
	ResultSetMetaData metaData = null;
	ResultSet resultSet = null;
	int status = 0;
	int numberOfColumns = 0;
	int numberOfRows = 0;

	Results(ResultSet resultSet, ResultSetMetaData metaData, String type, int numberOfColumns)
	{
		this.resultSet = resultSet;
		this.metaData = metaData;
		this.type =	type;
		this.numberOfColumns = numberOfColumns;
	}

	Results(int status, String type)
	{
		this.status = status;
		this.type =	type;
	}
}

// Handles all functions for connecting to a database, executring commands, disconnecting.
public class DatabaseConnection 
{
	private boolean connectionEstablished;
	private String databaseDriver, databaseUrl, username, password;
	private Statement statement;
	private Connection connection;

	// Constructs the DatabaseConnection Object with no connection.
	public DatabaseConnection()
	{
		connectionEstablished = false;
	}

	// Disconeects from the current database.
	public void dissconnectFromDB() throws SQLException
	{
		statement.close();
		connection.close();
		connectionEstablished = false;
	}

	// Packages the response metaData in a custom Reuslt Class to send back to the GUI.
	public static Results processMetaData(ResultSet resultSet) throws SQLException
	{
		ResultSetMetaData metaData = resultSet.getMetaData();

		return new Results(resultSet, metaData, "Query", metaData.getColumnCount());
	}

	// Detects whether the SQL command from GUI is a retrieval or an update command then handles execution.
	public Results executeSqlCommand(String command) throws SQLException
	{
		String firstWord = command.split(" ", 2)[0];

		if (firstWord.equalsIgnoreCase("select"))
		{
			ResultSet resultSet = statement.executeQuery(command);
			return processMetaData(resultSet);
		}

		else
		{
			int status = statement.executeUpdate(command);
			return new Results(status, "Change");
		}
	}

	// Creates a connection to the database based on a selected driver, username, and password from the GUI.
	public void connectToDB(String databaseDriver, String url, String username, char[] password) throws ClassNotFoundException, SQLException 
	{
		Class.forName(databaseDriver);
		
		connection = DriverManager.getConnection(url, username, String.valueOf(password));
		statement = connection.createStatement();
		connectionEstablished = true;
	}
}
