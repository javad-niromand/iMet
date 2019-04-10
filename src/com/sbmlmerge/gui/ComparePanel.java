package com.sbmlmerge.gui;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Species;

public class ComparePanel extends SBMLPanel {

    public ComparePanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        leftPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(1100, 500));
        setPreferredSize(new java.awt.Dimension(1100, 500));

        jSplitPane1.setResizeWeight(0.5);

        leftPanel.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setLeftComponent(leftPanel);

        rightPanel.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setRightComponent(rightPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
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

    public void reload(Object obj1, Object obj2) {
        if (obj1 instanceof Species && obj2 instanceof Species) {
           leftPanel.add(new SpeciesPanel((Species)obj1, this, parentSBMLFrame));
           rightPanel.add(new SpeciesPanel((Species)obj2, this, parentSBMLFrame));
        }
        else if (obj1 instanceof Reaction && obj2 instanceof Reaction){
            leftPanel.add(new ReactionPanel((Reaction) obj1, this, parentSBMLFrame));
            rightPanel.add(new ReactionPanel((Reaction) obj2, this, parentSBMLFrame));
        }
        this.revalidate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel rightPanel;
    // End of variables declaration//GEN-END:variables
}
