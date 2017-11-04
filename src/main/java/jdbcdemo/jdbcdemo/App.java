package jdbcdemo.jdbcdemo;

import java.util.List;

import jdbcdemo.dao.PersonRepository;
import jdbcdemo.domain.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	PersonRepository repo = new PersonRepository();
    	repo.createTable();
    	
    	Person janek = new Person();
    	janek.setAge(30);
    	janek.setName("Jan");
    	janek.setSurname("Kowalski");
    	
    	repo.add(janek);
    	
    	List<Person> people = repo.getAll();
    	for(Person p : people){
    		System.out.println(p.getId()+"\t" 
    				+ p.getName()+"\t"
    				+ p.getSurname()+"\t"
    				+ p.getAge());
    	}
    	
        System.out.println( "Koniec" );
    }
}
