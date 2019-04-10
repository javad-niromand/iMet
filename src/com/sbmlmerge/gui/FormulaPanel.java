package com.sbmlmerge.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Species;

public class FormulaPanel extends SBMLPanel {

    public FormulaPanel(Reaction re, SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        reaction = re;
        initComponents();
        initMyComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(10, 30));
        setPreferredSize(new java.awt.Dimension(10, 30));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 1, 1));
    }// </editor-fold>//GEN-END:initComponents

    private void initMyComponents() {
        this.setName("formulaPanel");
        int leftItems = 0;
        int rightItems = 0;
        int middleItems = 0;
        int allItems = 0;
        leftItems += 3 * reaction.getListOfReactants().size();
        if (leftItems > 0) {
            leftItems--; // the last metabolite does not need "+" sign
        }
        rightItems += 3 * reaction.getListOfProducts().size();
        if (rightItems > 0) {
            rightItems--; // the last metabolite does not need "+" sign
        }        // if reaction has just one side, we don't need direction sign
        if (leftItems > 0 && rightItems > 0) {
            middleItems += 1; //for reaction direction sign
        }
        allItems = leftItems + middleItems + rightItems;
        labelList = new JLabel[allItems];
        int j = 0;

        for (int i = 0; i < reaction.getListOfReactants().size(); i++) {
            labelList[j] = new JLabel(((Double) reaction.getReactant(i).getStoichiometry()).toString());
            this.add(labelList[j++]);

            labelList[j] = new JLabel(reaction.getReactant(i).getSpeciesInstance().getName());
            Border paddingBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3);
            Border border = BorderFactory.createLineBorder(Color.BLUE);
            labelList[j].setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));
            labelList[j].setForeground(Color.blue);
            labelList[j].setBackground(Color.lightGray);
            labelList[j].setOpaque(true);
            labelList[j].setToolTipText(reaction.getReactant(i).getSpecies());
            labelList[j].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    labelListMouseClicked(evt);
                }
            });
            this.add(labelList[j++]);

            if ((i + 1) < reaction.getListOfReactants().size()) {
                labelList[j] = new JLabel(" + ");
                this.add(labelList[j++]);
            }
        }
        if (middleItems > 0) {
            String direction = " <=> ";
            if (reaction.isSetReversible()) {
                if (!reaction.getReversible()) {
                    direction = " => ";
                }
            }
            labelList[j] = new JLabel(direction);
            this.add(labelList[j]);
            j++;
        }
        for (int i = 0; i < reaction.getListOfProducts().size(); i++) {
            labelList[j] = new JLabel(((Double) reaction.getProduct(i).getStoichiometry()).toString());
            this.add(labelList[j++]);

            labelList[j] = new JLabel(reaction.getProduct(i).getSpeciesInstance().getName());
            Border paddingBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3);
            Border border = BorderFactory.createLineBorder(Color.BLUE);
            labelList[j].setBorder(BorderFactory.createCompoundBorder(border, paddingBorder));
            labelList[j].setForeground(Color.blue);
            labelList[j].setBackground(Color.lightGray);
            labelList[j].setOpaque(true);
            labelList[j].setToolTipText(reaction.getProduct(i).getSpecies());
            labelList[j].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    labelListMouseClicked(evt);
                }
            });
            this.add(labelList[j++]);

            if ((i + 1) < reaction.getListOfProducts().size()) {
                labelList[j] = new JLabel(" + ");
                this.add(labelList[j++]);
            }
        }
        this.revalidate();
    }

    private void labelListMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() >= 2) {
            try {
                Component speciesPanel;
                String id = ((JLabel) evt.getSource()).getToolTipText();
                Species s = reaction.getModel().getSpecies(id);
                if (s != null) {
                    ModelPanel modelPanel = (ModelPanel) findModelPanel(parentSBMLJPanel);
                    if (modelPanel != null) {
                        JPanel sPanel = modelPanel.getSpeciesPanel();
                        JTabbedPane jTabbedPane = modelPanel.getjTabbedPanelModel();
                        if (sPanel != null) {
                            for (Component cmp : sPanel.getComponents()) {
                                if (cmp.equals(modelPanel.getSpeciesComponent())) {
                                    sPanel.remove(cmp);
                                }
                            }
                            jTabbedPane.setSelectedComponent(sPanel);
                            sPanel.add(speciesPanel = new SpeciesPanel(s, modelPanel, parentSBMLFrame));
                            modelPanel.setSpeciesComponent(speciesPanel);
                            sPanel.revalidate();
                        }
                    } else {
                        if (parentSBMLFrame != null) {
                            parentSBMLFrame.setEnabled(false);
                            SBMLFrame spFrame = new SBMLFrame(parentSBMLFrame);
                            spFrame.setLayout(new BorderLayout());
                            spFrame.add(new SpeciesPanel(s, this, spFrame));
                            spFrame.setMinimumSize(new Dimension(550, 450));
                            spFrame.setMinimumSize(new Dimension(550, 450));
                            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                            spFrame.setLocation(dim.width / 2 - spFrame.getSize().width / 2,
                                    dim.height / 2 - spFrame.getSize().height / 2
                            );
                            spFrame.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosing(WindowEvent e) {
                                    ((SBMLFrame) e.getSource()).getParentSBMLFrame().setEnabled(true);
                                }
                            });
                            spFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            spFrame.setVisible(true);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(ReactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private SBMLPanel findModelPanel(SBMLPanel parent) throws NullPointerException {
        if (parent instanceof ModelPanel) {
            return parent;
        } else {
            if (parent.getParentSBMLJPanel() == null) {
                return null;
            }
            return findModelPanel(parent.getParentSBMLJPanel());
        }
    }

    private JLabel[] labelList;
    private Reaction reaction;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
