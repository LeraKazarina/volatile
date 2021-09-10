package com.company;

public class App {
    private volatile boolean switchButton = false;
    private final int SLEEP_TIME = 1000;
    private final int ITERATION_LIMIT = 10;

    public static void main(String[] args) {
        App myApp = new App();
        myApp.doUselessWork();
    }

    public void doUselessWork() {
        final Thread userThread = new Thread() {
            @Override
            public void run() {
                try {
                    int iteration = 0;
                    while (iteration < ITERATION_LIMIT) {
                        if (switchButton) {
                            Thread.sleep(SLEEP_TIME);
                            System.out.println("Пользователь: выключил");
                            switchButton = false;
                            iteration++;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        final Thread toyThread = new Thread() {
            @Override
            public void run() {
                try {
                    int iteration = 0;
                    while (iteration < ITERATION_LIMIT) {
                        if (!switchButton) {
                            Thread.sleep(SLEEP_TIME);
                            System.out.println("Хитрый робот: включил");
                            switchButton = true;
                            iteration++;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        };

        userThread.start();
        toyThread.start();
    }
}

