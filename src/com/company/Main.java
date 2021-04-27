package com.company;

import java.util.*;

public class Main implements RouteFinder {
    public static char[][] startMas = new char[5][5];
    public static volatile Map<Integer,char[][]> resMass = new HashMap<>();

    static {
        startMas[0][0] = '.';
        startMas[0][1] = '.';
        startMas[0][2] = '.';
        startMas[0][3] = '@';
        startMas[0][4] = '.';

        startMas[1][0] = '.';
        startMas[1][1] = '#';
        startMas[1][2] = '#';
        startMas[1][3] = '#';
        startMas[1][4] = '#';

        startMas[2][0] = '.';
        startMas[2][1] = '.';
        startMas[2][2] = '.';
        startMas[2][3] = '.';
        startMas[2][4] = '.';

        startMas[3][0] = '#';
        startMas[3][1] = '#';
        startMas[3][2] = '#';
        startMas[3][3] = '#';
        startMas[3][4] = '.';

        startMas[4][0] = '.';
        startMas[4][1] = 'X';
        startMas[4][2] = '.';
        startMas[4][3] = '.';
        startMas[4][4] = '.';
    }

    public static void main(String[] args) {
        System.out.println("Исходный массив");
        for (int i = 0; i < startMas.length; i++) {
            for (int j = 0; j < startMas[0].length; j++) {
                System.out.print(startMas[i][j]);
            }
            System.out.println();
        }

        Main main = new Main();
        System.out.println("Результат поиска");
        char[][] res = main.findRoute(startMas);
        ServiceRoute.printResult(res);
    }

    @Override
    public char[][] findRoute(char[][] map) {
        char[][] result;
        int min = 0;
        FinderThread.createFinderThread(map,map.length-1,ServiceRoute.pointFinish(map),0); // запускаем первый поток поиска

        if (resMass.isEmpty()) {
            return null;
        } else {
            // Выбираем из всех результатов самый короткий
            for (Map.Entry<Integer,char[][]> entry : resMass.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    if (min == 0) {
                        min = entry.getKey();
                    } else {
                        min = (min > entry.getKey()) ? entry.getKey() : min;
                    }
                }
            }
            return resMass.get(min);
        }
    }
}
