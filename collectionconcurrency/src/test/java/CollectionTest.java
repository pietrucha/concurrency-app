import org.junit.Test;

import java.util.concurrent.*;
import java.util.stream.Stream;

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

    @Test
    public void testBlockingQueue() throws Exception {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>(10);

        ExecutorService es = Executors.newFixedThreadPool(4);
        Callable<String> cp = () -> {
            String poll = blockingQueue.poll();
            return poll;
        };
        Runnable co = () -> {
            try {
                blockingQueue.put(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        es.submit(co);
        //Future<String> submit2 = es.submit(cp);
        Callable<Object> callablewrite = new Callable<Object>() {
            public Object call() throws Exception {
                Stream.generate(() -> cp).parallel().map(stringCallable -> {
                    try {
                        return es.submit(stringCallable).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                }).filter(s -> s != null).forEach(System.out::println);
                return null;
            }
        };

        Callable<Object> callable = new Callable<Object>() {
            public Object call() throws Exception {
                Stream.generate(() -> co).limit(100_000).parallel().forEach(o -> {
                    try {
                        System.out.println("sleep");
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    es.submit(o);
                });
                return null;
            }
        };d

        es.submit(callablewrite);
        es.submit(callable);

        Thread.currentThread().join();

    }
}
