package com.sbmlmerge.gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.text.PlainDocument;
import javax.xml.stream.XMLStreamException;
import org.bounce.text.xml.XMLEditorKit;
import org.bounce.text.xml.XMLStyleConstants;
import org.sbml.jsbml.xml.XMLNode;

public class xmlViewPanel extends SBMLPanel {

    public xmlViewPanel(
            XMLNode xmlNode,
            SBMLPanel parentSBMLJPanel,
            SBMLFrame parentSBMLFrame) throws XMLStreamException
    {
        super(parentSBMLJPanel,parentSBMLFrame);
        this.xmlNode = xmlNode;
        initComponents();
        initMyComponents();
        jEditorPane.setText(this.xmlNode.toXMLString());
    }
    
      
    public xmlViewPanel(
            String s,
            SBMLPanel parentSBMLJPanel,
            SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel,parentSBMLFrame);
        initComponents();
        initMyComponents();
        jEditorPane.setText(s);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane = new javax.swing.JScrollPane();
        jEditorPane = new javax.swing.JEditorPane();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(250, 150));
        setLayout(new java.awt.GridBagLayout());

        jEditorPane.setEditable(false);
        jScrollPane.setViewportView(jEditorPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 207;
        gridBagConstraints.ipady = 176;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 10);
        add(jScrollPane, gridBagConstraints);

        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 11, 0);
        add(btnSave, gridBagConstraints);

        btnCancel.setText("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 11, 0);
        add(btnCancel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.out.print("Canceled.");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        System.out.print("Saved.");
    }//GEN-LAST:event_btnSaveActionPerformed

    private void initMyComponents(){
        jEditorPane.setEditorKit(new XMLEditorKit());
        
        // Set the font style.
        jEditorPane.setFont(new Font("Courier", Font.PLAIN, 12));
        
        // Set the tab size
        jEditorPane.getDocument().putProperty(PlainDocument.tabSizeAttribute,4);
        
        // Enable auto indentation.
        ((XMLEditorKit)jEditorPane.getEditorKit()).setAutoIndentation(true);
        
        // Enable tag completion.
        ((XMLEditorKit)jEditorPane.getEditorKit()).setTagCompletion(true);
        
        // Enable error highlighting.
        jEditorPane.getDocument().putProperty(
                XMLEditorKit.ERROR_HIGHLIGHTING_ATTRIBUTE, true);

        // Set a style
        ((XMLEditorKit)jEditorPane.getEditorKit()).setStyle(
                XMLStyleConstants.ATTRIBUTE_NAME,new Color(255,0,0),Font.BOLD);
        
    }
    
    private XMLNode xmlNode;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JEditorPane jEditorPane;
    private javax.swing.JScrollPane jScrollPane;
    // End of variables declaration//GEN-END:variables
}
