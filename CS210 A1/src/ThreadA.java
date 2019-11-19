
import java.util.Random;

public class ThreadA extends Thread {

    private SharedClass allNumbers;
    private int storage;

    public ThreadA(SharedClass SharedClass, int cap) {
        this.allNumbers = SharedClass;
        this.storage = cap;
    }

    public synchronized void run() {
        Random rand = new Random();
        int randomNum = storage * 100;

        for (int i = 0; i < randomNum; i++) {
            int temp = rand.nextInt(100);
            if (allNumbers.isNotFull()) {
                allNumbers.enqueue(temp);
                System.out.println(allNumbers.toString());

            } else {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        allNumbers.setThreadAFinished(true);
    }
}
