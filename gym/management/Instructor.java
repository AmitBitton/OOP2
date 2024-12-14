package gym.management;

import gym.customers.Gender;
import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.util.List;

public class Instructor extends Person {
    private int salaryPerHour;
    private List<String> certifiedClasses;

    public Instructor(Person person,int salaryPerHour, List<String> certifiedClasses) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.salaryPerHour = salaryPerHour;
        this.certifiedClasses = certifiedClasses;

    }
    public String toString(){
       return( "ID: " + this.getId() + " | " +
                "Name: " + this.getName() + " | " +
                "Gender: " + this.getGender() + " | " +
                "Birthday: " + this.getBirthDate() + " | " +
                "Age: " + this.age + " | " +
                "Balance: " + this.getBalance()) +
               "Role: Instructor | " +
               "Salary per Hour: " + this.salaryPerHour + " | " +
               "Certified Classes: " + String.join(", ", certifiedClasses.toString());
    }
    public List<String> getCertifiedClassescertifiedClasses(){
        return this.certifiedClasses;
    }
}
