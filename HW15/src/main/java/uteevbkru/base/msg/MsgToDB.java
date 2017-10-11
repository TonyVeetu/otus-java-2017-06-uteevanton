package uteevbkru.base.msg;

import uteevbkru.backend.db.dbService.DBService;
import uteevbkru.messageSystem.Address;
import uteevbkru.messageSystem.Addressee;
import uteevbkru.messageSystem.Message;

/**
 * Created by tully.
 */
public abstract class MsgToDB extends Message {
    public MsgToDB(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof DBService) {
            exec((DBService) addressee);
        }
    }

    public abstract void exec(DBService dbService);
}
