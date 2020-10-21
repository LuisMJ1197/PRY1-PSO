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
public class Address implements IAddress {
    private int memoryKind;
    private int offset;
    
    public Address (int memoryKind, int offset) {
        this.memoryKind = memoryKind;
        this.offset = offset;
    }

    public Address(String value) {
        this.memoryKind = IAddress.MAIN_MEMORY;
        if (value.substring(0, 1).equals("1")) {
            this.memoryKind = IAddress.DISK_MEMORY;
        }
        this.offset = Integer.parseInt(value.substring(4));
    }

    @Override
    public int getMemoryKind() {
        return memoryKind;
    }

    @Override
    public int getOffset() {
        return offset;
    }
    
    @Override
    public String getAddressString() {
        String address = "";
        address += IAddress.pad(Integer.toString(this.memoryKind), "0", 4, false);
        address += IAddress.pad(Integer.toString(this.offset), "0", 4, true);
        return address;
    }
    
    
}
