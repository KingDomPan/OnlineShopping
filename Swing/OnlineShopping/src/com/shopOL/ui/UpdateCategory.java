package com.shopOL.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.shopOL.bean.CategoryBean;
import com.shopOL.dao.CategoryDao;
import com.shopOL.jdbc.JDBCUtils;
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
public class UpdateCategory extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JButton jButton1;
	private JButton jButton2;
	private JTextField description;
	private JTextField name;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				UpdateCategory inst = new UpdateCategory(frame, null, null);
				inst.setVisible(true);
			}
		});
	}

	private CategoryBean categoryBean;

	public UpdateCategory(JFrame frame, RightPanel rightPanel, String old) {
		super(frame, true);
		categoryBean = CategoryDao.getCategoryBean(old);
		initGUI(frame,rightPanel, old);
	}

	private void initGUI(JFrame frame, final RightPanel rightPanel, final String old) {
		try {
			{
				this.setTitle("\u7c7b\u522b\u4fee\u6539");
				getContentPane().setLayout(null);
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("\u540d\u79f0\uff1a");
					jLabel1.setBounds(34, 19, 36, 15);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("\u63cf\u8ff0\uff1a");
					jLabel2.setBounds(34, 52, 36, 15);
				}
				{
					name = new JTextField();
					getContentPane().add(name);
					name.setBounds(77, 16, 157, 22);
					name.setText(categoryBean.getName());
				}
				{
					description = new JTextField();
					getContentPane().add(description);
					description.setBounds(77, 49, 157, 22);
					description.setText(categoryBean.getDescription());
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("\u4fee\u6539");
					jButton1.setBounds(70, 92, 55, 22);
					jButton1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (name.getText().trim().length()==0) {
								JOptionPane.showMessageDialog(
										UpdateCategory.this, "名称不能为空");
								name.requestFocus();
							} else {
								String sql = "select * from T_CATEGORY where name=?";
								Map<Object, Object> map = JDBCUtils.executeQuery(
										sql, name.getText());

								if (map.size() != 0 && !name.getText().equals(old)) {
									JOptionPane.showMessageDialog(
											UpdateCategory.this, "名称不能重复");
									name.requestFocus();
								} else {
									if (!CategoryDao.updateCategoryBean(
											name.getText(), description.getText(),
											old)) {
										JOptionPane.showMessageDialog(
												UpdateCategory.this, "添加失败");
									} else {
										JOptionPane.showMessageDialog(
												UpdateCategory.this, "添加成功");
										rightPanel.resetView();
									}
								}
							}
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("\u91cd\u7f6e");
					jButton2.setBounds(162, 92, 55, 22);
					jButton2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							clear();
						}
					});
				}
			}
			this.setSize(274, 165);
			setLocationRelativeTo(frame);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clear() {
		name.setText(categoryBean.getName());
		description.setText(categoryBean.getDescription());
		name.requestFocus();
	}
}
