package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.database;

public class SuppliersAll extends JPanel {

	static JTable table;
	public SuppliersAll() {
		
		setLayout(null);
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 260, 440, 300);
		add(scrollPane);
		
		// *** Header ***//
		JLabel hName = new JLabel("Name :");
		hName.setBounds(150, 83, 89, 14);
		add(hName);

		JLabel hAddress = new JLabel("Address :");
		hAddress.setBounds(150, 107, 89, 14);
		add(hAddress);

		JLabel hPhone = new JLabel("Phone :");
		hPhone.setBounds(150, 132, 89, 14);
		add(hPhone);

		JLabel hEmail = new JLabel("Email :");
		hEmail.setBounds(150, 153, 89, 14);
		add(hEmail);

		final JTextField txtName = new JTextField("");
		txtName.setBounds(257, 83, 176, 20);
		add(txtName);

		final JTextField txtAddress = new JTextField("");
		txtAddress.setBounds(257, 107, 176, 20);
		add(txtAddress);

		final JTextField txtPhone = new JTextField("");
		txtPhone.setBounds(257, 130, 176, 20);
		add(txtPhone);

		final JTextField txtEmail = new JTextField("");
		txtEmail.setBounds(257, 153, 176, 20);
		add(txtEmail);

		// Save Button
				JButton btnSave = new JButton("ADD");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtName.getText().equals("") || txtAddress.getText().equals("") || txtPhone.getText().equals("")
								|| txtEmail.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Please input Again");
						} else {

						Connection connect = null;
						Statement s = null;

						try {
							database.db();

							database.s = database.connect.createStatement();
							
							// SQL Insert
							String sql = "INSERT INTO suppliers "
									+ "(supplierName,supplierAddress,Phone,Email) "
									+ "VALUES ('" + txtName.getText() + "','"
									+ txtAddress.getText() + "'" + ",'"
									+ txtPhone.getText() + "','"
									+ txtEmail.getText() + "') ";
							database.s.execute(sql);
							
							// Reset Text Fields
						
							txtName.setText("");
							txtAddress.setText("");
							txtPhone.setText("");
							txtEmail.setText("");
							
							
							JOptionPane.showMessageDialog(null,"Record Inserted Successfully");
							PopulateData();


						} catch (Exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage());
							e.printStackTrace();
						}
						
						}

					
	}});
				database.close();
				btnSave.setBounds(173, 195, 64, 23);
				add(btnSave);
				
				JButton btnClear = new JButton("Clear");
				btnClear.setBounds(284, 195, 64, 23);
				btnClear.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						txtName.setText("");
						txtAddress.setText("");
						txtPhone.setText("");
						txtEmail.setText("");
					}
				});
				add(btnClear);
				
				JLabel lblAddSuppliers = new JLabel("ADD Suppliers");
				lblAddSuppliers.setBounds(228, 40, 103, 14);
				add(lblAddSuppliers);
				
				
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getSelectedRow();
				String SuppliersID = table.getValueAt(index, 0).toString();
				/*
				String Name = table.getValueAt(index, 1).toString();
				String Email = table.getValueAt(index, 2).toString();
				String CountryCode = table.getValueAt(index, 3).toString();
				String Budget = table.getValueAt(index, 4).toString();
				String Used = table.getValueAt(index, 5).toString();
				*/

				SuppliersDetail detail = new SuppliersDetail(SuppliersID);
				detail.setModal(true);
				detail.setVisible(true);
				PopulateData();
			}
		});
		scrollPane.setViewportView(table);

		PopulateData();
	}

	private static void PopulateData() {

		// Clear table
		table.setModel(new DefaultTableModel());
		// Model for Table
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addColumn("supplierID");
		model.addColumn("supplierName");
		model.addColumn("supplierAddress");
		model.addColumn("Phone");
		model.addColumn("Email");
		
		Connection connect = null;
		Statement s = null;
		
		try {
			database.db();
			database.s = database.connect.createStatement();
			
			String sql = "SELECT * FROM  suppliers ORDER BY supplierID ASC";
			
			ResultSet rec = database.s.executeQuery(sql);
			
			int row = 0;
			while((rec!=null) && (rec.next()))
            {			
				model.addRow(new Object[0]);
				model.setValueAt(rec.getString("supplierID"), row, 0);
				model.setValueAt(rec.getString("supplierName"), row, 1);
				model.setValueAt(rec.getString("supplierAddress"), row, 2);
				model.setValueAt(rec.getString("Phone"), row, 3);
				model.setValueAt(rec.getString("Email"), row, 4);
				row++;
            }
             
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		database.close();


	}
	
}
