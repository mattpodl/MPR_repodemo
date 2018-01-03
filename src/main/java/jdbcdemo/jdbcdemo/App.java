package jdbcdemo.jdbcdemo;

import java.util.List;

import jdbcdemo.dao.*;
import jdbcdemo.domain.*;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {


    	PersonRepository repo = new PersonRepository();
    	CarRepository carRepo = new CarRepository();
    	/* 	carRepo.createTable();
        	repo.createTable();

    	Person janek = new Person();
    	janek.setAge(40);
    	janek.setName("TAT");
    	janek.setSurname("KKKKki");

    	repo.add(janek);

    	Car samochod = new Car();
    	samochod.setBrand("opel");
    	samochod.setRegistration("GDA 123");
    	carRepo.add(samochod);
*/
    	List<Person> people = repo.getAll();
    	List<Car> cars = carRepo.getAll();

    	for(Person p : people){
    		System.out.println(p.getId()+"\t"
    				+ p.getName()+"\t"
    				+ p.getSurname()+"\t"
    				+ p.getAge());
    	}
    	System.out.println("Samochody: ");
    	for(Car c : cars){
    		System.out.println(c.getId()+"\t"
    				+ c.getBrand()+"\t"
    				+ c.getRegistration()+"\t");
    	}
/**/
        System.out.println( "Koniec" );
    }
}
