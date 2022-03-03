package com.bam.server;

import com.bam.MessagePattern;
import com.bam.MessageType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameField {

    /**
     * Поле с клопами
     */
    private final BedBug[][] __field;
    /**
     * Коллекция игроков
     */
    private final LinkedList<Player> __players = new LinkedList<>();

    /**
     * Ходящий
     */
    private Player __mover;

    /**
     * Колличество ходов для игрока
     */
    private int __count = 3;

    /**
     * Номер хода в игре
     */
    private int __gameMove = 0;
    /**
     * Размер поля
     */
    private int __size;

    /**
     * Конструктор класса
     *
     * @param size размер поля
     */
    public GameField(int size) {
        __size = size;
        __field = new BedBug[__size][__size];

        preparePlayer("Player-green", 1, 1);
        preparePlayer("Player-blue", __size - 2, __size - 2);

    }

    /**
     * Подготовка поля установка 2х игроков
     * @param name
     * @param x
     * @param y
     */
    public void preparePlayer(String name, int x, int y) {
        if (x > 0 && x < __size - 1 && y > 0 && y < __size - 1) {
            Player _player = new Player(name);
            BedBug _bedBug = new BedBug(_player, x, y);
            _bedBug.setMainBedBug(true);
            __field[x][y] = _bedBug;
            __players.add(_player);
        }

    }


    /**
     * @param x координата x
     * @param y координата y
     * @return Сообщение для клиента игры
     */
    public String change(int x, int y) {
        moverControl();
        BedBug _binder = findBinder(__mover, x, y);
        if (_binder != null) {

                changeBugState(_binder, x, y);
                __count--;
                __gameMove++;



            return MessagePattern.generate(MessageType.SUCCESS, __field[x][y].toString());
        } else return MessagePattern.generate(MessageType.ERROR, "Не допустимый ход");
    }


    /**
     * Изменяет состояние ячейки поля
     *
     * @param i координата x
     * @param j координата y
     */
    private void changeBugState(BedBug binder, int i, int j) {

        if (__field[i][j] == null) {
            __field[i][j] = new BedBug(__mover, i, j);
            __field[i][j].setBinder(binder);
            return;
        }

        if (__field[i][j].isAlive() && !__field[i][j].isMainBedBug()) {
            __field[i][j].kill(__mover);
            __field[i][j].setBinder(binder);
        }

    }

    /**
     * Контролирует очередь хода
     */
    private void moverControl() {
        if (__mover == null) __mover = __players.get(0);
        if (__count == 0) {
            int _index = __gameMove % __players.size();
            __mover = __players.get(_index);
            __count = 3;
        }
    }


    /**
     * Ищет связующих рядом с выбранной ячейкой
     * @param owner
     * @param x
     * @param y
     * @return
     */
    private BedBug findBinder(Player owner, int x, int y) {
        BedBug _result = null;

        if (__field[x][y] != null && !__field[x][y].isAlive()) return null;
        if (__field[x][y] != null && __field[x][y].getOwner() != null && __field[x][y].getOwner().equals(__mover)) return null;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i > -1 && i < __size && j > -1 && j < __size) {
                    if (!(i == x && j == y)) {
                        if (__field[i][j] != null) {
                            if (__field[i][j].getOwner().equals(owner)) {
                                if (checkConnectionWithBase(__field[i][j])) {

                                    _result = (__field[i][j]);
                                }

                            }
                        }
                    }
                }
            }
        }
        return _result;
    }

    /**
     * Проверяет связана ли ячейка с базой
     * @param bedBug
     * @return
     */
    private boolean checkConnectionWithBase(BedBug bedBug) {

        while (bedBug.getBinder() != null || bedBug.isMainBedBug()) {
            if (bedBug.isMainBedBug()) {
                return true;
            }
            return checkConnectionWithBase(bedBug.getBinder());
        }

        return false;
    }


}
