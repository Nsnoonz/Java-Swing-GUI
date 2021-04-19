package Main;

import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class MemberEdit extends JPanel {

	private static JTable table;

	/**
	 * Create the panel.
	 */
	public MemberEdit() {

		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 70, 500, 400);
		add(scrollPane);

		// Table
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table.getSelectedRow();
				String memberID = table.getValueAt(index, 0).toString();
//				String memName = table.getValueAt(index, 1).toString();
//				String memSur = table.getValueAt(index, 2).toString();
//				String Phone = table.getValueAt(index, 3).toString();
//				String Email = table.getValueAt(index, 4).toString();
//				String Address = table.getValueAt(index, 5).toString();

				MemberUpdateForm update = new MemberUpdateForm(memberID);
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
			while ((rec != null) && (rec.next())) {
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

		try {
			if (database.s != null) {
				database.s.close();
				database.connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
