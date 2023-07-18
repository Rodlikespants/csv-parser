package app;

import config.AppConfig;
import healthchecks.AppHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import services.PersonService;

/**
 * java -jar sample-dropwizard-rest-stub-1.0-SNAPSHOT.jar server ../config.yml
 *
 */
public class App extends Application<AppConfig> {

    @Override
    public void run(AppConfig config, Environment env) {
        final PersonService personService = new PersonService();
        env.jersey().register(personService);

        env.healthChecks().register("template",
                new AppHealthCheck(config.getVersion()));
    }
    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }
}
