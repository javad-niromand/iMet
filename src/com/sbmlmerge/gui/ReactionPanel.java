package com.sbmlmerge.gui;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sbml.jsbml.Reaction;

public class ReactionPanel extends SBMLPanel {

    public ReactionPanel(
            Reaction reaction,
            SBMLPanel parentSBMLJPanel,
            SBMLFrame parentSBMLJFrame) {
        super(parentSBMLJPanel,parentSBMLJFrame);
        this.reaction = reaction;
        initComponents();
        initMyComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelReaction = new javax.swing.JPanel();
        panelXml = new javax.swing.JPanel();
        panelInfo = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblKineticLaw = new javax.swing.JLabel();
        txtKineticLaw = new javax.swing.JTextField();
        txtNotes = new javax.swing.JTextField();
        lblNotes = new javax.swing.JLabel();
        lblAnnotation = new javax.swing.JLabel();
        txtAnnotation = new javax.swing.JTextField();
        lblModel = new javax.swing.JLabel();
        txtModel = new javax.swing.JTextField();

        panelReaction.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reaction", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
        panelReaction.setMinimumSize(new java.awt.Dimension(22, 50));
        panelReaction.setPreferredSize(new java.awt.Dimension(22, 50));
        panelReaction.setLayout(new java.awt.BorderLayout());

        panelXml.setBorder(javax.swing.BorderFactory.createTitledBorder("Xml"));

        panelInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Info"));

        lblId.setText("Id:");

        txtId.setEditable(false);

        lblName.setLabelFor(txtName);
        lblName.setText("Name:");

        lblKineticLaw.setLabelFor(txtKineticLaw);
        lblKineticLaw.setText("Kinetic Law:");
        lblKineticLaw.setToolTipText("");

        txtKineticLaw.setEditable(false);
        txtKineticLaw.setToolTipText("Double click to edit..");
        if (reaction.getKineticLaw() != null)
        txtKineticLaw.setText("Double click to edit..");
        txtKineticLaw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtKineticLawMouseClicked(evt);
            }
        });

        txtNotes.setEditable(false);
        txtNotes.setToolTipText("Double click to edit..");
        if (reaction.getNotes()!=null)
        txtNotes.setText("Double click to edit..");
        txtNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNotesMouseClicked(evt);
            }
        });

        lblNotes.setLabelFor(txtNotes);
        lblNotes.setText("Notes:");

        lblAnnotation.setLabelFor(txtAnnotation);
        lblAnnotation.setText("Annotation:");

        txtAnnotation.setEditable(false);
        txtAnnotation.setToolTipText("Double click to edit..");
        if (reaction.getAnnotation().getFullAnnotation()!=null)
        txtAnnotation.setText("Double click to edit..");
        txtAnnotation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAnnotationMouseClicked(evt);
            }
        });

        lblModel.setText("Model:");

        txtModel.setEditable(false);

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblId)
                    .addComponent(lblName)
                    .addComponent(lblKineticLaw)
                    .addComponent(lblNotes)
                    .addComponent(lblAnnotation)
                    .addComponent(lblModel))
                .addGap(15, 15, 15)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName)
                    .addComponent(txtKineticLaw)
                    .addComponent(txtAnnotation, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNotes, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtModel))
                .addContainerGap())
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblModel)
                    .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKineticLaw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblKineticLaw))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNotes))
                .addGap(8, 8, 8)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnnotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnnotation))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelReaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelReaction, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNotesMouseClicked
        if (evt.getClickCount() >= 2) {
            try {
                if (reaction.getNotes() != null) {
                    panelXml.setLayout(new java.awt.BorderLayout());
                    for (Component cmp : panelXml.getComponents()) {
                        if (cmp.equals(xmlComponent)) {
                            panelXml.remove(cmp);
                        }
                    }
                    panelXml.add(xmlComponent
                            = new xmlViewPanel(reaction.getNotes(), this,parentSBMLFrame));
                    xmlComponent.setSize(panelXml.getSize());
                    panelXml.setName("notes");
                    panelXml.revalidate();
                }
            } catch (Exception ex) {
                Logger.getLogger(ReactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtNotesMouseClicked

    private void txtAnnotationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnnotationMouseClicked
        if (evt.getClickCount() >= 2) {
            try {
                if (reaction.getAnnotation().getFullAnnotation() != null) {
                    panelXml.setLayout(new java.awt.BorderLayout());
                    for (Component cmp : panelXml.getComponents()) {
                        if (cmp.equals(xmlComponent)) {
                            panelXml.remove(cmp);
                        }
                    }
                    panelXml.add(xmlComponent
                            = new xmlViewPanel(reaction.getAnnotation().getFullAnnotation(), this,parentSBMLFrame));
                    xmlComponent.setSize(panelXml.getSize());
                    panelXml.setName("annotation");
                    panelXml.revalidate();
                }
            } catch (Exception ex) {
                Logger.getLogger(ReactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtAnnotationMouseClicked

    private void txtKineticLawMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtKineticLawMouseClicked
        if (evt.getClickCount() >= 2) {
            try {
                if (reaction.getKineticLaw() != null) {
                    panelXml.setLayout(new java.awt.BorderLayout());
                    for (Component cmp : panelXml.getComponents()) {
                        if (cmp.equals(xmlComponent)) {
                            panelXml.remove(cmp);
                        }
                    }
                    panelXml.add(xmlComponent
                            = new xmlViewPanel(reaction.getKineticLaw().toString(), this,parentSBMLFrame));
                    panelXml.setName("kinetic");
                    panelXml.revalidate();
                }
            } catch (Exception ex) {
                Logger.getLogger(ReactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtKineticLawMouseClicked

    private void initMyComponents() {
        panelReaction.setLayout(new java.awt.BorderLayout());
        panelReaction.add(new FormulaPanel(reaction, this,parentSBMLFrame));
        txtModel.setText(reaction.getModel().toString());
        txtId.setText(reaction.getId());
        txtName.setText(reaction.getName());
        try {
            if (reaction.getNotes() != null) {

                if (reaction.getNotes() != null) {
                    panelXml.setLayout(new java.awt.BorderLayout());
                    for (Component cmp : panelXml.getComponents()) {
                        if (cmp.equals(xmlComponent)) {
                            panelXml.remove(cmp);
                        }
                    }
                    panelXml.add(xmlComponent
                            = new xmlViewPanel(reaction.getNotes(), this,parentSBMLFrame));
                    xmlComponent.setSize(panelXml.getSize());
                    panelXml.setName("notes");
                    panelXml.revalidate();
                }
            } else if (reaction.getAnnotation().getFullAnnotation() != null) {
                panelXml.setLayout(new java.awt.BorderLayout());
                for (Component cmp : panelXml.getComponents()) {
                    if (cmp.equals(xmlComponent)) {
                        panelXml.remove(cmp);
                    }
                }
                panelXml.add(xmlComponent
                        = new xmlViewPanel(reaction.getAnnotation().getFullAnnotation(), this,parentSBMLFrame));
                xmlComponent.setSize(panelXml.getSize());
                panelXml.setName("annotation");
                panelXml.revalidate();
            } else if (reaction.getKineticLaw() != null) {
                panelXml.setLayout(new java.awt.BorderLayout());
                for (Component cmp : panelXml.getComponents()) {
                    if (cmp.equals(xmlComponent)) {
                        panelXml.remove(cmp);
                    }
                }
                panelXml.add(xmlComponent
                        = new xmlViewPanel(reaction.getKineticLaw().toString(), this,parentSBMLFrame));
                panelXml.setName("kinetic");
                panelXml.revalidate();
            }
        } catch (Exception ex) {
            Logger.getLogger(ReactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Reaction reaction;
    private Component xmlComponent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblAnnotation;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblKineticLaw;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNotes;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelReaction;
    private javax.swing.JPanel panelXml;
    private javax.swing.JTextField txtAnnotation;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtKineticLaw;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNotes;
    // End of variables declaration//GEN-END:variables

}
