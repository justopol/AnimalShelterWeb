package pl.shelter.rest.config;

import jakarta.annotation.sql.DataSourceDefinition;

import java.sql.Connection;

@DataSourceDefinition(
        name = "java:app/jdbc/appdbDS",
        className = "org.postgresql.ds.PGSimpleDataSource",
        user = "postgres",
        password = "justyna",
        serverName = "127.0.0.1",
        portNumber = 5433,
        databaseName = "animalshelterdatabase",
        isolationLevel = Connection.TRANSACTION_READ_COMMITTED
)

public class JDBCConfig {
}
