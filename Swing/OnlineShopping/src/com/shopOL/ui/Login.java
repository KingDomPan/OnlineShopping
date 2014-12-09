/*
 * 登陆
 */
package com.shopOL.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import com.shopOL.bean.AdminBean;
import com.shopOL.dao.AdminDao;
import com.shopOL.jdbc.JDBConnection;
import com.shopOL.util.FileUtil;
import com.shopOL.util.Img;
import com.shopOL.util.TypeLenghtLimitedDmt;

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
public class Login extends javax.swing.JFrame {
	private JPanel jPanel1;
	private JLabel securityCord;
	private JLabel pwd;
	private JButton jButton1;
	private JPanel jPanel2;
	private JButton jButton3;
	private JLabel imgcode;
	private JTextField securityCord_text;
	private JPasswordField pwd_text;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JTextField dbName;
	private JButton jButton5;
	private JButton jButton4;
	private JTextField port;
	private JPasswordField password;
	private JTextField sa;
	private JLabel jLabel5;
	private JLabel jLabel4;
	private JTextField hostName;
	private JLabel jLabel1;
	private JTextField name_text;
	private JLabel name;

	private int number = 0;// 允许登陆次数

	// 验证码
	private String code = "";
	// 数据库配置
	private final String DB_FOLDER = "dataBase.ini";
	@SuppressWarnings("rawtypes")
	Map map = null;

	public void creatFile(String filename) {
		File file = new File(filename);
		if (!file.exists() || !file.isFile()) {
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("hostName", "localhost");
			map2.put("port", "1433");
			map2.put("dbName", "OnlineShopping");
			map2.put("userName", "sa");
			map2.put("password", "123456");
			FileUtil.saveProperties(filename, map2);
			map = FileUtil.getProperties(filename);
		} else {
			map = FileUtil.getProperties(filename);
		}
		if (map.size() < 5) {
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("hostName", "localhost");
			map2.put("port", "1433");
			map2.put("dbName", "OnlineShopping");
			map2.put("userName", "sa");
			map2.put("password", "123456");
			FileUtil.saveProperties(filename, map2);
			map = FileUtil.getProperties(filename);
		}
	}

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
				Login inst = new Login();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public Login() {
		super();
		initGUI();
	}

