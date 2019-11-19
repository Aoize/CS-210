
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Enter the size of storage:");
        int storage = in.nextInt();

        Lock localLock = new ReentrantLock();
        SharedClass sharedClass = new SharedClass(localLock, storage);

        ThreadA A = new ThreadA(sharedClass, storage);
        A.start();
        
        ThreadB B = new ThreadB(sharedClass);
        B.start();
        
        ThreadC C = new ThreadC(sharedClass);
        C.start();

        try {
            A.join();
            B.join();
            C.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }

}
