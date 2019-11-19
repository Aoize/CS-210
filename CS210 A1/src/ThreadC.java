
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ThreadC extends Thread {

    private SharedClass sharedClass;

    public ThreadC(SharedClass SharedClass) {
        sharedClass = SharedClass;
    }

    private void fileWriter(int numWriter) throws IOException {
        BufferedWriter write = new BufferedWriter(new FileWriter("Even.txt", true));
        write.write(numWriter + System.getProperty("line.separator"));
        write.close();
    }

    public synchronized void run() {
        while (sharedClass.getThreadAFin() != true) {
            if (sharedClass.peek() % 2 == 0) {
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
