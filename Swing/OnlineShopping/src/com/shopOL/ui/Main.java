package com.shopOL.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import com.shopOL.bean.AdminBean;
import com.shopOL.dao.AdminDao;
import com.shopOL.myFrame.RightPanel;
import com.shopOL.myFrame.StatusBar;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
@SuppressWarnings("serial")
public class Main extends javax.swing.JFrame {
	private JMenuBar jMenuBar1;
	private JPanel jPanel1;
	private JMenu jMenu5;
	private JMenuItem alreadyProcessingOrderItem;
	private JMenuItem pendingOrderItem;
	private JMenuItem updateProfile;
	private JMenuItem userItem;
	private JMenuItem categoryItem;
	private JMenuItem productItem;
	private JMenuItem adminItem;
	private JMenu jMenu6;
	private JPanel jPanel2;
	private JMenu jMenu4;
	private JMenu jMenu3;
	private JMenu jMenu2;
	private JMenu jMenu1;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					// UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessLookAndFeel");
					UIManager
							.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeSilver2007LookAndFeel");
					// UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceSaharaLookAndFeel");
					// UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceOfficeBlue2007LookAndFeel");
					// UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel");

				} catch (Exception e) {
					e.printStackTrace();
				}
				new Main(AdminDao.getAdminBean("admin"));
			}
		});
	}

	public Main(AdminBean adminBean) {
		super();
		initGUI(adminBean);
	}

	private static String[] selectNames = new String[] { "管理员列表", "产品列表",
			"类别列表", "用户列表" ,"待处理订单列表","已处理订单列表"};
	private CardLayout WorkPanelLayout;
	private RightPanel currentPanel;

	private void initGUI(final AdminBean adminBean) {
		// 创建文件夹
		String path = "pictures/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		String path1 = path + "admins/";
		file = new File(path1);
		if (!file.exists()) {
			file.mkdir();
		}
		path1 = path + "products/";
		file = new File(path1);
		if (!file.exists()) {
			file.mkdir();
		}
		try {
			setResizable(false);// 禁止改变大小
			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			Image image = ImageIO
					.read(this.getClass().getResource("/logo.gif"));
			BoxLayout thisLayout = new BoxLayout(getContentPane(),
					javax.swing.BoxLayout.Y_AXIS);
			getContentPane().setLayout(thisLayout);
			this.setIconImage(image); // 设置图标
			this.setSize(621, 441);
			this.setTitle("\u7f51\u4e0a\u5546\u57ce\u540e\u53f0\u7ba1\u7406");
			{
				jPanel1 = new JPanel();
				WorkPanelLayout = new CardLayout();
				jPanel1.setLayout(WorkPanelLayout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setPreferredSize(new java.awt.Dimension(615, 370));

				if (adminBean.getGrade() == 2) {
					jPanel1.add(new AdminItem(this, adminBean), selectNames[0]);
				}else {
					jPanel1.add(new ProductItem(this), selectNames[0]);
				}
				jPanel1.add(new ProductItem(this), selectNames[1]);
				jPanel1.add(new CategoryItem(this), selectNames[2]);
				jPanel1.add(new UserItem(this), selectNames[3]);
				jPanel1.add(new PendingOrderItem(this,adminBean), selectNames[4]);
				jPanel1.add(new AlreadyProcessingOrderItem(this), selectNames[5]);
			}
			{
				jPanel2 = new StatusBar(adminBean);
				getContentPane().add(jPanel2);
			}
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					if (adminBean.getGrade() == 2) {
						jMenuBar1.add(jMenu1);
					}
					jMenu1.setText("\u7ba1\u7406\u5458\u7ba1\u7406");
					{
						adminItem = new JMenuItem();
						jMenu1.add(adminItem);
						adminItem.setText("\u7ba1\u7406\u5458\u5217\u8868");
						adminItem.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent arg0) {
								WorkPanelLayout.show(jPanel1, selectNames[0]);
								currentPanel = (RightPanel) jPanel1
										.getComponents()[0];
								currentPanel.resetView();
							}
						});
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("\u4ea7\u54c1\u7ba1\u7406");
					{
						productItem = new JMenuItem();
						jMenu2.add(productItem);
						productItem.setText("\u4ea7\u54c1\u5217\u8868");
						productItem.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								WorkPanelLayout.show(jPanel1, selectNames[1]);
								currentPanel = (RightPanel) jPanel1
										.getComponents()[1];
								currentPanel.resetView();
							}
						});
					}
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("\u7c7b\u522b\u7ba1\u7406");
					{
						categoryItem = new JMenuItem();
						jMenu3.add(categoryItem);
						categoryItem.setText("\u7c7b\u522b\u5217\u8868");
						categoryItem.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								WorkPanelLayout.show(jPanel1, selectNames[2]);
								currentPanel = (RightPanel) jPanel1
										.getComponents()[2];
								currentPanel.resetView();
							}
						});
					}
				}
				{
					jMenu4 = new JMenu();
					if (adminBean.getGrade() == 2) {
						jMenuBar1.add(jMenu4);
					}
					jMenu4.setText("\u7528\u6237\u7ba1\u7406");
					{
						userItem = new JMenuItem();
						jMenu4.add(userItem);
						userItem.setText("\u7528\u6237\u5217\u8868");
						userItem.setBounds(106, 19, 60, 19);
						userItem.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								WorkPanelLayout.show(jPanel1, selectNames[3]);
								currentPanel = (RightPanel) jPanel1
										.getComponents()[3];
								currentPanel.resetView();
							}
						});
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("\u8ba2\u5355\u7ba1\u7406");
					{
						pendingOrderItem = new JMenuItem();
						jMenu5.add(pendingOrderItem);
						pendingOrderItem.setText("\u5f85\u5904\u7406\u8ba2\u5355\u5217\u8868");
						pendingOrderItem.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								WorkPanelLayout.show(jPanel1, selectNames[4]);
								currentPanel = (RightPanel) jPanel1
										.getComponents()[4];
								currentPanel.resetView();
							}
						});
					}
					{
						alreadyProcessingOrderItem = new JMenuItem();
						jMenu5.add(alreadyProcessingOrderItem);
						alreadyProcessingOrderItem.setText("\u5df2\u5904\u7406\u8ba2\u5355\u5217\u8868");
						alreadyProcessingOrderItem.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								WorkPanelLayout.show(jPanel1, selectNames[5]);
								currentPanel = (RightPanel) jPanel1
										.getComponents()[5];
								currentPanel.resetView();
							}
						});
					}
				}
				{
					jMenu6 = new JMenu();
					jMenuBar1.add(jMenu6);
					jMenu6.setText("\u4e2a\u4eba\u4fe1\u606f\u7ba1\u7406");
					{
						updateProfile = new JMenuItem();
						jMenu6.add(updateProfile);
						updateProfile
								.setText("\u4e2a\u4eba\u8d44\u6599\u4fee\u6539");
						updateProfile.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								currentPanel = (RightPanel) jPanel1
										.getComponents()[0];
								new UpdateProfile(Main.this, adminBean
										.getUsername(), currentPanel);
							}
						});
					}
				}
				addWindowListener(new WindowListener() {

					@Override
					public void windowClosing(WindowEvent e) {
						int flag = JOptionPane.showConfirmDialog(Main.this,
								"确定退出？", "注意!", JOptionPane.YES_NO_OPTION,
								JOptionPane.INFORMATION_MESSAGE);
						if (JOptionPane.YES_OPTION == flag) {
							System.exit(0);
						} else {
							return;
						}
					}

					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub

					}

				});
				setLocationRelativeTo(null);
				setVisible(true);
			}
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

}
