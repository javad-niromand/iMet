package com.sbmlmerge.gui;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.text.DefaultCaret;

public class FindSimilarReactions extends SBMLPanel {

    public FindSimilarReactions(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
        reloadedByPreviousStep = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        similarReactionPanel = new javax.swing.JPanel();
        pbarSimilarReaction = new javax.swing.JProgressBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();
        btnSimilarReaction = new javax.swing.JButton();

        similarReactionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Find similar reactions"));
        similarReactionPanel.setPreferredSize(new java.awt.Dimension(12, 55));

        pbarSimilarReaction.setToolTipText("");
        pbarSimilarReaction.setStringPainted(true);
        pbarSimilarReaction.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pbarSimilarReactionStateChanged(evt);
            }
        });

        txtLog.setColumns(20);
        txtLog.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        txtLog.setRows(5);
        txtLog.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        DefaultCaret caret = (DefaultCaret)txtLog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jScrollPane2.setViewportView(txtLog);

        btnSimilarReaction.setText("Find Similar Reactions");
        btnSimilarReaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimilarReactionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout similarReactionPanelLayout = new javax.swing.GroupLayout(similarReactionPanel);
        similarReactionPanel.setLayout(similarReactionPanelLayout);
        similarReactionPanelLayout.setHorizontalGroup(
            similarReactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(similarReactionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(similarReactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pbarSimilarReaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addGroup(similarReactionPanelLayout.createSequentialGroup()
                        .addComponent(btnSimilarReaction)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        similarReactionPanelLayout.setVerticalGroup(
            similarReactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, similarReactionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSimilarReaction)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbarSimilarReaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(similarReactionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(similarReactionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimilarReactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimilarReactionActionPerformed
        if (parentSBMLFrame != null) {
            final WizardFrame parent = (WizardFrame) parentSBMLFrame;
            try {
                NotificationThread notifiedRunnable = new NotificationThread() {
                    @Override
                    public void doWork() {
                        parent.mergeSbml.findSimilarReaction(
                                    parent.sBMLDocuments,
                                    txtLog, pbarSimilarReaction);
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
                        if (parent.doAutomatically) {
                            synchronized (parent.getDoAutomaticThread()) {
                                parent.getDoAutomaticThread().notify();
                            }
                        }
                    }
                };
                notifiedRunnable.addListener(doAutoListener);
                
                similarityThread = new Thread(notifiedRunnable);
                similarityThread.start();
                isThreadRunning = true;
                parent.getBtnNext().setEnabled(false);
                parent.getBtnBack().setEnabled(false);
            } catch (Exception e) {
                ((WizardFrame) parentSBMLFrame).getLogger().info(e.getMessage());
            }
        }
        btnSimilarReaction.setEnabled(false);
    }//GEN-LAST:event_btnSimilarReactionActionPerformed

    private void pbarSimilarReactionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pbarSimilarReactionStateChanged
        JProgressBar pbar = (JProgressBar) evt.getSource();
        if (pbar.getValue() == pbar.getMaximum()) {
            isThreadRunning = false;
            btnSimilarReaction.setEnabled(true);
        }
    }//GEN-LAST:event_pbarSimilarReactionStateChanged

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
        
        return !isThreadRunning && (pbarSimilarReaction.getValue() == pbarSimilarReaction.getMaximum());
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
        pbarSimilarReaction.setValue(0);
        txtLog.setText("");
        isThreadRunning = false;
        reloadedByPreviousStep = true;
        this.revalidate();
        return 0;
    }

    @Override
    String getHelp(){
        return "In this step, iMet finds similar reactions in two models by comparing each reaction of model1 with all reactions of model2\n"
                + "and assign a similarity score.\n\n" 
                + "  - The process of finding similar reaction can be started by clicking the \"Find Similar Reactions\" button,\n"
                + "  - This step can not be skipped as it is vital to go through the next steps.\n" 
                + "  - The progress bar shows the progress of the comparing process in percent.\n" 
                + "  - The result of the comparing for each reaction is also reported in the Text Area.";
    }

    @Override
    void doAutomatically() {
        final WizardFrame parent = ((WizardFrame) parentSBMLFrame);
        btnSimilarReaction.doClick();
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
    private javax.swing.JButton btnSimilarReaction;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JProgressBar pbarSimilarReaction;
    private javax.swing.JPanel similarReactionPanel;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables
    private boolean isThreadRunning;
    private Thread similarityThread;
    private Boolean reloadedByPreviousStep;
}
