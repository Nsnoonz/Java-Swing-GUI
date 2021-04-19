package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.database;

public class StockDelete extends JPanel {

	private static JTable table;

	
	public StockDelete() {
		setLayout(null);
		// Customer List
				JLabel lblMemberList = new JLabel("Stock List");
				lblMemberList.setBounds(253, 43, 87, 14);
				add(lblMemberList);

				// ScrollPane
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(45, 70, 500, 200);
				add(scrollPane);

				// Table
				table = new JTable();
				scrollPane.setViewportView(table);

				// Button Delete
				JButton btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int index = table.getSelectedRow();
						if (index < 0) {
							JOptionPane.showMessageDialog(null,
									"Please select record for Delete!");
						} else {
							String StockID = table.getValueAt(index, 0).toString();
							Object[] options = { "Yes", "No" };
							int n = JOptionPane.showOptionDialog(null,
									"Do you want to Delete data?",
									"Confirm to Delete?",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, options,
									options[1]);
							if (n == 0) // Confirm Delete = Yes
							{
								DeleteData(StockID); // Delete Data
								PopulateData(); // Reload Table
							}

						}

					}
				});
				btnDelete.setBounds(239, 299, 89, 23);
			add(btnDelete);

				PopulateData();
			}

	private static void PopulateData() {

		// Clear table
		table.setModel(new DefaultTableModel());
		// Model for Table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
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
				model.setValueAt(rec.getString("Price"), row, 3);
				row++;
			}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}

			}

			// Delete
			private void DeleteData(String strStockID) {

				Connection connect = null;
				Statement s = null;

				try {
					database.db();
					database.s = database.connect.createStatement();


					String sql = "DELETE FROM stock  WHERE " +
							"stockID = '" + strStockID + "' ";
					database.s.execute(sql);

					JOptionPane.showMessageDialog(null, "Delete Data Successfully");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}

			}

}