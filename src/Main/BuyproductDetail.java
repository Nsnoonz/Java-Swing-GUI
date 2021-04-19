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
import java.sql.Timestamp;

import javax.swing.JOptionPane;

import database.database;
import java.awt.Font;
import javax.swing.JTextField;

public class BuyproductDetail extends JDialog {


	/**
	 * Create the dialog.
	 */
	public BuyproductDetail(String sProductID) {
		
		
		setTitle("Buy Product Detail");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		getContentPane().setLayout(null);
		setResizable(false);
		
		// Header Customer Detail
		JLabel hMemberDetail = new JLabel("Buy Product Details");
		hMemberDetail.setBounds(118, 24, 132, 14);
		getContentPane().add(hMemberDetail);
		
		//*** Header ***//
		JLabel hProductID = new JLabel("ProductID :");
		hProductID.setBounds(74, 54, 89, 14);
		getContentPane().add(hProductID);
		
		JLabel hName = new JLabel("Name :");
		hName.setBounds(74, 79, 89, 14);
		getContentPane().add(hName);
		
		JLabel hDetail = new JLabel("Detail :");
		hDetail.setBounds(74, 103, 89, 14);
		getContentPane().add(hDetail);
		
		JLabel hcost = new JLabel("cost :");
		hcost.setBounds(74, 128, 100, 14);
		getContentPane().add(hcost);
		
		JLabel hCategory = new JLabel("Category :");
		hCategory.setBounds(74, 149, 100, 14);
		getContentPane().add(hCategory);
		
		//*** Detail ***//
		JLabel lblProductID = new JLabel("lblProductID");
		lblProductID.setBounds(181, 54, 100, 14);
		getContentPane().add(lblProductID);
		
		JLabel lblName = new JLabel("lblName");
		lblName.setBounds(181, 79, 200, 14);
		getContentPane().add(lblName);
		
		JLabel lblDetail = new JLabel("lblDetail");
		lblDetail.setBounds(181, 103, 200, 14);
		getContentPane().add(lblDetail);
		
		
		JLabel lblunitcost = new JLabel("lblunitcost");
		lblunitcost.setBounds(184, 128, 97, 14);
		getContentPane().add(lblunitcost);
	
		JLabel lblCategory = new JLabel("lblCategory");
		lblCategory.setBounds(184, 149, 200, 14);
		getContentPane().add(lblCategory);
		JLabel labelAmount = new JLabel("จำนวน :");
		labelAmount.setFont(new Font("Kanit", Font.PLAIN, 14));
		labelAmount.setBounds(74, 174, 63, 14);
		getContentPane().add(labelAmount);
		
		final JTextField textQTY = new JTextField();
		textQTY.setBounds(181, 174, 86, 20);
		getContentPane().add(textQTY);
		textQTY.setColumns(10);
		
		//*** Bind Data ***//

		try {
	
			
			database.db();
			database.s = database.connect.createStatement();
			
//			String sql = "SELECT * FROM product , category,stock  WHERE product.productID=stock.productID ";
		
			String sql = "SELECT * FROM product,category WHERE product.categoryID = category.categoryID AND productID = '" + sProductID + "' ";
			ResultSet rec = database.s.executeQuery(sql);
		
			
			if(rec != null) {
				rec.next();
				lblProductID.setText(rec.getString("productID"));
				lblName.setText(rec.getString("productName"));
				lblDetail.setText(rec.getString("productDetails"));
				lblunitcost.setText(rec.getString("unitcost"));
				lblCategory.setText(rec.getString("category.categoryName"));
				
            }
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		database.close();
		
		// Buy Button
				JButton btnBuy = new JButton("BUY");
				btnBuy.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int productID = Integer.parseInt(lblProductID.getText());
						int amount = Integer.parseInt(textQTY.getText());		
			            int sum = 0;
			            int total_sum = 0;
			            int maxID = 0;
			            int supID = 0;

					try {
						database.db();
						database.s = database.connect.createStatement();	
		          		String sql_invoiceMaxID = "SELECT MAX(invoiceID) AS maxID FROM invoice";
		                database.s.execute(sql_invoiceMaxID);
		                ResultSet rec_invoiceMaxID = database.s.executeQuery(sql_invoiceMaxID);
		                if (rec_invoiceMaxID != null) {
		                	rec_invoiceMaxID.next();
		                    maxID = rec_invoiceMaxID.getInt("maxID") + 1;
		                }
                
		                
		                database.db();
						database.s = database.connect.createStatement();
						 
						String sql_Good = "SELECT * FROM product,stock WHERE product.productID=stock.productID";
                        ResultSet rec_Good = database.s.executeQuery(sql_Good);
                        if((rec_Good != null) && (rec_Good.next())) {
                        	database.s = database.connect.createStatement();
                        	
                        	 supID = rec_Good.getInt("stock.supplierID");
                        	 System.out.print(supID);
                        	
                            sum = rec_Good.getInt("unitcost") * Integer.valueOf(amount);
                            total_sum = total_sum + sum;
                         String sql_invoice_DetAdd = "INSERT INTO `invoice_details` (`invoiceDetails`, `invoiceID`, `productID`, `Quantity`, `Amount`) VALUES (NULL, '" + maxID + "', '" + productID + "', '" + textQTY.getText() + "', '" + sum + "')";
                            database.s.execute(sql_invoice_DetAdd);
                         }
                        		 String sql_invoiceAdd = "INSERT INTO `invoice` (`invoiceID`, `supplierID`, `invoiceDate`, `invoiceTotal`,status, `total_price`) VALUES (NULL,'"+supID+"', CURRENT_TIMESTAMP, '" + textQTY.getText() + "','0', '" + total_sum + "')";
                        	  database. s.execute(sql_invoiceAdd);
                        } catch (Exception e) {
			                // TODO Auto-generated catch block
			                
			            }
                        }
					});
				
				btnBuy.setBounds(74, 235, 69, 23);
				getContentPane().add(btnBuy);
		// Close Button
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnClose.setBounds(181, 235, 69, 23);
		getContentPane().add(btnClose);
		
		
		


		
	}

}