package Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.database;

public class ProductDetail extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public ProductDetail(String sProductID) {
		setTitle("Product Detail");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		getContentPane().setLayout(null);
		setResizable(false);
		
		// Header Customer Detail
		JLabel hMemberDetail = new JLabel("Product Details");
		hMemberDetail.setBounds(106, 20, 132, 14);
		getContentPane().add(hMemberDetail);
		
		//*** Header ***//
		JLabel hProductID = new JLabel("ProductID :");
		hProductID.setBounds(62, 50, 89, 14);
		getContentPane().add(hProductID);
		
		JLabel hName = new JLabel("Name :");
		hName.setBounds(62, 75, 89, 14);
		getContentPane().add(hName);
		
		JLabel hDetail = new JLabel("Detail :");
		hDetail.setBounds(62, 99, 89, 14);
		getContentPane().add(hDetail);
		
		JLabel hTime = new JLabel("TIME REG :");
		hTime.setBounds(62, 122, 89, 14);
		getContentPane().add(hTime);
		
		JLabel hcost = new JLabel("cost :");
		hcost.setBounds(62, 145, 100, 14);
		getContentPane().add(hcost);
		
		JLabel hprice = new JLabel("price :");
		hprice.setBounds(62, 165, 100, 14);
		getContentPane().add(hprice);
		
		JLabel hAmount = new JLabel("Amount :");
		hAmount.setBounds(62, 187, 100, 14);
		getContentPane().add(hAmount);
		
		JLabel hCategory = new JLabel("Category :");
		hCategory.setBounds(62, 212, 100, 14);
		getContentPane().add(hCategory);
		
		//*** Detail ***//
		JLabel lblMemberID = new JLabel("lblMemberID");
		lblMemberID.setBounds(169, 50, 100, 14);
		getContentPane().add(lblMemberID);
		
		JLabel lblName = new JLabel("lblName");
		lblName.setBounds(169, 75, 200, 14);
		getContentPane().add(lblName);
		
		JLabel lblDetail = new JLabel("lblDetail");
		lblDetail.setBounds(169, 99, 200, 14);
		getContentPane().add(lblDetail);
		
		JLabel lblTime = new JLabel("lblTime");
		lblTime.setBounds(169, 122, 200, 14);
		getContentPane().add(lblTime);
		
		
		JLabel lblunitcost = new JLabel("lblunitcost");
		lblunitcost.setBounds(172, 145, 97, 14);
		getContentPane().add(lblunitcost);
		
		JLabel lblunitprice = new JLabel("lblunitprice");
		lblunitprice.setBounds(172, 165, 97, 14);
		getContentPane().add(lblunitprice);
		
		JLabel lblamount = new JLabel("lblamount");
		lblamount.setBounds(172, 187, 46, 14);
		getContentPane().add(lblamount);
	
		JLabel lblCategory = new JLabel("lblCategory");
		lblCategory.setBounds(172, 212, 200, 14);
		getContentPane().add(lblCategory);
		
		
		//*** Bind Data ***//
		Connection connect = null;
		Statement s = null;
		
		try {
	
			
			database.db();
			database.s = database.connect.createStatement();
			
//			String sql = "SELECT * FROM  product " + "WHERE productID = '" + sProductID + "' ";
		
			String sql = "SELECT * FROM product , category WHERE product.categoryID = category.categoryID AND productID = '" + sProductID + "' ";
			ResultSet rec = database.s.executeQuery(sql);
		
			
			if(rec != null) {
				rec.next();
				lblMemberID.setText(rec.getString("productID"));
				lblName.setText(rec.getString("productName"));
				lblDetail.setText(rec.getString("productDetails"));
				lblTime.setText(rec.getString("productTime"));
				lblunitcost.setText(rec.getString("unitcost"));
				lblunitprice.setText(rec.getString("unitprice"));
				lblamount.setText(rec.getString("amount"));
				lblCategory.setText(rec.getString("category.categoryName"));
				
            }
	
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
		btnClose.setBounds(106, 237, 69, 23);
		getContentPane().add(btnClose);
		


		
	}

}