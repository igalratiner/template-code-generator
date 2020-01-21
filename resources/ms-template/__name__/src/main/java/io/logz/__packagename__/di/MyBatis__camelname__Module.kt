package io.logz.__packagename__.di

import com.google.inject.Exposed
import com.google.inject.PrivateModule
import com.google.inject.Provides
import com.logshero.gaia.common.metric.GaiaMetricRegistry
import com.logshero.gaia.datastore.ManagedDataSource
import com.logshero.gaia.di.BaseMyBatisModule
import io.logz.__packagename__.configuration.__camelname__DataStoreConfiguration

import javax.inject.Singleton
import javax.sql.DataSource
import java.util.HashSet


import java.util.function.Consumer
import javax.inject.Named

class MyBatis__camelname__Module(val configuration: __camelname__DataStoreConfiguration) : PrivateModule() {

    private val exposedMappers = HashSet<Class<*>>()

    override fun configure() {
        install(InternalMyBatisModule())
        exposedMappers.forEach(Consumer<Class<*>> { this.expose(it) })
    }

    @Provides
    @Singleton
    internal fun provideManagedDataSource(metricRegistry: GaiaMetricRegistry): ManagedDataSource {
        return ManagedDataSource(
                configuration.host,
                configuration.port,
                configuration.user,
                configuration.password,
                configuration.optionalParams,
                configuration.schema,
                "__methodname__DB",
                configuration.migrationConfiguration,
                metricRegistry
        )
    }

    @Singleton
    @Provides
    private fun provideDataSource(managedDataSource: ManagedDataSource): DataSource {
        return managedDataSource.get()
    }

    // This binding is needed by the MyBatisTaskzModule since it expects a named DataSource
    // to prevent accidental usage of a DataSource bound from external modules
    @Singleton
    @Provides
    @Exposed
    @Named("TaskzDataSource")
    fun provideTaskzDataSource(managedDataSource: ManagedDataSource) : DataSource
    {
        return managedDataSource.get();
    }

    private inner class InternalMyBatisModule : BaseMyBatisModule() {

        override fun initialize() {
            init()
            /*
            Here put configuration directives for MyBatis - DB tables mapping XML configuration file:
            registerMapper({PersistentInterfaceName}Dao::class.java)

            And also aliases used in the MyBatis XML DB mapping configuration file:
            addSimpleAlias({DataClassName}::class.java)
            * */
        }

        private fun registerMapper(mapperClass: Class<*>) {
            addMapperClass(mapperClass)
            exposedMappers.add(mapperClass)
        }

    }

}
