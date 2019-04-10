package com.sbmlmerge.gui;

import com.sbmlmerge.core.MergeSBML;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.Species;

public class SpeciesPanel extends SBMLPanel {

    public SpeciesPanel(Species species, SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        this.species = species;
        initComponents();
        initMyComponents();
    }

    private void initMyComponents() {
        txtModel.setText(this.species.getModel().toString());
        txtId.setText(this.species.getId());
        txtName.setText(this.species.getName());
        int i = 0;
        for (Compartment cmp : this.species.getModel().getListOfCompartments()) {
            cmbCompartment.addItem(cmp);
            if (cmp.getId().equals(this.species.getCompartment())) {
                cmbCompartment.setSelectedIndex(i);
            }
            i++;
        }
        try {
            if (species.getNotes() != null) {
                panelXml.setLayout(new java.awt.BorderLayout());
                for (Component cmp : panelXml.getComponents()) {
                    if (cmp.equals(xmlComponent)) {
                        panelXml.remove(cmp);
                    }
                }
                panelXml.add(xmlComponent = new xmlViewPanel(species.getNotes(), this, parentSBMLFrame));
                xmlComponent.setSize(panelXml.getSize());
                panelXml.setName("notes");
                panelXml.revalidate();
            }
        } catch (Exception ex) {
            Logger.getLogger(ReactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblCompartment = new javax.swing.JLabel();
        cmbCompartment = new javax.swing.JComboBox();
        lblNotes = new javax.swing.JLabel();
        lblAnnotation = new javax.swing.JLabel();
        txtNotes = new javax.swing.JTextField();
        txtAnnotation = new javax.swing.JTextField();
        panelXml = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        lblModel = new javax.swing.JLabel();
        txtModel = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(513, 214));
        setPreferredSize(new java.awt.Dimension(550, 250));

        lblId.setLabelFor(txtId);
        lblId.setText("Id:");

        txtId.setEditable(false);

        lblName.setLabelFor(txtName);
        lblName.setText("Name:");

        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFocusLost(evt);
            }
        });

        lblCompartment.setLabelFor(cmbCompartment);
        lblCompartment.setText("Compartment:");

        cmbCompartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCompartmentActionPerformed(evt);
            }
        });

        lblNotes.setLabelFor(txtNotes);
        lblNotes.setText("Notes:");

        lblAnnotation.setLabelFor(txtAnnotation);
        lblAnnotation.setText("Annotation:");

        txtNotes.setEditable(false);
        txtNotes.setToolTipText("Double click to edit..");
        if (species.getNotes()!=null)
        txtNotes.setText("Double click to edit..");
        txtNotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNotesMouseClicked(evt);
            }
        });

        txtAnnotation.setEditable(false);
        txtAnnotation.setToolTipText("Double click to edit..");
        if (species.getAnnotation().getFullAnnotation()!=null)
        txtAnnotation.setText("Double click to edit..");
        txtAnnotation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAnnotationMouseClicked(evt);
            }
        });

        panelXml.setBorder(javax.swing.BorderFactory.createTitledBorder("Xml"));
        panelXml.setMinimumSize(null);
        panelXml.setPreferredSize(null);

        btnSearch.setText("Find in KEGG");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblModel.setText("Model:");

        txtModel.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAnnotation))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNotes)
                            .addComponent(txtAnnotation, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblCompartment)
                            .addComponent(lblName)
                            .addComponent(lblId)
                            .addComponent(lblModel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName)
                            .addComponent(cmbCompartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtId)
                            .addComponent(txtModel))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblModel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtModel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCompartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCompartment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNotes)
                            .addComponent(txtNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAnnotation)
                            .addComponent(txtAnnotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelXml, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusLost
        if (!txtName.getText().equals(species.getName())) {
            species.setName(txtName.getText());
        }
    }//GEN-LAST:event_txtNameFocusLost

    private void cmbCompartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCompartmentActionPerformed
        Compartment selectedCmp;
        selectedCmp = (Compartment) cmbCompartment.getSelectedItem();
        species.setCompartment(selectedCmp.getId());
    }//GEN-LAST:event_cmbCompartmentActionPerformed

    private void txtNotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNotesMouseClicked
        if (evt.getClickCount() >= 2) {
            try {
                if (species.getNotes() != null) {
                    panelXml.setLayout(new java.awt.BorderLayout());
                    for (Component cmp : panelXml.getComponents()) {
                        if (cmp.equals(xmlComponent)) {
                            panelXml.remove(cmp);
                        }
                    }
                    panelXml.add(xmlComponent = new xmlViewPanel(species.getNotes(), this, parentSBMLFrame));
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
                if (species.getAnnotation().getFullAnnotation() != null) {
                    panelXml.setLayout(new java.awt.BorderLayout());
                    for (Component cmp : panelXml.getComponents()) {
                        if (cmp.equals(xmlComponent)) {
                            panelXml.remove(cmp);
                        }
                    }
                    panelXml.add(xmlComponent = new xmlViewPanel(species.getAnnotation().getFullAnnotation(), this, parentSBMLFrame));
                    xmlComponent.setSize(panelXml.getSize());
                    panelXml.setName("annotation");
                    panelXml.revalidate();
                }
            } catch (Exception ex) {
                Logger.getLogger(ReactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtAnnotationMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if (btnSearch.getText().equalsIgnoreCase("Cancel")) {
            btnSearch.setText("Find in KEGG");
            return;
        }

        searchThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread started");
                MergeSBML.usingInternetDB(species);
                btnSearch.setText("Find in KEGG");
                updateXmlData();
                System.out.println("thread finished");
            }
        });
        searchThread.start();
        btnSearch.setText("Cancel");
    }//GEN-LAST:event_btnSearchActionPerformed

    private void updateXmlData() {
        try {
            if (species.getNotes() != null) {
                panelXml.setLayout(new java.awt.BorderLayout());
                for (Component cmp : panelXml.getComponents()) {
                    if (cmp.equals(xmlComponent)) {
                        panelXml.remove(cmp);
                    }
                }
                panelXml.add(xmlComponent = new xmlViewPanel(species.getNotes(), this, parentSBMLFrame));
                xmlComponent.setSize(panelXml.getSize());
                panelXml.setName("notes");
                panelXml.revalidate();
            }
        } catch (Exception ex) {
            Logger.getLogger(ReactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Component xmlComponent;
    private Species species;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox cmbCompartment;
    private javax.swing.JLabel lblAnnotation;
    private javax.swing.JLabel lblCompartment;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblModel;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNotes;
    private javax.swing.JPanel panelXml;
    private javax.swing.JTextField txtAnnotation;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNotes;
    // End of variables declaration//GEN-END:variables
    private Thread searchThread;
}
