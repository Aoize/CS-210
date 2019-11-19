
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ThreadB extends Thread {

    private SharedClass sharedClass;

    public ThreadB(SharedClass SharedClass) {
        sharedClass = SharedClass;
    }

    private void fileWriter(int numWriter) throws IOException {
        BufferedWriter write = new BufferedWriter(new FileWriter("Odd.txt", true));
        write.write(numWriter + System.getProperty("line.separator"));
        write.close();
    }

    public synchronized void run() {
        while (sharedClass.getThreadAFin() != true) {
            if (sharedClass.peek() % 2 == 1) {
                int newNum = sharedClass.dequeue();
                try {
                    fileWriter(newNum);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
