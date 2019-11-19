
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class SharedClass {

    Queue<Integer> allNumbers;
    private boolean threadAFinished;
    private int storage;
    private Lock localLock;

    public SharedClass(Lock locallock, int cap) {
        allNumbers = new LinkedList<Integer>();
        this.localLock = locallock;
        this.storage = cap;
        this.threadAFinished = false;
    }

    public synchronized boolean enqueue(int num) {
        localLock.lock();
        if (allNumbers.size() < storage) {
            allNumbers.add(num);
            localLock.unlock();
            return true;
        } else {
            localLock.unlock();
            return false;
        }
    }

    public synchronized int dequeue() {
        localLock.lock();
        int head = ((LinkedList<Integer>) allNumbers).get(0);
        allNumbers.remove();
        localLock.unlock();
        return head;
    }

    public boolean isNotFull() {
        if (allNumbers.size() != storage) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return allNumbers.isEmpty();
    }

    public synchronized int peek() {
        if (allNumbers.size() > 0) {
            return ((LinkedList<Integer>) allNumbers).get(0);
        } else {
            return -1;
        }
    }

    public void setThreadAFinished(Boolean finished) {
        this.threadAFinished = finished;
    }

    public boolean getThreadAFin() {
        return threadAFinished;
    }

    public String toString() {
        LinkedList<Integer> queueTemp = (LinkedList<Integer>) ((LinkedList<Integer>) allNumbers).clone();
        return queueTemp.toString();
    }

}
