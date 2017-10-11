package uteevbkru.frontend;

import uteevbkru.messageSystem.Addressee;

/**
 * Created by anton on 03.10.17.
 */
public interface FrontendService extends Addressee {
    void init();

    void handleRequest(String login);

    void addUser(int id, String name);

    void getCacheStatistic();

    void setCacheStatistic(int[] cacheStatistic);

    int[] copyCacheStatistic(int[] mas);
}
