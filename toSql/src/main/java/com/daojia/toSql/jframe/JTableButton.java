package com.daojia.toSql.jframe;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

/**
  * @ClassName: JTableButton
  * @Description: JTable中使用的JButton
  * @author-csx
  * @date 2017年5月3日 上午9:48:40
  *
  */
public class JTableButton extends AbstractCellEditor implements TableCellRenderer, TableCellEditor{

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(JTableButton.class.getName());
	
	private JButton jButton = new JButton("保存");
	private static boolean isInsert = true;
	
	public JTableButton(final JTable table) {
		super();
		jButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
		jButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

			}
		});
	}

	
	@Override
	public Object getCellEditorValue() {
		return jButton;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		return jButton;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return jButton;
	}

}
