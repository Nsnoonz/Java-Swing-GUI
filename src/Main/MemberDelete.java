package Main;

import javax.swing.JPanel;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import database.database;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MemberDelete extends JPanel {

	private static JTable table;

	/**
	 * Create the panel.
	 */
	public MemberDelete() {
		setLayout(null);
		// Customer List
				JLabel lblMemberList = new JLabel("Member List");
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
							String MemberID = table.getValueAt(index, 0).toString();
							Object[] options = { "Yes", "No" };
							int n = JOptionPane.showOptionDialog(null,
									"Do you want to Delete data?",
									"Confirm to Delete?",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null, options,
									options[1]);
							if (n == 0) // Confirm Delete = Yes
							{
								DeleteData(MemberID); // Delete Data
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
				model.addColumn("memberID");
				model.addColumn("memName");
				model.addColumn("memSur");
				model.addColumn("Phone");
				model.addColumn("Email");
				model.addColumn("Address");

				Connection connect = null;
				Statement s = null;

				try {
					database.db();
					database.s = database.connect.createStatement();
					
					String sql = "SELECT * FROM  member ORDER BY memberID ASC";
					
					ResultSet rec = database.s.executeQuery(sql);
					
					int row = 0;
					while((rec!=null) && (rec.next()))
		            {			
						model.addRow(new Object[0]);
						model.setValueAt(rec.getString("memberID"), row, 0);
						model.setValueAt(rec.getString("memName"), row, 1);
						model.setValueAt(rec.getString("memSur"), row, 2);
						model.setValueAt(rec.getString("Phone"), row, 3);
						model.setValueAt(rec.getString("Email"), row, 4);
						model.setValueAt(rec.getString("Address"), row, 5);
						row++;
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}

			}

			// Delete
			private void DeleteData(String strMemberID) {

				Connection connect = null;
				Statement s = null;

				try {
					database.db();
					database.s = database.connect.createStatement();

					database.s = database.connect.createStatement();

					String sql = "DELETE FROM member  WHERE " +
							"memberID = '" + strMemberID + "' ";
					database.s.execute(sql);

					JOptionPane.showMessageDialog(null, "Delete Data Successfully");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				}

			}

}