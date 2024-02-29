package com.kimmy.two_database_connection.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.ReactiveTransactionManager;

import java.lang.reflect.Field;

@Configuration
public class TransactionManagerInspector {

    public static void inspectTransactionManager(Object repository) {
        if (repository != null) {
            Field[] fields = repository.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (ReactiveTransactionManager.class.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    try {
                        ReactiveTransactionManager transactionManager = (ReactiveTransactionManager) field.get(repository);
                        System.out.println("TransactionManager for " + repository.getClass().getSimpleName() + ": " + transactionManager.getClass().getName());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

