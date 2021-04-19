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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.database;

public class Product extends JDialog {

	public String sProductID;
	public String unitcost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Product dialog = new Product();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Product() {
		setBounds(100, 100, 400, 400);
		setTitle("Choose Product");

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
		model.addColumn("productID");
		model.addColumn("productName");
		
		try {
			database.db();
			
			database.s = database.connect.createStatement();
			
			String sql = "SELECT * FROM  product ORDER BY productID ASC";
			
			ResultSet rec = database.s.executeQuery(sql);
			int row = 0;
			while((rec!=null) && (rec.next()))
            {			
				model.addRow(new Object[0]);
				model.setValueAt(rec.getString("productID"), row, 0);
				model.setValueAt(rec.getString("productName"), row, 1);
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
						sProductID = table.getValueAt(index, 0).toString();
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
	
	