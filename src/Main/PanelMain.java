package Main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanelMain extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelMain() {
		setLayout(null);
		
		JLabel lblPanelMain = new JLabel("Welcome Admin");
		lblPanelMain.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPanelMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelMain.setBounds(167, 169, 239, 31);
		add(lblPanelMain);

	}
}