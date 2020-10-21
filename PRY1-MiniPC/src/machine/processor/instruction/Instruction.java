/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor.instruction;

import machine.memory.Address;
import machine.processor.Core;

/**
 *
 * @author Luism
 */
public abstract class Instruction implements IInstruction {
    protected int height;
    private int executionTime = 0;
    protected Core core;
    
    @Override
    public abstract boolean execute();

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int incrementExecutionTime() {
        return ++this.executionTime;
    }
    
    @Override
    public int getExecutionTime() {
        return this.executionTime;
    }
    
    @Override
    public boolean isDone() {
        return this.executionTime == this.height;
    }
    
    @Override 
    public void movePc(int offset) { 
        int pc = new Address(this.core.getPCB().getPc().getValue()).getOffset();
        pc += offset;
        this.core.getPCB().getPc().setValue(new Address(this.core.getPCB().getPc().getAddress().getMemoryKind(), pc).getAddressString());
    }
}
