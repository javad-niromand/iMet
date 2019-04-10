package com.sbmlmerge.gui;

import com.sbmlmerge.core.MergeSBML;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Species;

public class TablePanel extends SBMLPanel {

    public TablePanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        BottomPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table = new CustomTable();

        setBackground(new java.awt.Color(225, 235, 245));
        setMinimumSize(new java.awt.Dimension(250, 350));
        setPreferredSize(new java.awt.Dimension(250, 350));

        topPanel.setBackground(new java.awt.Color(225, 235, 245));

        jLabel2.setText("Find:");

        txtFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindActionPerformed(evt);
            }
        });

        btnFind.setText("Find / Next");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFind)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFind))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dataModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//getColumnName(column).equalsIgnoreCase("Edit");
            }
        };
        table.setModel(dataModel);
        table.setMaximumSize(new java.awt.Dimension(30, 0));
        table.setRowHeight(25);
        table.setRowMargin(2);
        table.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setTableHeader(null);
        dataModel.setDataVector(
            new Object[][] {  },new Object[] { "Index","Object","SimilarNum" /*,"Edit"*/ });
        table.getColumn("Index").setCellRenderer(new com.sbmlmerge.gui.TextCellRenderer());
        table.getColumn("Object").setCellRenderer(new com.sbmlmerge.gui.TextCellRenderer());
        table.getColumn("SimilarNum").setCellRenderer(new com.sbmlmerge.gui.TextCellRenderer());
        //table.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        //table.getColumn("Edit").setCellEditor(
            //                                        new ButtonEditor(new JCheckBox()));
        table.getColumn("Index").setMaxWidth(40);
        table.getColumn("SimilarNum").setMaxWidth(50);
        //table.getColumn("Edit").setMaxWidth(40);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tableValueChanged(e);
            }
        });
        jScrollPane3.setViewportView(table);

        javax.swing.GroupLayout BottomPanelLayout = new javax.swing.GroupLayout(BottomPanel);
        BottomPanel.setLayout(BottomPanelLayout);
        BottomPanelLayout.setHorizontalGroup(
            BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
            .addGroup(BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        );
        BottomPanelLayout.setVerticalGroup(
            BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
            .addGroup(BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        if (txtFind.getText().trim().isEmpty()) {
            return;
        }

        int dataColumnIndex = 1;
        int i;
        for (i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals("Object")) {
                dataColumnIndex = i;
            }
        }

        i = 0;
        int selectedIndex = table.getSelectedRow();
        if (selectedIndex >= 0 && selectedIndex < table.getRowCount()) {
            i = selectedIndex + 1;
        }

        for (; i < table.getModel().getRowCount(); i++) {
            if (table.getModel().getValueAt(i, dataColumnIndex)
                    .toString().toLowerCase().contains(
                            txtFind.getText().toLowerCase())) {
                table.clearSelection();
                table.setRowSelectionInterval(i, i);
                return;
            }
        }
        table.clearSelection();
    }//GEN-LAST:event_btnFindActionPerformed

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        btnFind.doClick();
    }//GEN-LAST:event_txtFindActionPerformed

    /**
     *
     * @param list : list of Species to insert to table
     */
    public void reloadTable(ListOf<Species> list) {
        removeTableContent();
        int i = 0;
        for (Species sp : list) {
            String similarNumber;
            if (getSimilarList(sp).length > 0) {
                similarNumber = Integer.toString(getSimilarList(sp).length) + " similar";
            } else {
                similarNumber = "";
            }
            ((DefaultTableModel) table.getModel()).addRow(new Object[]{++i, sp, similarNumber, "Edit"});
        }
        table.revalidate();
    }

    /**
     *
     * @param list : list of Species to insert to table
     */
    public void reloadReactionTable(ListOf<Reaction> list) {
        removeTableContent();
        int i = 0;
        for (Reaction re : list) {
            String similarNumber;
            if (getSimilarList(re).length > 0) {
                similarNumber = Integer.toString(getSimilarList(re).length) + " similar";
            } else {
                similarNumber = "";
            }
            ((DefaultTableModel) table.getModel()).addRow(new Object[]{++i, re, similarNumber, "Edit"});
        }
        table.revalidate();
    }

    private void removeTableContent() {
        try {
            for (int i = table.getModel().getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) table.getModel()).removeRow(i);
            }
            table.revalidate();
        } catch (NullPointerException e) {
        }
    }

    public JTable getTable() {
        return table;
    }

    private void tableValueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (table.getSelectedRow() == -1) {
            return;
        }
        
        if (parentSBMLJPanel instanceof EditSpeciesSimilarity)
            ((EditSpeciesSimilarity) parentSBMLJPanel).tableSelectedItemChanged(table);
        else if (parentSBMLJPanel instanceof EditReactionSimilarity)
            ((EditReactionSimilarity) parentSBMLJPanel).tableSelectedItemChanged(table);

        if (isCellVisible(table, table.getSelectedRow(), 0)) {
            return;
        }

        JViewport viewport = (JViewport) table.getParent();
        Rectangle rect = table.getCellRect(table.getSelectedRow(), 0, true);
        Rectangle r2 = viewport.getVisibleRect();
        table.scrollRectToVisible(new Rectangle(
                rect.x, rect.y, (int) r2.getWidth(), (int) r2.getHeight()));
    }

    private static boolean isCellVisible(JTable table, int rowIndex, int vColIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return false;
        }
        JViewport viewport = (JViewport) table.getParent();
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
        Point pt = viewport.getViewPosition();
        rect.setLocation(rect.x - pt.x, rect.y - pt.y);
        return new Rectangle(viewport.getExtentSize()).contains(rect);
    }

    String[] getSimilarList(Object obj) {
        String[] similarSpecieIdsArray = null;
        if (obj instanceof Species)
            similarSpecieIdsArray = MergeSBML.getSimilarSpecies((Species)obj);
        else if (obj instanceof Reaction)
            similarSpecieIdsArray = MergeSBML.getSimilarReaction((Reaction)obj);
        
        if (similarSpecieIdsArray == null) {
            similarSpecieIdsArray = new String[0];
        }
        return similarSpecieIdsArray;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BottomPanel;
    private javax.swing.JButton btnFind;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable table;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel dataModel;
}
