package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import java.awt.Font;

public class Buyproduct extends JPanel {
	static JTextField txtKeyword;
	static JTable table;

	public Buyproduct() {
		
		setLayout(null);
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(73, 122, 440, 300);
		add(scrollPane);
		
		// Keyword
				txtKeyword = new JTextField();
				txtKeyword.setBounds(160, 71, 160, 20);
				add(txtKeyword);
				txtKeyword.setColumns(10);
				
				// Button Search
				JButton btnSearch = new JButton("Search");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Bind new Data.
						PopulateData();
					}
				});
				btnSearch.setBounds(330, 70, 79, 23);
				add(btnSearch);
				
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int index = table.getSelectedRow();
						String ProductID = table.getValueAt(index, 0).toString();
						/*
						String Name = table.getValueAt(index, 1).toString();
						String Email = table.getValueAt(index, 2).toString();
						String CountryCode = table.getValueAt(index, 3).toString();
						String Budget = table.getValueAt(index, 4).toString();
						String Used = table.getValueAt(index, 5).toString();
						*/

					BuyproductDetail detail = new BuyproductDetail(ProductID);
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
		model.addColumn("productID");
		model.addColumn("productName");
		model.addColumn("productDetails");
		model.addColumn("unitcost");
		model.addColumn("categoryID");
		
		try {
			database.db();
			database.s = database.connect.createStatement();
			
			String sql = "SELECT * FROM  product,category WHERE productName like '%" + txtKeyword.getText() + "%' "
					+ "AND product.categoryID = category.categoryID ORDER BY productID ASC";
			
			ResultSet rec = database.s.executeQuery(sql);
			
			int row = 0;
			while((rec!=null) && (rec.next()))
            {			
				model.addRow(new Object[0]);
				model.setValueAt(rec.getString("productID"), row, 0);
				model.setValueAt(rec.getString("productName"), row, 1);
				model.setValueAt(rec.getString("productDetails"), row, 2);
				model.setValueAt(rec.getString("unitcost"), row, 3);
				model.setValueAt(rec.getString("category.categoryName"), row, 4);
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
