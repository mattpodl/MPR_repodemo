package jdbcdemo.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import jdbcdemo.dao.*;
import jdbcdemo.domain.*;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Repository<Person> repo = null;
		Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb", "SA", "");
			RepositoryCatalog repCat = new RepositoryCatalog(connection);
			repo = repCat.people();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Person janek = new Person();
		janek.setAge(21);
		janek.setName("Piotrek");
		janek.setSurname("Kowalewski");
		janek.setId(7);

		if (repo != null) {
			repo.update(janek);
			List<Person> people = repo.getAll();
			// List<Car> cars = carRepo.getAll();

			for (Person p : people) {
				System.out.println(p.getId()
						+ "\t\t" + p.getName()
						+ "\t\t" + p.getSurname()
						+ "\t\t" + p.getAge());
			}



		}
		/*
		 * System.out.println("Samochody: "); for(Car c : cars){
		 * System.out.println(c.getId()+"\t" + c.getBrand()+"\t" +
		 * c.getRegistration()+"\t"); }
		 */
		System.out.println("Koniec");
	}
}
