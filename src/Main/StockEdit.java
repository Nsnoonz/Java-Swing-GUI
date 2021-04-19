package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.database;

public class StockEdit extends JPanel {

	static JTable table;
	
	public StockEdit() {
		// ScrollPane
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(40, 70, 500, 400);
				add(scrollPane);

				// Table
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int index = table.getSelectedRow();
						String StockID = table.getValueAt(index, 0).toString();
						/*
						String Name = table.getValueAt(index, 1).toString();
						String Email = table.getValueAt(index, 2).toString();
						String CountryCode = table.getValueAt(index, 3).toString();
						String Budget = table.getValueAt(index, 4).toString();
						String Used = table.getValueAt(index, 5).toString();
						*/

						StockUpdate update = new StockUpdate(StockID);
						update.setModal(true);
						update.setVisible(true);

						PopulateData(); // Reload Table
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
				model.addColumn("stockID");
				model.addColumn("supplier");
				model.addColumn("product");
				model.addColumn("Price");


				Connection connect = null;
				Statement s = null;

				try {
					database.db();
					database.s = database.connect.createStatement();
				
					String sql = "SELECT * FROM  stock,suppliers,product "
							+ "WHERE stock.supplierID = suppliers.supplierID AND stock.productID = product.productID ORDER BY stockID ASC ";

					ResultSet rec = database.s.executeQuery(sql);

					int row = 0;
					while ((rec != null) && (rec.next())) {
						model.addRow(new Object[0]);
						model.setValueAt(rec.getString("stockID"), row, 0);
						model.setValueAt(rec.getString("suppliers.supplierName"), row, 1);
						model.setValueAt(rec.getString("product.productName"), row, 2);
						model.setValueAt(rec.getString("product.unitcost"), row, 3);
						row++;
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}

				try {
					if (database.s != null) {
						database.s.close();
						database.connect.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
