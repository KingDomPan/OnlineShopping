package com.shopOL.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.shopOL.bean.ProductBean;
import com.shopOL.dao.CategoryDao;
import com.shopOL.dao.ProductDao;
import com.shopOL.jdbc.JDBConnection;
import com.shopOL.myFrame.FileIconView;
import com.shopOL.myFrame.ImagePreviewer;
import com.shopOL.myFrame.RightPanel;
import com.shopOL.util.DateChooser;
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
public class AddProduct extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField date;
	private JScrollPane jScrollPane1;
	private JTextArea description;
	private JComboBox<String> category;
	private JTextField member;
	private JTextField normal;
	private JLabel jLabel6;
	private JButton jButton1;
	private JButton jButton3;
	private JButton jButton2;
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
				AddProduct inst = new AddProduct(frame, null);
				inst.setVisible(true);
			}
		});
	}

	public AddProduct(JFrame frame, RightPanel rightPanel) {
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
					jLabel1.setText("\u540d    \u79f0\uff1a");
					jLabel1.setBounds(253, 15, 62, 15);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("\u751f\u4ea7\u65e5\u671f\uff1a");
					jLabel2.setBounds(253, 55, 60, 15);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("\u666e\u901a\u4ef7\u683c\uff1a");
					jLabel3.setBounds(253, 95, 60, 15);
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4);
					jLabel4.setText("\u4ea7\u54c1\u63cf\u8ff0\uff1a");
					jLabel4.setBounds(253, 215, 60, 15);
				}
				{
					jLabel5 = new JLabel();
					getContentPane().add(jLabel5);
					jLabel5.setText("\u4ea7\u54c1\u7c7b\u522b\uff1a");
					jLabel5.setBounds(253, 175, 60, 15);
				}
				{
					jLabel6 = new JLabel();
					getContentPane().add(jLabel6);
					jLabel6.setText("\u4f1a\u5458\u4ef7\u683c\uff1a");
					jLabel6.setBounds(253, 135, 60, 15);
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
					name.setBounds(333, 12, 160, 22);
				}
				{
					DateChooser dateChooser = DateChooser
							.getInstance("yyyy-MM-dd");
					date = new JTextField();
					dateChooser.register(date);
					getContentPane().add(date);
					SimpleDateFormat formatter = new SimpleDateFormat(
							"yyyy-MM-dd");
					String datetime = formatter.format(new Date());
					date.setText(datetime);
					date.setBounds(333, 52, 160, 22);
					date.setFocusable(false);
				}
				{
					normal = new JTextField();
					getContentPane().add(normal);
					normal.setText("");
					normal.setBounds(333, 92, 160, 22);
					normal.setDocument(new TypeLenghtLimitedDmt(100, 4));
					normal.getDocument().addDocumentListener(
							new DocumentListener() {

								@Override
								public void removeUpdate(DocumentEvent e) {
									checkCardId(e);
								}

								@Override
								public void insertUpdate(DocumentEvent e) {
									checkCardId(e);
								}

								@Override
								public void changedUpdate(DocumentEvent e) {
									checkCardId(e);
								}

								private void checkCardId(DocumentEvent e) {
									member.setText(normal.getText());
								}
							});
				}
				{
					member = new JTextField();
					getContentPane().add(member);
					member.setText("");
					member.setBounds(333, 132, 160, 22);
					member.setDocument(new TypeLenghtLimitedDmt(100, 4));
					member.addFocusListener(new FocusListener() {
						
						@Override
						public void focusLost(FocusEvent arg0) {
							double normalPrice=Double.parseDouble(normal.getText().trim());
							double memberPrice=Double.parseDouble(member.getText().trim());
							if (memberPrice>normalPrice) {
								JOptionPane.showMessageDialog(
										AddProduct.this, "会员价格不能大于普通价格");
							}
							member.requestFocus();
						}
						
						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO 自动生成的方法存根
							
						}
					});
				}
				{
					jScrollPane1 = new JScrollPane();
					getContentPane().add(jScrollPane1);
					jScrollPane1.setBounds(333, 213, 217, 113);
					{
						description = new JTextArea();
						jScrollPane1.setViewportView(description);
						description.setLineWrap(true);
					}
				}
				{
					category = new JComboBox<String>();
					getContentPane().add(category);
					category.setBounds(333, 172, 160, 22);
					update();
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("\u6dfb\u52a0");
					jButton2.setBounds(156, 351, 65, 22);
					jButton2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (name.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(AddProduct.this,
										"用户名不能为空");
								name.requestFocus();
							} else if (normal.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(AddProduct.this,
										"普通价格不能为空");
								normal.requestFocus();
							} else if (member.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(AddProduct.this,
										"会员价格不能为空");
								member.requestFocus();
							} else {
								ProductBean productBean = new ProductBean();
								productBean.setName(name.getText());
								productBean.setNormalPrice(normal.getText());
								productBean.setMemberPrice(member.getText());
								productBean.setPath(path.getText());
								productBean.setPdate(date.getText());
								int id = CategoryDao.getCategoryBean(
										category.getSelectedItem().toString())
										.getId();
								productBean.setCategoryId(id);
								productBean.setDescription(description.getText());
								boolean fls = ProductDao.addProduct(
										productBean, title);
								if (fls) {
									JOptionPane.showMessageDialog(
											AddProduct.this, "添加成功");
									rightPanel.resetView();
									clear();
								} else {
									JOptionPane.showMessageDialog(
											AddProduct.this, "添加失败");
								}
							}
						}
					});
				}
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3);
					jButton3.setText("\u91cd\u7f6e");
					jButton3.setBounds(333, 351, 65, 22);
					jButton3.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							clear();
						}
					});
				}
			}
			this.setSize(583, 418);
			this.setTitle("\u6dfb\u52a0\u4ea7\u54c1");
			setLocationRelativeTo(frame);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clear() {
		name.setText("");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		date.setText(formatter.format(new Date()));
		normal.setText("");
		member.setText("");
		description.setText("");
		path.setText("");
		image.setIcon(new ImageIcon(getClass().getClassLoader().getResource(
				"photo.png")));
		name.requestFocus();
	}

	public void update() {
		category.removeAllItems();
		String sql = "select name from T_CATEGORY where grade=1 order by grade";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBConnection.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				category.addItem(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBConnection.closeConnection(con, ps, rs);
		}

		category.repaint();
		category.updateUI();
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
					JOptionPane.showMessageDialog(AddProduct.this,
							"只能上传jpg、jpeg、gif、png的图片");
				}

			}
		}
	}
}
