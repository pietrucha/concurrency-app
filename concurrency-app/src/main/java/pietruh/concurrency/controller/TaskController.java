package pietruh.concurrency.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pietruh.concurrency.InfoThread;

import java.util.Collections;
import java.util.List;

/**
 * Created by ppietrucha on 2017-06-21.
 */
@RequestMapping("/tasks")
public class TaskController {
    @RequestMapping(method = RequestMethod.GET)
    public List<InfoTask> tasks() {
        return Collections.emptyList();
    }
}
