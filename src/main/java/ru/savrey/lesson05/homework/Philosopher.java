package ru.savrey.lesson05.homework;

public  class Philosopher extends Thread {

    public final static int THINKING = 0;
    public final static int HUNGRY = 1;
    public final static int EATING = 2;

    private final int i;
    private int state;
    private final Semaphore semaphore;
    final static int n = 5;
    final static String[] st = new String[3];
    static int[] pilStatus = new int[n];
    final static Philosopher[] philosophers = new Philosopher[n];

    final static Semaphore mutex = new Semaphore(1,1);


    Philosopher(int id) {
        this.i = id;
        semaphore = new Semaphore(1, 1);
        state = THINKING;
    }

    public static void main(String[] args)
    {
        st[0] = "Thinking";
        st[1] = "Hungry";
        st[2] = "Eating";

        philosophers[0] = new Philosopher(0);
        for (int i = 1; i < n; i++) {
            philosophers[i] = new Philosopher(i);

        }

        for(int i = 0; i < n; i++)
            pilStatus[i] = 0;
        new Thread(philosophers[0],"Plato").start();
        new Thread(philosophers[1],"Socrates").start();
        new Thread(philosophers[2],"Aristotle").start();
        new Thread(philosophers[3],"Diogenes").start();
        new Thread(philosophers[4],"Epicurus").start();


    }
    private Philosopher left() {
        return philosophers[(i + n - 1) % n];
    }

    private Philosopher right() {
        return philosophers[(i + 1) % n];
    }

    public void run() {
        try {
            while (true) {
                statusShow();

                if(state == THINKING)
                {
                    thinking();
                    mutex.down();
                    state = HUNGRY;
                }

                else if(state == HUNGRY)
                {
                    test(this);
                    mutex.up();
                    semaphore.acquire();
                    state = EATING;

                }
                else
                {
                    eating();
                    mutex.down();
                    state = THINKING;

                    test(left());
                    test(right());
                    mutex.up();
                }

            }
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }



    void test(Philosopher p) {
        if (p.left().state != EATING && p.state == HUNGRY &&
                p.right().state != EATING) {
            try {
                p.state = EATING;
                p.semaphore.release();
            } catch (Exception ex) {
                System.out.println(Philosopher.class.getName() + ex);
            }
        }
    }

    void eating() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    void thinking() {
        try {
            Thread.sleep((long) Math.round(Math.random() * 5000));
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    void statusShow() {
        String status;
        if(state == THINKING)
        {
            status = "Thinking";
            pilStatus[i] = THINKING;
        }
        else if(state==EATING)
        {
            status = "Eating";
            pilStatus[i] = EATING;
        }
        else
        {
            status="Hungry";
            pilStatus[i]=HUNGRY;
        }

        System.out.println(Thread.currentThread().getName() + " is " + status);
    }
}
