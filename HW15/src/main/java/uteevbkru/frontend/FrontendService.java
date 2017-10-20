package uteevbkru.frontend;

import uteevbkru.messageSystem.Addressee;

public interface FrontendService extends Addressee {

    void handleRequest(String login);

    void addUser(int id, String name);

    void getCacheStatistic();

    void setCacheStatistic(int[] cacheStatistic);

    int[] copyCacheStatistic(int[] mas);
}
