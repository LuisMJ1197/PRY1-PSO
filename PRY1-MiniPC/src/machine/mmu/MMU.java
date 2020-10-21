/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.mmu;

import machine.memory.Address;
import machine.memory.IMemory;
import machine.memory.Register;

/**
 *
 * @author Luism
 */
public class MMU implements IMMU {
    private IMemory mainMemory;
    private IMemory diskMemory;
    private Frame[] frameTable;
    
    public MMU(IMemory mainMemory, IMemory diskMemory) {
        this.mainMemory = mainMemory;
        this.diskMemory = diskMemory;
        this.frameTable = new Frame[(this.mainMemory.getSize() + this.diskMemory.getSize()) / IFrame.FRAME_SIZE];
        this.initFrameTable();
    }
    
    private void initFrameTable() {
        for (int i = 0; i < frameTable.length; i++){
            IMemory memory = null;
            int j;
            if (i < this.mainMemory.getSize() / IFrame.FRAME_SIZE) {
                memory = this.mainMemory;
                j = i * IFrame.FRAME_SIZE;
            } else {
                memory = this.diskMemory;
                j = (i - (this.mainMemory.getSize() / IFrame.FRAME_SIZE)) * IFrame.FRAME_SIZE;
            }
            Register[] registers = new Register[IFrame.FRAME_SIZE];
            for (int k = 0; k < IFrame.FRAME_SIZE; k++) {
                registers[k] = memory.getRegister(j);
            }
            frameTable[i] = new Frame(i, registers);
        }
    }
    
    public String load(int address) {
        return null;
    }
    
    public void store(int address, String value) {
        
    }

    @Override
    public Register[] saveMemory(int size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String loadFromMemory(Address address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storeToMemory(Address address, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
