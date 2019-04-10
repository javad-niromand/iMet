package com.sbmlmerge.gui;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.xml.stream.XMLStreamException;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLException;
import org.sbml.jsbml.SBMLWriter;

public class FinishPanel extends SBMLPanel {

    public FinishPanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblSave = new javax.swing.JLabel();
        txtSave = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblSummary = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSave.setText("Save to :");

        txtSave.setEditable(false);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSave)
                    .addComponent(txtSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Summary"));

        lblSummary.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblSummary.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblSummary.setName("lblSummary"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSummary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSummary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblSummary.getAccessibleContext().setAccessibleName("lblSummary");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            WizardFrame parent = (WizardFrame) parentSBMLFrame;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setMultiSelectionEnabled(false);
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public String getDescription() {
                    return "SBML file (*.xml)";
                }

                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        return f.getName().toLowerCase().endsWith(".xml");
                    }
                }
            });
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    if (!file.getAbsolutePath().toLowerCase().endsWith(".xml")) {
                        txtSave.setText(file.getAbsolutePath() + ".xml");
                    } else {
                        txtSave.setText(file.getAbsolutePath());
                    }
                }
            }
            SBMLWriter.write(parent.mergedSBMLDoc, txtSave.getText(), "SBMLMerge", "1", '\t', (short) 1);
            lblMessage.setText("Merged model saved successfully.");
        } catch (HeadlessException | XMLStreamException | FileNotFoundException | SBMLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error occured while saving merge model\n" + ex.getMessage(),
                    "ErrorMsg", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

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
        return savedSuccessfully;
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
        reloadedByPreviousStep = true;
        txtSave.setText("");
        loadingSummary();
        return 0;
    }

    @Override
    String getHelp(){
        return "Save the new merged model into a file by clicking \"Save\" button.";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblSave;
    private javax.swing.JLabel lblSummary;
    private javax.swing.JTextField txtSave;
    // End of variables declaration//GEN-END:variables
    private Boolean reloadedByPreviousStep;
    private Boolean savedSuccessfully;

    private void loadingSummary() {
        WizardFrame parent = ((WizardFrame) parentSBMLFrame);
        ArrayList<SBMLDocument> sbmlDocList = parent.sBMLDocuments;
        String summary = "<html>" 
                + "<p><strong>Model1:</strong></p>" 
                + "<ul style=\"list-style-type: circle;\">" 
                + "<li><span style=\"color: #0000ff;\">Model ID:</span> " + sbmlDocList.get(0).getModel().getId()+ "</li>" 
                + "<li><span style=\"color: #0000ff;\">Number of Species:</span> " + sbmlDocList.get(0).getModel().getListOfSpecies().size() + "</li>" 
                + "<li><span style=\"color: #0000ff;\">Number of Reactions:</span> " + sbmlDocList.get(0).getModel().getListOfReactions().size() + "</li>" 
                + "</ul>" 
                + "<hr />"
                + "<br>" 
                + "<p><strong>Model2:</strong></p>" 
                + "<ul style=\"list-style-type: circle;\">" 
                + "<li><span style=\"color: #0000ff;\">Model ID:</span> " + sbmlDocList.get(1).getModel().getId() + "</li>" 
                + "<li><span style=\"color: #0000ff;\">Number of Species:</span> " + sbmlDocList.get(1).getModel().getListOfSpecies().size() + "</li>" 
                + "<li><span style=\"color: #0000ff;\">Number of Reactions:</span> " + sbmlDocList.get(1).getModel().getListOfReactions().size() + "</li>" 
                + "</ul>" 
                + "<hr />"
                + "<br>" 
                + "<p><strong>Merged Model:</strong></p>" 
                + "<ul style=\"list-style-type: circle;\">" 
                + "<li><span style=\"color: #0000ff;\">Model ID:</span> " + parent.mergedSBMLDoc.getModel().getId() + " </li>" 
                + "<li><span style=\"color: #0000ff;\">Number of similar Species:</span> " + parent.mergedSBMLDoc.getModel().getListOfSpecies().size() + " </li>" 
                + "<li><span style=\"color: #0000ff;\">Number of similar Reactions:</span> " + parent.mergedSBMLDoc.getModel().getListOfReactions().size() + "</li>" 
                + "</ul>" 
                + "<hr />"
                + "<br>" 
                + "<p><strong>Merging Algorithm:</strong></p>" 
                + "<ul style=\"list-style-type: circle;\">" 
                + "<li><span style=\"color: #0000ff;\">Number of similar Species:</span> " + parent.getNumOfSimilarSpecies() + "</li>" 
                + "<li><span style=\"color: #0000ff;\">Number of similar Reactions:</span> " + parent.getNumOfSimilarReactions() + "</li>" 
                + "</ul>";
        this.lblSummary.setText(summary);
    }
}
