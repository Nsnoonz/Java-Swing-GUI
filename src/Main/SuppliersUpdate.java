package Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.database;

public class SuppliersUpdate extends JDialog {

	/**
	 * Create the dialog.
	 */
	public SuppliersUpdate(String sSuppliersID) {
		setTitle("Edit Member");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 285);
		getContentPane().setLayout(null);
		setResizable(false);

		// Header Customer Update
		JLabel hSuppliersUpdate = new JLabel("Suppliers Update");
		hSuppliersUpdate.setBounds(144, 21, 132, 14);
		getContentPane().add(hSuppliersUpdate);

		// *** Header ***//
		JLabel hSuppliersID = new JLabel("SuppliersID");
		hSuppliersID.setBounds(100, 51, 89, 14);
		getContentPane().add(hSuppliersID);

		JLabel hName = new JLabel("Name :");
		hName.setBounds(100, 76, 89, 14);
		getContentPane().add(hName);

		JLabel hAddress = new JLabel("Address  :");
		hAddress.setBounds(100, 100, 89, 14);
		getContentPane().add(hAddress);

		JLabel hPhone = new JLabel("Phone :");
		hPhone.setBounds(100, 125, 89, 14);
		getContentPane().add(hPhone);

		JLabel hEmail = new JLabel("Email :");
		hEmail.setBounds(100, 146, 89, 14);
		getContentPane().add(hEmail);

		// *** Update Form ***//
		// CustomerID
		final JLabel lblSuppliersID = new JLabel("lblSuppliersID");
		lblSuppliersID.setBounds(207, 51, 99, 20);
		getContentPane().add(lblSuppliersID);

		// Name
		final JTextField txtName = new JTextField("");
		txtName.setBounds(207, 76, 176, 20);
		getContentPane().add(txtName);

		// Email
		final JTextField txtAddress = new JTextField("");
		txtAddress.setBounds(207, 100, 176, 20);
		getContentPane().add(txtAddress);

		// CountryCode
		final JTextField txtPhone = new JTextField("");
		txtPhone.setBounds(207, 123, 176, 20);
		getContentPane().add(txtPhone);

		// Budget
		final JTextField txtEmail = new JTextField("");
		txtEmail.setBounds(207, 146, 176, 20);
		getContentPane().add(txtEmail);

		// *** Bind Data ***//
		Connection connect = null;
		Statement s = null;

		try {
			database.db();
			database.s = database.connect.createStatement();

			String sql = "SELECT * FROM  suppliers " + "WHERE supplierID = '"
					+ sSuppliersID + "' ";

			ResultSet rec = database.s.executeQuery(sql);

			if (rec != null) {
				rec.next();
				lblSuppliersID.setText(rec.getString("supplierID"));
				txtName.setText(rec.getString("supplierName"));
				txtAddress.setText(rec.getString("supplierAddress"));
				txtPhone.setText(rec.getString("Phone"));
				txtEmail.setText(rec.getString("Email"));
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
						SaveData(lblSuppliersID.getText(), txtName.getText(),
							 txtPhone.getText(),txtEmail.getText(), txtAddress.getText());
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
		add(btnClose);

	}

	// Update
	private void SaveData(String strSuppliersID, String strName,
			String strPhone, String strEmail, String strAddress) {
		
		Connection connect = null;
		Statement s = null;
		
		try {
			database.db();
			
			database.s = database.connect.createStatement();
			
			String sql = "UPDATE suppliers " +
					"SET supplierName = '" + strName + "' " +
					", supplierAddress = '" + strAddress + "' " +
					", Phone = '" + strPhone + "' " +
					", Email = '" + strEmail + "' " +
					
					" WHERE supplierID = '"+strSuppliersID+"' ";
			database.s.execute(sql);
            
     		JOptionPane.showMessageDialog(null, "Record Update Successfully");
             
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
	}
}