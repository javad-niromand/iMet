package com.sbmlmerge.gui;

import com.sbmlmerge.core.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.sbml.jsbml.SBMLDocument;

public class WizardFrame extends SBMLFrame {

    public WizardFrame(SBLogger log, SBMLFrame parentSBMLFrame) {
        super(parentSBMLFrame);
        logger = log;
        stepList = new Step[9];
        stepList[0] = new Step(1, "Start", new StartPanel(null, this));
        stepList[1] = new Step(2, "Input structure", new ChooseFilePanel(null, this));
        stepList[2] = new Step(3, "Curating and enhancing", new KeggPanel(null, this));
        stepList[3] = new Step(4, "Similar metabolite detection", new FindSimilarSpecies(null, this));
        stepList[4] = new Step(5, "Editing metabolite similarities", new EditSpeciesSimilarity(null, this));
        stepList[5] = new Step(6, "Similar reaction detection", new FindSimilarReactions(null, this));
        stepList[6] = new Step(7, "Editing reaction similarities", new EditReactionSimilarity(null, this));
        stepList[7] = new Step(8, "Integrating the input models", new CreateMergedModel(null, this));
        stepList[8] = new Step(9, "Finish", new FinishPanel(null, this));
        doAutomatically = false;
        stepNum = stepList.length;
        stepListLabel = new JLabel[stepNum];
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(
                dim.width / 2 - this.getSize().width / 2,
                dim.height / 2 - this.getSize().height / 2
        );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentStep = -1; // before start
        sBMLDocuments = new ArrayList<>();
        mergeSbml = new MergeSBML(this,log);
        changeStep(0);
    }

    private int changeStep(int newStep) {
        Boolean initializing = false;
        if (currentStep == -1) {
            initializing = true;
        }

        if (newStep == currentStep) {
            return 0;
        }

        if (newStep >= stepNum || newStep < 0) {
            return 0;
        }

        if (!initializing) {
            if (newStep > currentStep && !stepList[currentStep].getPanel().isReadyGoToNextStep()
                    || newStep < currentStep && !stepList[currentStep].getPanel().isReadyGoToPrevStep()) {
                return currentStep;
            }
        }

        if (!initializing) {
            toggleBold(stepListLabel[currentStep]);
        }
        toggleBold(stepListLabel[newStep]);
        if (!initializing) {
            if ((newStep > currentStep && stepList[currentStep].getPanel().needReloadNextStep())
                    || (newStep < currentStep && stepList[currentStep].getPanel().needReloadPrevStep())) {
                stepList[currentStep].getPanel().beforeGoToNextStep();
                stepList[newStep].getPanel().reload();
            }
        } else { // if initializing
            stepList[newStep].getPanel().reload();
        }

        changeNoberryComponent(stepList[newStep].getPanel());

        if (newStep == stepNum - 1) {
            btnNext.setEnabled(false);
            btnFinish.setEnabled(true);
        } else {
            btnNext.setEnabled(true);
            btnFinish.setEnabled(false);
        }

        if (newStep == 0) {
            btnBack.setEnabled(false);
        } else {
            btnBack.setEnabled(true);
        }

        lblRightTitle.setText(stepList[newStep].getName());
        currentStep = newStep;

        if (doAutomatically) {
            setDoAutomaticThread(new Thread(new Runnable() {
                
                @Override
                public void run() {
                    stepList[currentStep].getPanel().doAutomatically();
                }
            }));
            getDoAutomaticThread().start();
        }
        return currentStep;
    }

    private void changeNoberryComponent(JPanel panel) {
        for (Component cmp : pnlNoberry.getComponents()) {
            if (cmp.equals(cmpNoberry)) {
                pnlNoberry.remove(cmp);
            }
        }
        pnlNoberry.add(panel);
        cmpNoberry = panel;
        panel.revalidate();
        panel.repaint();
    }

