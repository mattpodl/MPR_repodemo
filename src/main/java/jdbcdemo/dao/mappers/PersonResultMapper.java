package jdbcdemo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcdemo.domain.Person;

public class PersonResultMapper implements ResultSetMapper<Person>{

	public Person map(ResultSet rs) throws SQLException {
		Person p = new Person();
		p.setId(rs.getInt("id"));
		p.setName(rs.getString("name"));
		p.setSurname(rs.getString("surname"));
		p.setAge(rs.getInt("age"));
		return p;
	}



}
