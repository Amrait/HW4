package HW3Apples;

import sun.security.krb5.internal.TGSRep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олексій on 16.02.2017.
 */
public class Simulation {
    private static final Object monitor = new Object();

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.DoMagic();
    }

    public void DoMagic() {
        ArrayList<Apple> apples = new ArrayList<>();
        Apple a1 = new Apple(200);
        apples.add(a1);
        AppleStorage appleStorage = new AppleStorage(apples, monitor, 200);
        Thread storage = new Thread(appleStorage);
        new Thread(new AppleShop(10, 2000, monitor, appleStorage, "Магазин №1")).start();
        new Thread(new AppleShop(10, 1000, monitor, appleStorage, "Магазин №2")).start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storage.start();
    }

}
