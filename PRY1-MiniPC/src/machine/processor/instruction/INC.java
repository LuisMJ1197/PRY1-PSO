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
public class INC extends Instruction {
    
    public INC(Core cpu) {
        this.height = IInstruction.INC;
        this.core = cpu;
    }
    
    @Override
    public boolean execute() {
        Kernel.getInstance().getMMU().storeToMemory((Address) this.core.getPCB().getAc().getAddress(),
                Integer.toString(
                        Integer.parseInt(this.core.getPCB().getAc().getValue()) +
                            1
                ));
        this.movePc(1);
        return true;
    }
}
