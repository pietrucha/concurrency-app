package pietruh.concurrency;

import pietruh.concurrency.controller.InfoTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by pietruh on 21.06.2017.
 */
public class DataProvider<T> {
    BlockingQueue<T> queue = new ArrayBlockingQueue<>(100);

    protected BlockingQueue<T> getQueue() {
        return queue;
    }

    public void addAndStart(T t) {
        queue.offer(t);
        //        threadSet.stream().forEach(infoThread -> infoThread.inform());
        return;
    }

    public void notifyWorkers() {

    }
}
