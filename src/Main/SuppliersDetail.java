package Main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

		public class SuppliersDetail extends JDialog {

			/**
			 * Create the dialog.
			 */
			public SuppliersDetail(String sSuppliersID) {
				setTitle("Member Detail");
				setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				setBounds(100, 100, 385, 266);
				getContentPane().setLayout(null);
				setResizable(false);
				
				// Header Customer Detail
				JLabel hSuppliersDetail = new JLabel("Suppliers Details");
				hSuppliersDetail.setBounds(106, 20, 132, 14);
				getContentPane().add(hSuppliersDetail);
				
				//*** Header ***//
				JLabel hSuppliesID = new JLabel("hMemberID :");
				hSuppliesID.setBounds(62, 50, 89, 14);
				getContentPane().add(hSuppliesID);
				
				JLabel hName = new JLabel("Name :");
				hName.setBounds(62, 75, 89, 14);
				getContentPane().add(hName);
				
				JLabel hAddress = new JLabel("Address :");
				hAddress.setBounds(62, 99, 89, 14);
				getContentPane().add(hAddress);
				
				JLabel hPhone = new JLabel("Phone :");
				hPhone.setBounds(62, 122, 89, 14);
				getContentPane().add(hPhone);
				
				JLabel hEmail = new JLabel("Email :");
				hEmail.setBounds(62, 145, 89, 14);
				getContentPane().add(hEmail);
				
				//*** Detail ***//
				JLabel lblMemberID = new JLabel("lblMemberID");
				lblMemberID.setBounds(169, 50, 89, 14);
				getContentPane().add(lblMemberID);
				
				JLabel lblName = new JLabel("lblName");
				lblName.setBounds(169, 75, 200, 14);
				getContentPane().add(lblName);
				
				JLabel lblAddress = new JLabel("lblAddress");
				lblAddress.setBounds(169, 99, 200, 14);
				getContentPane().add(lblAddress);
				
				JLabel lblPhone = new JLabel("lblPhone");
				lblPhone.setBounds(169, 122, 100, 14);
				getContentPane().add(lblPhone);
				
				JLabel lblEmail = new JLabel("lblEmail");
				lblEmail.setBounds(169, 145, 200, 14);
				getContentPane().add(lblEmail);
				
				//*** Bind Data ***//
				Connection connect = null;
				Statement s = null;
				
				try {
			
					
					database.db();
					database.s = database.connect.createStatement();
					
					String sql = "SELECT * FROM  suppliers " +
							"WHERE supplierID = '" + sSuppliersID + "' ";
					
					ResultSet rec = database.s.executeQuery(sql);
				
					
					if(rec != null) {
						rec.next();
						lblMemberID.setText(rec.getString("supplierID"));
						lblName.setText(rec.getString("supplierName"));
						lblAddress.setText(rec.getString("supplierAddress"));
						lblPhone.setText(rec.getString("Phone"));
						lblEmail.setText(rec.getString("Email"));
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
				btnClose.setBounds(106, 183, 69, 23);
				getContentPane().add(btnClose);
				
			}

		}