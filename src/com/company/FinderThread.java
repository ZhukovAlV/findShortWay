package com.company;

public class FinderThread extends Thread {
    private char[][] map;
    private int count = 0;
    int x, y;

    public char[][] getMap() {
        Integer i = ServiceRoute.pointStart(map);
        if ((map[1][i] == '+')
                || (i + 1 < map[0].length && map[0][i + 1] == '+')
                || (i - 1 >=0 && map[0][i - 1] == '+')) {
            return map;
        } else return null;
    }

    public int getCount() {
        return count;
    }

    public FinderThread(char[][] map, int x, int y, int count) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.count = count;
    }

    @Override
    public void run() {
        super.run();
        boolean fork = false;

        while (true) {
            int x2 = 0, y2 = 0; // это смещение, на которое потом нужно будет сдвинуть поиск
            // проставляем плюсики и создаем новые нити на каждую развилку
            fork = false;
            if ((y + 1) < map[0].length && (map[x][y + 1] == '.' || map[x][y + 1] == '@')) {
                if (map[x][y] == '@') break; // значит до финиша дошли
                else if (map[x][y] != 'X') {
                    map[x][y] = '+'; // проставляем плюсик, если это не точка старта
                    count++; // считаем количество плюсиков добавленных
                }
                fork = true; // флаг развилки устанавливаем в true
                y2 += 1;
            }
            if ((y - 1) >= 0 && (map[x][y - 1] == '.' || map[x][y - 1] == '@')) {
                if (fork) {
                    createFinderThread(ServiceRoute.cloneMass(map), x,y - 1, getCount());
                } else if (map[x][y] == '@') {
                    break;
                } else {
                    if (map[x][y] != 'X') {
                        map[x][y] = '+';
                    }
                    count++;
                    fork = true;
                    y2 -= 1;
                }
            }
            if ((x + 1) < map.length && (map[x + 1][y] == '.' && map[x + 1][y] == '@')) {
                if (fork) {
                    createFinderThread(ServiceRoute.cloneMass(map),x + 1, y, getCount());
                } else if (map[x][y] == '@') {
                    break;
                } else {
                    if (map[x][y] != 'X') {
                        map[x][y] = '+';
                    }
                    count++;
                    fork = true;
                    x2 += 1;
                }
            }
            if ((x - 1) >= 0 && (map[x - 1][y] == '.' || map[x - 1][y] == '@')) {
                if (fork) {
                    createFinderThread(ServiceRoute.cloneMass(map),x - 1, y, getCount());
                } else if (map[x][y] == '@') {
                    break;
                } else {
                    if (map[x][y] != 'X') {
                        map[x][y] = '+';
                    }
                    count++;
                    fork = true;
                    x2 -= 1;
                }
            }
            x += x2;
            y += y2;
            if (!fork) break;
        }
    }

    public static void createFinderThread(char[][] map, int x, int y, int count) {
        FinderThread finderThread = new FinderThread(map,x,y,count);
        finderThread.start();
        try {
            finderThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // ServiceRoute.printResult(finderThread.getMap(),finderThread.getCount());
        if (finderThread.getMap() != null) {
            Main.resMass.put(finderThread.getCount(),finderThread.getMap());
        }
    }
}
