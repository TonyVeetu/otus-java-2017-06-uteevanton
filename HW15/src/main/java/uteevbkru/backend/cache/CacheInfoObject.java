package uteevbkru.backend.cache;

/**
 * Created by anton on 15.09.17.
 */
public class CacheInfoObject {
    private int[] cacheInfoMass = new int[7];

    public CacheInfoObject(int hit, int miss, int size, int maxsize, int idle, int life, boolean eternal){
        cacheInfoMass[0] = hit;
        cacheInfoMass[1] = miss;
        cacheInfoMass[2] = size;
        cacheInfoMass[3] = maxsize;
        cacheInfoMass[4] = idle;
        cacheInfoMass[5] = life;
        if(eternal)
            cacheInfoMass[6] = 1;
        else
            cacheInfoMass[6] = 0;
    }

    public int[] getCacheInfoMass(){
        return cacheInfoMass;
    }
}
