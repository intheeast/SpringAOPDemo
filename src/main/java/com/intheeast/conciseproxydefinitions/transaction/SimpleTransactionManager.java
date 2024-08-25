package com.intheeast.conciseproxydefinitions.transaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.SimpleTransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

public class SimpleTransactionManager implements PlatformTransactionManager {
	@Override
    public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionSystemException {
        System.out.println("Transaction started");
        return new SimpleTransactionStatus();
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionSystemException {
        System.out.println("Transaction committed");
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionSystemException {
        System.out.println("Transaction rolled back");
    }
}
