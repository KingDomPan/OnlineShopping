package com.shopOL.myFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.shopOL.bean.AdminBean;

@SuppressWarnings("serial")
public class StatusBar extends JPanel {
	public StatusBar(AdminBean adminBean) {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(10, 23));

		JPanel rightPanel = new JPanel(new BorderLayout());
		rightPanel.add(new JLabel(new AngledLinesWindowsCornerIcon()),
				BorderLayout.SOUTH);
		rightPanel.setOpaque(false);
		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		// JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		JLabel lb = new JLabel("管理员:"+adminBean.getName());
		JLabel s = new JLabel(new SeperatorIcon());
		JLabel lb1 = new JLabel("级别:"+adminBean.getType());
		JLabel s1 = new JLabel(new SeperatorIcon());

		leftPanel.add(lb);
		leftPanel.add(s);
		leftPanel.add(lb1);
		leftPanel.add(s1);

		add(leftPanel, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
		setBackground(SystemColor.control);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int y = 0;
		g.setColor(new Color(156, 154, 140));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(196, 194, 183));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(218, 215, 201));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(233, 231, 217));
		g.drawLine(0, y, getWidth(), y);

		y = getHeight() - 3;
		g.setColor(new Color(233, 232, 218));
		g.drawLine(0, y, getWidth(), y);
		y++;
		g.setColor(new Color(233, 231, 216));
		g.drawLine(0, y, getWidth(), y);
		y = getHeight() - 1;
		g.setColor(new Color(221, 221, 220));
		g.drawLine(0, y, getWidth(), y);

	}

}

class AngledLinesWindowsCornerIcon implements Icon {
	private static final Color WHITE_LINE_COLOR = new Color(255, 255, 255);

	private static final Color GRAY_LINE_COLOR = new Color(172, 168, 153);
	private static final int WIDTH = 13;

	private static final int HEIGHT = 13;

	public int getIconHeight() {
		return WIDTH;
	}

	public int getIconWidth() {
		return HEIGHT;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {

		g.setColor(WHITE_LINE_COLOR);
		g.drawLine(0, 12, 12, 0);
		g.drawLine(5, 12, 12, 5);
		g.drawLine(10, 12, 12, 10);

		g.setColor(GRAY_LINE_COLOR);
		g.drawLine(1, 12, 12, 1);
		g.drawLine(2, 12, 12, 2);
		g.drawLine(3, 12, 12, 3);

		g.drawLine(6, 12, 12, 6);
		g.drawLine(7, 12, 12, 7);
		g.drawLine(8, 12, 12, 8);

		g.drawLine(11, 12, 12, 11);
		g.drawLine(12, 12, 12, 12);

	}
}

class SeperatorIcon implements Icon {

	private static final Color GRAY_LINE_COLOR = new Color(172, 168, 153);
	private static final int WIDTH = 16;

	private static final int HEIGHT = 16;

	public int getIconHeight() {
		return WIDTH;
	}

	public int getIconWidth() {
		return HEIGHT;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(GRAY_LINE_COLOR);
		// g.drawLine(0, 0, 0, WIDTH);
		g.draw3DRect(0, 0, 0, WIDTH, true);
	}
}