package com.vosipov.rmi;

import java.math.BigDecimal;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.concurrent.*;

/**
 * Server class. Created by info_000 on 11-Mar-17.
 */
public class PrimeNumbersSearchServer implements ClientRegister {

    private static final String BINDING_NAME = "ClientRegister";

    /** checkers queue */
    private final BlockingQueue<PrimeChecker> availableCheckers = new LinkedBlockingQueue<>();

    /** current number */
    private BigDecimal number = BigDecimal.ONE;

    public void register(PrimeChecker checker) throws RemoteException {
        availableCheckers.add(checker);
    }

    private void startSearch() {
        while (true) {
            try {
                final PrimeChecker checker = availableCheckers.take();
                final BigDecimal numberToCheck = increment();

                new Thread(() -> {
                    try {
                        if (checker.check(numberToCheck)) {
                            System.out.println (numberToCheck);
                        }
                        availableCheckers.add (checker);
                    } catch (RemoteException e) {
                        System.out.println ("Client disconnected or unknown error occured");
                    }
                }).start();

            } catch (InterruptedException e) {

            }
        }
    }

    private synchronized BigDecimal increment () {
        number = number.add(BigDecimal.ONE);
        return number;
    }

    // TODO: write test for Server.main() method. How to deal with Thread.start()?
    public static void main(String[] args) {
        try {
            System.out.print("Starting registry...");
            final Registry registry = LocateRegistry.createRegistry(12345);
            System.out.println(" OK");

            final PrimeNumbersSearchServer server = new PrimeNumbersSearchServer();
            Remote stub = UnicastRemoteObject.exportObject(server, 0);

            System.out.print("Binding service...");
            registry.bind(BINDING_NAME, stub);
            System.out.println(" OK");

            server.startSearch();
        } catch (Exception e) {
            System.out.println (String.format("Error occured: %s", e.getMessage()));
            System.exit (1);
        }
    }
}