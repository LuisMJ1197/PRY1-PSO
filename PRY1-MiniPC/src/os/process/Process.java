/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.process;

import machine.memory.Register;

/**
 *
 * @author Luism
 */
public class Process implements IProcess {
    private static int sCantProcess = 0;
    private String name;
    private int pid;
    private boolean loaded = true;
    private PCB pcb;
    private Register[] savedMemory;
    private String[] code;
    
    public Process(String name, String[] code) {
        this.name = name;
        this.pid = sCantProcess++;
        this.code = code;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
    
    @Override
    public int getProgramSize() {
        return this.code.length;
    }
    
    @Override
    public int getTotalSize() {
        return PCB.PCB_SIZE + this.getProgramSize();
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public PCB getPcb() {
        return pcb;
    }
    
    public void createPCB(Register[] array) {
        this.savedMemory = array;
        this.pcb = new PCB(array);
        this.pcb.getPid().setValue(Integer.toString(this.pid));
        this.pcb.getCpuNumber().setValue("-1");
        this.getPcb().getBase().setValue(savedMemory[PCB.PCB_SIZE].getAddress().getAddressString());
        this.getPcb().getLimit().setValue(Integer.toString(this.getProgramSize()));
        this.getPcb().getPc().setValue(this.getPcb().getBase().getValue());
        this.getPcb().getListOfIO().setValue("00000000");
        this.getPcb().getStartTime().setValue("-1");
        this.getPcb().getExecutingTime().setValue("0");
    }
    
    public String[] getProcessCode() {
        return this.code;
    }
}
