package com.sbmlmerge.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Species;

public class ShowMatchPanel extends SBMLPanel {

    public ShowMatchPanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new CustomTable();
        btnEdit = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(309, 309));
        setPreferredSize(new java.awt.Dimension(309, 309));

        lblName.setForeground(new java.awt.Color(0, 102, 255));
        lblName.setText("Object Name");

        jLabel2.setText("is similar to below items:");

        table.setModel(dataModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;//getColumnName(column).equalsIgnoreCase("Edit");
            }
        });
        table.setMaximumSize(new java.awt.Dimension(30, 0));
        table.setRowHeight(25);
        table.setRowMargin(2);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        dataModel.setDataVector(new Object[][] {  },
            new Object[] { "ID","Name","Model","Similarity Score" ,"Status" });
        table.setDefaultRenderer(Object.class,new com.sbmlmerge.gui.TextCellRenderer());
        table.getColumn("ID").setMaxWidth(60);
        table.getColumn("Model").setMaxWidth(70);
        table.getColumn("Similarity Score").setMaxWidth(90);
        jScrollPane1.setViewportView(table);

        btnEdit.setBackground(java.awt.SystemColor.textHighlight);
        btnEdit.setContentAreaFilled(false);
        btnEdit.setOpaque(true);
        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit / Match manually");
        btnEdit.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153)));
        btnEdit.setFocusPainted(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (selectedObj == null)
            return;
        
        WizardFrame parent = (WizardFrame) parentSBMLFrame;
        parent.setEnabled(false);
        SBMLFrame editMatchFrame = new SBMLFrame(parentSBMLFrame);
        Component comp;
        editMatchFrame.setLayout(new BorderLayout());
        editMatchFrame.add(comp = new EditMatchPanel(editMatchFrame,this.parentSBMLJPanel, parent));
        editMatchFrame.setPreferredSize(comp.getPreferredSize());
        editMatchFrame.setMinimumSize(comp.getMinimumSize());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        editMatchFrame.setLocation(
                dim.width / 2 - editMatchFrame.getSize().width / 2,
                dim.height / 2 - editMatchFrame.getSize().height / 2
        );
        editMatchFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ((SBMLFrame) e.getSource()).getParentSBMLFrame().setEnabled(true);
            }
        });
        editMatchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editMatchFrame.setVisible(true);
        if (selectedObj != null)
            ((EditMatchPanel)comp).reload(selectedObj);
    }//GEN-LAST:event_btnEditActionPerformed

    public void reload(Object slctObj, Object[] objList, int[] scoreList) {
        removeTableContent();
        this.selectedObj = slctObj;
        this.objects = objList;
        this.scores = scoreList;
        
        if (selectedObj != null) {
            lblName.setText(selectedObj.toString());
        }
        if (objects == null || scores == null) {
            return;
        }
        for (int i = 0; i < objects.length; i++) {
            String selected;
            if (i == 0) {
                selected = "Selected as Most Similar";
            } else {
                selected = "";
            }

            if (objects[i] instanceof Species) {
                Species sp = (Species) objects[i];
                dataModel.addRow(new Object[]{sp.getId(), sp, sp.getModel(), scores[i], selected});
            }

            if (objects[i] instanceof Reaction) {
                Reaction re = (Reaction) objects[i];
                dataModel.addRow(new Object[]{re.getId(), re, re.getModel(), scores[i], selected});
            }

        }
    }

    private void removeTableContent() {
        try {
            for (int i = table.getModel().getRowCount() - 1; i >= 0; i--) {
                ((DefaultTableModel) table.getModel()).removeRow(i);
            }
            table.revalidate();
        } catch (NullPointerException e) {
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblName;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel dataModel;
    private Object selectedObj;
    private Object[] objects;
    private int[] scores;
}
