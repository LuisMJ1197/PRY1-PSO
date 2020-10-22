/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.MonitorFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import machine.Kernel;
import machine.memory.IAddress;
import os.OperatingSystem;
import os.Program;
import util.FileBrowser;

/**
 *
 * @author Luism
 */
public class MainFrameController implements ActionListener {
    private static final Color PROCESSLIST_COLOR = new Color(170, 204, 0);
    
    private MonitorFrame view;
    private final OperatingSystem os;
    private final MemoryPanelController mainMemoryPanelController;
    private final MemoryPanelController diskMemoryPanelController;
    private final CPUController cpuCore1Controller;
    private final CPUController cpuCore2Controller;
    private ProcessPanelController processPanelController;
    private KeyboardController keyboardController;
    private DisplayController displayController;
    
    public MainFrameController() {
        os = OperatingSystem.getInstance();
        this.startView();
        this.mainMemoryPanelController = new MemoryPanelController(Kernel.getInstance().getMainMemory(), this.view.mainPanel.mainMemoryPanel);
        this.diskMemoryPanelController = new MemoryPanelController(Kernel.getInstance().getDiskMemory(), this.view.mainPanel.virtualMemoryPanel);
        this.cpuCore1Controller = new CPUController(this.view.mainPanel.cPUCorePanel1, Kernel.getInstance().getProcessor().getCore1());
        this.cpuCore2Controller = new CPUController(this.view.mainPanel.cPUCorePanel2, Kernel.getInstance().getProcessor().getCore2());
        this.keyboardController = new KeyboardController(this.view.mainPanel.keyboardTF, this.view.mainPanel.keyboardProcessID);
        this.displayController = new DisplayController(this.view.mainPanel.displayTA, this.view.mainPanel.displayProcessID);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "openFiles":
                this.openFiles();
                break;
            case "next":
                this.next();
                break;
            default: 
                break;
        }
    }
    
    private void openFiles() {
        FileBrowser fileBrowser = new FileBrowser();
        File[] selectedFiles = fileBrowser.browseMultiple(view);
        if (selectedFiles == null) return;
        ArrayList<Program> programList = new ArrayList<>();
        for (File file: selectedFiles) {
            try {
                Program program = new Program(file.getName(), fileBrowser.extractFileInfo(file).split("\\r?\\n")); 
                programList.add(program);
            } catch (IOException ex) {
               Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String errors = this.os.loadPrograms(programList);
        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(this.view, errors);
        }
        if (Kernel.getInstance().getProcessor().getProcessList().isEmpty()) {
            return;
        }
        Kernel.getInstance().getProcessor().start();
        processPanelController = new ProcessPanelController(Kernel.getInstance().getProcessor().getProcessList(), this.view.mainPanel.processListPanel);
        processPanelController.init();
        this.mainMemoryPanelController.update();
        this.diskMemoryPanelController.update();
        this.mainMemoryPanelController.paintBorder(MainFrameController.PROCESSLIST_COLOR, 0, Kernel.getInstance().getProcessor().getProcessList().size());
        this.paintMemories();
        this.view.mainPanel.nextButton.setEnabled(true);
        this.view.mainPanel.openFilesButton.setEnabled(false);
        
    }
    
    private void paintMemories() {
        this.processPanelController.getProcessDecorators().forEach((proc) -> {
            int initPos = proc.getPcb().getPid().getAddress().getOffset();
            int endPos = Integer.parseInt(proc.getPcb().getBase().getValue().substring(proc.getPcb().getBase().getValue().length() - 3))
                    + Integer.parseInt(proc.getPcb().getLimit().getValue()) - 1;
            MemoryPanelController memory = this.diskMemoryPanelController;
            if (proc.getPcb().getPid().getAddress().getMemoryKind() == IAddress.MAIN_MEMORY) {
                memory = this.mainMemoryPanelController;
            }
            memory.paintBorder(proc.getColor(), initPos, endPos);
        });
    }
    
    /**
     * Starts the view frame.
     */
    private void startView() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MonitorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MonitorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MonitorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MonitorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        this.view = new MonitorFrame();
        MonitorFrame view = this.view;
        this.view.mainPanel.openFilesButton.addActionListener(this);
        this.view.mainPanel.nextButton.addActionListener(this);
        //this.view.nextButton.addActionListener(this);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                view.setVisible(true);
            }
        });
    }

    private void next() {
        if (Kernel.getInstance().getProcessor().nextCycle()) {
            displaySummary();
        }
        this.processPanelController.update();
        this.mainMemoryPanelController.update();
        this.diskMemoryPanelController.update();
        this.cpuCore1Controller.update();
        this.cpuCore2Controller.update();
        this.view.mainPanel.processorTimeLabel.setText(Integer.toString(Kernel.getInstance().getProcessor().getProcessTime()));
    }
    
    private void displaySummary() {
        
    }
}
