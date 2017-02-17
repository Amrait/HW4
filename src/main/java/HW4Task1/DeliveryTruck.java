package HW4Task1;

/**
 * Created by Олексій on 15.02.2017.
 */
public class DeliveryTruck implements Runnable{
    private Outlet Origin;//початкова точка
    private Outlet Destination;//точка доставки
    private String Name;//ім'я машини
    private int Count;//кількість зроблених рейсів
    //створюємо машину доставки
    public DeliveryTruck(Outlet origin, Outlet destination, String name){
        this.Origin = origin;
        this.Destination = destination;
        this.Name = name;
        this.Count = 0;
    }
    public void run() {
        boolean isAlreadyWaiting = false;//допоміжна змінна, аби не засмічувати лог подій.
        while (true)//у безкінечному циклі перевіряємо
        {
            if (this.Count < 10){//якщо водій ще не втомився
                if (Origin.Packages>0){//чи є на пункті відправлення щось для транспортування
                    //якщо є
                    try {
                        isAlreadyWaiting = false;//говоримо, що не чекаємо
                        System.out.println(this.Name + " розпочала завантаження");
                        this.Origin.Packages--;//зменшуємо кількість пакунків на відділенні
                        Thread.sleep(2000);//вантажимось
                        System.out.println(this.Name + " готова. Вирушаємо!");
                        Thread.sleep(Origin.Distances.get(Destination.Name));//"їдемо" до пункту призначення, базуючись на відстані до нього
                        System.out.println(this.Name + " прибула до " + Destination.Name +", розвантажуємось...");
                        Thread.sleep(2000);//розвантажуємось
                        this.Destination.Packages++;//збільшуємо кількість пакунків на складі
                        Count++;//повідомляємо, що водій здійснив рейс
                        System.out.println("Водій " + this.Name + " здійснив " + this.Count + " поїздок");
                        Thread.sleep(Destination.Distances.get(Origin.Name));//повертаємось порожніми назад
                        System.out.println(this.Name + " прибула до " + Origin.Name);
                        Count++;//повідомляємо, що водій здійснив рейс
                        System.out.println("Водій " + this.Name + " здійснив " + this.Count + " поїздок");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{//якщо ж пакунків немає
                    if(!isAlreadyWaiting) {//і якщо ми ще не оікуємо на пакунки
                        System.out.println("Поки що немає чого везти з " + this.Origin.Name + ". " + this.Name + " очікує...");
                        isAlreadyWaiting = true;//говоримо, що вже очікуємо, аби не виводити повідомлення щопівсекунди
                    }
                    try {
                        Thread.sleep(500);//чекає відправлень
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {//якщо ж водій втомився
                System.out.println("Водій " + this.Name + " надто втомлений, аби сьогодні кудись їздити!");
                return;//виходимо з потоку
            }
        }
    }
}
