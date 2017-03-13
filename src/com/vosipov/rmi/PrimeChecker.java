package com.vosipov.rmi;

import java.math.BigDecimal;
import java.rmi.*;

/**
 * Checking whether number is prime. Created by info_000 on 11-Mar-17.
 */
interface PrimeChecker extends Remote {
    boolean check(BigDecimal number) throws RemoteException;
}
