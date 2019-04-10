package com.sbmlmerge.gui;

import com.sbmlmerge.core.MergeSBML;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.XMLStreamException;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBMLReader;

public class ChooseFilePanel extends SBMLPanel {

    public ChooseFilePanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
        reloadedByPreviousStep = false;
        MaximumFiles = 2;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        inputFileTable = new CustomTable();
        lblStatus = new javax.swing.JLabel();
        jProgressBar = new javax.swing.JProgressBar();

        setMinimumSize(new java.awt.Dimension(300, 200));

        jLabel1.setText("SBML Files to merge:");

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnClear.setText("Clear List");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        dataModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return
                (getColumnName(column).equalsIgnoreCase("Edit") ||
                    getColumnName(column).equalsIgnoreCase("Remove"));
            }
        };
        inputFileTable.setModel(dataModel);
        inputFileTable.setIntercellSpacing(new java.awt.Dimension(0, 3));
        inputFileTable.setRowHeight(30);
        inputFileTable.setSelectionForeground(new java.awt.Color(0, 0, 0));
        inputFileTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        inputFileTable.setShowHorizontalLines(false);
        inputFileTable.setShowVerticalLines(false);
        inputFileTable.setTableHeader(null);
        dataModel.setDataVector(
            new Object[][] {  },new Object[] { "Input Model", "Edit" ,"Remove" });
        inputFileTable.getColumn("Input Model").setCellRenderer(new TextCellRenderer());
        inputFileTable.getColumn("Edit").setCellRenderer(new ButtonRenderer());
        inputFileTable.getColumn("Edit").setCellEditor(
            new ButtonEditor(new JCheckBox()));
        inputFileTable.getColumn("Edit").setMaxWidth(45);
        inputFileTable.getColumn("Remove").setCellRenderer(new ButtonRenderer());
        inputFileTable.getColumn("Remove").setCellEditor(
            new ButtonEditor(new JCheckBox()));
        inputFileTable.getColumn("Remove").setMaxWidth(85);
        inputFileTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputFileTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(inputFileTable);

        lblStatus.setForeground(java.awt.SystemColor.textHighlight);
        lblStatus.setText("status");
        lblStatus.setText("");

        jProgressBar.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblStatus))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClear)
                            .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblStatus))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        String runningPath;
        String sampleDirectoryName = "sample";
        try {
            runningPath = ChooseFilePanel.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if (runningPath.endsWith(".jar")) {
                runningPath = runningPath.substring(0, runningPath.lastIndexOf("/") + 1);
            }
            if ((new File(runningPath + sampleDirectoryName)).exists()) {
                runningPath += sampleDirectoryName;
            }
            fileChooser.setCurrentDirectory(new File(runningPath));
        } catch (Exception e) {
        }
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
            
            NotificationThread notifiedRunnable = new NotificationThread() {
                @Override
                public void doWork() {
                    jProgressBar.setValue(0);
                    int progressBarHops = 50;
                    Random rand = new Random();
                    int middle = 5;
                    int sleepTime = 25;
                    File[] files = fileChooser.getSelectedFiles();
                    jProgressBar.setMaximum(progressBarHops * files.length);
                    for (File file : files) {
                        middle = rand.nextInt(progressBarHops);
                        for (int i = 0; i < progressBarHops - middle; i++) {
                            jProgressBar.setValue(jProgressBar.getValue() + 1);
                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ChooseFilePanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        int ret = addToFileListModel(file);
                        if (ret == 0) {
                            for (int i = 0; i < middle; i++) {
                                jProgressBar.setValue(jProgressBar.getValue() + 1);
                                try {
                                    Thread.sleep(sleepTime);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ChooseFilePanel.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                    lblStatus.setText(" ");
                }
            };
            final WizardFrame parent = (WizardFrame) parentSBMLFrame;
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
            addingThread = new Thread(notifiedRunnable);
            addingThread.start();
            parent.getBtnNext().setEnabled(false);
            parent.getBtnBack().setEnabled(false);
            lblStatus.setText("Loading files, Please wait...");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        WizardFrame parent = (WizardFrame) this.parentSBMLFrame;
        for (int row = dataModel.getRowCount() - 1; row >= 0; row--) {
            dataModel.removeRow(row);
            parent.sBMLDocuments.remove(row);
        }
        jProgressBar.setValue(0);
    }//GEN-LAST:event_btnClearActionPerformed

    private void inputFileTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputFileTableMouseClicked
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        int col = table.columnAtPoint(evt.getPoint());
        WizardFrame parent = (WizardFrame) this.parentSBMLFrame;
        if (table.getColumnName(col).equalsIgnoreCase("Edit")) {
            try {
                final Model model = parent.sBMLDocuments.get(row).getModel();
                parent.setEnabled(false);
                SBMLFrame modelFrame = new SBMLFrame(parentSBMLFrame);
                File SbmlFile = (File) (dataModel.getValueAt(row, 0));
                modelFrame.setTitle(SbmlFile.getName());
                Component comp;
                modelFrame.setLayout(new BorderLayout());
                modelFrame.add(comp = new ModelPanel(null, modelFrame, model));
                modelFrame.setPreferredSize(comp.getPreferredSize());
                modelFrame.setMinimumSize(comp.getMinimumSize());
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                modelFrame.setLocation(dim.width / 2 - modelFrame.getSize().width / 2,
                        dim.height / 2 - modelFrame.getSize().height / 2
                );
                modelFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ((SBMLFrame) e.getSource()).getParentSBMLFrame().setEnabled(true);
                    }
                });
                modelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                modelFrame.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error occured while loading Model\n" + ex.getMessage(),
                        "ErrorMsg", JOptionPane.ERROR_MESSAGE);
                this.setEnabled(true);
            }
        } else if (table.getColumnName(col).equalsIgnoreCase("Remove")) {
            dataModel.removeRow(row);
            parent.sBMLDocuments.remove(row);
        }
    }//GEN-LAST:event_inputFileTableMouseClicked

    private int addToFileListModel(Object obj) {
        for (int row = 0; row < dataModel.getRowCount(); row++) {
            if (((File) obj).getAbsoluteFile().equals(
                    (File) (dataModel.getValueAt(row, 0)))) {
                JOptionPane.showMessageDialog(
                        this, "This file is already added to list.", "ErrorMsg",
                        JOptionPane.ERROR_MESSAGE);
                return 1;
            }
        }

        if (dataModel.getRowCount() >= MaximumFiles) {
            JOptionPane.showMessageDialog(
                    this, "You can not add more than 2 files.", "ErrorMsg",
                    JOptionPane.ERROR_MESSAGE);
            return 1;
        }

        try {
            SBMLDocument document = SBMLReader.read(((File) obj).getAbsoluteFile());
            dataModel.addRow(new Object[]{obj, "Edit", "Remove"});

            WizardFrame parent = (WizardFrame) this.parentSBMLFrame;
            parent.sBMLDocuments.add(document);

            for (SBMLDocument doc : parent.sBMLDocuments) {
                MergeSBML.clearAllSimilarities(doc);
            }

            filesChanged = true;
            return 0;
        } catch (XMLStreamException | IOException ex) {
            JOptionPane.showMessageDialog(
                    this, "Error occured while loading file" + "\n"
                    + ex.getMessage(), "ErrorMsg",
                    JOptionPane.ERROR_MESSAGE);
            return 1;
        }
    }

    @Override
    Boolean needReloadNextStep() {
        return reloadedByPreviousStep || filesChanged;
    }

    @Override
    Boolean needReloadPrevStep() {
        return true;
    }

    @Override
    Boolean isReadyGoToNextStep() {
        if (addingThread == null)
            return false;
        
        if (addingThread.isAlive())
            return false;
        
        if (dataModel.getRowCount() < 2)
            return false;
        
        WizardFrame parent = (WizardFrame) this.parentSBMLFrame;
        if (!parent.mergeSbml.isEqualVersions(parent.sBMLDocuments)) {
                JOptionPane.showMessageDialog(
                        this.parentSBMLFrame,
                        "SBML files has different levels or versions",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
        }
        return true;
    }

    @Override
    Boolean isReadyGoToPrevStep() {
        return true;
    }

    @Override
    int beforeGoToNextStep() {
        WizardFrame parent = (WizardFrame) this.parentSBMLFrame;
        reloadedByPreviousStep = false;
        filesChanged = false;
        return parent.mergeSbml.standarding(parent.sBMLDocuments);
    }

    @Override
    int reload() {
        WizardFrame parent = (WizardFrame) this.parentSBMLFrame;
        for (int row = dataModel.getRowCount() - 1; row >= 0; row--) {
            dataModel.removeRow(row);
            parent.sBMLDocuments.remove(row);
        }
        filesChanged = false;
        reloadedByPreviousStep = true;
        this.revalidate();
        return 0;
    }

    @Override
    void doAutomatically(){
        final WizardFrame parent = ((WizardFrame)parentSBMLFrame);
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
    
    @Override
    String getHelp() {
        return "In this step, you can add two SBML files, which you want to merge them by iMet.\n\n"
                + "  - By clicking the \"Add\" button, you can add one (or two) SBML model(s).\n"
                + "  - By using the \"Clear List\" button, all imported models will be deleted from the List.\n"
                + "  - In each line of the list, By clicking \"Edit\" button, you can edit the reactions and species of each model.\n"
                + "  - In each line of the list, By clicking \"Remove\" button, you can remove the added model from the list.";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JTable inputFileTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
    DefaultTableModel dataModel;
    int MaximumFiles;
    Boolean filesChanged;
    Boolean reloadedByPreviousStep;
    private Thread addingThread;
}
