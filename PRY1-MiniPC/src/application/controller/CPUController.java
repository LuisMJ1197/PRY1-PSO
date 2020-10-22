/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.view.CPUCorePanel;
import machine.Kernel;
import machine.memory.Address;
import machine.memory.Register;
import machine.processor.Core;

/**
 *
 * @author Luism
 */
public class CPUController {
    private final Core core;
    private final CPUCorePanel panel;
    
    public CPUController(CPUCorePanel panel, Core core) {
        this.core = core;
        this.panel = panel;
    }
    
    public void update() {
        this.panel.zeroFlagLabel.setText(Integer.toString(core.getZeroFlag()));
        if (this.core.getPCB() != null) {
            this.panel.irRegisterLabel.setText(Kernel.getInstance().getMMU().loadFromMemory(new Address(this.core.getPCB().getPc().getValue())));
            this.panel.processIdLabel.setText(this.core.getPCB().getPid().getValue());
        } else {
            this.panel.irRegisterLabel.setText(Register.EMPTY);
            this.panel.processIdLabel.setText("-1");
        }
    }
}
