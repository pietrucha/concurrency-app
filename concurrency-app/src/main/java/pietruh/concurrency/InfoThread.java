package pietruh.concurrency;

/**
 * Created by ppietrucha on 2017-06-21.
 */
public class InfoThread {
    private String name;
    private String stacktrace;
    private String state;

    public InfoThread() {
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

    @Override
    public String toString() {
        return "InfoThread{" + "name='" + name + '\'' + ", stacktrace='" + stacktrace + '\'' + ", state='" + state + '\'' + '}';
    }
}
