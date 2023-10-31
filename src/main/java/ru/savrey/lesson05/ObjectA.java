package ru.savrey.lesson05;

public class ObjectA {
    public synchronized void stepOne(ObjectB object) {
        System.out.println("stepOne: " + Thread.currentThread().getName());
        object.stepTwo(this);
    }

    public synchronized void stepTwo(ObjectB object) {
        System.out.println("stepTwo: " + Thread.currentThread().getName());
        object.toString();
    }
}
