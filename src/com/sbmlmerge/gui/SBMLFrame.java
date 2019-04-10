package com.sbmlmerge.gui;

import java.net.URL;
import javax.swing.ImageIcon;

public class SBMLFrame extends javax.swing.JFrame {

    public SBMLFrame(SBMLFrame parentSBMLFrame) {
        URL iconURL = getClass().getResource("/com/sbmlmerge/gui/iMet_icon.gif");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        this.parentSBMLFrame = parentSBMLFrame;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected SBMLFrame parentSBMLFrame;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public SBMLFrame getParentSBMLFrame() {
        return parentSBMLFrame;
    }

    public void setParentSBMLFrame(SBMLFrame parentSBMLFrame) {
        this.parentSBMLFrame = parentSBMLFrame;
    }
}
