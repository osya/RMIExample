package com.vosipov.rmi;

import java.rmi.*;

/**
 * Client registering interface. Created by info_000 on 11-Mar-17.
 */
interface ClientRegister extends Remote {
    void register(PrimeChecker checker) throws RemoteException;
}