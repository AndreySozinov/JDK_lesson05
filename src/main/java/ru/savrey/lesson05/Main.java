package ru.savrey.lesson05;
// Создать два класс ObjectA, ObjectB
//● Реализовать класс в котором два потока при запуске провоцируют DeadLock для
//объектов ObjectA, ObjectB
public class Main {
    public static void main(String[] args) {
        ObjectA objectA = new ObjectA();
        ObjectB objectB = new ObjectB();
        DeadLockCl deadLockCl = new DeadLockCl();
        deadLockCl.getThreadBA(objectB, objectA);
        deadLockCl.getThreadAB(objectA, objectB);
    }
}
