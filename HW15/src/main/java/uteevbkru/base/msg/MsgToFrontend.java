package uteevbkru.base.msg;


import uteevbkru.frontend.FrontendService;
import uteevbkru.messageSystem.Address;
import uteevbkru.messageSystem.Addressee;
import uteevbkru.messageSystem.Message;

public abstract class MsgToFrontend extends Message {
    public MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Addressee addressee) {
        if (addressee instanceof FrontendService) {
            exec((FrontendService) addressee);
        } else {
            //todo error!
        }
    }

    public abstract void exec(FrontendService frontendService);
}