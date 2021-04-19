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

public class stockDetail extends JDialog {

	/**
	 * Create the panel.
	 */
	public stockDetail(String sStockID) {
		setTitle("Stock Detail");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 385, 266);
		getContentPane().setLayout(null);
		setResizable(false);
		
		// Header Customer Detail
		JLabel hStockDetail = new JLabel("Stock Details");
		hStockDetail.setBounds(106, 20, 132, 14);
		getContentPane().add(hStockDetail);
		
		//*** Header ***//
		JLabel hStockID = new JLabel("hStockID :");
		hStockID.setBounds(62, 50, 89, 14);
		getContentPane().add(hStockID);
		
		JLabel hSupplier = new JLabel("Supplier :");
		hSupplier.setBounds(62, 75, 89, 14);
		getContentPane().add(hSupplier);
		
		JLabel hProduct = new JLabel("Product :");
		hProduct.setBounds(62, 99, 89, 14);
		getContentPane().add(hProduct);
		
		JLabel hPrice = new JLabel("Price :");
		hPrice.setBounds(62, 122, 89, 14);
		getContentPane().add(hPrice);
		
		//*** Detail ***//
		JLabel lblStockID = new JLabel("lblStockID");
		lblStockID.setBounds(169, 50, 89, 14);
		getContentPane().add(lblStockID);
		
		JLabel lblSupplier = new JLabel("lblSupplier");
		lblSupplier.setBounds(169, 75, 200, 14);
		getContentPane().add(lblSupplier);
		
		JLabel lblProduct = new JLabel("lblProduct");
		lblProduct.setBounds(169, 99, 200, 14);
		getContentPane().add(lblProduct);
		
		JLabel lblPrice = new JLabel("lblPrice");
		lblPrice.setBounds(169, 122, 100, 14);
		getContentPane().add(lblPrice);
		
		//*** Bind Data ***//
		Connection connect = null;
		Statement s = null;
		
		try {
	
			
			database.db();
			database.s = database.connect.createStatement();
			
			String sql = "SELECT * FROM  stock,suppliers,product "
					+ "WHERE stock.supplierID = suppliers.supplierID AND stock.productID = product.productID AND  stockID = '" + sStockID + "' ";
			
			ResultSet rec = database.s.executeQuery(sql);
		
			
			if(rec != null) {
				rec.next();
				lblStockID.setText(rec.getString("stockID"));
				lblSupplier.setText(rec.getString("suppliers.supplierName"));
				lblProduct.setText(rec.getString("product.productName"));
				lblPrice.setText(rec.getString("product.unitcost"));
				
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
		btnClose.setBounds(106, 160, 69, 23);
		getContentPane().add(btnClose);
		
	}

}