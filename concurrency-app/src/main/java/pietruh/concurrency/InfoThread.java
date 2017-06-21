package pietruh.concurrency;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ppietrucha on 2017-06-21.
 */
public class InfoThread {
    private String name;
    private String stacktrace;
    private String state;
    private transient DataProvider dataProvider;
    

    public InfoThread(@Autowired DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public InfoThread(String name, String stacktrace, String state) {
        this.name = name;
        this.stacktrace = stacktrace;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public String getState() {
        return state;
    }

    public void inform() {

//        dataProvider.
    }

    @Override
    public String toString() {
        return "InfoThread{" + "name='" + name + '\'' + ", stacktrace='" + stacktrace + '\'' + ", state='" + state + '\'' + '}';
    }
}
