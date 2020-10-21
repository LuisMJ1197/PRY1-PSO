/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor;

import machine.Kernel;
import machine.memory.Address;
import machine.memory.Register;
import machine.processor.instruction.IInstruction;
import os.process.PCB;

/**
 *
 * @author Luism
 */
public class Core {
    private Register ir;
    private PCB pcb;
    private int zeroFlag = 0;
    private IInstruction executingInstruction;
    private int number;
    
    public Core(int number) {
        this.number = number;
    }
    
    public void loadPCB(PCB pcb) {
        this.pcb = pcb;
    }
    
    public boolean nextCycle() {
        if (pcb == null) {
            this.pcb = Kernel.getInstance().getProcessor().nextProcess(number);
        } 
        if (this.pcb != null) {
            if (this.executingInstruction != null) {
                this.executingInstruction.incrementExecutionTime();
                if (this.executingInstruction.isDone()) {
                    if (!this.executingInstruction.execute()) {
                        this.abortProcess();
                    } else {
                        this.nextInstruction();
                    }
                }
            } else {
                this.nextInstruction();
            }
            return true;
        }
        return false;
    }
    
    private void nextInstruction() {
        if (this.validatePC()) {
            String nextInstruction = Kernel.getInstance().getMMU().loadFromMemory(new Address(this.pcb.getPc().getValue()));
            this.executingInstruction = InstructionDecoder.decode(this, nextInstruction.toUpperCase());
        } else {
            this.abortProcess();
        }
    }
    
    private boolean validatePC() {
        int pc = new Address(this.getPCB().getPc().getValue()).getOffset();
        int base = new Address(this.getPCB().getBase().getValue()).getOffset();
        int limit = base + Integer.parseInt(this.getPCB().getLimit().getValue());
        return pc >= base && pc <= limit - 1;
    }
    
    public PCB getPCB() {
        return this.pcb;
    }
    
    public int getZeroFlag() {
        return this.zeroFlag;
    }
    
    public void setZeroFlag(int value) {
        this.zeroFlag = value;
    }
    
    public void abortProcess() {
        this.pcb.getStatus().setValue(PCB.TERMINATED);
        this.pcb = Kernel.getInstance().getProcessor().nextProcess(number);
    }
}
