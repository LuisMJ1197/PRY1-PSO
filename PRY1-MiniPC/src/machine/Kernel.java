/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machine;

import java.util.ArrayList;
import machine.io.Display;
import machine.io.IODevice;
import machine.io.Keyboard;
import machine.memory.IMemory;
import machine.memory.Memory;
import machine.mmu.IMMU;
import machine.mmu.SimpleMMU;
import machine.processor.Processor;

/**
 *
 * @author Luism
 */
public class Kernel {
    private Processor processor;
    private IMemory mainMemory;
    private IMemory diskMemory;
    private IMMU mmu;
    private static Kernel instance = new Kernel();
    private IODevice keyboard;
    private IODevice display;
    
    private Kernel() {
        processor = new Processor();
        mainMemory = new Memory(Memory.MAIN_MEMORY_SIZE);
        diskMemory = new Memory(Memory.DISK_MEMORY_SIZE);
        mmu = new SimpleMMU(this.mainMemory, this.diskMemory);
        keyboard = new Keyboard();
        display = new Display();
    }
    
    public static Kernel getInstance() {
        return Kernel.instance;
    }

    public IODevice getKeyboard() {
        return keyboard;
    }

    public IODevice getDisplay() {
        return display;
    }
    
    public IMemory getMainMemory() {
        return mainMemory;
    }

    public IMemory getDiskMemory() {
        return diskMemory;
    }

    public Processor getProcessor() {
        return this.processor;
    }
    
    public IMMU getMMU() {
        return this.mmu;
    }
    
}
