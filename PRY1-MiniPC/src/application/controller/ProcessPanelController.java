/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.ProcessDecorator;
import application.view.ProcessPanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import os.process.Process;

/**
 *
 * @author Luism
 */
public class ProcessPanelController {
    public static Color[] color = {
        new Color(26, 188, 156),
        new Color(46, 236, 113),
        new Color(52, 152, 219),
        new Color(52, 73, 94),
        new Color(22, 160, 133),
        new Color(39, 174, 96),
        new Color(41, 128, 185),
        new Color(142, 68, 173),
        new Color(44, 62, 80),
        new Color(241, 196, 15),
        new Color(230, 126, 34),
        new Color(231, 76, 60),
        new Color(2, 128, 144),
        new Color(149, 165, 166),
        new Color(243, 156, 18),
        new Color(211, 84, 0),
        new Color(192, 57, 43),
        new Color(189, 195, 199),
        new Color(127, 140, 141)
    };
    
    private ArrayList<ProcessDecorator> processList;
    private JPanel processListPanel;

    public ProcessPanelController(ArrayList<Process> processList, JPanel processListPanel) {
        this.processListPanel = processListPanel;
        this.processList = new ArrayList<>();
        processList.forEach((process) -> {
            this.processList.add(new ProcessDecorator(process));
        });
        this.setGridLayoutRowsCount(this.processList.size());
        
    }
    
    public ArrayList<ProcessDecorator> getProcessDecorators() {
        return this.processList;
    }

    private void setGridLayoutRowsCount(int count) {
        this.processListPanel.setLayout(new java.awt.GridLayout(10 + count, 0));
    }
    
    public void init() {
        //this.shuffleColor();
        if (this.processList != null) {
            int i = 0;
            for (ProcessDecorator proc: this.processList) {
                ProcessPanel procPanel = new ProcessPanel();
                while (i >= ProcessPanelController.color.length) {
                   i = i - ProcessPanelController.color.length;
                }
                proc.setColor(ProcessPanelController.color[i]);
                procPanel.setProcessColor(proc.getColor());
                procPanel.nameLabel.setText(proc.getName());
                procPanel.pidLabel.setText(Integer.toString(proc.getPid()));
                this.processListPanel.add(procPanel);
                i++;
            }
        }
        this.update();
    }
    
    public void update() {
        for (int i = 0; i < this.processList.size(); i++) {
            ProcessPanel procPanel = (ProcessPanel) this.processListPanel.getComponent(i);
            ProcessDecorator proc = this.processList.get(i);
            procPanel.statusLabel.setText(proc.getPcb().getStatus().getValue());
            procPanel.pcLabel.setText(proc.getPcb().getPc().getValue());
            procPanel.acLabel.setText(proc.getPcb().getAc().getValue());
            procPanel.axLabel.setText(proc.getPcb().getAx().getValue());
            procPanel.bxLabel.setText(proc.getPcb().getBx().getValue());
            procPanel.cxLabel.setText(proc.getPcb().getCx().getValue());
            procPanel.dxLabel.setText(proc.getPcb().getDx().getValue());
        }
    }
    
    private void shuffleColor() {
        List<Color> intList = Arrays.asList(ProcessPanelController.color);
        Collections.shuffle(intList);
	intList.toArray(ProcessPanelController.color);
    }
    
}
