package app;

import com.google.inject.AbstractModule;
import config.AppConfig;
import db.MongoDBFactoryConnection;
import db.MongoDBManaged;
import db.OrderDAO;
import db.PersonDAO;
import db.entities.PersonEntity;
import healthchecks.DropwizardMongoDBHealthCheck;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Environment;

public class BasicModule extends AbstractModule {
    private AppConfig config;
    private Environment env;

    public BasicModule(AppConfig config, Environment env) {
        this.config = config;
        this.env = env;
    }

    /**
     * Add all entities here
     */
    private final HibernateBundle<AppConfig> hibernate = new HibernateBundle<AppConfig>(PersonEntity.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    protected void configure() {

        final MongoDBFactoryConnection mongoDBManagerConn = new MongoDBFactoryConnection(config.getMongoDBConnection());

        final MongoDBManaged mongoDBManaged = new MongoDBManaged(mongoDBManagerConn.getClient());

        final PersonDAO personDAO = new PersonDAO(
                mongoDBManagerConn.getClient()
                        .getDatabase(
                                config.getMongoDBConnection().getDatabase()).getCollection("persons")); // TODO complete
        /*
        new DonutDAO(mongoDBManagerConn.getClient()
                .getDatabase(configuration.getMongoDBConnection().getDatabase())
                .getCollection("donuts"));
         */

        bind(PersonDAO.class).toInstance(personDAO);

        // TODO needed for mySQL
//        final OrderDAO orderDAO = new OrderDAO(hibernate.getSessionFactory());
//        bind(OrderDAO.class).toInstance(orderDAO);


        env.lifecycle().manage(mongoDBManaged);
//        env.jersey().register(new DonutResource(donutDAO));
        env.healthChecks().register("DropwizardMongoDBHealthCheck",
                new DropwizardMongoDBHealthCheck(mongoDBManagerConn.getClient()));
    }
}
