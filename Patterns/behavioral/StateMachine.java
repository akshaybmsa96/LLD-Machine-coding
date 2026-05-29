package Patterns.behavioral;

public class StateMachine {
    public static void main(String[] args) {

        VendingMachine machine = new VendingMachine();

        machine.selectProduct();
        machine.insertCoin();
        machine.selectProduct();
        machine.insertCoin();
        machine.dispense();

        machine.selectProduct();
        machine.insertCoin();

    }
}

interface VendingMachineState {
    void insertCoin(VendingMachine machine);
    void selectProduct(VendingMachine machine);
    void dispenseItem(VendingMachine machine);
}

class NoCoinStateVendingMachine implements  VendingMachineState{
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin Inserted");
        machine.setVendingMachine(new InsertCoinStateVendingMachine());
    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Insert a Coin first");
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Insert a Coin first");
    }
}

class InsertCoinStateVendingMachine implements  VendingMachineState{
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin Already Inserted");

    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Product selected");
        machine.setVendingMachine(new DispenseStateVendingMachine());
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Select a product first");
    }
}

class DispenseStateVendingMachine implements  VendingMachineState{
    @Override
    public void insertCoin(VendingMachine machine) {
        System.out.println("Coin Already Inserted");

    }

    @Override
    public void selectProduct(VendingMachine machine) {
        System.out.println("Product selected");
    }

    @Override
    public void dispenseItem(VendingMachine machine) {
        System.out.println("Item Dispensed! Collect your Item");
        machine.setVendingMachine(new NoCoinStateVendingMachine());
    }
}

class VendingMachine {
    private VendingMachineState vendingMachine;

    public VendingMachine() {
        this.vendingMachine = new NoCoinStateVendingMachine();
    }

    protected void setVendingMachine(VendingMachineState state){
        vendingMachine = state;
    }

    public void insertCoin(){
        vendingMachine.insertCoin(this);
    }

    public void selectProduct(){
        vendingMachine.selectProduct(this);
    }

    public void dispense(){
        vendingMachine.dispenseItem(this);
    }

}


