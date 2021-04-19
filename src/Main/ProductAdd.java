package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.database;
import java.awt.Font;

public class ProductAdd extends JPanel {
	private JTextField txtunitcost;
	private JTextField txtunitprice;
	private JTextField txtamount;

	/**
	 * Create the panel.
	 */
	public ProductAdd() {
		setLayout(null);
		// *** Header ***//
		JLabel hName = new JLabel("Name :");
		hName.setBounds(151, 75, 89, 14);
		add(hName);

		JLabel hDetail = new JLabel("Detail :");
		hDetail.setBounds(151, 102, 89, 14);
		add(hDetail);

		JLabel hCategory = new JLabel("category:");
		hCategory.setBounds(151, 124, 89, 14);
		add(hCategory);
		
		JLabel lblAddProduct = new JLabel("ADD Product");
		lblAddProduct.setBounds(229, 32, 79, 14);
		add(lblAddProduct);
		JLabel labelunitPrice = new JLabel("ราคาขาย :");
		labelunitPrice.setFont(new Font("Kanit", Font.PLAIN, 14));
		labelunitPrice.setBounds(151, 172, 69, 14);
		add(labelunitPrice);
		JLabel labelunitcost = new JLabel("ต้นทุน :");
		labelunitcost.setFont(new Font("Kanit", Font.PLAIN, 14));
		labelunitcost.setBounds(150, 147, 70, 20);
		add(labelunitcost);
		
		JLabel label = new JLabel("จำนวน :");
		label.setFont(new Font("Kanit", Font.PLAIN, 14));
		label.setBounds(151, 199, 46, 14);
		add(label);
		
		final JTextField txtamount = new JTextField("");
		txtamount.setBounds(258, 197, 86, 20);
		add(txtamount);
		txtamount.setColumns(10);
		
		final JTextField txtName = new JTextField("");
		txtName.setBounds(258, 75, 176, 20);
		add(txtName);

		final JTextField txtDetail = new JTextField("");
		txtDetail.setBounds(258, 99, 176, 20);
		add(txtDetail);

		final JTextField txtCategory = new JTextField("");
		txtCategory.setBounds(258, 122, 176, 20);
		add(txtCategory);
		
		
		final JTextField txtunitcost = new JTextField("");
		txtunitcost.setBounds(258, 147, 86, 20);
		add(txtunitcost);
		txtunitcost.setColumns(10);
		
		
		
		final JTextField txtunitprice = new JTextField("");
		txtunitprice.setBounds(258, 170, 86, 20);
		add(txtunitprice);
		txtunitprice.setColumns(10);
		// Choose
				JButton btnChoose = new JButton("...");
				btnChoose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						category caterory = new category();
						caterory.setModal(true);
						caterory.setVisible(true);
						txtCategory.setText(caterory.sCategoryID);
						
					}
				});
				btnChoose.setBounds(444, 121, 25, 20);
				add(btnChoose);

		
		// Save Button
				JButton btnSave = new JButton("ADD");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (txtName.getText().equals("") || txtDetail.getText().equals("") ) {
							JOptionPane.showMessageDialog(null, "Please input Again");
						} else {

						Connection connect = null;
						Statement s = null;

						try {
							database.db();

							database.s = database.connect.createStatement();
							
							// SQL Insert
							String sql = "INSERT INTO product "
									+ "(productName,productDetails,unitcost,unitprice,amount,categoryID) "
									+ "VALUES ('" + txtName.getText() + "','"
									+ txtDetail.getText() + "'" + ",'"
									+ txtunitcost.getText() + "'" + ",'"
									+ txtunitprice.getText() + "'" + ",'"
									+ txtamount.getText() + "'" + ",'"
									+ txtCategory.getText() + "') ";
							database.s.execute(sql);
							
							// Reset Text Fields
						
							txtName.setText("");
							txtDetail.setText("");
							txtunitcost.setText("");
							txtunitprice.setText("");
							txtamount.setText("");
							txtCategory.setText("");
						
							
							JOptionPane.showMessageDialog(null,"Record Inserted Successfully");


						} catch (Exception e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e.getMessage());
							e.printStackTrace();
						}
						}

					
	}});
				btnSave.setBounds(175, 265, 64, 23);
				add(btnSave);
				
				JButton btnClear = new JButton("Clear");
				btnClear.setBounds(286, 265, 64, 23);
				btnClear.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						txtName.setText("");
						txtDetail.setText("");
						txtunitcost.setText("");
						txtunitprice.setText("");
						txtCategory.setText("");
							}
				});
				add(btnClear);
				
				
			
	}
}
