import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class Gui 
{

	private DBLogic db;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() 
	{
		db = new DBLogic();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 680, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Database Information");
		lblNewLabel.setBounds(6, 6, 177, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEnterASql = new JLabel("Enter A SQL Command");
		lblEnterASql.setBounds(345, 6, 177, 16);
		frame.getContentPane().add(lblEnterASql);
		
		JButton btnNewButton = new JButton("Execute SQL Command");
		btnNewButton.setBounds(497, 220, 177, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClearSqlCommands = new JButton("Clear SQL Commands");
		btnClearSqlCommands.setBounds(319, 220, 177, 29);
		frame.getContentPane().add(btnClearSqlCommands);
		
		JButton btnConnectToDB = new JButton("Connect to Database");
		btnConnectToDB.setBounds(161, 220, 157, 29);
		frame.getContentPane().add(btnConnectToDB);
		
		JButton btnClearResultsWindow = new JButton("Clear Results Window");
		btnClearResultsWindow.setBounds(6, 416, 177, 29);
		frame.getContentPane().add(btnClearResultsWindow);
		
		textField = new JTextField();
		textField.setBounds(6, 220, 143, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(342, 34, 321, 172);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(6, 258, 668, 146);
		frame.getContentPane().add(textArea_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(106, 35, 206, 27);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(106, 63, 206, 27);
		frame.getContentPane().add(comboBox_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(106, 97, 206, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("JDBC Driver");
		lblNewLabel_1.setBounds(6, 34, 88, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Database URL");
		lblNewLabel_1_1.setBounds(6, 66, 88, 16);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Username");
		lblNewLabel_1_1_1.setBounds(6, 101, 88, 16);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Password");
		lblNewLabel_1_1_2.setBounds(6, 129, 88, 16);
		frame.getContentPane().add(lblNewLabel_1_1_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(106, 135, 206, 26);
		frame.getContentPane().add(passwordField);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Send text from textarea to method execute SQL Command
				String response = db.executeSqlCommand(textArea.getText());
				JOptionPane.showMessageDialog(null, "");
			}
		});

		btnClearSqlCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clears the SQL command text area.
				textArea.setText(null);
			}
		});

		btnConnectToDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Sends username and password to DB for connection
				db.connectToDB(textField_1.getText(), passwordField.getPassword());

			}
		});
	}
}
