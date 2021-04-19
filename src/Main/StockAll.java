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

public class StockAll extends JPanel {
	
	static JTable table;

	public StockAll() {
		setLayout(null);
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 217, 440, 300);
		add(scrollPane);

		// *** Header ***//
		JLabel hSuppliers = new JLabel("Suppliers :");
		hSuppliers.setBounds(150, 83, 89, 14);
		add(hSuppliers);

		JLabel hProduct = new JLabel("Product :");
		hProduct.setBounds(150, 107, 89, 14);
		add(hProduct);

		final JTextField txtSuppliers = new JTextField("");
		txtSuppliers.setBounds(257, 83, 176, 20);
		add(txtSuppliers);

		final JTextField txtProduct = new JTextField("");
		txtProduct.setBounds(257, 107, 176, 20);
		add(txtProduct);

		// Choose Suppliers
		JButton btnChoose = new JButton("Selete Suppliers");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Suppliers Suppliers = new Suppliers();
				Suppliers.setModal(true);
				Suppliers.setVisible(true);
				txtSuppliers.setText(Suppliers.sSupplierID);

			}
		});
		btnChoose.setBounds(432, 83, 25, 20);
		add(btnChoose);

		// Choose Product
		JButton btnChoose2 = new JButton("Selete Product");
		btnChoose2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Product Product = new Product();
				Product.setModal(true);
				Product.setVisible(true);
				txtProduct.setText(Product.sProductID);

			}
		});
		btnChoose2.setBounds(432, 107, 25, 20);
		add(btnChoose2);

		// Save Button
		JButton btnSave = new JButton("ADD");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					database.db();

					database.s = database.connect.createStatement();
					
					// SQL Insert
					String sql = "INSERT INTO stock "
							+ "(supplierID,productID) "
							+ "VALUES ('" + txtSuppliers.getText() + "','"
							+ txtProduct.getText() +  "') ";
						
					database.s.execute(sql);
					
					// Reset Text Fields
				
					txtSuppliers.setText("");
					txtProduct.setText("");
				
					
					JOptionPane.showMessageDialog(null,"Record Inserted Successfully");
					PopulateData();


				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
				}

			
});
		database.close();
		btnSave.setBounds(173, 172, 64, 23);
		add(btnSave);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(284, 172, 64, 23);
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtSuppliers.setText("");
				txtProduct.setText("");
				
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
				String StockID = table.getValueAt(index, 0).toString();
				/*
				 * String Name = table.getValueAt(index, 1).toString(); String Email =
				 * table.getValueAt(index, 2).toString(); String CountryCode =
				 * table.getValueAt(index, 3).toString(); String Budget =
				 * table.getValueAt(index, 4).toString(); String Used = table.getValueAt(index,
				 * 5).toString();
				 */
				stockDetail detail = new stockDetail(StockID);
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
				model.setValueAt(rec.getString("product.unitcost"), row, 3);
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
