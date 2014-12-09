package com.shopOL.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.shopOL.jdbc.JDBCUtils;
import com.shopOL.jdbc.JDBConnection;
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
public class AddCategory extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jButton1;
	private JButton jButton2;
	private JComboBox<String> up;
	private JRadioButton choose2;
	private JRadioButton choose1;
	private JTextField description;
	private JTextField name;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				AddCategory inst = new AddCategory(frame, null);
				inst.setVisible(true);
			}
		});
	}

	public AddCategory(JFrame frame, RightPanel rightPanel) {
		super(frame, true);
		initGUI(frame,rightPanel);
	}

	private ButtonGroup bgroup1 = new ButtonGroup();// radio组

	private void initGUI(JFrame frame, final RightPanel rightPanel) {
		try {
			{
				this.setTitle("\u7c7b\u522b\u6dfb\u52a0");
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
				}
				{
					description = new JTextField();
					getContentPane().add(description);
					description.setBounds(77, 49, 157, 22);
				}
				{
					choose1 = new JRadioButton("", true);
					getContentPane().add(choose1);
					choose1.setText("\u6839\u7c7b\u522b");
					choose1.setBounds(84, 91, 65, 19);
					bgroup1.add(choose1);
					choose1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							up.setEnabled(false);
						}
					});
				}
				{
					choose2 = new JRadioButton();
					getContentPane().add(choose2);
					choose2.setText("\u5b50\u7c7b\u522b");
					choose2.setBounds(154, 91, 65, 19);
					bgroup1.add(choose2);
					choose2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							up.setEnabled(true);
						}
					});
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("\u4e0a\u5c42\u7c7b\u522b\uff1a");
					jLabel3.setBounds(35, 136, 60, 15);
				}
				{
					up = new JComboBox<String>();
					getContentPane().add(up);
					up.setBounds(107, 129, 135, 22);
					up.setEnabled(false);
					update();
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("\u6dfb\u52a0");
					jButton1.setBounds(47, 168, 55, 22);
					jButton1.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (name.getText().trim().length()==0) {
								JOptionPane.showMessageDialog(
										AddCategory.this, "名称不能为空");
								name.requestFocus();
							} else {
								int index=0;
								String sql="select * from T_CATEGORY where name=?";
								Map<Object, Object> map=JDBCUtils.executeQuery(sql, name.getText());

								if (map.size()!=0) {
									JOptionPane.showMessageDialog(
											AddCategory.this, "名称不能重复");
									name.requestFocus();
								} else {
									if (choose1.isSelected()) {
										sql="insert into T_CATEGORY(name,description,grade) values(?,?,0)";
										index=JDBCUtils.update(sql, name.getText(),description.getText());
									}else {
										sql="select * from T_CATEGORY where name=?";
										map=JDBCUtils.executeQuery(sql, up.getSelectedItem().toString());
										sql="insert into T_CATEGORY(name,description,grade,csuperId) values(?,?,1,?)";
										index=JDBCUtils.update(sql, name.getText(),description.getText(),map.get("id"));
									}
									if (index==0) {
										JOptionPane.showMessageDialog(
												AddCategory.this, "添加失败");
									} else {
										JOptionPane.showMessageDialog(
												AddCategory.this, "添加成功");
										rightPanel.resetView();
										update();
										clear();
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
					jButton2.setBounds(146, 168, 55, 22);
					jButton2.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							clear();
						}
					});
				}
			}
			this.setSize(274, 236);
			setLocationRelativeTo(frame);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void clear(){
		name.setText("");
		description.setText("");
		name.requestFocus();
	}
	public void update(){
		up.removeAllItems();
		String sql = "select name from T_CATEGORY where grade=0 order by grade";
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = JDBConnection.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				up.addItem(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBConnection.closeConnection(con, ps, rs);
		}

		up.repaint();
		up.updateUI();
	}
}
