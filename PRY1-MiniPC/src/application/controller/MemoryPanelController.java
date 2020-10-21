/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.MemoryPanel;
import application.view.MemoryRegisterPanel;
import java.awt.Color;
import machine.memory.IMemory;

/**
 *
 * @author Luism
 */
public class MemoryPanelController {
    private MemoryPanel meroryPanel;
    private IMemory memory;
    private MemoryRegisterPanel[] memoryRegisterPanels;
    
    public MemoryPanelController(IMemory memory, MemoryPanel memoryPanel) {
        this.memory = memory;
        this.meroryPanel = memoryPanel;
        this.memoryRegisterPanels = new MemoryRegisterPanel[this.memory.getSize()];
        this.initMemoryView();
        this.update();
    }
    
    private void initMemoryView() {
        this.meroryPanel.setGridLayoutRowsCount(this.memory.getSize());
        for (int i = 0; i < this.memory.getSize(); i++) {
            this.memoryRegisterPanels[i] = new MemoryRegisterPanel();
            this.memoryRegisterPanels[i].addressLabel.setText(this.memory.getRegister(i).getAddress().getAddressString());
            this.meroryPanel.memoryRegsPanel.add(this.memoryRegisterPanels[i]);
        }
    }
    
    public void update() {
        int i = 0;
        for (MemoryRegisterPanel memRegPanel: this.memoryRegisterPanels) {
            memRegPanel.valueLabel.setText(this.memory.load(i));
            i++;
        }
    }
    
    public void paintBorder(Color color, int initPos, int count) {
        for (int i = 0; i <= count - initPos; i++) {
            this.memoryRegisterPanels[initPos + i].setBorder(javax.swing.BorderFactory.createLineBorder(color));
        }
    }
}
