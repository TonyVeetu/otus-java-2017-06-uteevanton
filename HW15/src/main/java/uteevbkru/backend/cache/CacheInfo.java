package uteevbkru.backend.cache;

/**
 * Created by anton on 14.09.17.
 */
public interface CacheInfo {
    int getHitCount();

    int getMissCount();

    int getSize();

    int getMaxSize();

    long getIdleTime();

    long getLifeTime();

    boolean isEternal();
}
