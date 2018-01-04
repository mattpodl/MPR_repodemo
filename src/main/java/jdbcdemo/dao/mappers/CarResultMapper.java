package jdbcdemo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcdemo.domain.Car;

public class CarResultMapper implements ResultSetMapper<Car> {

	public Car map(ResultSet rs) throws SQLException {
		Car c = new Car();
		c.setId(rs.getInt("id"));
		c.setBrand(rs.getString("brand"));
		c.setRegistration(rs.getString("registration"));
		return c;
	}

}
