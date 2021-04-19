package Main;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import database.database;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductUpdate extends JDialog {
	
	/**
	 * Launch the application.
	 */

	public ProductUpdate(String sProductID) {
		setTitle("Update");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 447, 400);
		getContentPane().setLayout(null);
		setResizable(false);
		// Header Customer Update
				JLabel hProductUpdate = new JLabel("Product Update");
				hProductUpdate.setBounds(144, 21, 132, 14);
				getContentPane().add(hProductUpdate);

				// *** Header ***//
				JLabel hProductID = new JLabel("ProductID :");
				hProductID.setBounds(100, 53, 89, 14);
				getContentPane().add(hProductID);

				JLabel hName = new JLabel("Name :");
				hName.setBounds(100, 78, 89, 14);
				getContentPane().add(hName);

				JLabel hDetail = new JLabel("Detail :");
				hDetail.setBounds(100, 106, 89, 14);
				getContentPane().add(hDetail);

				JLabel hcategory = new JLabel("category :");
				hcategory.setBounds(100, 178, 89, 14);
				getContentPane().add(hcategory);
				
				JLabel lblUnitcost = new JLabel("Unitcost :");
				lblUnitcost.setBounds(100, 128, 46, 14);
				getContentPane().add(lblUnitcost);
				
			
				
				JLabel lblUnitprice = new JLabel("unitprice");
				lblUnitprice.setBounds(100, 153, 46, 14);
				getContentPane().add(lblUnitprice);
				
				JLabel lblAmount = new JLabel("Amount :");
				lblAmount.setBounds(100, 203, 46, 14);
				getContentPane().add(lblAmount);
				
				final JTextField txtAmount = new JTextField("");
				txtAmount.setBounds(207, 200, 86, 20);
				getContentPane().add(txtAmount);
				txtAmount.setColumns(10);
			
				// *** Update Form ***//
				// CustomerID
				final JLabel lblProductID = new JLabel("lblProductID");
				lblProductID.setBounds(207, 51, 99, 20);
				getContentPane().add(lblProductID);

				// Name
				final JTextField txtName = new JTextField("");
				txtName.setBounds(207, 76, 99, 20);
				getContentPane().add(txtName);

				// Email
				final JTextField txtDetail = new JTextField("");
				txtDetail.setBounds(207, 100, 176, 20);
				getContentPane().add(txtDetail);

				final JTextField txtunitcost = new JTextField("");
				txtunitcost.setBounds(207, 125, 86, 20);
				getContentPane().add(txtunitcost);
				txtunitcost.setColumns(10);
				
				final JTextField txtunitprice = new JTextField("");
				txtunitprice.setBounds(207, 151, 86, 20);
				getContentPane().add(txtunitprice);
				txtunitprice.setColumns(10);

				// Budget
				final JTextField txtcategory = new JTextField("");
				txtcategory.setBounds(207, 175, 99, 20);
				getContentPane().add(txtcategory);
				
				
				// Choose
				JButton btnChoose = new JButton("...");
				btnChoose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						category caterory = new category();
						caterory.setModal(true);
						caterory.setVisible(true);
						txtcategory.setText(caterory.sCategoryID);
						
					}
				});
				
				btnChoose.setBounds(316, 175, 25, 20);
				getContentPane().add(btnChoose);

				// *** Bind Data ***//
				Connection connect = null;
				Statement s = null;

				try {
					database.db();
					database.s = database.connect.createStatement();

					String sql = "SELECT * FROM product , category WHERE product.categoryID = category.categoryID AND productID = '"
							+ sProductID + "' ";

					ResultSet rec = database.s.executeQuery(sql);

					if (rec != null) {
						rec.next();
						lblProductID.setText(rec.getString("productID"));
						txtName.setText(rec.getString("productName"));
						txtDetail.setText(rec.getString("productDetails"));
						txtunitcost.setText(rec.getString("unitcost"));
						txtunitprice.setText(rec.getString("unitprice"));
						txtAmount.setText(rec.getString("amount"));
						txtcategory.setText(rec.getString("categoryID"));
					
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
						SaveData(lblProductID.getText(), txtName.getText(),
								txtDetail.getText(),txtunitcost.getText(),txtunitprice.getText(),txtAmount.getText(), txtcategory.getText());
						dispose();
					}
				});
				btnSave.setBounds(129, 235, 69, 23);
				getContentPane().add(btnSave);

				// Button Close
				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnClose.setBounds(215, 235, 69, 23);
				getContentPane().add(btnClose);
				
			
				
			
			}
	
			// Update
			private void SaveData(String strProductID, String strName, String strDetail, String strunitcost, String strunitprice,String strAmount,String strcategory) {
				
				Connection connect = null;
				Statement s = null;
				
				try {
					database.db();
					database.s = database.connect.createStatement();
					
					String sql = "UPDATE product " +
							"SET productName = '" + strName + "' " +
							", productDetails = '" + strDetail + "' " +
							", unitcost = '" + strunitcost + "' " +
							", unitprice = '" + strunitprice + "' " +
							", amount = '" + strAmount + "' " +
							", categoryID = '" + strcategory + "' " +
							" WHERE productID = '"+strProductID+"' ";
					database.s.execute(sql);
		            
		     		JOptionPane.showMessageDialog(null, "Record Update Successfully");
		             
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}
				
				try {
					if(s != null) {
						s.close();
						connect.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
		
	}
}
