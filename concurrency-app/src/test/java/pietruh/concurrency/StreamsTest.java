package pietruh.concurrency;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by ppietrucha on 2017-06-22.
 * workspace-concurrency
 */
public class StreamsTest {
    private final String states[] = { "WAIT", "RUNNING", "TERMINATE" };
    private Map<String, InfoThread> threadMap = new HashMap<>();

    @Before
    public void setup() throws Exception {
        Random random = new Random();
        threadMap = IntStream.rangeClosed(0, 20).mapToObj(
              value -> new InfoThread(UUID.randomUUID().toString(), null, states[ThreadLocalRandom.current().nextInt(3)],
                    new Date(Math.abs(System.currentTimeMillis() - random.nextInt(2_000_000_000))), new Random().nextInt(1000)))
              .collect(Collectors.toMap(o -> o.getName(), t -> t));
    }

    @Test
    public void streamTest() throws Exception {

        List<InfoThread> collect = getSortedStream().collect(Collectors.toList());
    HashMap<String,List<String>> maps = new HashMap<>();

        System.out.println("MAP ->" + threadMap.size());
        System.out.println("\n List size: " + collect.size());
        collect.forEach(System.out::println);

        int multipy = getSortedStream().mapToInt(InfoThread::getVotes).reduce(1, (left, right) -> (left * right));
        int sum = getSortedStream().mapToInt(InfoThread::getVotes).sum();
        System.out.println(sum + " " + multipy);
        // reduction - collect
        StringBuilder collect1 = getSortedStream().map(InfoThread::getName).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        getSortedStream().map(InfoThread::getName).collect(Collectors.joining());

        System.out.println(collect1);

        System.out.println("end");
    }

    @Test
    public void groupingBy() throws Exception {
        //        https://www.ibm.com/developerworks/java/library/j-java-streams-2-brian-goetz/index.html

        //        Map<Seller, List<Txn>> bigTxnsBySeller =
        //              txns.stream()
        //                    .filter(t -> t.getAmount() > 1000)
        //                    .collect(groupingBy(Txn::getSeller));
        //        getSortedStream()
    }

    private Stream<InfoThread> getSortedStream() {
        return threadMap.values().stream().filter(infoThread -> infoThread.getState().equals(states[1])).limit(2)
              .sorted(Comparator.comparing(InfoThread::getStartDate).reversed());
    }

}
