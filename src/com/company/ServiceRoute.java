package com.company;

public class ServiceRoute {

    public static Integer pointFinish(char[][] map) {
        for (int i = 0; i < map[0].length; i++) {
            if (map[map.length-1][i] == 'X') {
                return i;
            }
        }
        return null;
    }

    public static Integer pointStart(char[][] map) {
        for (int i = 0; i < map[0].length; i++) {
            if (map[0][i] == '@') {
                return i;
            }
        }
        return null;
    }

/*    public static synchronized void printResult(char[][] map, int count) {
        System.out.println("Количество шагов " + count);
        printResult(map);
    }*/

    public static synchronized void printResult(char[][] map) {
        // System.out.println(Thread.currentThread().getName());
        if (map == null) {
            System.out.println("null");
            return;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    public static char[][] cloneMass (char[][] map) {
        char[][] mapClone = new char[map.length][map[0].length];
        for (int i = 0; i < mapClone.length; i++) {
            System.arraycopy(map[i], 0, mapClone[i], 0, mapClone[0].length);
        }
        return mapClone;
    }
}
