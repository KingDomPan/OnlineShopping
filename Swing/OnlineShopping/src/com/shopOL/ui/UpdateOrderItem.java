package com.shopOL.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.shopOL.bean.SalesOrderBean;
import com.shopOL.dao.SalesOrderDao;
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
public class UpdateOrderItem extends javax.swing.JDialog {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel oid;
	private JLabel address;
	private JLabel totalPrice;
	private JButton jButton3;
	private JLabel phone;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				UpdateOrderItem inst = new UpdateOrderItem(frame, null, "", 0);
				inst.setVisible(true);
			}
		});
	}

	public UpdateOrderItem(JFrame frame, RightPanel rightPanel, String oid, int adminId) {
		super(frame, true);
		initGUI(frame, rightPanel, oid,adminId);
	}

	private void initGUI(JFrame frame, final RightPanel rightPanel,
			final String id, final int adminId) {
		SalesOrderBean salesOrderBean = SalesOrderDao.getSalesOrderBean(id);
		try {
			{
				this.setTitle("\u8ba2\u5355\u5904\u7406");
				getContentPane().setLayout(null);
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("\u8ba2 \u5355 \u53f7\uff1a");
					jLabel1.setBounds(33, 25, 60, 15);
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(jLabel2);
					jLabel2.setText("\u603b    \u4ef7\uff1a");
					jLabel2.setBounds(33, 55, 60, 15);
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(jLabel3);
					jLabel3.setText("\u5730    \u5740\uff1a");
					jLabel3.setBounds(33, 85, 60, 15);
				}
				{
					jLabel4 = new JLabel();
					getContentPane().add(jLabel4);
					jLabel4.setText("\u8054\u7cfb\u7535\u8bdd\uff1a");
					jLabel4.setBounds(33, 115, 60, 15);
				}
				{
					oid = new JLabel();
					getContentPane().add(oid);
					oid.setBounds(95, 25, 175, 15);
					oid.setText(salesOrderBean.getOid());
				}
				{
					totalPrice = new JLabel();
					getContentPane().add(totalPrice);
					totalPrice.setBounds(95, 55, 175, 15);
					totalPrice.setText(salesOrderBean.getTotalPrice());
				}
				{
					address = new JLabel();
					getContentPane().add(address);
					address.setBounds(95, 85, 175, 15);
					address.setText(salesOrderBean.getAddress());
				}
				{
					phone = new JLabel();
					getContentPane().add(phone);
					phone.setBounds(95, 115, 175, 15);
					phone.setText(UserDao.getUserBean(
							salesOrderBean.getUserId()).getPhone());
				}
				{
					jButton3 = new JButton();
					getContentPane().add(jButton3);
					jButton3.setText("\u53d1\u8d27");
					jButton3.setBounds(80, 157, 62, 22);
					jButton3.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int flag = JOptionPane.showConfirmDialog(
									UpdateOrderItem.this, "确定？", "注意!",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (JOptionPane.YES_OPTION == flag) {
								if (SalesOrderDao.closing(id,adminId)) {
									JOptionPane.showMessageDialog(
											UpdateOrderItem.this, "操作成功");
									rightPanel.resetView();
								} else {
									JOptionPane.showMessageDialog(
											UpdateOrderItem.this, "操作失败");
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
}
