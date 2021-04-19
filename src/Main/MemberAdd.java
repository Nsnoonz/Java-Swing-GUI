package Main;

import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import database.database;

public class MemberAdd extends JPanel {


	/**
	 * Create the panel.
	 */
	public MemberAdd() {
		
		setLayout(null);
		// *** Header ***//
		JLabel hName = new JLabel("Name :");
		hName.setBounds(150, 118, 89, 14);
		add(hName);

		JLabel hSurname = new JLabel("Surname :");
		hSurname.setBounds(150, 142, 89, 14);
		add(hSurname);

		JLabel hPhone = new JLabel("Phone :");
		hPhone.setBounds(150, 167, 89, 14);
		add(hPhone);

		JLabel hEmail = new JLabel("Email :");
		hEmail.setBounds(150, 188, 89, 14);
		add(hEmail);

		JLabel hAddress = new JLabel("Address :");
		hAddress.setBounds(150, 213, 89, 14);
		add(hAddress);

		final JTextField txtName = new JTextField("");
		txtName.setBounds(257, 118, 176, 20);
		add(txtName);

		final JTextField txtSurname = new JTextField("");
		txtSurname.setBounds(257, 142, 176, 20);
		add(txtSurname);

		final JTextField txtPhone = new JTextField("");
		txtPhone.setBounds(257, 165, 176, 20);
		add(txtPhone);

		final JTextField txtEmail = new JTextField("");
		txtEmail.setBounds(257, 188, 176, 20);
		add(txtEmail);

		final JTextField txtAddress = new JTextField("");
		txtAddress.setBounds(257, 213, 176, 20);
		add(txtAddress);

		// Save Button
				JButton btnSave = new JButton("ADD");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtName.getText().equals("") || txtSurname.getText().equals("") || txtEmail.getText().equals("")
								|| txtAddress.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Please input Again");
						} else {

						Connection connect = null;
						Statement s = null;

						try {
							database.db();

							database.s = database.connect.createStatement();
							
							// SQL Insert
							String sql = "INSERT INTO member "
									+ "(memName,memSur,Phone,Email,Address) "
									+ "VALUES ('" + txtName.getText() + "','"
									+ txtSurname.getText() + "'" + ",'"
									+ txtPhone.getText() + "','"
									+ txtEmail.getText() + "','"
									+ txtAddress.getText() + "') ";
							database.s.execute(sql);
							
							// Reset Text Fields
						
							txtName.setText("");
							txtSurname.setText("");
							txtPhone.setText("");
							txtEmail.setText("");
							txtAddress.setText("");
							
							JOptionPane.showMessageDialog(null,"Record Inserted Successfully");


						} catch (Exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage());
							e.printStackTrace();
						}
						
						}

					
	}});
				database.close();
				btnSave.setBounds(175, 265, 64, 23);
				add(btnSave);
				
				JButton btnClear = new JButton("Clear");
				btnClear.setBounds(286, 265, 64, 23);
				btnClear.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						txtName.setText("");
						txtSurname.setText("");
						txtPhone.setText("");
						txtEmail.setText("");
						txtAddress.setText("");
					}
				});
				add(btnClear);
				
				JLabel lblAddMember = new JLabel("ADD Member");
				lblAddMember.setBounds(228, 75, 79, 14);
				add(lblAddMember);
	}
}