package HW4Task1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Олексій on 15.02.2017.
 */
//службовий потік, який буде автономно запускати машини з відділення. Цікавить, чи є краща опція для цього?
public class Runner implements Runnable{
    private Outlet Origin;//точка відправки
    private Outlet Destination;//точка доставки
    private CyclicBarrier Starter;//бар'єр, по якому розпочинаємо роботу
    public Runner(Outlet origin, Outlet destination, CyclicBarrier starter){
        this.Origin = origin;
        this.Destination = destination;
        this.Starter = starter;
    }
    public void run() {
        //очікуємо початку робочого дня
        try {
            this.Starter.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        int counter = 1; //службова змінна для нумерації машин
        ExecutorService truckPark = Executors.newFixedThreadPool(this.Origin.getTrucks());//створюємо автопарк на усі доступні відділенню машини
        //допоки лічильник менший за кількість машин +1 ми робимо виклик завдання доставки
        while(counter<this.Origin.getTrucks()+1){
            truckPark.submit(new DeliveryTruck(this.Origin,this.Destination,"Вантажівка #"+counter + " з "+ Origin.Name));
            counter++;
        }
    }
}

