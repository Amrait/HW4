package HW3Apples;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олексій on 16.02.2017.
 */
public class AppleStorage implements Runnable {
    public volatile int SummaryAmount;
    private ArrayList<Apple> Apples;
    private int ResupplyAmount;
    private Object Monitor;

    public AppleStorage(List apples, Object monitor, int ressuplyAmount) {
        this.Apples = new ArrayList<>(apples);
        this.Monitor = monitor;
        this.ResupplyAmount = ressuplyAmount;
        for (Apple a : this.Apples
                ) {
            this.SummaryAmount += a.getAmount();
        }
    }

    @Override
    public void run() {
        synchronized (Monitor) {
            try {
                System.out.println("Storage is here!");
                Monitor.notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (true) {

            if (this.SummaryAmount <= 9) {
                System.out.println("Ой, лишенько! Яблука закінчилися! Робимо постачання яблук...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.SummaryAmount += ResupplyAmount;
                System.out.println("Фюх, яблука знову тут. Відпочиваємо...");
                synchronized (Monitor) {
                    Monitor.notifyAll();
                }
            } else {
                System.out.println("Apple Amount: " + this.SummaryAmount);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

