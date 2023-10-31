package ru.savrey.lesson05;

public class DeadLockCl {
    void getThreadAB(ObjectA objectA, ObjectB objectB) {
        new Thread(() -> {
            System.out.println("run: " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            objectA.stepOne(objectB);
        }).start();
    }

    void getThreadBA(ObjectB objectB, ObjectA objectA) {
        new Thread(() -> {
            System.out.println("run: " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            objectB.stepOne(objectA);
        }).start();
    }
}