	private void initGUI() {
		creatFile(DB_FOLDER);
		try {
			setResizable(false); // 禁止改变大小
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			getContentPane().setBackground(new java.awt.Color(255, 255, 255));
			getContentPane().setLayout(null);
			this.setTitle("\u7f51\u7edc\u5546\u57ce\u540e\u53f0\u7ba1\u7406");
			this.setSize(328, 223);
			Image image = ImageIO
					.read(this.getClass().getResource("/logo.gif"));
			this.setIconImage(image); // 设置图标
			setLocationRelativeTo(null);
			setVisible(true);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBackground(new java.awt.Color(255, 255, 255));
				jPanel1.setForeground(new java.awt.Color(0, 0, 0));
				jPanel1.setBounds(6, 3, 301, 186);
				jPanel1.setBorder(BorderFactory.createTitledBorder(null,
						"\u7528\u6237\u767b\u9646", TitledBorder.LEFT,
						TitledBorder.DEFAULT_POSITION, new java.awt.Font("黑体",
								0, 12), new java.awt.Color(0, 128, 255)));
				jPanel1.setLayout(null);
				{
					name = new JLabel();
					jPanel1.add(name);
					name.setText("\u7528 \u6237 \u540d");
					name.setBounds(50, 33, 58, 15);
					name.setFont(new java.awt.Font("宋体", 0, 12));
				}
				{
					pwd = new JLabel();
					jPanel1.add(pwd);
					pwd.setText("\u5bc6    \u7801");
					pwd.setBounds(50, 73, 57, 15);
					pwd.setFont(new java.awt.Font("宋体", 0, 12));
				}
				{
					securityCord = new JLabel();
					jPanel1.add(securityCord);
					securityCord.setText("\u9a8c \u8bc1 \u7801");
					securityCord.setBounds(50, 113, 55, 15);
					securityCord.setFont(new java.awt.Font("宋体", 0, 12));
				}
				{
					name_text = new JTextField();
					jPanel1.add(name_text);
					name_text.setBounds(105, 28, 145, 25);
					name_text.setDocument(new TypeLenghtLimitedDmt(20, 1));// 只能输入20位字母或数字
					name_text.setFont(new java.awt.Font("宋体", 0, 12));
					name_text.addActionListener(new Button3Act());
				}
				{
					pwd_text = new JPasswordField();
					jPanel1.add(pwd_text);
					pwd_text.setBounds(105, 68, 145, 25);
					pwd_text.setDocument(new TypeLenghtLimitedDmt(18, 1));// 只能输入18位字母或数字
					pwd_text.setFont(new java.awt.Font("宋体", 0, 12));
					pwd_text.addActionListener(new Button3Act());
				}
				{
					securityCord_text = new JTextField();
					jPanel1.add(securityCord_text);
					securityCord_text.setBounds(105, 108, 49, 25);
					securityCord_text
							.setDocument(new TypeLenghtLimitedDmt(4, 0));// 只能输入4位字母或数字
					securityCord_text.setFont(new java.awt.Font("宋体", 0, 12));
					securityCord_text.addActionListener(new Button3Act());
				}
				{
					imgcode = new JLabel();
					jPanel1.add(imgcode);
					imgcode.setBounds(166, 110, 70, 22);
					code = Img.generateTextCode(0, 4, null);
					imgcode.setIcon(new ImageIcon(Img.generateImageCode(code,
							70, 22, 0, true, Color.lightGray, null, null)));
					imgcode.addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent arg0) {
							// TODO 自动生成的方法存根

						}

						@Override
						public void mousePressed(MouseEvent arg0) {
							securityCord_text.setText("");
							securityCord_text.requestFocus();
							code = Img.generateTextCode(0, 4, null);
							imgcode.setIcon(new ImageIcon(Img
									.generateImageCode(code, 70, 22, 0, true,
											Color.lightGray, null, null)));
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
							// TODO 自动生成的方法存根

						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
							// TODO 自动生成的方法存根

						}

						@Override
						public void mouseClicked(MouseEvent arg0) {
							// TODO 自动生成的方法存根

						}
					});
				}
				{
					jButton1 = new JButton();
					jPanel1.add(jButton1);
					jButton1.setText("\u8bbe\u7f6e\u2193");
					jButton1.setBounds(56, 146, 70, 22);
					jButton1.setBackground(new java.awt.Color(255, 128, 0));
					jButton1.setForeground(new java.awt.Color(192, 192, 192));
					jButton1.setFont(new java.awt.Font("宋体", 0, 12));
					jButton1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if (getHeight() == 450) {
								setSize(320, 223);
								jButton1.setText("设置↓");
								hostName.setVisible(false);
								port.setVisible(false);
								dbName.setVisible(false);
								jButton4.setVisible(false);
								jButton5.setVisible(false);
							} else {
								setSize(320, 450);
								jButton1.setText("隐藏↑");
								hostName.setVisible(true);
								port.setVisible(true);
								dbName.setVisible(true);
								jButton4.setVisible(true);
								jButton5.setVisible(true);
							}

						}
					});
				}
				{
					jButton3 = new JButton();
					jPanel1.add(jButton3);
					jButton3.setText("\u63d0\u4ea4");
					jButton3.setBounds(178, 146, 68, 22);
					jButton3.setBackground(new java.awt.Color(255, 128, 0));
					jButton3.setForeground(new java.awt.Color(192, 192, 192));
					jButton3.setFont(new java.awt.Font("宋体", 0, 12));
					jButton3.addActionListener(new Button3Act());
				}
			}
			{
				jPanel2 = new JPanel();
				getContentPane().add(jPanel2);
				jPanel2.setBackground(new java.awt.Color(255, 255, 255));
				jPanel2.setForeground(new java.awt.Color(0, 0, 0));
				jPanel2.setBorder(BorderFactory.createTitledBorder(null,
						"\u6570\u636e\u5e93\u8fde\u63a5\u914d\u7f6e",
						TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
						new java.awt.Font("黑体", 0, 12), new java.awt.Color(0,
								128, 255)));
				jPanel2.setLayout(null);
				jPanel2.setBounds(6, 195, 301, 219);
				{
					jLabel1 = new JLabel();
					jPanel2.add(jLabel1);
					jLabel1.setText("\u4e3b \u673a \u540d");
					jLabel1.setBounds(50, 30, 55, 15);
					jLabel1.setFont(new java.awt.Font("宋体", 0, 12));
				}
				{
					jLabel2 = new JLabel();
					jPanel2.add(jLabel2);
					jLabel2.setText("\u7aef    \u53e3");
					jLabel2.setBounds(50, 60, 58, 15);
					jLabel2.setFont(new java.awt.Font("宋体", 0, 12));
				}
				{
					jLabel3 = new JLabel();
					jPanel2.add(jLabel3);
					jLabel3.setText("\u6570\u636e\u5e93\u540d");
					jLabel3.setBounds(50, 90, 55, 15);
					jLabel3.setFont(new java.awt.Font("宋体", 0, 12));
				}
				{
					jLabel4 = new JLabel();
					jPanel2.add(jLabel4);
					jLabel4.setText("\u8d26    \u53f7");
					jLabel4.setBounds(50, 120, 55, 15);
				}
				{
					jLabel5 = new JLabel();
					jPanel2.add(jLabel5);
					jLabel5.setText("\u5bc6    \u7801");
					jLabel5.setBounds(50, 150, 55, 15);
				}
				{
					hostName = new JTextField();
					jPanel2.add(hostName);
					hostName.setBounds(105, 27, 128, 22);
					hostName.setFont(new java.awt.Font("宋体", 0, 12));
					hostName.setVisible(false);
					hostName.setText(map.get("hostName").toString());
					hostName.addActionListener(new Button5Act());
				}
				{
					port = new JTextField();
					jPanel2.add(port);
					port.setBounds(105, 57, 128, 22);
					port.setFont(new java.awt.Font("宋体", 0, 12));
					port.setVisible(false);
					port.setText(map.get("port").toString());
					port.addActionListener(new Button5Act());
				}
				{
					dbName = new JTextField();
					jPanel2.add(dbName);
					dbName.setBounds(105, 87, 128, 22);
					dbName.setFont(new java.awt.Font("宋体", 0, 12));
					dbName.setVisible(false);
					dbName.setText(map.get("dbName").toString());
					dbName.addActionListener(new Button5Act());
				}
				{
					sa = new JTextField();
					jPanel2.add(sa);
					sa.setBounds(105, 117, 128, 22);
					sa.setText(map.get("userName").toString());
					sa.addActionListener(new Button5Act());
				}
				{
					password = new JPasswordField();
					jPanel2.add(password);
					password.setBounds(105, 147, 128, 22);
					password.setText(map.get("password").toString());
					password.addActionListener(new Button5Act());
				}
				{
					jButton4 = new JButton();
					jPanel2.add(jButton4);
					jButton4.setText("\u6d4b\u8bd5\u8fde\u63a5");
					jButton4.setBackground(new java.awt.Color(255, 128, 0));
					jButton4.setFont(new java.awt.Font("宋体", 0, 12));
					jButton4.setForeground(new java.awt.Color(192, 192, 192));
					jButton4.setBounds(50, 187, 82, 22);
					jButton4.setVisible(false);
					jButton4.addActionListener(new Button4Act());
				}
				{
					jButton5 = new JButton();
					jPanel2.add(jButton5);
					jButton5.setText("\u4fdd\u5b58\u914d\u7f6e");
					jButton5.setBackground(new java.awt.Color(255, 128, 0));
					jButton5.setFont(new java.awt.Font("宋体", 0, 12));
					jButton5.setForeground(new java.awt.Color(192, 192, 192));
					jButton5.setBounds(160, 187, 82, 22);
					jButton5.setVisible(false);
					jButton5.addActionListener(new Button5Act());
				}
			}
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	class Button3Act implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (name_text.getText().equals("")) {
				JOptionPane.showMessageDialog(Login.this, "用户名为空");
				name_text.requestFocus();
			} else if (String.valueOf(pwd_text.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(Login.this, "密码为空");
				pwd_text.requestFocus();
			} else if (securityCord_text.getText().equals("")) {
				JOptionPane.showMessageDialog(Login.this, "验证码为空");
				securityCord_text.requestFocus();
			} else if (securityCord_text.getText().toCharArray().length < 4) {
				JOptionPane.showMessageDialog(Login.this, "验证码位数不足");
				securityCord_text.requestFocus();
			} else if (!(securityCord_text.getText().equals(code))) {
				JOptionPane.showMessageDialog(Login.this, "验证码错误");
				securityCord_text.setText("");
				code = Img.generateTextCode(0, 4, null);
				imgcode.setIcon(new ImageIcon(Img.generateImageCode(code, 70,
						22, 0, true, Color.lightGray, null, null)));
				securityCord_text.requestFocus();
			} else {
				Connection con = null;
				if ((con = JDBConnection.getConnection()) != null) {
					AdminBean adminBean = AdminDao.getAdminBean(name_text
							.getText().trim());
					if (adminBean.getGrade() == 0) {
						JOptionPane.showMessageDialog(Login.this, "不存在此用户");
						name_text.setText("");
						pwd_text.setText("");
						securityCord_text.setText("");
						code = Img.generateTextCode(0, 4, null);
						imgcode.setIcon(new ImageIcon(Img.generateImageCode(
								code, 70, 22, 0, true, Color.lightGray, null,
								null)));
						name_text.requestFocus();
					} else {
						if (!String.valueOf(pwd_text.getPassword()).equals(
								adminBean.getPassword())) {
							JOptionPane.showMessageDialog(Login.this, "密码错误");
							pwd_text.setText("");
							securityCord_text.setText("");
							code = Img.generateTextCode(0, 4, null);
							imgcode.setIcon(new ImageIcon(Img
									.generateImageCode(code, 70, 22, 0, true,
											Color.lightGray, null, null)));
							pwd_text.requestFocus();
							number++;
							if (number == 3) {
								System.exit(0);
							}
						} else {
							new Main(AdminDao.getAdminBean(name_text.getText()));
							dispose();
						}
					}
				} else {
					JOptionPane.showMessageDialog(Login.this, "数据库连接失败");
					JDBConnection.closeConnection(con, null, null);
				}
			}
		}
	}

	class Button4Act implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean a = JDBConnection.creatConnection(hostName.getText(),
					port.getText(), dbName.getText(), sa.getText(),
					String.valueOf(password.getPassword()));
			if (a) {
				JOptionPane.showMessageDialog(Login.this, "连接成功");
			} else {
				JOptionPane.showMessageDialog(Login.this, "连接失败");
			}
		}
	}

	class Button5Act implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			File file = new File(DB_FOLDER);
			if (!file.exists()) {
				file.mkdir();
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("hostName", hostName.getText());
			map.put("port", port.getText());
			map.put("dbName", dbName.getText());
			map.put("userName", sa.getText());
			map.put("password", String.valueOf(password.getPassword()));
			FileUtil.saveProperties(DB_FOLDER, map);
			JOptionPane.showMessageDialog(Login.this, "配置保存成功");
		}
	}
}
