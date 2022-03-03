package com.bam.server;

import java.util.Objects;

public class Player {

    private final String __name;


    public Player(String name) {
        __name = name;
    }


    public String getPlayerName() {
        return __name;
    }

    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        Player __player = (Player) _o;
        return __name.equals(__player.__name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(__name);
    }
}
