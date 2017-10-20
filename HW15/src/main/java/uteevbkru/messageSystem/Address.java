package uteevbkru.messageSystem;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

public final class Address implements Addressee{
    private final String id;

    public Address(String id) {
        this.id = id;
    }

    public Address getAddress(){
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return id != null ? id.equals(address.id) : address.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
