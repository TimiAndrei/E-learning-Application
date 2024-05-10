package com.timi.service;

public interface AuditingService {
    void logAction(String action);
    void logCurrentAction();
}
