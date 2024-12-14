package gym.customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Person {
private final String name;
private final Gender gender;
private final String birthDate;
protected int age;
private int id;
private static int nextId= 1110;
private int balance;
    public Person(String name, int balance, Gender gender, String birthDate) {
        this.name=name;
        this.balance=balance;
        this.gender=gender;
        this.birthDate=birthDate;
        this.id=nextId++;
        try {
            this.age = calculateAge(birthDate);
            System.out.println("Age: " + age);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }
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
    }

