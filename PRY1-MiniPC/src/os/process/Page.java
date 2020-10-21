/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.process;

import machine.mmu.IFrame;
import machine.memory.Register;

/**
 *
 * @author Luism
 */
public class Page {
    private int number;
    private String[] memoryRegs = new String[IFrame.FRAME_SIZE];
    
    public Page(int number, String[] memoryRegs) {
        this.number = number;
        this.memoryRegs = memoryRegs;
    }
}
