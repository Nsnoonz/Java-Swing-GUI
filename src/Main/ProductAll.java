package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.database;

public class ProductAll extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProductAll() {
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 70, 500, 400);
		add(scrollPane);
				
				// Table
				final JTable table = new JTable();
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

						ProductDetail detail = new ProductDetail(ProductID);
						detail.setModal(true);
						detail.setVisible(true);

					}
				});
				scrollPane.setViewportView(table);
				
				// Model for Table
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.addColumn("productID");
				model.addColumn("productName");
				model.addColumn("productDetails");
				model.addColumn("productTime");
				model.addColumn("unitcost");
				model.addColumn("unitprice");
				model.addColumn("amount");
				model.addColumn("categoryID");
				
				Connection connect = null;
				Statement s = null;
				
				try {
					database.db();
					database.s = database.connect.createStatement();
					
					String sql = "SELECT * FROM product , category WHERE product.categoryID = category.categoryID";
					
					ResultSet rec = database.s.executeQuery(sql);
					
					int row = 0;
					while((rec!=null) && (rec.next()))
		            {			
						model.addRow(new Object[0]);
						model.setValueAt(rec.getString("productID"), row, 0);
						model.setValueAt(rec.getString("productName"), row, 1);
						model.setValueAt(rec.getString("productDetails"), row, 2);
						model.setValueAt(rec.getString("productTime"), row, 3);
						model.setValueAt(rec.getString("unitcost"), row, 4);
						model.setValueAt(rec.getString("unitprice"), row, 5);
						model.setValueAt(rec.getString("amount"), row, 6);
						model.setValueAt(rec.getString("category.categoryName"), row, 7);
						row++;
		            }
					rec.close();
		             
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
			
				
			}
		}