    private void toggleBold(Component comp) {
        Font f = comp.getFont();
        comp.setFont(f.deriveFont(f.getStyle() ^ Font.BOLD));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btmLine = new javax.swing.JSeparator();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        Help = new javax.swing.JButton();
        pnlLeft = new javax.swing.JPanel();
        lblLeftTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlStepList = new javax.swing.JPanel();
        pnlRight = new com.sbmlmerge.gui.SBMLPanel(null,this);
        lblRightTitle = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnlNoberry = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iMet");
        setMinimumSize(new java.awt.Dimension(1024, 678));
        setPreferredSize(new java.awt.Dimension(1024, 678));

        btnBack.setText("< Back");
        btnBack.setPreferredSize(new java.awt.Dimension(67, 23));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setText("Next >");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnFinish.setText("Finish");
        btnFinish.setPreferredSize(new java.awt.Dimension(67, 23));
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.setPreferredSize(new java.awt.Dimension(67, 23));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        Help.setText("Help");
        Help.setPreferredSize(new java.awt.Dimension(67, 23));
        Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpActionPerformed(evt);
            }
        });

        pnlLeft.setBackground(new java.awt.Color(255, 255, 255));

        lblLeftTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblLeftTitle.setText("Merge steps");

        pnlStepList.setBackground(new java.awt.Color(255, 255, 255));
        pnlStepList.setLayout(new javax.swing.BoxLayout(pnlStepList, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLeftTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLeftLayout.createSequentialGroup()
                        .addGroup(pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlStepList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addContainerGap())))
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLeftTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlStepList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        for (int i=0;i<stepNum;i++){
            stepListLabel[i] = new JLabel(stepList[i].getName());
            stepListLabel[i].setBorder(new EmptyBorder(5,3,5,3));
            pnlStepList.add(stepListLabel[i],BorderLayout.PAGE_END);
        }
        pnlStepList.revalidate();

        lblRightTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblRightTitle.setText("Title");

        pnlNoberry.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addGroup(pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(pnlRightLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblRightTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlNoberry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRightLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblRightTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlNoberry, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btmLine)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(194, 202, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext)
                .addGap(31, 31, 31)
                .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Help, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btmLine, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext)
                    .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Help, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        changeStep(currentStep + 1);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        doAutomatically = false;
        getHelp().setEnabled(true);
        changeStep(currentStep - 1);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnFinishActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpActionPerformed
        JOptionPane.showMessageDialog(
                this,
                stepList[currentStep].getPanel().getHelp(),
                "Help for " + stepList[currentStep].getName(),
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_HelpActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Help;
    private javax.swing.JSeparator btmLine;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnNext;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblLeftTitle;
    private javax.swing.JLabel lblRightTitle;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlNoberry;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlStepList;
    // End of variables declaration//GEN-END:variables
    final int stepNum;
    int currentStep;
    final Step[] stepList;
    final JLabel[] stepListLabel;
    //private final SBMLPanel[] stepListPanel;
    Component cmpNoberry;
    private Thread doAutomaticThread;
    ArrayList<SBMLDocument> sBMLDocuments;
    MergeSBML mergeSbml;
    final SBLogger logger;
    SBMLDocument mergedSBMLDoc;
    boolean doAutomatically;
    private long numOfSimilarSpecies;
    private long numOfSimilarReactions;

    public SBMLDocument getMergedSBMLDoc() {
        return mergedSBMLDoc;
    }

    public void setMergedSBMLDoc(SBMLDocument mergedSBMLDoc) {
        this.mergedSBMLDoc = mergedSBMLDoc;
    }

    public SBLogger getLogger() {
        return logger;
    }

    public MergeSBML getMergeSbml() {
        return mergeSbml;
    }

    public void setMergeSbml(MergeSBML mergeSbml) {
        this.mergeSbml = mergeSbml;
    }

    public JButton getHelp() {
        return Help;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnFinish() {
        return btnFinish;
    }

    public JButton getBtnNext() {
        return btnNext;
    }

    public Thread getDoAutomaticThread() {
        return doAutomaticThread;
    }

    public void setDoAutomaticThread(Thread doAutomaticThread) {
        this.doAutomaticThread = doAutomaticThread;
    }

    /**
     * @return the numOfSimilarSpecies
     */
    public long getNumOfSimilarSpecies() {
        return numOfSimilarSpecies;
    }

    /**
     * @param numOfSimilarSpecies the numOfSimilarSpecies to set
     */
    public void setNumOfSimilarSpecies(long numOfSimilarSpecies) {
        this.numOfSimilarSpecies = numOfSimilarSpecies;
    }

    /**
     * @return the numOfSimilarReactions
     */
    public long getNumOfSimilarReactions() {
        return numOfSimilarReactions;
    }

    /**
     * @param numOfSimilarReactions the numOfSimilarReactions to set
     */
    public void setNumOfSimilarReactions(long numOfSimilarReactions) {
        this.numOfSimilarReactions = numOfSimilarReactions;
    }
}
