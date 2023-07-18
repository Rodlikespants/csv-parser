package healthchecks;

import com.codahale.metrics.health.HealthCheck;
import db.PersonDB;

public class AppHealthCheck extends HealthCheck {
    private final String version;

    public AppHealthCheck(String version) {
        this.version = version;
    }

    @Override
    protected Result check() throws Exception {
        if (PersonDB.getCount() == 0) {
            return Result.unhealthy("No persons in DB! Version: " +
                    this.version);
        }
        return Result.healthy("OK with version: " + this.version +
                ". Persons count: " + PersonDB.getCount());
    }
}
