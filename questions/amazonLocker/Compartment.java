package questions.amazonLocker;

class Compartment{
    Package aPackage;
    private final Size size;
    private final int number;

    public Compartment(Size size, int number) {
        this.size = size;
        this.number = number;
    }

    boolean isAvailable(){
        return aPackage == null;
    }
    Size getSize(){
        return size;
    }
    boolean putPackage(Package aPackage){
        if(isAvailable()){
            this.aPackage = aPackage;
            return true;
        } else {
            System.out.println("Compartment Occupied");
            return false;
        }

    }

    public int getNumber() {
        return number;
    }

    void freeCompartment(){
        aPackage = null;
    }
}