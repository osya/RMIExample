package com.vosipov.rmi;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * Client class. Created by info_000 on 11-Mar-17.
 */
public class PrimeNumbersSearchClient implements PrimeChecker {

    public boolean check(BigDecimal number) throws RemoteException {
        boolean isPrime = true;
        BigDecimal i = new BigDecimal (2);

        /* mistaken? */
        BigDecimal sqrt = new BigDecimal (Math.sqrt(number.doubleValue()));
        BigDecimal div = number.divide (sqrt, BigDecimal.ROUND_UP);
        sqrt = (div.compareTo (sqrt) == 1) ? div : sqrt.add(BigDecimal.ONE);

        /* while i less than sqrt */
        while(i.compareTo(sqrt) == -1) {
            if(number.divideAndRemainder(i)[1].compareTo(BigDecimal.ZERO) == 0) {
                isPrime = false;
                break;
            }
            i = i.add(BigDecimal.ONE);
        }

        System.out.println (number + ((isPrime) ? " is prime" : " is not prime"));

        return isPrime;
    }

    // TODO: write test for Client.main() method
    public static void main(String[] args) {
        PrimeNumbersSearchClient client = new PrimeNumbersSearchClient();

        try {
            Registry registry = LocateRegistry.getRegistry(null, 12345);
            ClientRegister server = (ClientRegister)registry.lookup("ClientRegister");

            PrimeChecker stub = (PrimeChecker) UnicastRemoteObject.exportObject(client, 0);
            server.register(stub);

        } catch (Exception e) {
            System.out.println (String.format("Error occured: %s", e.getMessage()));
            System.exit (1);
        }
    }
}