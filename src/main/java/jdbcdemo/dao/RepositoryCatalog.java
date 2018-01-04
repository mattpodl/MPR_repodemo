package jdbcdemo.dao;

import java.sql.Connection;
import java.sql.SQLException;

import jdbcdemo.dao.mappers.*;
import jdbcdemo.domain.*;

public class RepositoryCatalog {
	Connection connection;

	public RepositoryCatalog(Connection connection) {
		this.connection = connection;
	}

	public Repository<Person> people() {
		try {
			return new PersonRepository(connection, new PersonResultMapper());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}
	}

	public Repository<Car> cars() {
		try {
			return new CarRepository(connection, new CarResultMapper());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
}