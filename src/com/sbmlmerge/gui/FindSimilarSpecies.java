package com.sbmlmerge.gui;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.text.DefaultCaret;

public final class FindSimilarSpecies extends SBMLPanel {

    public FindSimilarSpecies(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
        reloadedByPreviousStep = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        pbarSimilar = new javax.swing.JProgressBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaSimilar = new javax.swing.JTextArea();
        btnFindSimilarSP = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Find similar species"));
        jPanel3.setPreferredSize(new java.awt.Dimension(12, 55));

        pbarSimilar.setToolTipText("");
        pbarSimilar.setStringPainted(true);
        pbarSimilar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pbarSimilarStateChanged(evt);
            }
        });

        txtAreaSimilar.setColumns(20);
        txtAreaSimilar.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        txtAreaSimilar.setRows(5);
        txtAreaSimilar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        DefaultCaret caret = (DefaultCaret)txtAreaSimilar.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jScrollPane2.setViewportView(txtAreaSimilar);

        btnFindSimilarSP.setText("Find Similar Species");
        btnFindSimilarSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindSimilarSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addComponent(pbarSimilar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnFindSimilarSP)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(btnFindSimilarSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbarSimilar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindSimilarSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindSimilarSPActionPerformed
        if (parentSBMLFrame != null) {
            txtAreaSimilar.setText("");
            final WizardFrame parent = (WizardFrame) parentSBMLFrame;
            try {
                if (!isThreadRunning) {
                    NotificationThread notifiedRunnable = new NotificationThread() {
                        @Override
                        public void doWork() {
                            parent.mergeSbml.findSimilarSpecies(
                                            parent.sBMLDocuments,
                                            txtAreaSimilar, pbarSimilar);
                        }
                    };
                    TaskListener nxtBtnListener = new TaskListener() {
                        @Override
                        public void threadComplete(Runnable runner) {
                            parent.getBtnNext().setEnabled(true);
                            parent.getBtnBack().setEnabled(true);
                        }
                    };
                    notifiedRunnable.addListener(nxtBtnListener);
                    
                    TaskListener doAutoListener = new TaskListener() {
                        @Override
                        public void threadComplete(Runnable runner) {
                            if (parent.doAutomatically)
                                synchronized (parent.getDoAutomaticThread()){
                                    parent.getDoAutomaticThread().notify();
                                }
                        }
                    };
                    notifiedRunnable.addListener(doAutoListener);
            
                    similarityThread = new Thread(notifiedRunnable);
                    similarityThread.start();
                    isThreadRunning = true;
                    parent.getBtnNext().setEnabled(false);
                    parent.getBtnBack().setEnabled(false);
                }
            } catch (Exception e) {
                ((WizardFrame) parentSBMLFrame).getLogger().info(e.getMessage());
            }
        }
        btnFindSimilarSP.setEnabled(false);
    }//GEN-LAST:event_btnFindSimilarSPActionPerformed

    private void pbarSimilarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pbarSimilarStateChanged
        JProgressBar pbar = (JProgressBar) evt.getSource();
        if (pbar.getValue() == pbar.getMaximum()) {
            isThreadRunning = false;
            btnFindSimilarSP.setEnabled(true);
        }
    }//GEN-LAST:event_pbarSimilarStateChanged

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
        if (similarityThread == null)
            return false;
        
        if (similarityThread.isAlive())
            return false;
        
        return !isThreadRunning && (pbarSimilar.getValue() == pbarSimilar.getMaximum());
    }

    @Override
    Boolean isReadyGoToPrevStep() {
        return !isThreadRunning;
    }

    @Override
    int beforeGoToNextStep() {
        reloadedByPreviousStep = false;
        return 0;
    }

    @Override
    int reload() {
        pbarSimilar.setValue(0);
        txtAreaSimilar.setText("");
        isThreadRunning = false;
        reloadedByPreviousStep = true;
        this.revalidate();
        return 0;
    }

    @Override
    String getHelp(){
        return "In this step, iMet finds similar species in two models by comparing each species of model1 with all species of model2\n"
                + "and assign a similarity score.\n\n" 
                + "  - The process of finding similar species can be started by clicking the \"Find Similar Species\" button,\n"
                + "  - This step can not be skipped as it is vital to go through the next steps.\n" 
                + "  - The progress bar shows the progress of the comparing process in percent.\n" 
                + "  - The result of the comparing for each species is also reported in the Text Area.";
    }

    @Override
    void doAutomatically() {
        final WizardFrame parent = ((WizardFrame) parentSBMLFrame);
        btnFindSimilarSP.doClick();
        try {
            synchronized (parent.getDoAutomaticThread()) {
                parent.getDoAutomaticThread().wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ChooseFilePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (!isReadyGoToNextStep()) {
        }
        parent.getBtnNext().doClick(ButtonEditor.presstime);        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFindSimilarSP;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JProgressBar pbarSimilar;
    private javax.swing.JTextArea txtAreaSimilar;
    // End of variables declaration//GEN-END:variables
    private Thread similarityThread;
    private Boolean isThreadRunning;
    private Boolean reloadedByPreviousStep;
}
