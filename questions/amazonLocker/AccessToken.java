package questions.amazonLocker;

import javax.swing.*;

class AccessToken{
    String expiryTimestamp;
    String code;
    private final Compartment compartment;

    public AccessToken(Compartment compartment){
        this.code = "AB12CD";
        expiryTimestamp = "1234532124";
        this.compartment = compartment;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    boolean isExpired() {
        //check If it is out of time or not
        return false;
    }
}