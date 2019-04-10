package com.sbmlmerge.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableCellRenderer;

public class CustomTable extends JTable {

    @Override
    public Component prepareRenderer(
            TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        JComponent jc = (JComponent) c;
        int similarityColIndex = -1;
        int dataColIndex = -1;
        int scoreColindex = -1;
        for (int i = 0; i < getColumnCount(); i++) {
            if (getColumnName(i).equals("SimilarNum")) {
                similarityColIndex = i;
            }
            if (getColumnName(i).equals("Object")) {
                dataColIndex = i;
            }
            if (getColumnName(i).equals("Score")) {
                scoreColindex = i;
            }
        }

        Color borderColor = new Color(195, 195, 195);
        Color backColor = new Color(249, 249, 249);
        Color fgColor = new Color(0, 0, 0);
        Color selectedBackColor = new Color(50, 150, 255);
        Color selectedFgColor = new Color(255, 255, 255);
        Color infoBackColor = new Color(255, 255, 225);
//        Color infoFgColor = new Color(50, 150, 255);
        Color infoFgColor = new Color(0, 0, 0);

        if (column == this.getColumnCount() - 1) {
            jc.setBorder(new MatteBorder(1, 0, 1, 1, borderColor));
        } else if (column == 0) {
            jc.setBorder(new MatteBorder(1, 1, 1, 0, borderColor));
        } else {
            jc.setBorder(new MatteBorder(1, 0, 1, 0, borderColor));
        }
        if (jc instanceof JButton) {
            return c;
        }

        if (!isCellSelected(row, column)) {
            jc.setForeground(fgColor);
            jc.setBackground(backColor);
            if (similarityColIndex > 0) {
                if (getValueAt(row, similarityColIndex).toString().isEmpty()) {
                    jc.setForeground(infoFgColor);
                    jc.setBackground(infoBackColor);
                    return c;
                }
            }
            if (scoreColindex > 0) {
                if (!getValueAt(row, scoreColindex).toString().isEmpty()) {
                    jc.setForeground(infoFgColor);
                    jc.setBackground(infoBackColor);
                    return c;
                }
            }
        } else {
            jc.setForeground(selectedFgColor);
            jc.setBackground(selectedBackColor);
        }
        return c;
    }
}
