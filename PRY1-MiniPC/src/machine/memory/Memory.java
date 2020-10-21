/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine.memory;

/**
 *
 * @author Luism
 */
public class Memory implements IMemory {
    public static final int MAIN_MEMORY_SIZE = 128;
    public static final int DISK_MEMORY_SIZE = 512;
    
    private final Register[] registers;
    private final int size;
    
    public Memory(int size) {
        this.size = size;
        registers = new Register[size];
        this.initMemory();
    }
    
    @Override
    public Register getRegister(int pos) {
        return this.registers[pos];
    }
    
    @Override
    public String load(int address) {
        return this.registers[address].getValue();
    }

    @Override
    public void store(int address, String value) {
        this.registers[address].setValue(value);
    }
    
    @Override
    public String load(IAddress address) {
        if (address.getMemoryKind() == IAddress.MAIN_MEMORY && this.getSize() == Memory.MAIN_MEMORY_SIZE) {
            return this.registers[address.getOffset()].getValue();
        }
        return null;
    }

    @Override
    public void store(IAddress address, String value) {
        if (address.getMemoryKind() == IAddress.MAIN_MEMORY && this.getSize() == Memory.MAIN_MEMORY_SIZE) {
            this.registers[address.getOffset()].setValue(value);
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    private void initMemory() {
        for (int i = 0; i < this.size; i++) {
            IAddress address;
            if (this.size == Memory.MAIN_MEMORY_SIZE) address = new Address(IAddress.MAIN_MEMORY, i);
            else address = new Address(IAddress.DISK_MEMORY, i);
            this.registers[i] = new Register(address);
        }
    }
    
    public void clean() {
        for (Register reg: this.registers) {
            reg.clean();
        }
    }
}
