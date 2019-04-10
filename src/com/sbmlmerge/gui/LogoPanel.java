package com.sbmlmerge.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class LogoPanel extends SBMLPanel {
    String imagePath;
    double scale;
    Image scaledImage;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }
    
    public LogoPanel(SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        initComponents();
    }

    public LogoPanel(String imagePath, double scale, SBMLPanel parentSBMLJPanel, SBMLFrame parentSBMLFrame) {
        super(parentSBMLJPanel, parentSBMLFrame);
        this.imagePath = imagePath;
        this.scale = scale;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    int reload() {
        try {
            URL imageURL = getClass().getResource(imagePath);
            BufferedImage image;
            image = ImageIO.read(imageURL);
            int newWidth = (int)(image.getWidth() * scale);
            int newHeight = (int)(image.getHeight() * scale);
            this.setSize(new Dimension(newWidth, newHeight));
            this.setPreferredSize(new Dimension(newWidth, newHeight));
            this.setMinimumSize(new Dimension(newWidth, newHeight));
            this.setMaximumSize(new Dimension(newWidth, newHeight));
            scaledImage = image.getScaledInstance(
                                    newWidth, 
                                    newHeight, 
                                    BufferedImage.SCALE_SMOOTH);
            repaint();
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
            return 1;
        }
        return 0;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);        
        g.drawImage(scaledImage, 0, 0, null);            
    }
}
