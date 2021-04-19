package Main;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import database.database;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberUpdateForm extends JDialog {

	/**
	 * Create the dialog.
	 */
	public MemberUpdateForm(String sMemberID) {
		setTitle("Edit Member");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 285);
		getContentPane().setLayout(null);
		setResizable(false);

		// Header Customer Update
		JLabel hMemberUpdate = new JLabel("Member Update");
		hMemberUpdate.setBounds(144, 21, 132, 14);
		getContentPane().add(hMemberUpdate);

		// *** Header ***//
		JLabel hMemberID = new JLabel("MemberID");
		hMemberID.setBounds(100, 51, 89, 14);
		getContentPane().add(hMemberID);

		JLabel hName = new JLabel("Name :");
		hName.setBounds(100, 76, 89, 14);
		getContentPane().add(hName);

		JLabel hSurname = new JLabel("Surname :");
		hSurname.setBounds(100, 100, 89, 14);
		getContentPane().add(hSurname);

		JLabel hPhone = new JLabel("Phone :");
		hPhone.setBounds(100, 125, 89, 14);
		getContentPane().add(hPhone);

		JLabel hEmail = new JLabel("Email :");
		hEmail.setBounds(100, 146, 89, 14);
		getContentPane().add(hEmail);

		JLabel hAddress = new JLabel("Address :");
		hAddress.setBounds(100, 171, 89, 14);
		getContentPane().add(hAddress);

		// *** Update Form ***//
		// CustomerID
		final JLabel lblMemberID = new JLabel("lblMemberID");
		lblMemberID.setBounds(207, 51, 99, 20);
		getContentPane().add(lblMemberID);

		// Name
		final JTextField txtName = new JTextField("");
		txtName.setBounds(207, 76, 176, 20);
		getContentPane().add(txtName);

		// Email
		final JTextField txtSurname = new JTextField("");
		txtSurname.setBounds(207, 100, 176, 20);
		getContentPane().add(txtSurname);

		// CountryCode
		final JTextField txtPhone = new JTextField("");
		txtPhone.setBounds(207, 123, 176, 20);
		getContentPane().add(txtPhone);

		// Budget
		final JTextField txtEmail = new JTextField("");
		txtEmail.setBounds(207, 146, 176, 20);
		getContentPane().add(txtEmail);

		// Used
		final JTextField txtAddress = new JTextField("");
		txtAddress.setBounds(207, 171, 176, 20);
		getContentPane().add(txtAddress);

		// *** Bind Data ***//
		Connection connect = null;
		Statement s = null;

		try {
			database.db();
			database.s = database.connect.createStatement();

			String sql = "SELECT * FROM  member " + "WHERE memberID = '"
					+ sMemberID + "' ";

			ResultSet rec = database.s.executeQuery(sql);

			if (rec != null) {
				rec.next();
				lblMemberID.setText(rec.getString("memberID"));
				txtName.setText(rec.getString("memName"));
				txtSurname.setText(rec.getString("memSur"));
				txtPhone.setText(rec.getString("Phone"));
				txtEmail.setText(rec.getString("Email"));
				txtAddress.setText(rec.getString("Address"));
			}
			rec.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}

		// Save Button
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				SaveData(lblMemberID.getText(), txtName.getText(),
						txtSurname.getText(), txtPhone.getText(),
						txtEmail.getText(), txtAddress.getText());
				dispose();
			}
		});
		btnSave.setBounds(131, 206, 69, 23);
		getContentPane().add(btnSave);

		// Button Close
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(217, 206, 69, 23);
		getContentPane().add(btnClose);

	}

	// Update
	private void SaveData(String strMemberID, String strName, String strSurname,
			String strPhone, String strEmail, String strAddress) {
		
		Connection connect = null;
		Statement s = null;
		
		try {
			database.db();
			
			database.s = database.connect.createStatement();
			
			String sql = "UPDATE member " +
					"SET memName = '" + strName + "' " +
					", memSur = '" + strSurname + "' " +
					", Phone = '" + strPhone + "' " +
					", Email = '" + strEmail + "' " +
					", Address = '" + strAddress + "' " +
					" WHERE memberID = '"+strMemberID+"' ";
			database.s.execute(sql);
            
     		JOptionPane.showMessageDialog(null, "Record Update Successfully");
             
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
	}
}