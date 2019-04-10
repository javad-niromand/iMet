package com.sbmlmerge.gui;

public class SBMLPanel extends javax.swing.JPanel {

    public SBMLPanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        this.parentSBMLJPanel = parentSBMLJPanel;
        this.parentSBMLFrame = parentSBMLFrame;
        initComponents();
    }
    
    public SBMLPanel(SBMLFrame containerSBMLFrame, SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        this.parentSBMLJPanel = parentSBMLJPanel;
        this.parentSBMLFrame = parentSBMLFrame;
        this.containerSBMLFrame = containerSBMLFrame;
        initComponents();
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

    protected SBMLPanel parentSBMLJPanel;
    protected SBMLFrame parentSBMLFrame;
    protected SBMLFrame containerSBMLFrame;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public SBMLFrame getParentSBMLFrame() {
        return parentSBMLFrame;
    }

    public void setParentSBMLFrame(SBMLFrame parentSBMLFrame) {
        this.parentSBMLFrame = parentSBMLFrame;
    }

    public SBMLPanel getParentSBMLJPanel() {
        return parentSBMLJPanel;
    }

    public void setParentSBMLJPanel(SBMLPanel parentSBMLJPanel) {
        this.parentSBMLJPanel = parentSBMLJPanel;
    }

    Boolean needReloadNextStep() {
        return false;
    }
    
    Boolean needReloadPrevStep() {
        return false;
    } 
    
    Boolean isReadyGoToNextStep(){
        return true;
    }
    
    Boolean isReadyGoToPrevStep(){
        return true;
    }
    
    int beforeGoToNextStep(){
        return 0;
    }
    
    int reload(){
        return 0;
    }
    
    void doAutomatically(){
    }
    
    String getHelp(){
        return "";
    }
}
