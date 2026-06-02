package questions.amazonLocker;

import java.util.ArrayList;
import java.util.Map;

class Locker{
    ArrayList<Compartment> compartments;
    Map<String, AccessToken> codeCompartmentMap;
    //Map<String, Compartment> codeCompartmentMap;

    String putPackage(Package aPackage) throws Exception {
        Compartment compartment = compartments.stream()
                .filter(cmp-> cmp.getSize() == aPackage.size && cmp.isAvailable())
                .findFirst()
                .orElse(null);

        if(compartment == null){
            System.out.println("Couldn't find matching compartment! Sorry");
            throw new NoAvailableCompartmentException("All compartments are occupied");
        }
        boolean isSuccessfullyPut = compartment.putPackage(aPackage);
        if(!isSuccessfullyPut){
            throw new Exception("Hardware Failure");
        }
        AccessToken accessToken = new AccessToken(compartment);
        codeCompartmentMap.put(accessToken.code, accessToken);
        //codeCompartmentMap.put(accessToken.code, compartment); -> i like this one better

        return accessToken.code;
    }

    int accessPackage(String code) throws NoCompartmentFound, TokenExpired {
        if(codeCompartmentMap.containsKey(code)){

            AccessToken accessToken = codeCompartmentMap.get(code);

            if(accessToken.isExpired()){
                System.out.println("Token expired! Contact Admin!");
                throw new TokenExpired("Token expired! Contact Admin!");
            }
            Compartment compartment = accessToken.getCompartment();
            compartment.freeCompartment();
            codeCompartmentMap.remove(code);

            return compartment.getNumber();

        } else {
            throw new NoCompartmentFound("No compartment found for the provided code");
        }
    }

    int removePackage(String code) throws AccessDenied, NoCompartmentFound {

        if(codeCompartmentMap.containsKey(code)){

            AccessToken accessToken = codeCompartmentMap.get(code);

            if(!accessToken.isExpired()){
                System.out.println("Access Token is valid! Removal not allowed");
                throw new AccessDenied("Access Token is valid! Removal not allowed, Access Denied!");
            }
            Compartment compartment = accessToken.getCompartment();
            compartment.freeCompartment();
            codeCompartmentMap.remove(code);

            return compartment.getNumber();

        } else {
            throw new NoCompartmentFound("No compartment found for the provided code");
        }
    }

}