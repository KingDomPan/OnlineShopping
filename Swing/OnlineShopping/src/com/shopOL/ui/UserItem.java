package com.shopOL.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.shopOL.bean.UserBean;
import com.shopOL.dao.UserDao;
import com.shopOL.jdbc.JDBCUtils;
import com.shopOL.util.Type;

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
public class UserItem extends com.shopOL.myFrame.RightPanel {
	private JLabel jLabel1;
	private JTextField username;
	private JButton jButton1;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton2;
	private JButton jButton3;
	private JTable jTable1;
	private JScrollPane jScrollPane1;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new UserItem(frame));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public UserItem(JFrame frame) {
		super();
		initGUI(frame);
	}

	Vector<String> header = new Vector<String>();
	{
		// 表头Vector<String>
		header.add("ID");
		header.add("用户名");
		header.add("电话号码");
		header.add("住址");
		header.add("注册日期");
		header.add("用户状态");
	}

	private int userId = 0;
	private static String sql = "select * from T_USER";
	private String sql2 = " order by enable";

	private void initGUI(final JFrame frame) {
		try {
			this.setPreferredSize(new java.awt.Dimension(615, 370));
			this.setLayout(null);
			{
				jLabel1 = new JLabel();
				this.add(jLabel1);
				jLabel1.setText("\u8bf7\u8f93\u5165\u67e5\u8be2\u6761\u4ef6\uff1a");
				jLabel1.setBounds(12, 11, 97, 15);
			}
			{
				username = new JTextField();
				this.add(username);
				username.setBounds(106, 8, 157, 22);
				username.addActionListener(new ButtonAct());
			}
			{
				jButton1 = new JButton();
				this.add(jButton1);
				jButton1.setText("\u641c\u7d22");
				jButton1.setBounds(275, 8, 38, 22);
				jButton1.addActionListener(new ButtonAct());
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, "Center");
				jScrollPane1.setBounds(6, 42, 602, 322);
				jScrollPane1.setFont(new java.awt.Font("宋体", 0, 12));
				{
					// 表中数据Vector<Vector<String>>
					Vector<Vector<String>> data = new Vector<Vector<String>>();

					jTable1 = new JTable();
					JTableHeader tableHeader = jTable1.getTableHeader();
					// 不允许重新改变列的大小
					// tableHeader.setResizingAllowed(false);
					// 不允许重新改变列的顺序
					tableHeader.setReorderingAllowed(false);
					// 只能选择单行
					jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					DefaultTableModel model = new DefaultTableModel(data,
							header) {
						// 不允许修改每个单元格(重写DefaultTableModel中的isCellEditable方法，让它返回false)
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					jTable1.setModel(model);
					jScrollPane1.setViewportView(jTable1);
					jTable1.setFont(new java.awt.Font("宋体", 0, 12));

					setTableColumn();
					// 执行数据库查询
					setTable(sql + sql2);

					jTable1.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() == 1) {// 点击几次，这里是双击事件
								int row = jTable1.getSelectedRow();
								userId = Integer.parseInt(jTable1.getValueAt(
										row, 0).toString());
								if (UserDao.getUserBean(userId).getEnable()
										.equals("0")) {
									jButton4.setVisible(true);
									jButton3.setVisible(false);
								} else {
									jButton3.setVisible(true);
									jButton4.setVisible(false);
								}
							} else if (e.getClickCount() == 2) {
								new UpdateUser(frame, UserItem.this, userId);
							}
						}
					});
				}
			}
			{
				jButton2 = new JButton();
				this.add(jButton2);
				jButton2.setText("\u67e5\u770b");
				jButton2.setBounds(435, 8, 38, 22);
				jButton2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (userId != 0) {
							new UpdateUser(frame, UserItem.this, userId);
						} else {
							JOptionPane.showMessageDialog(UserItem.this,
									"请先选择用户");
						}
					}
				});
			}
			{
				jButton3 = new JButton();
				this.add(jButton3);
				jButton3.setText("\u51bb\u7ed3");
				jButton3.setBounds(487, 8, 38, 22);
				jButton3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (userId != 0) {
							int flag = JOptionPane.showConfirmDialog(
									UserItem.this, "确定？", "注意!",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (JOptionPane.YES_OPTION == flag) {
								if (UserDao.blocked(userId)) {
									JOptionPane.showMessageDialog(
											UserItem.this, "操作成功");
									resetView();
								} else {
									JOptionPane.showMessageDialog(
											UserItem.this, "操作失败");
								}
							} else {
								return;
							}
						} else {
							JOptionPane.showMessageDialog(UserItem.this,
									"请先选择用户");
						}
					}
				});
			}
			{
				jButton4 = new JButton();
				this.add(jButton4);
				jButton4.setText("\u89e3\u51bb");
				jButton4.setBounds(487, 8, 38, 22);
				jButton4.setVisible(false);
				jButton4.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (userId != 0) {
							int flag = JOptionPane.showConfirmDialog(
									UserItem.this, "确定？", "注意!",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (JOptionPane.YES_OPTION == flag) {
								if (UserDao.thaw(userId)) {
									JOptionPane.showMessageDialog(
											UserItem.this, "操作成功");
									resetView();
								} else {
									JOptionPane.showMessageDialog(
											UserItem.this, "操作失败");
								}
							} else {
								return;
							}
						} else {
							JOptionPane.showMessageDialog(UserItem.this,
									"请先选择用户");
						}
					}
				});
			}
			{
				jButton5 = new JButton();
				this.add(jButton5);
				jButton5.setText("\u91cd\u7f6e\u5bc6\u7801");
				jButton5.setBounds(539, 8, 62, 22);
				jButton5.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (userId != 0) {
							int flag = JOptionPane.showConfirmDialog(
									UserItem.this, "确定？", "注意!",
									JOptionPane.YES_NO_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (JOptionPane.YES_OPTION == flag) {
								if (UserDao.resetPassword(userId)) {
									JOptionPane.showMessageDialog(
											UserItem.this, "操作成功");
								} else {
									JOptionPane.showMessageDialog(
											UserItem.this, "操作失败");
								}
							} else {
								return;
							}
						} else {
							JOptionPane.showMessageDialog(UserItem.this,
									"请先选择用户");
						}
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void setTableColumn() {
		// 设置列的列宽 table.getColumnModel().getColumn(0)
		TableColumn[] tableColumn = new TableColumn[7];
		// 第一列
		tableColumn[0] = jTable1.getColumnModel().getColumn(0);
		tableColumn[0].setPreferredWidth(40);
		tableColumn[0].setMinWidth(40);
		// tableColumn[0].setMaxWidth(160);
		// 第二列
		tableColumn[1] = jTable1.getColumnModel().getColumn(1);
		tableColumn[1].setPreferredWidth(80);
		tableColumn[1].setMinWidth(80);
		// tableColumn[1].setMaxWidth(80);
		// 第三列
		tableColumn[2] = jTable1.getColumnModel().getColumn(2);
		tableColumn[2].setPreferredWidth(60);
		tableColumn[2].setMinWidth(60);
		// tableColumn[2].setMaxWidth(80);
		// 第四列
		tableColumn[3] = jTable1.getColumnModel().getColumn(3);
		tableColumn[3].setPreferredWidth(160);
		tableColumn[3].setMinWidth(160);
		// tableColumn[3].setMaxWidth(80);
		// // 第五列
		tableColumn[4] = jTable1.getColumnModel().getColumn(4);
		tableColumn[4].setPreferredWidth(125);
		tableColumn[4].setMinWidth(125);
//		tableColumn[4].setMaxWidth(125);
		// // 第六列
		tableColumn[5] = jTable1.getColumnModel().getColumn(5);
		tableColumn[5].setPreferredWidth(60);
		tableColumn[5].setMinWidth(60);
		// tableColumn[5].setMaxWidth(85);
	}

	void setTable(String sql) {

		Vector<Vector<String>> data = JDBCUtils.getTableData(sql);
		for (Vector<String> vector : data) {
			UserBean userBean = UserDao.getUserBean(Integer.parseInt(vector
					.get(0)));
			vector.removeAllElements();
			vector.add(0, userBean.getId() + "");
			vector.add(1, userBean.getUsername());
			vector.add(2, userBean.getPhone());
			vector.add(3, userBean.getAddress());
			vector.add(4, userBean.getRdate());
			vector.add(5, userBean.getType());
		}
		DefaultTableModel model = new DefaultTableModel(data, header) {
			// 不允许修改每个单元格(重写DefaultTableModel中的isCellEditable方法，让它返回false)
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jTable1.setModel(model);
		setTableColumn();
	}

	class ButtonAct implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = username.getText().trim();
			String sql = UserItem.sql;
			if (str.length() == 0) {
				sql = sql + " ";
				setTable(sql);
			} else {
				if (Type.isInteger(str)) {
					sql = sql + " where enable=" + str + " or id=" + str
							+ " or phone=" + str + " or rdate like '%" + str
							+ "%' ";
					sql = sql + sql2;
				} else {
					sql = sql + " where username like '%" + str
							+ "%' or rdate like '%" + str
							+ "%' or address like '%" + str + "%'";
					sql = sql + sql2;
				}
				// 执行数据库查询
				setTable(sql);
			}
		}
	}

	@Override
	public void resetView() {
		// 执行数据库查询
		setTable(sql + sql2);
		username.setText("");
		userId = 0;
		username.requestFocus();

		jButton3.setVisible(true);
		jButton4.setVisible(false);
	}

}
