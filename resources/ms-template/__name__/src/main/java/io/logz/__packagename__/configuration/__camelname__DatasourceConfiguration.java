package io.logz.__packagename__.configuration;

import com.logshero.gaia.datastore.MigrationConfiguration;
import com.logshero.gaia.infra.engine.DataStoreConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import static com.google.common.base.Preconditions.checkArgument;

public class __camelname__DatasourceConfiguration extends DataStoreConfiguration {

    private final String schema;

    public __camelname__DatasourceConfiguration(JSONObject json) {
        super(json, StringUtils.EMPTY);

        this.schema = this.sectionObject.getString("schema");

        checkArgument(StringUtils.isNotBlank(schema));
    }

    @Override
    protected MigrationConfiguration createMigrationConfiguration() {
        return new MigrationConfiguration.Builder()
                .enabled(true)
                .ignoreMissingMigrations(true)
                .locations("classpath:db/migration/__packagename__", "filesystem:/etc/gaia/db/migration/__packagename__")
                .build();
    }

    @Override
    public String section() { return "dataStore"; }

    public String getSchema() {
        return schema;
    }

}
