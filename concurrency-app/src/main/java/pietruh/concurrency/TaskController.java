package pietruh.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pietruh.concurrency.controller.InfoTask;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by pietruh on 21.06.2017.
 */
@RequestMapping("/tasks")
@RestController
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    private DataProvider<InfoTask> dataProvider;

    public TaskController(@Autowired DataProvider<InfoTask> dataProvider) {
        this.dataProvider = dataProvider;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<InfoTask> tasks() {
        return dataProvider.getQueue().parallelStream().collect(Collectors.toList());
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public InfoTask getTask(@PathVariable("name") String name) {
        log.info("getTask(name = [{}])", new Object[] { name });

        Optional<InfoTask> any = dataProvider.getQueue().parallelStream().filter(it -> it.getName().equals(name)).findAny();
        return any.get();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void addTask(@RequestBody InfoTask infoTask) {
        log.info("addTask(infoTask = [{}])", new Object[] { infoTask });
        dataProvider.getQueue().offer(infoTask);

    }
}
