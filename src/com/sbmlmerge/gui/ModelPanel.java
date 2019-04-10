package com.sbmlmerge.gui;

import java.awt.Component;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Reaction;

public class ModelPanel extends SBMLPanel {

    public ModelPanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame,Model model) {
        super(parentSBMLJPanel, parentSBMLFrame);
        this.model = model;
        initComponents();
        initMyComponents();
        listReaction.setSelectedIndex(1);
    }

    private void initMyComponents(){
        this.setName("ModelPanel");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPanelModel = new javax.swing.JTabbedPane();
        reactionPanel = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelReactionLiset = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listReaction = new javax.swing.JList();
        listModel = new DefaultListModel();
        for (Reaction reaction : model.getListOfReactions()){
            listModel.addElement(reaction);  
        }
        listReaction.setModel(listModel);
        listReaction.revalidate();
        panelReaction = new javax.swing.JPanel();
        speciesPanel = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(777, 444));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 666));

        jSplitPane1.setResizeWeight(0.5);

        panelReactionLiset.setMinimumSize(new java.awt.Dimension(50, 23));
        panelReactionLiset.setLayout(new java.awt.BorderLayout());

        listReaction.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listReaction.setAutoscrolls(false);
        listReaction.setMaximumSize(new java.awt.Dimension(50, 80));
        listReaction.setMinimumSize(new java.awt.Dimension(50, 80));
        listReaction.setPreferredSize(null);
        listReaction.setSelectedIndices(new int[] {0});
        listReaction.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                ListReactionvalueChanged(e);
            }
        });
        jScrollPane1.setViewportView(listReaction);

        panelReactionLiset.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jSplitPane1.setLeftComponent(panelReactionLiset);

        panelReaction.setLayout(new java.awt.BorderLayout());
        jSplitPane1.setRightComponent(panelReaction);

        javax.swing.GroupLayout reactionPanelLayout = new javax.swing.GroupLayout(reactionPanel);
        reactionPanel.setLayout(reactionPanelLayout);
        reactionPanelLayout.setHorizontalGroup(
            reactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reactionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        reactionPanelLayout.setVerticalGroup(
            reactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reactionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        jTabbedPanelModel.addTab("Reaction", reactionPanel);

        speciesPanel.setLayout(new java.awt.BorderLayout());
        jTabbedPanelModel.addTab("Species", speciesPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPanelModel, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPanelModel, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public void ListReactionvalueChanged(ListSelectionEvent listSelectionEvent) {
        JList list = (JList) listSelectionEvent.getSource();
        int index = list.getSelectedIndex();
        Reaction reaction = (Reaction) listReaction.getModel().getElementAt(index);
        for (Component cmp : panelReaction.getComponents()) {
            if (cmp.equals(reactionComponent)) {
                panelReaction.remove(cmp);
            }
        }
        panelReaction.add(reactionComponent = new ReactionPanel(reaction, this,parentSBMLFrame));
        panelReaction.revalidate();
    }

    private Component reactionComponent;
    private Component speciesComponent;
    private DefaultListModel listModel;
    private Model model;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPanelModel;
    private javax.swing.JList listReaction;
    private javax.swing.JPanel panelReaction;
    private javax.swing.JPanel panelReactionLiset;
    private javax.swing.JPanel reactionPanel;
    private javax.swing.JPanel speciesPanel;
    // End of variables declaration//GEN-END:variables

    public JTabbedPane getjTabbedPanelModel() {
        return jTabbedPanelModel;
    }

    public void setjTabbedPanelModel(JTabbedPane jTabbedPanelModel) {
        this.jTabbedPanelModel = jTabbedPanelModel;
    }

    public Component getReactionComponent() {
        return reactionComponent;
    }

    public void setReactionComponent(Component reactionComponent) {
        this.reactionComponent = reactionComponent;
    }

    public Component getSpeciesComponent() {
        return speciesComponent;
    }

    public void setSpeciesComponent(Component speciesComponent) {
        this.speciesComponent = speciesComponent;
    }

    public JPanel getReactionPanel() {
        return reactionPanel;
    }

    public void setReactionPanel(JPanel reactionPanel) {
        this.reactionPanel = reactionPanel;
    }

    public JPanel getSpeciesPanel() {
        return speciesPanel;
    }

    public void setSpeciesPanel(JPanel speciesPanel) {
        this.speciesPanel = speciesPanel;
    }

}
