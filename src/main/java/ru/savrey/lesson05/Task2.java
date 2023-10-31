package ru.savrey.lesson05;
// Создайте два потока A и B.
//● Поток A меняет значение Boolean переменной switcher с задержкой 1000
//миллисекунд (true в состояние false и наоборот).
//● Поток B ожидает состояния true переменной switcher и выводит на консоль
//обратный отсчет от 100 с задержкой 100 миллисекунд и приостанавливает свое
//действие, как только поток A переключит switcher в состояние false.
//● Условием завершения работы потоков является достижение отсчета нулевой
//отметки.
//● Можно воспользоваться синхронизацией для управления значения переменной или
//volatile
public class Task2 {
    private static Boolean switcher = false;
    private static int counter = 100;

    public static void main(String[] args) {

        Runnable task1 = () -> {
            try {
                while (counter > 0) {
                    Thread.sleep(1000);
                    switcher = !switcher;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        };

        Runnable task2 = () -> {
            while (counter > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (switcher) {
                    System.out.println(counter--);
                }
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();
    }
}
