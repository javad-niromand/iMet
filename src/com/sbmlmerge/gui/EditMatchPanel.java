package com.sbmlmerge.gui;

import com.sbmlmerge.core.MergeSBML;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.Species;

public class EditMatchPanel extends SBMLPanel {

    public EditMatchPanel(SBMLFrame containerSBMLFrame, SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(containerSBMLFrame, parentSBMLJPanel, parentSBMLFrame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        cmbModel = new javax.swing.JComboBox();
        lblName = new javax.swing.JLabel();
        lblModel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        BottomPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table = new CustomTable();

        setBackground(new java.awt.Color(225, 235, 245));
        setMinimumSize(new java.awt.Dimension(600, 400));

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

        cmbModel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbModelItemStateChanged(evt);
            }
        });

        lblName.setForeground(new java.awt.Color(0, 102, 255));
        lblName.setText("Object Name");

        lblModel.setText("Model:");

        jLabel1.setText("Choose similar for: ");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFind, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFind))
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(lblModel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbModel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind)))
        );

        dataModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return getColumnName(column).equalsIgnoreCase("SelectAsMatch") ||
                getColumnName(column).equalsIgnoreCase("Compare");
            }
        };
        table.setModel(dataModel);
        table.setMaximumSize(null);
        table.setMinimumSize(null);
        table.setName("editMatchTable"); // NOI18N
        table.setPreferredSize(null);
        table.setRowHeight(25);
        table.setRowMargin(2);
        table.setSelectionForeground(new java.awt.Color(0, 0, 0));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setShowHorizontalLines(false);
        table.setShowVerticalLines(false);
        table.setTableHeader(null);
        dataModel.setDataVector(
            new Object[][] {  },new Object[] { "Index","Object", "Score" ,"Compare","SelectAsMatch" });
        table.getColumn("Index").setCellRenderer(new com.sbmlmerge.gui.TextCellRenderer());
        table.getColumn("Object").setCellRenderer(new com.sbmlmerge.gui.TextCellRenderer());
        table.getColumn("Score").setCellRenderer(new com.sbmlmerge.gui.TextCellRenderer());
        table.getColumn("Compare").setCellRenderer(new ButtonRenderer());
        table.getColumn("Compare").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.getColumn("SelectAsMatch").setCellRenderer(new ButtonRenderer());
        table.getColumn("SelectAsMatch").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.getColumn("Index").setMaxWidth(40);
        table.getColumn("Score").setMaxWidth(50);
        table.getColumn("Compare").setMaxWidth(60);
        table.getColumn("SelectAsMatch").setMaxWidth(100);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
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
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
        );
        BottomPanelLayout.setVerticalGroup(
            BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
            .addGroup(BottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
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

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        btnFind.doClick();
    }//GEN-LAST:event_txtFindActionPerformed

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

    private void cmbModelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbModelItemStateChanged
        JComboBox cb = (JComboBox) evt.getSource();
        reloadTableAfterComboBoxChanged((Model) cb.getSelectedItem());
    }//GEN-LAST:event_cmbModelItemStateChanged

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        int row = table.rowAtPoint(evt.getPoint());
        int col = table.columnAtPoint(evt.getPoint());
        int userSimilarityScore = 100000;
        Object selectedRowData = table.getModel().getValueAt(row, getColumnIndex(table, "Object"));
        if (table.getColumnName(col).equalsIgnoreCase("SelectAsMatch")) {
            if (selectedRowData instanceof Species) {
                MergeSBML.setSimilarityManually((Species) selectedRowData,
                        (Species) firstObj,
                        userSimilarityScore
                );
            }
            else if (selectedRowData instanceof Reaction) {
                MergeSBML.setSimilarity((Reaction) selectedRowData,
                        (Reaction) firstObj,
                        userSimilarityScore
                );
            }
            if (firstObj instanceof Species)
                ((EditSpeciesSimilarity) parentSBMLJPanel).reload();
            else if (firstObj instanceof Reaction)
                ((EditReactionSimilarity) parentSBMLJPanel).reload();
            containerSBMLFrame.dispatchEvent(new WindowEvent(containerSBMLFrame, WindowEvent.WINDOW_CLOSING));
        } else if (table.getColumnName(col).equalsIgnoreCase("Compare")) {
            containerSBMLFrame.setEnabled(false);
            SBMLFrame compareFrame = new SBMLFrame(containerSBMLFrame);
            compareFrame.setLayout(new BorderLayout());
            Component comp;
            compareFrame.add(comp = new ComparePanel(this, compareFrame));
            compareFrame.setPreferredSize(comp.getPreferredSize());
            compareFrame.setMinimumSize(comp.getMinimumSize());
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            compareFrame.setLocation(
                    dim.width / 2 - compareFrame.getSize().width / 2,
                    dim.height / 2 - compareFrame.getSize().height / 2
            );
            compareFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ((SBMLFrame) e.getSource()).getParentSBMLFrame().setEnabled(true);
                }
            });
            compareFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            compareFrame.setVisible(true);
            ((ComparePanel) comp).reload(firstObj, selectedRowData);
        }
    }//GEN-LAST:event_tableMouseClicked

    public void reloadTableAfterComboBoxChanged(Model model) {
        removeTableContent();
        int i = 0;
        if (firstObj == null) {
            return;
        }

        if (firstObj instanceof Species) {
            String[] similarIdScore = MergeSBML.getSimilarSpecies((Species) firstObj);
            for (Species sp : model.getListOfSpecies()) {
                String score = "";
                //"Index", "Object", "Score", "Select as match"
                if (similarIdScore != null) {
                    for (String IdScore : similarIdScore) {
                        if (MergeSBML.getId(IdScore).equals(sp.getId())) {
                            score = Integer.toString(MergeSBML.getScore(IdScore));
                        }
                    }
                }
                ((DefaultTableModel) table.getModel()).addRow(new Object[]{++i, sp, score, "Compare", "Select/Match"});
            }
        } else if (firstObj instanceof Reaction) {
            String[] similarIdScore = MergeSBML.getSimilarReaction((Reaction) firstObj);
            for (Reaction re : model.getListOfReactions()) {
                String score = "";
                //"Index", "Object", "Score", "Select as match"
                if (similarIdScore != null) {
                    for (String IdScore : similarIdScore) {
                        if (MergeSBML.getId(IdScore).equals(re.getId())) {
                            score = Integer.toString(MergeSBML.getScore(IdScore));
                        }
                    }
                }
                ((DefaultTableModel) table.getModel()).addRow(new Object[]{++i, re, score, "Compare", "Select/Match"});
            }
        }
        table.revalidate();
    }

    public void reload(Object slctObj) {
        if (slctObj == null) {
            return;
        }
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        lblName.setText(slctObj.toString());
        this.firstObj = slctObj;
        cmbModel.removeAllItems();
        for (SBMLDocument doc : parent.sBMLDocuments) {
            if (firstObj instanceof Species) {
                if (!doc.getModel().equals(((Species) firstObj).getModel())) {
                    cmbModel.addItem(doc.getModel());
                }
            } else if (firstObj instanceof Reaction) {
                if (!doc.getModel().equals(((Reaction) firstObj).getModel())) {
                    cmbModel.addItem(doc.getModel());
                }
            }
        }
        if (cmbModel.getItemCount() > 0) {
            cmbModel.setSelectedIndex(0);
        }
        revalidate();
    }

    private int getColumnIndex(JTable table, String colName) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(colName)) {
                return i;
            }
        }
        return -1;
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

    private void tableValueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (table.getSelectedRow() == -1) {
            return;
        }

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BottomPanel;
    private javax.swing.JButton btnFind;
    private javax.swing.JComboBox cmbModel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblName;
    private javax.swing.JTable table;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables
    private javax.swing.table.DefaultTableModel dataModel;
    private Object firstObj;
}
