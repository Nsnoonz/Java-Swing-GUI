package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import database.database;
import java.awt.Font;

public class Main extends JFrame {

	static JPanel MainPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Main frame = new Main();
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setTitle("Bucket Hat Hat : Administrator");
		
		getContentPane().add(new PanelMain()); // Load Panel Main
		
		// Menu Bar
        JMenuBar menuBar=new JMenuBar();
        
        // Main Menu
        JMenu menuProduct = new JMenu("Product");
        JMenu menuSuppliers = new JMenu("Suppliers");
        JMenu menuMember = new JMenu("Member");
        JMenu menuStock = new JMenu("Stock");
        JMenu menuInvoice = new JMenu(" สั่งซื้อ ");
        menuInvoice.setFont(new Font("Kanit", Font.PLAIN, 14));
        
        /////////menuProduct/////////////
        JMenuItem menu2_1 = new JMenuItem("All Product");
        menu2_1.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  ProductAll panel5 = new ProductAll();
        		  panel5.setBorder(BorderFactory.createTitledBorder("All Product"));
                  getContentPane().removeAll();
                  getContentPane().add(panel5, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        JMenuItem menu2_2 = new JMenuItem("ADD Product");
        menu2_2.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  ProductAdd panel6 = new ProductAdd();
        		  panel6.setBorder(BorderFactory.createTitledBorder("ADD Product"));
                  getContentPane().removeAll();
                  getContentPane().add(panel6, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        JMenuItem menu2_3 = new JMenuItem("Edit Product");
        menu2_3.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  ProductEdit panel7 = new ProductEdit();
        		  panel7.setBorder(BorderFactory.createTitledBorder("Edit Product"));
                  getContentPane().removeAll();
                  getContentPane().add(panel7, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        JMenuItem menu2_4 = new JMenuItem("Delete Product");
        menu2_4.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  ProductDelete panel8 = new ProductDelete();
        		  panel8.setBorder(BorderFactory.createTitledBorder("Delete Product"));
                  getContentPane().removeAll();
                  getContentPane().add(panel8, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        
        menuProduct.add(menu2_1);
        menuProduct.add(menu2_2);
        menuProduct.add(menu2_3);
        menuProduct.add(menu2_4);
        /////////menuProduct/////////////
        
        /////////Suppliers/////////////
        JMenuItem menu3_1 = new JMenuItem("All Suppliers");
        menu3_1.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  SuppliersAll panel9 = new SuppliersAll();
              	panel9.setBorder(BorderFactory.createTitledBorder("All Suppliers"));
                  getContentPane().removeAll();
                  getContentPane().add(panel9, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });

        JMenuItem menu3_2 = new JMenuItem("Edit Suppliers");
        menu3_2.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  SuppliersEdit panel10 = new SuppliersEdit();
              	panel10.setBorder(BorderFactory.createTitledBorder("Edit Suppliers"));
                  getContentPane().removeAll();
                  getContentPane().add(panel10, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        
        
        JMenuItem menu3_3 = new JMenuItem("Delete Suppliers");
        menu3_3.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  SuppliersDelete panel11 = new SuppliersDelete();
              	panel11.setBorder(BorderFactory.createTitledBorder("Delete Suppliers"));
                  getContentPane().removeAll();
                  getContentPane().add(panel11, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        menuSuppliers.add(menu3_1);
        menuSuppliers.add(menu3_2);
        menuSuppliers.add(menu3_3);
        /////////Suppliers/////////////
        
        /////////menuMember/////////////
        JMenuItem menu1 = new JMenuItem("All Member");
        menu1.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
              	MemberAll panel1 = new MemberAll();
              	panel1.setBorder(BorderFactory.createTitledBorder("All Member"));
                  getContentPane().removeAll();
                  getContentPane().add(panel1, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        JMenuItem menu2 = new JMenuItem("ADD Member");
        menu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	MemberAdd panel2 = new MemberAdd();
            	panel2.setBorder(BorderFactory.createTitledBorder("ADD Member"));
                getContentPane().removeAll();
                getContentPane().add(panel2, BorderLayout.CENTER);
                getContentPane().doLayout();
            }
        });
        JMenuItem menu3 = new JMenuItem("Edit Member");
        menu3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	MemberEdit panel3 = new MemberEdit();
            	panel3.setBorder(BorderFactory.createTitledBorder("Edit MemBer"));
                getContentPane().removeAll();
                getContentPane().add(panel3, BorderLayout.CENTER);
                getContentPane().doLayout();
            }
        });
        JMenuItem menu4 = new JMenuItem("Delete Member");
        menu4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	MemberDelete panel4 = new MemberDelete();
            	panel4.setBorder(BorderFactory.createTitledBorder("Delete Member"));
                getContentPane().removeAll();
                getContentPane().add(panel4, BorderLayout.CENTER);
                getContentPane().doLayout();
            }
        });
        
    
        menuMember.add(menu1);
        menuMember.add(menu2);
        menuMember.add(menu3);
        menuMember.add(menu4);
        
        /////////menuMember/////////////
        
        /////////menuStock/////////////
        JMenuItem menu4_1 = new JMenuItem("All Stock");
        menu4_1.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  StockAll panel12 = new StockAll();
        		  panel12.setBorder(BorderFactory.createTitledBorder("All Stock"));
                  getContentPane().removeAll();
                  getContentPane().add(panel12, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });

        JMenuItem menu4_2 = new JMenuItem("Edit Stock");
        menu4_2.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  StockEdit panel13 = new StockEdit();
        		  panel13.setBorder(BorderFactory.createTitledBorder("Edit Stock"));
                  getContentPane().removeAll();
                  getContentPane().add(panel13, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        
        
        JMenuItem menu4_3 = new JMenuItem("Delete Stock");
        menu4_3.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  StockDelete panel14 = new StockDelete();
              	panel14.setBorder(BorderFactory.createTitledBorder("Delete Stock"));
                  getContentPane().removeAll();
                  getContentPane().add(panel14, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        menuStock.add(menu4_1);
        menuStock.add(menu4_2);
        menuStock.add(menu4_3);
        
        /////////menuStock/////////////
        
        
        JMenuItem menu5_1 = new JMenuItem("สั่งซื้อสินค้า");
        menu5_1.setFont(new Font("Kanit", Font.PLAIN, 14));
        menu5_1.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent event) {
        		  Buyproduct panel15 = new Buyproduct();
              	panel15.setBorder(BorderFactory.createTitledBorder("Search Product"));
              	panel15.setFont(new Font("Kanit", Font.PLAIN, 14));
              	getContentPane().removeAll();
                  getContentPane().add(panel15, BorderLayout.CENTER);
                  getContentPane().doLayout();      
            }
        });
        menuInvoice.add(menu5_1);
        
        
        
        
        menuBar.add(menuProduct);
        menuBar.add(menuSuppliers);
        menuBar.add(menuMember);
        menuBar.add(menuStock);
        menuBar.add(menuInvoice);
        
        
        

        
        setJMenuBar(menuBar);
        
      
      
	}
}