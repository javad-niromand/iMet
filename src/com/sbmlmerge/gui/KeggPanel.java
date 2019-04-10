package com.sbmlmerge.gui;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class KeggPanel extends SBMLPanel {

    public KeggPanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
        textAreaDefaultValue = "Press \"Start\" button to collect data from KEGG database";
        reloadedByPreviousStep = false;
        isThreadRunning=false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        btnPause = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        pbarInternet = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaInternet = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setForeground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        setPreferredSize(new java.awt.Dimension(400, 500));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setName(""); // NOI18N

        btnStart.setBackground(new java.awt.Color(20, 180, 125));
        btnStart.setContentAreaFilled(false);
        btnStart.setOpaque(true);
        btnStart.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnStart.setForeground(new java.awt.Color(255, 255, 255));
        btnStart.setText("Start");
        btnStart.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnPause.setText("Pause");
        btnPause.setEnabled(false);
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.setEnabled(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        pbarInternet.setStringPainted(true);
        pbarInternet.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pbarInternetStateChanged(evt);
            }
        });

        txtAreaInternet.setColumns(20);
        txtAreaInternet.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        txtAreaInternet.setRows(5);
        txtAreaInternet.setText("Press \"Start\" button to collect data from KEGG database");
        txtAreaInternet.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        //DefaultCaret caret = (DefaultCaret)txtAreaInternet.getCaret();
        //caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        jScrollPane1.setViewportView(txtAreaInternet);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbarInternet, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPause)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPause, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbarInternet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sbmlmerge/gui/kegg_titlel.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        final WizardFrame parent = (WizardFrame) parentSBMLFrame;
        if (txtAreaInternet.getText().equalsIgnoreCase(textAreaDefaultValue))
            txtAreaInternet.setText("");
        try {
            
            NotificationThread notifiedRunnable = new NotificationThread() {
                @Override
                public void doWork() {
                    parent.mergeSbml.usingInternetDB(parent.sBMLDocuments,
                            internetThread, txtAreaInternet, pbarInternet);
                }
            };
            TaskListener nxtBtnListener = new TaskListener() {
                @Override
                public void threadComplete(Runnable runner) {
                    parent.getBtnNext().setEnabled(true);
                    parent.getBtnBack().setEnabled(true);
                }
            };
            TaskListener doAutoListener = new TaskListener() {
                @Override
                public void threadComplete(Runnable runner) {
                    if (parent.doAutomatically)
                        synchronized (parent.getDoAutomaticThread()){
                            parent.getDoAutomaticThread().notify();
                        }
                }
            };
            notifiedRunnable.addListener(nxtBtnListener);
            notifiedRunnable.addListener(doAutoListener);
            internetThread = new Thread(notifiedRunnable);
            internetThread.start();
            
            isThreadRunning = true;
            btnStart.setEnabled(false);
            btnStart.setBackground(Color.GRAY);
            btnReset.setEnabled(true);
            btnPause.setEnabled(true);
            parent.getBtnNext().setEnabled(false);
            parent.getBtnBack().setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                    "Error occured:" + "\n" + e.getMessage(),
                    "ErrorMsg", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        if (internetThread != null) {
            internetThread.interrupt();
            while (internetThread.isAlive()){
            }
        }
        btnStart.setText("Resume");
        btnStart.setEnabled(true);
        btnStart.setBackground(new java.awt.Color(20, 180, 125));
        btnReset.setEnabled(true);
        btnPause.setEnabled(false);
        isThreadRunning = false;
    }//GEN-LAST:event_btnPauseActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        if (internetThread != null) {
            internetThread.interrupt();
            try{
                internetThread.join();
            }
            catch(InterruptedException e){
                JOptionPane.showMessageDialog(this, 
                    "Error occured:" + "\n" + e.getMessage(),
                    "ErrorMsg", JOptionPane.ERROR_MESSAGE);
            }
            isThreadRunning = false;
        }
        txtAreaInternet.setText(textAreaDefaultValue);
        btnStart.setText("Start");
        btnStart.setEnabled(true);
        btnStart.setBackground(new java.awt.Color(20, 180, 125));
        btnReset.setEnabled(false);
        btnPause.setEnabled(false);
        ((WizardFrame) parentSBMLFrame).getMergeSbml().setLastInternetFetchedIndex(0);
        pbarInternet.setValue(0);
    }//GEN-LAST:event_btnResetActionPerformed

    private void pbarInternetStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pbarInternetStateChanged
        JProgressBar pbar = (JProgressBar) evt.getSource();
        if (pbar.getValue() == pbar.getMaximum())
            isThreadRunning = false;
    }//GEN-LAST:event_pbarInternetStateChanged

    @Override
    public Boolean needReloadNextStep() {
        return reloadedByPreviousStep;
    }

    @Override
    public Boolean needReloadPrevStep() {
        return false;
    }

    @Override
    public Boolean isReadyGoToNextStep() {
        boolean doAutomatically = ((WizardFrame)parentSBMLFrame).doAutomatically;
        if (doAutomatically && internetThread == null)
            return false;
        
        if (doAutomatically && internetThread.isAlive())
            return false;
        
        return !isThreadRunning;
    }

    @Override
    public Boolean isReadyGoToPrevStep() {
        return !isThreadRunning;
    }

    @Override
    public int beforeGoToNextStep() {
        reloadedByPreviousStep = false;
        return 0;
    }

    @Override
    public int reload() {
        txtAreaInternet.setText(textAreaDefaultValue);
        btnStart.setEnabled(true);
        btnStart.setText("Start");
        btnPause.setEnabled(false);
        btnReset.setEnabled(false);
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        parent.getMergeSbml().setLastInternetFetchedIndex(0);
        pbarInternet.setValue(0);
        reloadedByPreviousStep = true;
        isThreadRunning = false;
        this.revalidate();
        return 0;
    }
    
    @Override
    String getHelp(){
        return "In this step, iMet can Enrich the metadata of both models by using KEGG API. (https://www.kegg.jp/kegg/rest/keggapi.html)\n\n" 
                + "  - By clicking \"Start\", \"Pause\" and \"Reset\" you can control the searching process."
                + " It searches the KEGG database by using KEGG API.\n"
                + "     iMet searches the KEGG database for each specific species of each SBML model and adds"
                + " it's metadata (like Chebi-ID, Kegg-ID, Formula,\n"
                + "     and Pubchem-ID) to the Model if it is found in KEGG. These metadata would help the merge algorithm to find similar species accurately.\n" 
                + "  - The result of the search for each species is also reported.\n" 
                + "  - This step can be skipped by clicking \"Next\" button.";
    }

    @Override
    void doAutomatically() {
        final WizardFrame parent = ((WizardFrame)parentSBMLFrame);
        btnStart.doClick();
        try {
            synchronized(parent.getDoAutomaticThread()){
                parent.getDoAutomaticThread().wait();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ChooseFilePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        while ( !isReadyGoToNextStep() ){
        }
        parent.getBtnNext().doClick(ButtonEditor.presstime);
    }
    
    private Boolean isThreadRunning;
    private Thread internetThread;
    Boolean reloadedByPreviousStep;
    String textAreaDefaultValue;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar pbarInternet;
    private javax.swing.JTextArea txtAreaInternet;
    // End of variables declaration//GEN-END:variables
}
