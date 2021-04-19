package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.database;

public class SuppliersEdit extends JPanel {

	private static JTable table;
	
	public SuppliersEdit() {// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 70, 500, 400);
		add(scrollPane);

		// Table
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getSelectedRow();
				String SuppliersID = table.getValueAt(index, 0).toString();
//				String memName = table.getValueAt(index, 1).toString();
//				String memSur = table.getValueAt(index, 2).toString();
//				String Phone = table.getValueAt(index, 3).toString();
//				String Email = table.getValueAt(index, 4).toString();
//				String Address = table.getValueAt(index, 5).toString();

				SuppliersUpdate update = new SuppliersUpdate(SuppliersID);
				update.setModal(true);
				update.setVisible(true);

				PopulateData(); // Reload Table
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
		model.addColumn("supplierID");
		model.addColumn("supplierName");
		model.addColumn("supplierAddress");
		model.addColumn("Phone");
		model.addColumn("Email");

		Connection connect = null;
		Statement s = null;

		try {
			database.db();
			database.s = database.connect.createStatement();
			
			String sql = "SELECT * FROM  suppliers ORDER BY supplierID ASC";
			
			ResultSet rec = database.s.executeQuery(sql);
			
			int row = 0;
			while((rec!=null) && (rec.next()))
            {			
				model.addRow(new Object[0]);
				model.setValueAt(rec.getString("supplierID"), row, 0);
				model.setValueAt(rec.getString("supplierName"), row, 1);
				model.setValueAt(rec.getString("supplierAddress"), row, 2);
				model.setValueAt(rec.getString("Phone"), row, 3);
				model.setValueAt(rec.getString("Email"), row, 4);
				row++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}


	}

}
