import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by ppietrucha on 2017-06-26.
 * workspace-concurrency
 */
public class CollectionTest {

    @Test
    public void reviewOfConcurrentCollections() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<String> callable = () -> {
            TimeUnit.SECONDS.sleep(10l);
            Thread thread = Thread.currentThread();
            thread.interrupt();
            if (thread.isInterrupted())
                return "Hello from: " + thread.getName();
            return " null";
        };
        Future<String> submit = executorService.submit(callable);
        submit.cancel(false);
        System.out.println(submit.get());

    }
}
