package ru.clevertec.service;

public interface HealthCheckService {

    void check();

    boolean isAlive(int port);
}
