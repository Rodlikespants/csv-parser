package app;

import config.AppConfig;
import healthchecks.AppHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.PersonResource;

/**
 * java -jar sample-dropwizard-rest-stub-1.0-SNAPSHOT.jar server ../config.yml
 *
 */
public class App extends Application<AppConfig> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void run(AppConfig config, Environment env) {
        final PersonResource personResource = new PersonResource();
        env.jersey().register(personResource);

        env.healthChecks().register("template",
                new AppHealthCheck(config.getVersion()));
    }
    public static void main( String[] args ) throws Exception {
        LOGGER.info("About to run app...");
        new App().run(args);
    }
}
