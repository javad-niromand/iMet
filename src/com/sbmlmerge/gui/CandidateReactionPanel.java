package com.sbmlmerge.gui;

import com.sbmlmerge.core.MergeSBML;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.sbml.jsbml.Reaction;

public class CandidateReactionPanel extends SBMLPanel {

    public CandidateReactionPanel(
            Reaction selectedRe,
            Reaction[] reList,
            int[] scList,
            SBMLPanel parentSBMLJPanel,
            SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
        reactionArray = reList;
        scoreArray = scList;
        if (reList != null) {
            selectedArray = new Boolean[reList.length];
            for (int i = 0; i < selectedArray.length; i++) {
                selectedArray[i] = i == 0;
            }
        }
        selectedReaction = selectedRe;
        initMyComponents();
    }

    private void initMyComponents() {
        data = new Object[reactionArray.length][3];
        for (int i = 0; i < reactionArray.length; i++) {
            data[i][0] = reactionArray[i];
            data[i][1] = scoreArray[i];
            data[i][2] = selectedArray[i];
        }
        colomns = new String[]{"Reaction", "Similarity Score", "Select"};
        int[] editableColomns = {1};
        int[] checkBoxColomns = {2};
        dataModel = new DataModel(data, colomns, editableColomns, checkBoxColomns);
        this.setLayout(new BorderLayout());
        table = new JTable(dataModel);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableMouseClicked(e);
            }
        });
        table.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                tableValueChanged(e);
            }
        });
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setPreferredSize(new Dimension(300, 100));
        this.add(jScrollPane);
        this.revalidate();
    }

    private void tableValueChanged(TableModelEvent evt) {
        int col = evt.getColumn();
        int row = evt.getFirstRow();
        TableModel tableModel = (TableModel) evt.getSource();
        if (tableModel.getColumnClass(col) == Boolean.class) {
            Boolean selected = (Boolean) tableModel.getValueAt(row, col);
            if (selected) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (i != row) {
                        if ((Boolean) tableModel.getValueAt(i, col)) {
                            tableModel.setValueAt(Boolean.FALSE, i, col);
                        }
                    }
                }
                MergeSBML.setSimilarity(selectedReaction, reactionArray[row], 1000000);
            }
        }
    }

    private void tableMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            int column = target.getSelectedColumn();
            if (column == 0) {
                final Reaction re = (Reaction) table.getValueAt(row, column);
                if (parentSBMLFrame != null) {
                    parentSBMLFrame.setEnabled(false);
                    SBMLFrame spFrame = new SBMLFrame(parentSBMLFrame);
                    spFrame.setLayout(new BorderLayout());
                    spFrame.add(new ReactionPanel(re, this, spFrame));
                    spFrame.setPreferredSize(new Dimension(550, 450));
                    spFrame.setMinimumSize(new Dimension(550, 450));
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    spFrame.setLocation(dim.width / 2 - spFrame.getSize().width / 2,
                            dim.height / 2 - spFrame.getSize().height / 2
                    );
                    spFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            ((SBMLFrame) e.getSource()).getParentSBMLFrame().setEnabled(true);
                        }
                    });
                    spFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    spFrame.setVisible(true);
                }
            }
        }
    }

    public void addCandidate(Reaction re) {
        if (table.getModel() != null) {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if ((Boolean) tableModel.getValueAt(i, 2)) {
                    tableModel.setValueAt(Boolean.FALSE, i, 2);
                }
            }
            Object[] newRow = new Object[3];
            newRow[0] = re;
            newRow[1] = 1000000;
            newRow[2] = true;
            tableModel.addRow(newRow);
            MergeSBML.setSimilarity(selectedReaction, re, 1000000);
        }
        table.revalidate();
        table.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private Reaction[] reactionArray;
    private int[] scoreArray;
    private Boolean[] selectedArray;
    private Object[][] data;
    private String[] colomns;
    private DataModel dataModel;
    private JTable table;
    private Reaction selectedReaction;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
