package com.shopOL.ui;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.shopOL.bean.SalesItemBean;
import com.shopOL.dao.SalesItemDao;
import com.shopOL.jdbc.JDBCUtils;

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
public class ShowItemDetail extends javax.swing.JDialog {
	private JTable jTable1;
	private JScrollPane jScrollPane1;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				ShowItemDetail inst = new ShowItemDetail(frame, 0);
				inst.setVisible(true);
			}
		});
	}

	public ShowItemDetail(JFrame frame, int id) {
		super(frame, true);
		initGUI(frame, id);
	}

	Vector<String> header = new Vector<String>();
	{
		// ��ͷVector<String>
		header.add("������");
		header.add("��Ʒ����");
		header.add("����");
		header.add("����");
		header.add("�ܼ�");
	}

	private void initGUI(JFrame frame, int id) {
		try {
			this.setTitle("\u8ba2\u5355\u660e\u7ec6");
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, "Center");
				jScrollPane1.setBounds(6, 42, 602, 322);
				jScrollPane1.setFont(new java.awt.Font("����", 0, 12));
				{
					String sql = "select id from T_SALESITEM where orderId="
							+ id;
					// ��������Vector<Vector<String>>
					Vector<Vector<String>> data = JDBCUtils.getTableData(sql);
					int i = 1;
					for (Vector<String> vector : data) {
						SalesItemBean salesItemBean = SalesItemDao
								.getSalesItemBean(Integer.parseInt(vector
										.get(0)));
						vector.removeAllElements();
						vector.add(0, i + "");
						vector.add(1, salesItemBean.getProductName());
						vector.add(2, salesItemBean.getCount() + "");
						vector.add(3, salesItemBean.getUnitPrice());
						vector.add(4, salesItemBean.getAllPrice());
					}
					jTable1 = new JTable();
					JTableHeader tableHeader = jTable1.getTableHeader();
					// ���������¸ı��еĴ�С
					// tableHeader.setResizingAllowed(false);
					// ���������¸ı��е�˳��
					tableHeader.setReorderingAllowed(false);
					// ֻ��ѡ����
					jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					DefaultTableModel model = new DefaultTableModel(data,
							header) {
						// �������޸�ÿ����Ԫ��(��дDefaultTableModel�е�isCellEditable��������������false)
						@Override
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					jTable1.setModel(model);
					jScrollPane1.setViewportView(jTable1);
					jTable1.setFont(new java.awt.Font("����", 0, 12));

					// �����е��п� table.getColumnModel().getColumn(0)
					TableColumn[] tableColumn = new TableColumn[7];
					// ��һ��
					tableColumn[0] = jTable1.getColumnModel().getColumn(0);
					// tableColumn[0].setPreferredWidth(40);
					// tableColumn[0].setMinWidth(40);
					// tableColumn[0].setMaxWidth(115);
					// �ڶ���
					tableColumn[1] = jTable1.getColumnModel().getColumn(1);
					// tableColumn[1].setPreferredWidth(40);
					// tableColumn[1].setMinWidth(40);
					// tableColumn[1].setMaxWidth(80);
					// ������
					tableColumn[2] = jTable1.getColumnModel().getColumn(2);
					// tableColumn[2].setPreferredWidth(40);
					// tableColumn[2].setMinWidth(40);
					// tableColumn[2].setMaxWidth(80);
					// ������
					tableColumn[3] = jTable1.getColumnModel().getColumn(3);
					// tableColumn[3].setPreferredWidth(40);
					// tableColumn[3].setMinWidth(40);
					// tableColumn[3].setMaxWidth(125);
					// // ������
					tableColumn[4] = jTable1.getColumnModel().getColumn(4);
					// tableColumn[4].setPreferredWidth(40);
					// tableColumn[4].setMinWidth(40);
					// // tableColumn[4].setMaxWidth(100);
					// // ������
					// tableColumn[5] = jTable1.getColumnModel().getColumn(5);
					// tableColumn[5].setPreferredWidth(70);
					// tableColumn[5].setMinWidth(70);
					// tableColumn[5].setMaxWidth(70);
				}
			}
			this.setSize(439, 285);
			setLocationRelativeTo(frame);
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
