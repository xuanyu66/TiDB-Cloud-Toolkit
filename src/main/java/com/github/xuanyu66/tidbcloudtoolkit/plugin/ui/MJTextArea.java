package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
 
//实现JTextfield 的复制、剪切、粘贴功能。
 
public class MJTextArea extends JTextArea implements MouseListener {
 
	private JPopupMenu pop = null; // 弹出菜单
	private JMenuItem copy = null; // 三个功能菜单

	public MJTextArea(String text){
		super(text);
		this.setLineWrap(true);
		this.setEditable(false);
		this.setLineWrap(true);
		this.setCaretPosition(0);
		init();
	}

	private void init() {
		this.addMouseListener(this);
		pop = new JPopupMenu();
		pop.add(copy = new JMenuItem("复制"));

		copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		this.add(pop);
	}
 
	public void action(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(copy.getText())) { // 复制
			this.copy();
		}
	}
 
	public JPopupMenu getPop() {
		return pop;
	}
 
	public void setPop(JPopupMenu pop) {
		this.pop = pop;
	}

	/**
	 * 文本组件中是否具备复制的条件
	 * 
	 * @return true为具备
	 */
	public boolean copyable() {
		boolean b = false;
		int start = this.getSelectionStart();
		int end = this.getSelectionEnd();
    if (start != end) {
      b = true;
    }
		return b;
	}
 
	public void mouseClicked(MouseEvent e) {
	}
 
	public void mouseEntered(MouseEvent e) {
	}
 
	public void mouseExited(MouseEvent e) {
	}
 
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			copy.setEnabled(copyable());
			pop.show(this, e.getX(), e.getY());
		}
	}
 
	public void mouseReleased(MouseEvent e) {
	}
 
}