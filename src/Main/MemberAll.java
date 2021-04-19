package Main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import database.database;

public class MemberAll extends JPanel {

	/**
	 * Create the panel.
	 */
	public MemberAll() {
		
		setLayout(null);
		
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 65, 440, 300);
		add(scrollPane);
		
		// Table
		final JTable table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getSelectedRow();
				String MemberID = table.getValueAt(index, 0).toString();
				/*
				String Name = table.getValueAt(index, 1).toString();
				String Email = table.getValueAt(index, 2).toString();
				String CountryCode = table.getValueAt(index, 3).toString();
				String Budget = table.getValueAt(index, 4).toString();
				String Used = table.getValueAt(index, 5).toString();
				*/

				MemAllDetail detail = new MemAllDetail(MemberID);
				detail.setModal(true);
				detail.setVisible(true);

			}
		});
		scrollPane.setViewportView(table);
		
		// Model for Table
		DefaultTableModel model = (DefaultTableModel)table.getModel();
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
		database.close();
	
		
	}
}