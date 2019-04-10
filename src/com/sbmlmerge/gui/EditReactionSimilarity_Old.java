package com.sbmlmerge.gui;

import com.sbmlmerge.core.MergeSBML;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;

public class EditReactionSimilarity_Old extends SBMLPanel {

    public EditReactionSimilarity_Old(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ReactionPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        firstRETable = new CustomTable();
        CandidatePanel = new javax.swing.JPanel();
        findPanel = new javax.swing.JPanel();
        btnFind = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        otherRETable = new CustomTable();

        ReactionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Reactions"));

        firstDataModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return getColumnName(column).equalsIgnoreCase("Edit");
            }
        };
        firstRETable.setModel(firstDataModel);
        firstRETable.setMaximumSize(new java.awt.Dimension(30, 0));
        firstRETable.setRowHeight(25);
        firstRETable.setRowMargin(2);
        firstRETable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        firstRETable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        firstRETable.setShowHorizontalLines(false);
        firstRETable.setShowVerticalLines(false);
        firstRETable.setTableHeader(null);
        firstDataModel.setDataVector(
            new Object[][] {  },new Object[] { "Reaction", "Edit" });
        firstRETable.getColumn("Reaction").setCellRenderer(new com.sbmlmerge.gui.TextCellRenderer());
        firstRETable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        firstRETable.getColumn("Edit").setCellEditor(
            new ButtonEditor(new JCheckBox()));
        firstRETable.getColumn("Edit").setMaxWidth(35);
        firstRETable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                firstRETableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(firstRETable);

        javax.swing.GroupLayout ReactionPanelLayout = new javax.swing.GroupLayout(ReactionPanel);
        ReactionPanel.setLayout(ReactionPanelLayout);
        ReactionPanelLayout.setHorizontalGroup(
            ReactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 198, Short.MAX_VALUE)
            .addGroup(ReactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ReactionPanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        ReactionPanelLayout.setVerticalGroup(
            ReactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
            .addGroup(ReactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ReactionPanelLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        CandidatePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Similar candidates"));
        CandidatePanel.setLayout(new java.awt.BorderLayout());

        findPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Find reaction"));

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        jLabel2.setText("Id:");

        otherDataModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return getColumnName(column).equalsIgnoreCase("Edit") ||
                getColumnName(column).equalsIgnoreCase("Select");
            }
        };
        otherRETable.setModel(otherDataModel);
        otherRETable.setMaximumSize(new java.awt.Dimension(30, 0));
        otherRETable.setRowHeight(25);
        otherRETable.setRowMargin(2);
        otherRETable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        otherRETable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        otherRETable.setShowHorizontalLines(false);
        otherRETable.setShowVerticalLines(false);
        otherDataModel.setDataVector(
            new Object[][] {  },new Object[] { "Reaction", "Edit", "Select"});
        otherRETable.getColumn("Reaction").setCellRenderer(new com.sbmlmerge.gui.TextCellRenderer());
        otherRETable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        otherRETable.getColumn("Edit").setCellEditor(
            new ButtonEditor(new JCheckBox()));
        otherRETable.getColumn("Edit").setMaxWidth(35);
        otherRETable.getColumn("Select").setCellRenderer(new ButtonRenderer());
        otherRETable.getColumn("Select").setCellEditor(
            new ButtonEditor(new JCheckBox()));
        otherRETable.getColumn("Select").setMaxWidth(35);
        otherRETable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                otherRETableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(otherRETable);

        javax.swing.GroupLayout findPanelLayout = new javax.swing.GroupLayout(findPanel);
        findPanel.setLayout(findPanelLayout);
        findPanelLayout.setHorizontalGroup(
            findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFind, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, findPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );
        findPanelLayout.setVerticalGroup(
            findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, findPanelLayout.createSequentialGroup()
                        .addGroup(findPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFind)
                        .addContainerGap(205, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReactionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CandidatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(findPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CandidatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(findPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ReactionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private Reaction findReaction(String reId) {
        Reaction returnRE;
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        for (int i = 1; i < parent.sBMLDocuments.size(); i++) {
            returnRE = parent.sBMLDocuments.get(i).getModel().getReaction(reId);
            if (returnRE != null) {
                return returnRE;
            }
        }
        return null;
    }

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        if (txtId.getText().isEmpty()) {
        } else {
            removeTableContent(otherRETable);
            for (int i = 1; i < parent.sBMLDocuments.size(); i++) {
                for (Reaction reaction : parent.sBMLDocuments.get(i).getModel().getListOfReactions()) {
                    if (reaction.getId().equalsIgnoreCase(txtId.getText())) {
                        ((DefaultTableModel) otherDataModel).addRow(new Object[]{findReaction(reaction.getId()), "Edit", "Select"});
                    }
                }
            }
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void firstRETableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_firstRETableMouseClicked
        for (Component cmp : CandidatePanel.getComponents()) {
            if (cmp.equals(candidateReactionPanel)) {
                CandidatePanel.remove(cmp);
            }
        }
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        int col = table.columnAtPoint(evt.getPoint());
        if (table.getColumnName(col).equalsIgnoreCase("Edit")) {
            if (row != -1) {
                final Reaction reaction = (Reaction) firstRETable.getModel().getValueAt(row, 0);
                if (parentSBMLFrame != null) {
                    parentSBMLFrame.setEnabled(false);
                    SBMLFrame reFrame = new SBMLFrame(parentSBMLFrame);
                    reFrame.setLayout(new BorderLayout());
                    reFrame.add(new ReactionPanel(reaction, this, reFrame));
                    reFrame.setMinimumSize(new Dimension(550, 450));
                    reFrame.setMinimumSize(new Dimension(550, 450));
                    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                    reFrame.setLocation(dim.width / 2 - reFrame.getSize().width / 2,
                            dim.height / 2 - reFrame.getSize().height / 2
                    );
                    reFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            ((SBMLFrame) e.getSource()).getParentSBMLFrame().setEnabled(true);
                        }
                    });
                    reFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    reFrame.setVisible(true);
                }
            }
        } else if (table.getColumnName(col).equalsIgnoreCase("Reaction")) {
            if (row != -1) {
                Reaction firstListReaction = (Reaction) table.getModel().getValueAt(row, col);
                String[] similarReactionIdsArray = MergeSBML.getSimilarReaction(firstListReaction);
                Reaction[] similarReaction;
                int[] scores;
                if (similarReactionIdsArray != null) {
                    similarReaction = new Reaction[similarReactionIdsArray.length];
                    scores = new int[similarReactionIdsArray.length];
                    for (int i = 0; i < similarReactionIdsArray.length; i++) {
                        String id = MergeSBML.getId(similarReactionIdsArray[i]);
                        Reaction similarRE = findReaction(id);
                        similarReaction[i] = similarRE;
                        scores[i] = MergeSBML.getScore(similarReactionIdsArray[i]);
                    }
                } else {
                    similarReaction = new Reaction[0];
                    scores = new int[0];
                }
                candidateReactionPanel = new CandidateReactionPanel(
                        firstListReaction, similarReaction,
                        scores, parentSBMLJPanel, parentSBMLFrame
                );
                CandidatePanel.add(candidateReactionPanel);
                CandidatePanel.revalidate();
                CandidatePanel.repaint();
            }
        }
    }//GEN-LAST:event_firstRETableMouseClicked

    private void otherRETableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otherRETableMouseClicked
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        int col = table.columnAtPoint(evt.getPoint());
        if (table.getColumnName(col).equalsIgnoreCase("Edit")) {
            if (row != -1) {
                final Reaction reaction = (Reaction) otherRETable.getModel().getValueAt(row, 0);
                if (parentSBMLFrame != null) {
                    parentSBMLFrame.setEnabled(false);
                    SBMLFrame spFrame = new SBMLFrame(parentSBMLFrame);
                    spFrame.setLayout(new BorderLayout());
                    spFrame.add(new ReactionPanel(reaction, this, spFrame));
                    spFrame.setMinimumSize(new Dimension(550, 450));
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
        } else if (table.getColumnName(col).equalsIgnoreCase("Select")) {
            if (row != -1) {
                Reaction firstListReaction = (Reaction) firstRETable.getModel().getValueAt(row, 0);
                if (firstListReaction != null) {
                    Reaction selectedReaction = (Reaction) table.getModel().getValueAt(row, 0);
                    if (CandidatePanel.getComponentCount() > 0) {
                        candidateReactionPanel.addCandidate(selectedReaction);
                    } else {
                        Reaction[] similarReaction = new Reaction[0];
                        int[] scores = new int[0];
                        candidateReactionPanel = new CandidateReactionPanel(
                                firstListReaction, similarReaction,
                                scores, parentSBMLJPanel, parentSBMLFrame
                        );
                    }
                }
            }
        }
    }//GEN-LAST:event_otherRETableMouseClicked

    private void reloadTable(JTable table, SBMLDocument doc) {
        removeTableContent(table);
        for (Reaction sp : doc.getModel().getListOfReactions()) {
            ((DefaultTableModel) table.getModel()).addRow(new Object[]{sp, "Edit"});
        }
    }

    private void removeTableContent(JTable table) {
        for (int i = table.getModel().getRowCount() - 1; i >= 0; i--) {
            ((DefaultTableModel) table.getModel()).removeRow(i);
        }
    }

    @Override
    Boolean needReloadNextStep() {
        return reloadedByPreviousStep;
    }

    @Override
    Boolean needReloadPrevStep() {
        return false;
    }

    @Override
    Boolean isReadyGoToNextStep() {
        return true;
    }

    @Override
    Boolean isReadyGoToPrevStep() {
        return true;
    }

    @Override
    int beforeGoToNextStep() {
        reloadedByPreviousStep = false;
        return 0;
    }

    @Override
    int reload() {
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        removeTableContent(otherRETable);
        reloadTable(firstRETable, parent.sBMLDocuments.get(0));
        reloadedByPreviousStep = true;
        return 0;
    }
    
    @Override
    String getHelp(){
        return "this is help for \"Edit similar reaction founded by algorithm\" page of iMet program.";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CandidatePanel;
    private javax.swing.JPanel ReactionPanel;
    private javax.swing.JButton btnFind;
    private javax.swing.JPanel findPanel;
    private javax.swing.JTable firstRETable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable otherRETable;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
    private Boolean reloadedByPreviousStep;
    private DefaultTableModel firstDataModel;
    private DefaultTableModel otherDataModel;
    private CandidateReactionPanel candidateReactionPanel;
}
