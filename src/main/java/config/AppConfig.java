package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import db.PersonDAO;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import resources.PersonResource;
import services.PersonService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AppConfig extends Configuration implements DependencyInjectionConfiguration {
    @NotEmpty
    private String version;

    @JsonProperty
    public String getVersion() {
        return version;
    }

    @JsonProperty
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * The data configuration for MongoDB.
     */
    @Valid
    @NotNull
    private MongoDBConnection mongoDBConnection = new MongoDBConnection();


    @JsonProperty("mongoDBConnection")
    public MongoDBConnection getMongoDBConnection() {
        return mongoDBConnection;
    }

    @JsonProperty("mongoDBConnection")
    public void setMongoDBConnection(MongoDBConnection mongoDBConnection) {
        this.mongoDBConnection = mongoDBConnection;
    }

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }

    /**
     * I guess singletons are hardcoded here...
     * @return
     */
    public List<Class<?>> getSingletons() {
        final List<Class<?>> result = new ArrayList<>();
        result.add(PersonResource.class);
        result.add(PersonService.class);
        result.add(PersonDAO.class);

        return result;
    }

    @Override
    public List<NamedProperty<? extends Object>> getNamedProperties() {
        final List<NamedProperty<? extends Object>> result = new ArrayList<>();
        result.add(new NamedProperty<>("dbUser", "dummy_db_user", String.class));

        return result;
    }
}

