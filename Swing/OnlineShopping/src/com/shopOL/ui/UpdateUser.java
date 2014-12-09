package com.shopOL.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.shopOL.bean.UserBean;
import com.shopOL.dao.UserDao;
import com.shopOL.myFrame.RightPanel;
import javax.swing.JButton;

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
public class UpdateUser extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JButton jButton1;
	private JButton jButton3;
	private JButton jButton2;
	private JLabel type;
	private JTextField date;
	private JTextField address;
	private JTextField phone;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				UpdateUser inst = new UpdateUser(frame, null, 1);
				inst.setVisible(true);
			}
		});
	}

	public UpdateUser(JFrame frame, RightPanel rightPanel, int id) {
		super(frame, true);
		initGUI(frame,rightPanel, id);
	}

	private void initGUI(JFrame frame, final RightPanel rightPanel, final int id) {
		UserBean userBean = UserDao.getUserBean(id);
		try {
			{
				this.setTitle("\u7528\u6237\u4fee\u6539\u2014\u2014\u7528\u6237\uff1a");
				this.setTitle(getTitle() + userBean.getUsername());
				getContentPane().setLayout(null);
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("\u7535\u8bdd\u53f7\u7801\uff1a");
					jLabel1.setBounds(33, 25, 60, 15);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("\u4f4f    \u5740\uff1a");
					jLabel2.setBounds(33, 55, 60, 15);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("\u6ce8\u518c\u65f6\u95f4\uff1a");
					jLabel3.setBounds(33, 85, 60, 15);
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4);
					jLabel4.setText("\u72b6    \u6001\uff1a");
					jLabel4.setBounds(33, 115, 60, 15);
				}
				{
					phone = new JTextField();
					getContentPane().add(phone);
					phone.setBounds(95, 22, 175, 22);
					phone.setEnabled(false);
					phone.setText(userBean.getPhone());
				}
				{
					address = new JTextField();
					getContentPane().add(address);
					address.setBounds(95, 52, 175, 22);
					address.setEnabled(false);
					address.setText(userBean.getAddress());
				}
				{
					date = new JTextField();
					getContentPane().add(date);
					date.setBounds(95, 82, 175, 22);
					date.setEnabled(false);
					date.setText(userBean.getRdate());
				}
				{
					type = new JLabel();
					getContentPane().add(type);
					type.setBounds(95, 115, 175, 15);
					type.setText(userBean.getType());
				}
				{
					jButton1 = new JButton();
					getContentPane().add(jButton1);
					jButton1.setText("\u51bb\u7ed3");
					jButton1.setBounds(70, 153, 57, 22);
					jButton1.setVisible(false);
					jButton1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							int flag = JOptionPane.showConfirmDialog(
									UpdateUser.this, "确定？", "注意!",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (JOptionPane.YES_OPTION == flag) {
								if (UserDao.blocked(id)) {
									JOptionPane.showMessageDialog(
											UpdateUser.this, "操作成功");
									reset(id);
									rightPanel.resetView();
								} else {
									JOptionPane.showMessageDialog(
											UpdateUser.this, "操作失败");
								}
							} else {
								return;
							}
						}
					});
				}
				{
					jButton2 = new JButton();
					getContentPane().add(jButton2);
					jButton2.setText("\u89e3\u51bb");
					jButton2.setBounds(70, 153, 57, 22);
					jButton2.setVisible(false);
					jButton2.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int flag = JOptionPane.showConfirmDialog(
									UpdateUser.this, "确定？", "注意!",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (JOptionPane.YES_OPTION == flag) {
								if (UserDao.thaw(id)) {
									JOptionPane.showMessageDialog(
											UpdateUser.this, "操作成功");
									reset(id);
									rightPanel.resetView();
								} else {
									JOptionPane.showMessageDialog(
											UpdateUser.this, "操作失败");
								}
							} else {
								return;
							}
						}
					});
				}
				{
					if (userBean.getEnable().equals("0")) {
						jButton2.setVisible(true);
					} else {
						jButton1.setVisible(true);
					}
				}
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3);
					jButton3.setText("\u91cd\u7f6e\u5bc6\u7801");
					jButton3.setBounds(183, 153, 62, 22);
					jButton3.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int flag = JOptionPane.showConfirmDialog(
									UpdateUser.this, "确定？", "注意!",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (JOptionPane.YES_OPTION == flag) {
								if (UserDao.resetPassword(id)) {
									JOptionPane.showMessageDialog(
											UpdateUser.this, "操作成功");
								} else {
									JOptionPane.showMessageDialog(
											UpdateUser.this, "操作失败");
								}
							} else {
								return;
							}
						}
					});
				}
			}
			this.setSize(314, 231);
			setLocationRelativeTo(frame);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void reset(int id) {
		UserBean userBean = UserDao.getUserBean(id);
		type.setText(userBean.getType());
		if (userBean.getEnable().equals("0")) {
			jButton1.setVisible(false);
			jButton2.setVisible(true);
		} else {
			jButton2.setVisible(false);
			jButton1.setVisible(true);
		}
	}
}
