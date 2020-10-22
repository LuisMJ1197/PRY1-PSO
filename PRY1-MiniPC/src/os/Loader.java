/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os;

import java.util.ArrayList;
import machine.Kernel;
import machine.memory.Memory;
import machine.memory.Register;
import os.process.PCB;

/**
 *
 * @author Luism
 */
public class Loader {
    
    public Loader() {
        
    }
    
    public String loadPrograms(ArrayList<Program> programs) {
        String errMsg = "";
        ArrayList<os.process.Process> processes = new ArrayList<>();
        for (Program program: programs) {
            if (program.getCompiledLines().length + PCB.PCB_SIZE <= Memory.MAIN_MEMORY_SIZE || program.getCompiledLines().length + PCB.PCB_SIZE <= Memory.DISK_MEMORY_SIZE) {
                os.process.Process process = this.createProcess(program);
                processes.add(process);
            } else {
                errMsg += String.format("Program %s is too big.\n", program.getName());
            }
        }
        Kernel.getInstance().getProcessor().setProcessList(processes);
        this.loadProgramsToMemory();
        return errMsg;
    }
    
    private void loadProgramsToMemory() {
        this.initPrograms();
    }
    
    private void initPrograms() {
        for (os.process.Process process: Kernel.getInstance().getProcessor().getProcessList()) {
            Kernel.getInstance().getMainMemory().store(process.getPid(), process.getName());            
        }
        for (os.process.Process process: Kernel.getInstance().getProcessor().getProcessList()) {
            int requiredMemory = process.getTotalSize();
            Register[] savedMemory = Kernel.getInstance().getMMU().saveMemory(requiredMemory);
            if (savedMemory == null) {
                process.setLoaded(false);
            } else {
                process.createPCB(savedMemory);
                for (int i = PCB.PCB_SIZE; i < PCB.PCB_SIZE + process.getProgramSize(); i++) {
                    savedMemory[i].setValue(process.getProcessCode()[i - PCB.PCB_SIZE]);
                }
                process.setLoaded(true);
                process.getPcb().getStatus().setValue(PCB.READY);
                
            }
        }
        for (os.process.Process process: Kernel.getInstance().getProcessor().getProcessList()) {
            Kernel.getInstance().getMainMemory().store(process.getPid(), process.getPcb().getPid().getAddress().getAddressString());            
        }
    }

    private os.process.Process createProcess(Program program) {
        os.process.Process newProcess = new os.process.Process(program.getName(), program.getCompiledLines());
        return newProcess;
    }
}
