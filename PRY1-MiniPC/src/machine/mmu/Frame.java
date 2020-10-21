/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.mmu;

import machine.memory.Register;

/**
 *
 * @author Luism
 */
public class Frame implements IFrame {
    private int number;
    private Register[] memoryRegs = new Register[IFrame.FRAME_SIZE];
    
    public Frame(int number, Register[] memoryRegs) {
        this.number = number;
        this.memoryRegs = memoryRegs;
    }
}
