package HW4Task1;

import java.util.HashMap;
import java.util.concurrent.*;

/**
 * Created by Олексій on 15.02.2017.
 */
public class NovaPoshta {
    public static void main(String[] args) {
        NovaPoshta np = new NovaPoshta();
        np.Delivery();
    }
    public void Delivery(){
        CyclicBarrier starter = new CyclicBarrier(2);//аби розпочати роботу одночасно
        HashMap<String,Integer> A = new HashMap<>();//карта відстаней для пункту А
        A.put("Пункт В",1000);//вказуємо ім'я пункту і час дороги до нього
        HashMap<String,Integer> B = new HashMap<>();//карта відстаней до пункту В
        B.put("Пункт А",3000);
        Outlet PointA = new Outlet("Пункт А",A,1,10);//створюємо відділення А
        Outlet PointB = new Outlet("Пункт В",B,5,5);//створюємо відділення В
        Thread WorkPointA = new Thread(new Runner(PointA,PointB,starter));//створюємо потік роботи відділення А
        Thread WorkPointB = new Thread(new Runner(PointB,PointA,starter));//створюємо потік роботи відділення В
        //розпочинаємо потоки
        WorkPointA.start();
        WorkPointB.start();
    }
}
