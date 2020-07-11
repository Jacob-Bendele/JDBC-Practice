import java.sql.*;

public class DatabaseConnection 
{
	// These are actually never used because the local instance of the passed variables
	// are used instead.
	private boolean connectionEstablished;
	private String databaseDriver, databaseUrl, username, password;
	private Statement statement;

	public DatabaseConnection()
	{
		this.connectionEstablished = false;
	}

	public String executeSqlCommand(String query) throws SQLException
	{
		// TODO Auto-generated method stub
		ResultSet resultSet = statement.executeQuery(query);
		int result = statement.executeUpdate(query);
		return "";
	}

	public void connectToDB(String databaseDriver, String url, String username, char[] password) throws ClassNotFoundException, SQLException 
	{
		Class.forName(databaseDriver);
		
		Connection connection = DriverManager.getConnection(url, username, String.valueOf(password));

		statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("select * from bikes");

		while (resultSet.next())
		{
			System.out.println(resultSet.getString("bikename"));
		}

		connection.close();
	}
}
