package gym.customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class Person {
protected final String name;
protected final Gender gender;
protected final String birthDate;
protected int age;
protected int id;
private static int nextId= 1110;
    private static Map<String,Integer> idMAap= new HashMap<>();
    private static Map<Integer, Person> idMap = new HashMap<>();


private int balance;
    public Person(String name, int balance, Gender gender, String birthDate) {
        this.name=name;
        this.balance=balance;
        this.gender=gender;
        this.birthDate=birthDate;
          String uniqueKey = generateUniqueKey(name, birthDate, gender);
        if (idMAap.containsKey(uniqueKey)) {
            this.id = idMAap.get(uniqueKey);
        } else {
            this.id = ++nextId;
            idMAap.put(uniqueKey, this.id);
        }

        try {
            this.age = calculateAge(birthDate);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }
    }
    public Person(Person person) {
        this.name= person.name;
        this.balance=person.balance;
        this.gender=person.gender;
        this.birthDate=person.birthDate;
        this.id= person.id;
        try {
            this.age = calculateAge(birthDate);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }
    }
    private String generateUniqueKey(String name, String birthDate, Gender gender) {
        return name.toLowerCase() + "|" + birthDate + "|" + gender;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public int getBalance(){
        return this.balance;
    }
    public int getId(){
        return this.id;
    }
    public Gender getGender(){
        return this.gender;
    }
    public String getBirthDate(){
        return this.birthDate;
    }
    private int calculateAge(String birthDateString) {
            // Define the expected date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            // Parse the input date string into a LocalDate object
            LocalDate birthDate;
            try {
                birthDate = LocalDate.parse(birthDateString, formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date string. Expected format: dd-MM-yyyy");
            }
            // Get the current date
            LocalDate currentDate = LocalDate.now();
            // Calculate the age
            if (birthDate.isAfter(currentDate)) {
                throw new IllegalArgumentException("Birthdate cannot be in the future.");
            }
            return Period.between(birthDate, currentDate).getYears();
        }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id == person.id;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }


}

