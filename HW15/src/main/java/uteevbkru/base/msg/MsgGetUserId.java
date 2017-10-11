package uteevbkru.base.msg;

import uteevbkru.messageSystem.Address;
import uteevbkru.messageSystem.MessageSystem;
import uteevbkru.backend.db.dbService.DBService;

/**
 * Created by tully.
 */
public class  MsgGetUserId extends MsgToDB {
    private final MessageSystem messageSystem;
    private final String login;

    public MsgGetUserId(MessageSystem messageSystem, Address from, Address to, String login) {
        super(from, to);
        this.login = login;
        this.messageSystem = messageSystem;
    }

    @Override
    public void exec(DBService dbService) {
        int id = dbService.getUserId(login);
        messageSystem.sendMessage(new MsgGetUserIdAnswer(getTo(), getFrom(), login, id));
    }
}
