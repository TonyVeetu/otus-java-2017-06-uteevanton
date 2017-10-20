package uteevbkru.backend.cache;

public interface CacheInfo {
    int getHitCount();

    int getMissCount();

    int getSize();

    int getMaxSize();

    long getIdleTime();

    long getLifeTime();

    boolean isEternal();

    int COUNT_OF_ELEMENT = 7;
}
