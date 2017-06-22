package pietruh.concurrency.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import pietruh.concurrency.DataProvider;
import pietruh.concurrency.InfoThread;
import pietruh.concurrency.controller.InfoTask;

/**
 * Created by pietruh on 22.06.2017.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public DataProvider<InfoThread> dataProvider() {
        return new DataProvider<InfoThread>();
    }

    @Bean
    public DataProvider<InfoTask> taskDataProvider() {
        return new DataProvider<InfoTask>();
    }
}
