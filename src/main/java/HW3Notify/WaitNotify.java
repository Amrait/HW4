package HW3Notify;

/**
 * Created by Олексій on 16.02.2017.
 */
public class WaitNotify {
    private static final Object monitor = new Object();

    public static void main(String[] args) {
        WaitNotify wn = new WaitNotify();
        wn.DoMagic();
    }
    public void DoMagic(){
        for (int i = 1; i < 4; i++) {
            new Thread(new Handler(monitor,"Покупець " + i + " прийшов до магазину, очікує...")).start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new Thread(new Manager(monitor)).start();
    }

    public class Handler implements Runnable {
        private Object Monitor;
        private String Message;
        public Handler(Object monitor, String message) {
            this.Monitor = monitor;
            this.Message = message;
        }
        @Override
        public void run() {
            synchronized (this.Monitor) {
                System.out.println(Message);
                try {
                    Monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Все гаразд! Покупець в магазині.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Покупець йде.");
        }
    }
    public class Manager implements Runnable{
        private Object Monitor;
        public Manager(Object monitor){
            this.Monitor = monitor;
        }
        @Override
        public void run() {
            synchronized (Monitor){
                System.out.println("Менеджер тут, розпродаж розпочато!");
                        Monitor.notifyAll();
            }
        }
    }
}
