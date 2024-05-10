package com.timi.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import com.timi.service.AuditingService;

public class AuditingServiceImpl implements AuditingService {
    private static final String CSV_FILE_PATH = "audit_log.csv";
    private static final String CSV_SEPARATOR = ",";

    private static AuditingServiceImpl instance;

    private AuditingServiceImpl() {
    }

    public static AuditingServiceImpl getInstance() {
        if (instance == null) {
            instance = new AuditingServiceImpl();
        }
        return instance;
    }

    @Override
    public void logAction(String action) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String logEntry = action + CSV_SEPARATOR + LocalDateTime.now();
            writer.println(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logCurrentAction() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = stackTrace[2].getMethodName();
        logAction(methodName);
    }
}
