package Main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.database;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Suppliers extends JDialog {

	public String sSupplierID;
	public static void main(String[] args) {
		try {
			category dialog = new category();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Suppliers() {
		setBounds(100, 100, 400, 400);
		setTitle("Choose Suppliers");

		getContentPane().setLayout(null);
		
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 30, 200, 200);
		getContentPane().add(scrollPane);
		
		// Table
		final JTable table = new JTable();
		scrollPane.setViewportView(table);
			
		// Model for Table
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addColumn("supplierID");
		model.addColumn("supplierName");
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
				row++;
            }

			rec.close();
             
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		database.close();
		
		// Button  OK
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int index = table.getSelectedRow();
						sSupplierID = table.getValueAt(index, 0).toString();
						dispose();
					}
				});
				btnOk.setBounds(100, 250, 83, 23);
				getContentPane().add(btnOk);
				
				// Button Cancel
				JButton btnCancel = new JButton("Cancel");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setBounds(200, 250, 83, 23);
				getContentPane().add(btnCancel);
		
	}
	}
	
	