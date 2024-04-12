

package com.mycompany.kovtask1_14;




import java.util.concurrent.Semaphore;
class Student implements Runnable {
    private int id;
    private Semaphore table;
    public Student(int id, Semaphore table) {
        this.id = id;
        this.table = table;
    }
    @Override
    public void run() {
        try {
            System.out.println("Student " + id + " waiting");
            table.acquire(); // пытаемся занять столик
            System.out.println("Student " + id + " eating");
            Thread.sleep(3000); // имитация приема пищи
            System.out.println("Student " + id + " exit");
            table.release(); // освобождаем столик
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class KovTask1_14 {
    public static void main(String[] args) {
        System.out.print("Вариант 2 Ковальчук Артем Викторович\n");
        Semaphore table = new Semaphore(2); // создаем семафор
        // создаем и запускаем потоки для каждого студента
        for (int i = 1; i <= 7; i++) {
            Thread studentThread = new Thread(new Student(i, table));
            studentThread.start();
        }
    }
}
