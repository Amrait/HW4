package HW3Apples;

/**
 * Created by Олексій on 16.02.2017.
 */
public class AppleShop implements Runnable {
    private int ForSale;
    private long TimeToSell;
    private Object Monitor;
    private AppleStorage Storage;
    private String ShopName;

    public AppleShop(int forSale, long sellTime, Object monitor, AppleStorage appleStorage, String shopName) {
        this.ForSale = forSale;
        this.TimeToSell = sellTime;
        this.Monitor = monitor;
        this.Storage = appleStorage;
        this.ShopName = shopName;
    }

    @Override
    public void run() {
        synchronized (Monitor) {
            try {
                Monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            if (this.Storage.SummaryAmount - this.ForSale >= 0) {
                System.out.printf("Хоп, %s забрав свої %d яблук зі складу. Чекаємо реалізації...\n", this.ShopName, this.ForSale);
                this.Storage.SummaryAmount -= this.ForSale;
                try {
                    Thread.sleep(this.TimeToSell);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Так-с, %s реалізував усі яблука. Спробуємо отримати ще.\n", this.ShopName);
            } else {
                System.out.printf("Поки що яблук на складі немає. %s чекає нових поставок...\n", this.ShopName);
                synchronized (Monitor) {

                    try {
                        Monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
