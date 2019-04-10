package com.sbmlmerge.main;

import com.sbmlmerge.core.*;
import com.sbmlmerge.gui.WizardFrame;
import javax.swing.UIManager;

public class MainClass {

    public static void main(String args[]) throws Exception {
        javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        String runningDirPath = MainClass.class.getProtectionDomain().getCodeSource().getLocation().toString();
        String logFilePath = "log.txt";
        final SBLogger logger;
        logger = new SBLogger(logFilePath);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                WizardFrame mainFrame = new WizardFrame(logger, null);
                mainFrame.setVisible(true);
            }
        });
    }
}
