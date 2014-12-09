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

import com.shopOL.bean.CategoryBean;
import com.shopOL.dao.CategoryDao;
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
public class CategoryItem extends com.shopOL.myFrame.RightPanel {
	private JLabel jLabel1;
	private JTextField username;
	private JButton jButton1;
	private JButton jButton3;
	private JButton jButton2;
	private JTable jTable1;
	private JScrollPane jScrollPane1;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new CategoryItem(frame));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public CategoryItem(JFrame frame) {
		super();
		initGUI(frame);
	}

	Vector<String> header = new Vector<String>();
	{
		// 表头Vector<String>
		header.add("类别名称");
		header.add("类别描述");
		header.add("上层类别");
		header.add("级别");
	}

	private static String sql = "select * from T_CATEGORY";
	private String sql2 = " order by grade,id";
	private String category;

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
								category = jTable1.getValueAt(row, 0)
										.toString();
							} else if (e.getClickCount() == 2) {
								new UpdateCategory(null, CategoryItem.this,
										category);
							}
						}
					});
				}
			}
			{
				jButton2 = new JButton();
				this.add(jButton2);
				jButton2.setText("\u6dfb\u52a0");
				jButton2.setBounds(486, 8, 38, 22);
				jButton2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						new AddCategory(frame, CategoryItem.this);
					}
				});
			}
			{
				jButton3 = new JButton();
				this.add(jButton3);
				jButton3.setText("\u4fee\u6539");
				jButton3.setBounds(548, 8, 38, 22);
				jButton3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (!category.equals("")) {
							new UpdateCategory(frame, CategoryItem.this,
									category);
						} else {
							JOptionPane.showMessageDialog(CategoryItem.this,
									"请先选择类别");
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
		tableColumn[0].setPreferredWidth(80);
		tableColumn[0].setMinWidth(80);
		// tableColumn[0].setMaxWidth(160);
		// 第二列
		tableColumn[1] = jTable1.getColumnModel().getColumn(1);
		// tableColumn[1].setPreferredWidth(100);
		// tableColumn[1].setMinWidth(100);
		// tableColumn[1].setMaxWidth(80);
		// 第三列
		tableColumn[2] = jTable1.getColumnModel().getColumn(2);
		tableColumn[2].setPreferredWidth(80);
		tableColumn[2].setMinWidth(80);
		// tableColumn[2].setMaxWidth(80);
		// 第四列
		tableColumn[3] = jTable1.getColumnModel().getColumn(3);
		tableColumn[3].setPreferredWidth(60);
		tableColumn[3].setMinWidth(60);
		// tableColumn[3].setMaxWidth(80);
		// // 第五列
		// tableColumn[4] = jTable1.getColumnModel().getColumn(4);
		// // tableColumn[4].setPreferredWidth(85);
		// tableColumn[4].setMinWidth(70);
		// // tableColumn[4].setMaxWidth(100);
		// // 第六列
		// tableColumn[5] = jTable1.getColumnModel().getColumn(5);
		// tableColumn[5].setPreferredWidth(70);
		// tableColumn[5].setMinWidth(65);
		// tableColumn[5].setMaxWidth(85);
	}

	void setTable(String sql) {

		Vector<Vector<String>> data = JDBCUtils.getTableData(sql);
		for (Vector<String> vector : data) {
			CategoryBean categoryBean = CategoryDao.getCategoryBean(Integer
					.parseInt(vector.get(0)));
			vector.removeAllElements();
			vector.add(0, categoryBean.getName());
			vector.add(1, categoryBean.getDescription());
			vector.add(2, categoryBean.getCsuperName());
			vector.add(3, categoryBean.getGradeNane());
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
			if (str.length() == 0) {
				setTable(sql + sql2);
			} else {
				String sql = CategoryItem.sql;
				if (Type.isInteger(str)) {
					sql = sql + " where grade=" + str;
					sql = sql + sql2;
				} else {
					sql = sql + " where name like '%" + str
							+ "%' or description like '%" + str + "%'";
					CategoryBean categoryBean=CategoryDao.getCategoryBean(str);
					int id=categoryBean.getId();
					sql=sql+" or csuperId="+id;
					sql=sql+sql2;
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
		category = "";
		username.requestFocus();
	}

}
