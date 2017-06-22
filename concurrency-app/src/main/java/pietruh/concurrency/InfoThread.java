package pietruh.concurrency;

import org.springframework.beans.factory.annotation.Autowired;

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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof InfoThread))
            return false;

        InfoThread that = (InfoThread) o;

        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (stacktrace != null ? !stacktrace.equals(that.stacktrace) : that.stacktrace != null)
            return false;
        return state != null ? state.equals(that.state) : that.state == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (stacktrace != null ? stacktrace.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InfoThread{" + "name='" + name + '\'' + ", stacktrace='" + stacktrace + '\'' + ", state='" + state + '\'' + '}';
    }
}
