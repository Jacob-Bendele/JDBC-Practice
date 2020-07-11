import java.sql.*;

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
		//this.numberOfRows = numberOfRows;
	}

	Results(int status, String type)
	{
		this.status = status;
		this.type =	type;
	}
}


public class DatabaseConnection 
{
	// These are actually never used because the local instance of the passed variables
	// are used instead.
	private boolean connectionEstablished;
	private String databaseDriver, databaseUrl, username, password;
	private Statement statement;
	private Connection connection;

	public DatabaseConnection()
	{
		connectionEstablished = false;
	}

	public void dissconnectFromDB() throws SQLException
	{
		statement.close();
		connection.close();
		connectionEstablished = false;
	}

	public static Results processMetaData(ResultSet resultSet) throws SQLException
	{
		ResultSetMetaData metaData = resultSet.getMetaData();
		
		//resultSet.last();
		//int rowCount = resultSet.getRow();
		//resultSet.beforeFirst(); 

		return new Results(resultSet, metaData, "Query", metaData.getColumnCount());
		
	}

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
		


		//ResultSet resultSet = statement.executeQuery("select * from bikes");

		//while (resultSet.next())
		///{
		//	System.out.println(resultSet.getString("bikename"));
		//}

		//connection.close();
		//return "";
	}

	public void connectToDB(String databaseDriver, String url, String username, char[] password) throws ClassNotFoundException, SQLException 
	{
		Class.forName(databaseDriver);
		
		connection = DriverManager.getConnection(url, username, String.valueOf(password));

		statement = connection.createStatement();

		connectionEstablished = true;

		
	}
}
