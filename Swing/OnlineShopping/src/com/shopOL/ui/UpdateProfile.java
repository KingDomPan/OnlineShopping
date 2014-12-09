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

import com.shopOL.bean.AdminBean;
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
public class UpdateProfile extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel3;
	private JButton jButton1;
	private JButton jButton3;
	private JButton jButton2;
	private JPasswordField password;
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
				UpdateProfile inst = new UpdateProfile(frame, "admin", null);
				inst.setVisible(true);
			}
		});
	}

	public UpdateProfile(JFrame frame, String username, RightPanel rightPanel) {
		super(frame, true);
		admin=username;
		initGUI(frame,username, rightPanel);
	}

	private String title = "";
	private String admin = "";

	private void initGUI(JFrame frame, final String admin, final RightPanel rightPanel) {
		AdminBean adminBean = AdminDao.getAdminBean(admin);
		try {
			{
				getContentPane().setLayout(null);
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("\u59d3\u540d\uff1a");
					jLabel1.setBounds(253, 80, 42, 15);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("\u5bc6\u7801\uff1a");
					jLabel3.setBounds(253, 130, 42, 15);
				}
				{
					image = new JLabel();
					image.setBounds(7, 6, 220, 220);
					getContentPane().add(image);
					image.setHorizontalAlignment(JLabel.CENTER);
					if (adminBean.getPath().equals("")) {
						image.setIcon(new ImageIcon(getClass().getClassLoader()
								.getResource("photo.png")));
					} else {
						ImageIcon icon = new ImageIcon(adminBean.getPath());
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
						icon = new ImageIcon(icon.getImage().getScaledInstance(
								reImgWidth, reImgHeight, Image.SCALE_DEFAULT));
						image.setIcon(icon);
						image.setHorizontalAlignment(SwingConstants.CENTER);
					}
					chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"Image Files", "jpg", "jpeg", "gif", "png");
					chooser.setFileFilter(filter);
					// 预览
					chooser.setAccessory(new ImagePreviewer(chooser));
					// accessory 通常用于显示已选中文件的预览图像
					chooser.setFileView(new FileIconView(filter,
							new ImageIcon()));
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
					name.setBounds(288, 77, 158, 22);
					name.setText(adminBean.getName());
				}
				{
					password = new JPasswordField();
					getContentPane().add(password);
					password.setBounds(291, 127, 151, 22);
					password.setText(adminBean.getPassword());
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("\u4fee\u6539");
					jButton2.setBounds(167, 278, 67, 22);
					jButton2.setSize(65, 22);
					jButton2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (name.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(UpdateProfile.this,
										"用户名不能为空");
								name.requestFocus();
							} else if (String.valueOf(password.getPassword())
									.length() == 0) {
								JOptionPane.showMessageDialog(UpdateProfile.this,
										"密码不能为空");
								password.requestFocus();
							} else {
								boolean fls = AdminDao.updateAdmin(admin,
										String.valueOf(password.getPassword()),
										name.getText(), path.getText(), title);
								if (fls) {
									JOptionPane.showMessageDialog(
											UpdateProfile.this, "修改成功");
									rightPanel.resetView();
								} else {
									JOptionPane.showMessageDialog(
											UpdateProfile.this, "修改失败");
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
			this.setTitle("\u4e2a\u4eba\u8d44\u6599\u4fee\u6539");
			setLocationRelativeTo(frame);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clear() {
		AdminBean adminBean = AdminDao.getAdminBean(admin);
		name.setText(adminBean.getName());
		password.setText(adminBean.getPassword());
		path.setText("");
		ImageIcon icon = new ImageIcon(adminBean.getPath());
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
		icon = new ImageIcon(icon.getImage().getScaledInstance(reImgWidth,
				reImgHeight, Image.SCALE_DEFAULT));
		image.setIcon(icon);
		image.setHorizontalAlignment(SwingConstants.CENTER);
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
					JOptionPane.showMessageDialog(UpdateProfile.this,
							"只能上传jpg、jpeg、gif、png的图片");
				}

			}
		}
	}
}
