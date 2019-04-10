package com.sbmlmerge.gui;

import javax.swing.table.DefaultTableModel;

public class DataModel extends DefaultTableModel {

    private final int[] editableColomns;
    private final int[] checkboxColomns;

    public DataModel(Object[][] data, Object[] columnNames,
            int[] editableCols, int[] checkboxCols) {
        super(data, columnNames);
        editableColomns = editableCols;
        checkboxColomns = checkboxCols;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        for (int chIndex : checkboxColomns) {
            if (columnIndex == chIndex) {
                return getValueAt(0, chIndex).getClass();
            }
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        for (int editIndex : editableColomns) {
            if (column == editIndex) {
                return true;
            }
        }

        for (int chIndex : checkboxColomns) {
            if (column == chIndex) {
                return true;
            }
        }

        return false;
    }
}
