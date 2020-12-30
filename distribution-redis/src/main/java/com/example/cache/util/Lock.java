package com.example.cache.util;

public interface Lock {
    void lock(String key);
    boolean tryLock(String key);
    void unlock(String key) throws Exception;
}
