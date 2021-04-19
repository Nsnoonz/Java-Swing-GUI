package Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import database.database;

public class StockUpdate extends JDialog {

	public StockUpdate(String sStockID) {
		setTitle("Update");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 400);
		getContentPane().setLayout(null);
		setResizable(false);
		// Header Customer Update
		JLabel hProductUpdate = new JLabel("Stock Update");
		hProductUpdate.setBounds(161, 21, 132, 14);
		getContentPane().add(hProductUpdate);

		// *** Header ***//
		JLabel hStockID = new JLabel("Stock ID :");
		hStockID.setBounds(68, 64, 64, 14);
		getContentPane().add(hStockID);

		JLabel hSuppliers = new JLabel("Suppliers ID :");
		hSuppliers.setBounds(68, 92, 89, 14);
		getContentPane().add(hSuppliers);

		JLabel hProduct = new JLabel("Product ID :");
		hProduct.setBounds(68, 169, 89, 14);
		getContentPane().add(hProduct);

		JLabel hPrice = new JLabel("Price :");
		hPrice.setBounds(68, 256, 89, 14);
		getContentPane().add(hPrice);

		// *** Update Form ***//
		// CustomerID
		final JLabel lblStockID = new JLabel("lblStockID");
		lblStockID.setBounds(165, 58, 99, 20);
		getContentPane().add(lblStockID);

		final JTextField txtSuppliers = new JTextField("");
		txtSuppliers.setBounds(165, 86, 24, 20);
		getContentPane().add(txtSuppliers);

		JLabel lblSuppliername = new JLabel("supplierName");
		lblSuppliername.setBounds(165, 114, 200, 14);
		getContentPane().add(lblSuppliername);

		JLabel lblProductname = new JLabel("productName");
		lblProductname.setBounds(165, 191, 200, 14);
		getContentPane().add(lblProductname);

		JLabel lblunitcost = new JLabel("unitcost");
		lblunitcost.setBounds(165, 256, 46, 14);
		getContentPane().add(lblunitcost);
		final JTextField txtProduct = new JTextField("");
		txtProduct.setBounds(165, 163, 24, 20);
		getContentPane().add(txtProduct);

		// *** Bind Data ***//
		Connection connect = null;
		Statement s = null;

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
		btnChoose.setBounds(165, 135, 147, 20);
		getContentPane().add(btnChoose);

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
		btnChoose2.setBounds(165, 216, 147, 20);
		getContentPane().add(btnChoose2);

		try {
			database.db();
			database.s = database.connect.createStatement();

			String sql = "SELECT * FROM  stock,suppliers,product "
					+ "WHERE stock.supplierID = suppliers.supplierID AND stock.productID = product.productID AND  stockID = '"
					+ sStockID + "' ";

			ResultSet rec = database.s.executeQuery(sql);

			if (rec != null) {
				rec.next();
				lblStockID.setText(rec.getString("stockID"));
				txtSuppliers.setText(rec.getString("supplierID"));
				lblSuppliername.setText(rec.getString("suppliers.supplierName"));
				lblProductname.setText(rec.getString("product.productName"));
				txtProduct.setText(rec.getString("productID"));
				lblunitcost.setText(rec.getString("product.unitcost"));
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
				SaveData(lblStockID.getText(), txtSuppliers.getText(), txtProduct.getText());
				dispose();
			}
		});
		btnSave.setBounds(142, 284, 69, 23);
		getContentPane().add(btnSave);

		// Button Close
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(228, 284, 69, 23);
		getContentPane().add(btnClose);

	}

	// Update
	private void SaveData(String strStockID, String strsupplierID, String strProductID) {

		Connection connect = null;
		Statement s = null;

		try {
			database.db();
			database.s = database.connect.createStatement();

			String sql = "UPDATE stock " + "SET supplierID = '" + strsupplierID + "' " + ", productID = '"
					+ strProductID + "' " + " " + " WHERE stockID = '" + strStockID + "' ";
			database.s.execute(sql);

			JOptionPane.showMessageDialog(null, "Record Update Successfully");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}

	}
}
