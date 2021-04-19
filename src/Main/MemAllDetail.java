package Main;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import database.database;

public class MemAllDetail extends JDialog {

	/**
	 * Create the dialog.
	 */
	public MemAllDetail(String sMemberID) {
		setTitle("Member Detail");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 385, 266);
		getContentPane().setLayout(null);
		setResizable(false);
		
		// Header Customer Detail
		JLabel hMemberDetail = new JLabel("Member Details");
		hMemberDetail.setBounds(106, 20, 132, 14);
		getContentPane().add(hMemberDetail);
		
		//*** Header ***//
		JLabel hMemberID = new JLabel("hMemberID :");
		hMemberID.setBounds(62, 50, 89, 14);
		getContentPane().add(hMemberID);
		
		JLabel hName = new JLabel("Name :");
		hName.setBounds(62, 75, 89, 14);
		getContentPane().add(hName);
		
		JLabel hSurname = new JLabel("Surname :");
		hSurname.setBounds(62, 99, 89, 14);
		getContentPane().add(hSurname);
		
		JLabel hPhone = new JLabel("Phone :");
		hPhone.setBounds(62, 122, 89, 14);
		getContentPane().add(hPhone);
		
		JLabel hEmail = new JLabel("Email :");
		hEmail.setBounds(62, 145, 89, 14);
		getContentPane().add(hEmail);
		
		JLabel hAddress = new JLabel("Address :");
		hAddress.setBounds(62, 170, 89, 14);
		getContentPane().add(hAddress);
		
		//*** Detail ***//
		JLabel lblMemberID = new JLabel("lblMemberID");
		lblMemberID.setBounds(169, 50, 89, 14);
		getContentPane().add(lblMemberID);
		
		JLabel lblName = new JLabel("lblName");
		lblName.setBounds(169, 75, 200, 14);
		getContentPane().add(lblName);
		
		JLabel lblSurname = new JLabel("lblSurname");
		lblSurname.setBounds(169, 99, 200, 14);
		getContentPane().add(lblSurname);
		
		JLabel lblPhone = new JLabel("lblPhone");
		lblPhone.setBounds(169, 122, 100, 14);
		getContentPane().add(lblPhone);
		
		JLabel lblEmail = new JLabel("lblEmail");
		lblEmail.setBounds(169, 145, 200, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblAddress = new JLabel("lblAddress");
		lblAddress.setBounds(169, 170, 200, 14);
		getContentPane().add(lblAddress);
		
		//*** Bind Data ***//
		Connection connect = null;
		Statement s = null;
		
		try {
	
			
			database.db();
			database.s = database.connect.createStatement();
			
			String sql = "SELECT * FROM  member " +
					"WHERE memberID = '" + sMemberID + "' ";
			
			ResultSet rec = database.s.executeQuery(sql);
		
			
			if(rec != null) {
				rec.next();
				lblMemberID.setText(rec.getString("memberID"));
				lblName.setText(rec.getString("memName"));
				lblSurname.setText(rec.getString("memSur"));
				lblPhone.setText(rec.getString("Phone"));
				lblEmail.setText(rec.getString("Email"));
				lblAddress.setText(rec.getString("Address"));
            }
			rec.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		database.close();
		// Close Button
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(106, 203, 69, 23);
		getContentPane().add(btnClose);
		
	}

}