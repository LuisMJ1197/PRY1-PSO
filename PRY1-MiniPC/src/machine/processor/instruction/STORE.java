/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.processor.instruction;

import machine.Kernel;
import machine.memory.Address;
import machine.memory.Register;
import machine.processor.Core;

/**
 *
 * @author Luism
 */
public class STORE extends Instruction {
    private final Register reg;
    
    public STORE(Core cpu, Register reg) {
        this.height = IInstruction.STORE;
        this.reg = reg;
        this.core = cpu;
    }
    
    @Override
    public boolean execute() {
        Kernel.getInstance().getMMU().storeToMemory((Address) reg.getAddress(), this.core.getPCB().getAc().getValue());
        this.movePc(1);
        return true;
    }
}
