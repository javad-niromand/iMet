package com.sbmlmerge.gui;

import com.sbmlmerge.core.MergeSBML;
import javax.swing.JComboBox;
import javax.swing.JTable;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.SBMLDocument;

public class EditReactionSimilarity extends SBMLPanel {

    public EditReactionSimilarity(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        cmbModelLeft = new javax.swing.JComboBox();
        leftInsidePanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();

        jSplitPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jSplitPane1.setResizeWeight(0.5);

        leftPanel.setBackground(new java.awt.Color(225, 235, 245));

        cmbModelLeft.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbModelLeftItemStateChanged(evt);
            }
        });

        leftInsidePanel.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        leftInsidePanel.setPreferredSize(new java.awt.Dimension(300, 425));
        leftInsidePanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbModelLeft, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(leftInsidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbModelLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftInsidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(leftPanel);

        rightPanel.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setRightComponent(rightPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbModelLeftItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbModelLeftItemStateChanged
        if (tablePanel == null)
            return;
        JComboBox cb = (JComboBox) evt.getSource();
        if (cb.getSelectedItem() == null)
            return;
        tablePanel.reloadReactionTable(((Model) cb.getSelectedItem()).getListOfReactions());
    }//GEN-LAST:event_cmbModelLeftItemStateChanged

    private Reaction findReaction(String reId) {
        Reaction returnRe;
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        for (SBMLDocument sBMLDocument : parent.sBMLDocuments) {
            if (sBMLDocument.getModel().equals(cmbModelLeft.getSelectedItem())) {
                continue;
            }
            returnRe = sBMLDocument.getModel().getReaction(reId);
            if (returnRe != null) {
                return returnRe;
            }
        }
        return null;
    }

    public void tableSelectedItemChanged(JTable table) {
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        int row = table.getSelectedRow();
        int column = 1;
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals("Object")) {
                column = i;
            }
        }
        Reaction selectedRe = (Reaction) table.getValueAt(row, column);
        String[] similarReactionIdsArray = MergeSBML.getSimilarReaction(selectedRe);
        Reaction[] similarReactions = null;
        int[] scores = null;
        if (similarReactionIdsArray != null) {
            similarReactions = new Reaction[similarReactionIdsArray.length];
            scores = new int[similarReactionIdsArray.length];
            for (int i = 0; i < similarReactionIdsArray.length; i++) {
                String id = MergeSBML.getId(similarReactionIdsArray[i]);
                Reaction similarRe = findReaction(id);
                similarReactions[i] = similarRe;
                scores[i] = MergeSBML.getScore(similarReactionIdsArray[i]);
            }
        }
        if (showMatchPanel == null)
            showMatchPanel = new ShowMatchPanel(this, parentSBMLFrame);
        showMatchPanel.reload(selectedRe,similarReactions, scores);
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
        int selectedItem = -1;
        int selectedModel = -1;
        try{
            selectedItem = tablePanel.getTable().getSelectedRow();
        }catch(NullPointerException e){
        }
        try{
            selectedModel = cmbModelLeft.getSelectedIndex();
        }catch(NullPointerException e){
        }

        cmbModelLeft.removeAllItems();
        for (SBMLDocument sBMLDocument : parent.sBMLDocuments) {
            cmbModelLeft.addItem(sBMLDocument.getModel());
        }

        leftInsidePanel.removeAll();
        tablePanel = new TablePanel(this, parentSBMLFrame);
        leftInsidePanel.add(tablePanel);
        if (selectedModel >= 0)
            cmbModelLeft.setSelectedIndex(selectedModel);
        tablePanel.reloadReactionTable(((Model) cmbModelLeft.getSelectedItem()).getListOfReactions());

        rightPanel.removeAll();
        showMatchPanel = new ShowMatchPanel(this, parentSBMLFrame);
        rightPanel.add(showMatchPanel);
        showMatchPanel.reload(null,null,null);
        this.revalidate();
        if (selectedItem >= 0)
            tablePanel.getTable().getSelectionModel().setSelectionInterval(selectedItem, selectedItem);
        
        reloadedByPreviousStep = true;
        return 0;
    }

    @Override
    String getHelp() {
        return "In this step, iMet allows the supervisor to edit the automatically matched reactions and/or make new matches manually.\n\n"
                + "  - On the left panel, the list of all reactions for each model is shown.\n" 
                + "  - Each one of the merging models can be selected in the drop-down list.\n" 
                + "  - By clicking on each reaction name, it's similar reactions in the other model will be listed on the right panel.\n" 
                + "  - By clicking \"Edit/Match manually\" button, the supervisor can change the similarity score or change the similar reactions.";
    }

    @Override
    void doAutomatically() {
        while ( !isReadyGoToNextStep() ){
        }
        ((WizardFrame)parentSBMLFrame).getBtnNext().doClick(ButtonEditor.presstime);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbModelLeft;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel leftInsidePanel;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel rightPanel;
    // End of variables declaration//GEN-END:variables
    private boolean reloadedByPreviousStep;
    private TablePanel tablePanel;
    private ShowMatchPanel showMatchPanel;
}
