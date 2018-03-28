package ru.fella.jdbc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


@Slf4j
@Stateless
@Data
public class DAO {
    @Resource(lookup = "java:/PostgresDS")
    DataSource ds;

    public DAO() throws NamingException {
    }

    public void addNewEntry() {
        try (Connection connection = ds.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("insert  into ella_table(entry) values('К нам зашел новый пользователь')");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
