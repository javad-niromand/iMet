package com.sbmlmerge.gui;

import javax.swing.JOptionPane;
import org.sbml.jsbml.SBMLDocument;

public class CreateMergedModel extends SBMLPanel {

    public CreateMergedModel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MergePanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        MergePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Merge Model"));
        MergePanel.setLayout(new java.awt.BorderLayout());
        add(MergePanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

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
        SBMLDocument mergeDoc = null;
        if (parentSBMLFrame != null) {
            WizardFrame parent = (WizardFrame) parentSBMLFrame;
            try {
                mergeDoc = parent.mergeSbml.Merge(parent.sBMLDocuments);
                parent.mergedSBMLDoc = mergeDoc;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error occured while merging models\n" + e.getMessage(),
                        "ErrorMsg", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (mergeDoc != null) {
            MergePanel.add(new ModelPanel(this, this.parentSBMLFrame, mergeDoc.getModel()));
            MergePanel.revalidate();
            MergePanel.repaint();
        }
        reloadedByPreviousStep = true;
        return 0;
    }

    @Override
    String getHelp() {
        return "In this step, iMet allows the supervisor to edit the newly merged model. \n\n" 
                + "  - By clicking on the name of each reaction in the list, it's information will be shown on the right-side panel.\n" 
                + "  - By double clicking on each species name in the equation (which is surrounded by a dark rectangle),\n"
                + "     the information of this species will be shown.\n\n"
                + "  - By clicking \"Find in KEGG\" button, iMet will try to extract some more metadata of this species from KEGG DB.";
        
    }

    @Override
    void doAutomatically() {
        while (!isReadyGoToNextStep()) {
        }
        ((WizardFrame)parentSBMLFrame).getBtnNext().doClick(ButtonEditor.presstime);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MergePanel;
    // End of variables declaration//GEN-END:variables
    private Boolean reloadedByPreviousStep;
}
