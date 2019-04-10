package com.sbmlmerge.gui;

import javax.swing.JCheckBox;

public class StartPanel extends SBMLPanel {

    public StartPanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
        imetLogo = lbbLogo = null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        logoPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        chkBoxAutomatic = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        logoPanel.setMaximumSize(new java.awt.Dimension(239, 239));
        logoPanel.setMinimumSize(new java.awt.Dimension(239, 239));
        logoPanel.setLayout(new java.awt.GridBagLayout());

        leftPanel.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        logoPanel.add(leftPanel, gridBagConstraints);

        rightPanel.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        logoPanel.add(rightPanel, gridBagConstraints);

        chkBoxAutomatic.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkBoxAutomatic.setForeground(new java.awt.Color(153, 0, 51));
        chkBoxAutomatic.setText("Do next steps automatically (supervised steps won't be available) ");
        chkBoxAutomatic.setActionCommand("Do next steps automatically (supervised steps won't be available)\n");
        chkBoxAutomatic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBoxAutomaticActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(240, 240, 234));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 51, 153));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setTabSize(3);
        jTextArea1.setText("iMet is a standalone software tool to integrate metabolic networks. Metabolic networks can be loaded, edited and integrated in just seven steps.\n\nThis tool has been implemented using the Java programming language as cross-platform tool with a user friendly graphical interface.\n\niMet can do steps automatically after selecting input files in step 2.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                    .addComponent(chkBoxAutomatic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkBoxAutomatic, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkBoxAutomaticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBoxAutomaticActionPerformed
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        parent.doAutomatically = ((JCheckBox) evt.getSource()).isSelected();
        parent.getHelp().setEnabled(!((JCheckBox) evt.getSource()).isSelected());
    }//GEN-LAST:event_chkBoxAutomaticActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkBoxAutomatic;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JPanel rightPanel;
    // End of variables declaration//GEN-END:variables
    LogoPanel imetLogo;
    LogoPanel lbbLogo;

    @Override
    Boolean needReloadNextStep() {
        return false;
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
        return false;
    }

    @Override
    int beforeGoToNextStep() {
        return 0;
    }

    @Override
    int reload() {
        chkBoxAutomatic.setSelected(((WizardFrame) parentSBMLFrame).doAutomatically);
        if (imetLogo == null) {
            imetLogo = new LogoPanel(
                    "/com/sbmlmerge/gui/iMet_small.jpg",
                    0.8,
                    parentSBMLJPanel,
                    parentSBMLFrame
            );
            imetLogo.reload();
            leftPanel.add(imetLogo);
        }
        if (lbbLogo == null) {
            lbbLogo = new LogoPanel(
                    "/com/sbmlmerge/gui/LBB_small.jpg",
                    0.8,
                    parentSBMLJPanel,
                    parentSBMLFrame
            );
            lbbLogo.reload();
            rightPanel.add(lbbLogo);
        }
        return 0;
    }

    @Override
    String getHelp() {
        return "iMet is a standalone software tool to integrate metabolic networks.\n\n"
            + "  - By selecting \"Do next steps Automatically\" and then add two input files in next step (step1),\n"
            + "     all next steps will be done automatically step by step.\n"
            + "  - Pressing \"Back\" button in any step, will disable automatic process for next steps.";
    }
}
