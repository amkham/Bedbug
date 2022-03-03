package com.bam.server;

/**
 * Класс описывающий клопа
 */
public class BedBug {
    /**
     * Имя владельца клопа
     */
    private Player __owner;


    /**
     * Ссылка на связуещего клопа
     */
    private BedBug __binder;


    /**
     * Позиция клопа на поле
     */
    private final int __positionX;
    private final int __positionY;

    /**
     * true  случае если это клоп базы
     */
    private boolean __mainBedBug = false;

    private boolean __alive;


    public BedBug(Player owner, int x, int y) {
        __owner = owner;
        __positionX = x;
        __positionY = y;
        __alive = true;
    }


    public int getPositionX() {
        return __positionX;
    }

    public boolean isMainBedBug() {
        return __mainBedBug;
    }

    public void setMainBedBug(boolean mainBedBug) {
        __mainBedBug = mainBedBug;
    }

    public BedBug getBinder() {
        return __binder;
    }

    public void setBinder(BedBug binder) {
        __binder = binder;
    }

    public int getPositionY() {
        return __positionY;
    }

    public Player getOwner() {
        return __owner;
    }

    public void setOwner(Player owner) {
        __owner = owner;
    }

    public void kill(Player newOwner) {

        __alive = false;
        setOwner(newOwner);
    }


    public boolean isAlive() {
        return __alive;
    }




    @Override
    public String toString() {
        return __owner.getPlayerName() + "#" + __alive + "#" + getPositionX() + "#" + getPositionY();
    }
}
