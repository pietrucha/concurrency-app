package pietruh.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by ppietrucha on 2017-06-21.
 */
@RestController
@RequestMapping("/threads")
public class ThreadsController {
    private static final Logger log = LoggerFactory.getLogger(ThreadsController.class);
    Map<String, Thread> threads = new HashMap<>();

    @RequestMapping(method = RequestMethod.GET)
    public List<InfoThread> threads() {
        log.info("all threads");
        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        List<InfoThread> collect = threads.stream()
              .map(thread -> new InfoThread(thread.getName(), stacktraceToString(thread.getStackTrace()), thread.getState().name(), new Date(),
                    new Random().nextInt(1000)))
              .collect(Collectors.toList());
        return collect;
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public InfoThread threads(@PathVariable("name") String name) {
        log.info("get (name = [{}])", new Object[] { name });
        Optional<Thread> first = Thread.getAllStackTraces().keySet().stream().filter(thread -> thread.getName().equalsIgnoreCase(name)).findFirst();
        Thread thread = first.get();
        return new InfoThread(thread.getName(), stacktraceToString(thread.getStackTrace()), thread.getState().name(), new Date(), new Random().nextInt(1000));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void thread(@RequestBody InfoThread infoThread) {
        log.info("add (infoThread = [{}])", new Object[] { infoThread });

    }

    private String stacktraceToString(StackTraceElement[] stackTrace) {
        String collect = Arrays.stream(stackTrace).map(s -> s.toString()).collect(Collectors.joining());
        return collect;
    }

    private void show() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 100, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));
        ExecutorService executorService = Executors.newCachedThreadPool();
        BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
        Stack stack = null;
    }
}
