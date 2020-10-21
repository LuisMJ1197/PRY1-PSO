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
public class ADD extends Instruction {
    private final Register reg;
    
    public ADD(Core cpu, Register reg) {
        this.height = IInstruction.ADD;
        this.reg = reg;
    }
    
    @Override
    public boolean execute() {
        Kernel.getInstance().getMMU().storeToMemory((Address) this.core.getPCB().getAc().getAddress(),
                Integer.toString(
                        Integer.parseInt(this.core.getPCB().getAc().getValue()) + 
                            Integer.parseInt(reg.getValue())
                ));
        this.movePc(1);
        return true;
    }
}