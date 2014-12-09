package com.shopOL.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.shopOL.dao.AdminDao;
import com.shopOL.myFrame.FileIconView;
import com.shopOL.myFrame.ImagePreviewer;
import com.shopOL.myFrame.RightPanel;

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
public class AddAdmin extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jButton1;
	private JButton jButton3;
	private JButton jButton2;
	private JPasswordField password;
	private JTextField username;
	private JTextField name;
	private JTextField path;
	private JLabel image;

	private JFileChooser chooser;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				AddAdmin inst = new AddAdmin(frame, null);
				inst.setVisible(true);
			}
		});
	}

	public AddAdmin(JFrame frame,RightPanel rightPanel) {
		super(frame, true);
		initGUI(frame,rightPanel);
	}

	private String title = "";

	private void initGUI(JFrame frame, final RightPanel rightPanel) {
		try {
			{
				getContentPane().setLayout(null);
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("\u59d3\u540d\uff1a");
					jLabel1.setBounds(253, 52, 42, 15);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("\u8d26\u53f7\uff1a");
					jLabel2.setBounds(253, 91, 42, 15);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("\u5bc6\u7801\uff1a");
					jLabel3.setBounds(253, 129, 42, 15);
				}
				{
					image = new JLabel();
					getContentPane().add(image);
					image.setHorizontalAlignment(JLabel.CENTER);
					image.setIcon(new ImageIcon(getClass().getClassLoader()
							.getResource("photo.png")));

					chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"Image Files", "jpg", "jpeg", "gif", "png");
					chooser.setFileFilter(filter);
					// 预览
					chooser.setAccessory(new ImagePreviewer(chooser));
					// accessory 通常用于显示已选中文件的预览图像
					chooser.setFileView(new FileIconView(filter,
							new ImageIcon()));
					image.setBounds(7, 6, 220, 220);
				}
				{
					path = new JTextField();
					getContentPane().add(path);
					path.setBounds(12, 232, 176, 22);
					path.setEditable(false);
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("\u6d4f\u89c8");
					jButton1.setBounds(194, 232, 38, 22);
					jButton1.addActionListener(new FileOpenListener());
				}
				{
					name = new JTextField();
					getContentPane().add(name);
					name.setBounds(288, 49, 158, 22);
				}
				{
					username = new JTextField();
					getContentPane().add(username);
					username.setBounds(289, 88, 155, 22);
				}
				{
					password = new JPasswordField();
					getContentPane().add(password);
					password.setBounds(291, 126, 151, 22);
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("\u6dfb\u52a0");
					jButton2.setBounds(167, 278, 67, 22);
					jButton2.setSize(65, 22);
					jButton2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (name.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(AddAdmin.this,
										"用户名不能为空");
								name.requestFocus();
							} else if (username.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(AddAdmin.this,
										"账号不能为空");
								username.requestFocus();
							} else if (String.valueOf(password.getPassword())
									.length() == 0) {
								JOptionPane.showMessageDialog(AddAdmin.this,
										"密码不能为空");
								password.requestFocus();
							} else {
								boolean fls=AdminDao.addAdmin(username.getText(),
										String.valueOf(password.getPassword()),
										name.getText(), path.getText(), title);
								if (fls) {
									JOptionPane.showMessageDialog(AddAdmin.this,
											"添加成功");
									rightPanel.resetView();
									clear();
								}else {
									JOptionPane.showMessageDialog(AddAdmin.this,
											"添加失败");
								}
							}
						}
					});
				}
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3);
					jButton3.setText("\u91cd\u7f6e");
					jButton3.setBounds(313, 278, 69, 22);
					jButton3.setSize(65, 22);
					jButton3.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							clear();
						}
					});
				}
			}
			this.setSize(518, 357);
			this.setTitle("\u6dfb\u52a0\u7ba1\u7406\u5458");
			setLocationRelativeTo(frame);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clear(){
		name.setText("");
		password.setText("");
		username.setText("");
		path.setText("");
		image.setIcon(new ImageIcon(getClass().getClassLoader()
				.getResource("photo.png")));
		name.requestFocus();
	}
	
	private class FileOpenListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.setCurrentDirectory(new File("."));
			int result = chooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				String name = chooser.getSelectedFile().getPath();
				ImageIcon icon = new ImageIcon(name);
				title = chooser.getSelectedFile().getName();
				int index = title.lastIndexOf('.');
				if (index != -1) {
					title = title.substring(index).trim();
				}
				if (title.equals(".jpg") || title.equals(".jpeg")
						|| title.equals(".gif") || title.equals(".png")) {
					// 等比缩放条件
					int imgWidth = icon.getIconWidth();
					int imgHeight = icon.getIconHeight();
					int conWidth = image.getWidth();
					int conHeight = image.getHeight();
					int reImgWidth;
					int reImgHeight;
					if (imgWidth / imgHeight >= conWidth / conHeight) {
						if (imgWidth > conWidth) {
							reImgWidth = conWidth;
							reImgHeight = imgHeight * reImgWidth / imgWidth;
						} else {
							reImgWidth = imgWidth;
							reImgHeight = imgHeight;
						}
					} else {
						if (imgWidth > conWidth) {
							reImgHeight = conHeight;
							reImgWidth = imgWidth * reImgHeight / imgHeight;
						} else {
							reImgWidth = imgWidth;
							reImgHeight = imgHeight;
						}
					}
					// 这个是强制缩放到与组件(Label)大小相同
					// icon=new
					// ImageIcon(icon.getImage().getScaledInstance(image.getWidth(),
					// image.getHeight()-25, Image.SCALE_DEFAULT));
					// 这个是按等比缩放
					icon = new ImageIcon(icon.getImage().getScaledInstance(
							reImgWidth, reImgHeight, Image.SCALE_DEFAULT));
					path.setText(name);
					image.setIcon(icon);
					image.setHorizontalAlignment(SwingConstants.CENTER);
				} else {
					JOptionPane.showMessageDialog(AddAdmin.this,
							"只能上传jpg、jpeg、gif、png的图片");
				}

			}
		}
	}
}
