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
    private PCB pcb = null;
    private int zeroFlag = 0;
    private IInstruction executingInstruction;
    private final int number;
    
    public Core(int number) {
        this.number = number;
    }
    
    public void loadPCB(PCB pcb) {
        this.pcb = pcb;
    }
    
    public boolean nextCycle() {
        if (pcb == null) {
            this.pcb = Kernel.getInstance().getProcessor().nextProcess(number);
            if(this.pcb == null) return false;
        }
        if (this.executingInstruction == null) {
            this.nextInstruction();
            if (this.executingInstruction == null) return false;
        }
        this.pcb.getExecutingTime().setValue(Integer.toString(Integer.parseInt(this.pcb.getExecutingTime().getValue()) + 1));
        if (this.executingInstruction.getHeight() == -1) { //Interrupt
            if (!this.pcb.getStatus().getValue().equals(PCB.WAITING) && !this.executingInstruction.isDone()) { //If not waiting: interrup recent call
                Kernel.getInstance().getProcessor().getScheduler().suspend(pcb);
                this.executingInstruction.execute();
                this.executingInstruction.incrementExecutionTime();
            } else { //it is not a recent interrupt call
                if (this.executingInstruction.isDone()) {
                    this.nextInstruction(); // If interrupt is done, fetch next instruction
                } else {
                    this.executingInstruction.incrementExecutionTime();
                }
            }
        } else { //Non Interrupt
            this.executingInstruction.incrementExecutionTime();
            if (this.executingInstruction.isDone()) { // Seconds have past
                if (!this.executingInstruction.execute()) { // If there is a problem
                    this.abortProcess(); //Abort
                } else { // Otherwise
                    this.nextInstruction(); //FetchNextInstruction
                }
            }
        }
        return true;
    }
    
    private void nextInstruction() {
        if (this.validatePC()) {
            String nextInstruction = Kernel.getInstance().getMMU().loadFromMemory(new Address(this.pcb.getPc().getValue()));
            this.executingInstruction = InstructionDecoder.decode(this, nextInstruction.toUpperCase());
        } else {
            if (this.getPCB() == null) return;
            this.abortProcess();
        }
    }
    
    private boolean validatePC() {
        if (this.getPCB() == null) return false;
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
        Kernel.getInstance().getProcessor().getScheduler().remove(pcb);
        this.pcb = Kernel.getInstance().getProcessor().nextProcess(number);
        this.nextInstruction();
    }
}
