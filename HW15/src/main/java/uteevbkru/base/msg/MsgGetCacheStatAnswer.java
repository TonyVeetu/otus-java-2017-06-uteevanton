package uteevbkru.base.msg;

import uteevbkru.frontend.FrontendService;
import uteevbkru.messageSystem.Address;

import java.util.Arrays;

/**
 * Created by anton on 03.10.17.
 */
public class MsgGetCacheStatAnswer extends MsgToFrontend {
    private int[] cacheStatictic = new int[7];

    public MsgGetCacheStatAnswer(Address from, Address to, int[] cacheStat) {
        super(from, to);
        //TODO
        cacheStatictic = Arrays.copyOf(cacheStat, cacheStat.length);
    }

    @Override
    public void exec(FrontendService frontendService) {
        frontendService.setCacheStatistic(cacheStatictic);
        System.out.println("**********************************************");
        System.out.println("setCacheStatistic in Frontend!!!!!"+cacheStatictic[0]+" "+cacheStatictic[1]+" "+cacheStatictic[2]+" "+cacheStatictic[3]);
        System.out.println();
        System.out.println("**********************************************");
    }
}
