package gym.customers;

import gym.management.Gym;

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
    protected int balance;
    protected int age;
    protected int id;
    private static int nextId = 1110;
    private static Map<String, Integer> idMap = new HashMap<>();

    public Person(String name, int balance, Gender gender, String birthDate) {
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;
        String uniqueKey = generateUniqueKey(name, birthDate, gender);
        if (idMap.containsKey(uniqueKey)) {
            this.id = idMap.get(uniqueKey);
        } else {
            this.id = ++nextId;
            idMap.put(uniqueKey, this.id);
        }
        try {
            this.age = calculateAge(birthDate);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }
        Gym.getInstance().addPerson(this);
    }
    //copy constructor
    public Person(Person person) {
        this.name = person.getName();
        this.balance = person.getBalance();
        this.gender = person.getGender();
        this.birthDate = person.getBirthDate();
        this.id = person.getId();
        try {
            this.age = calculateAge(birthDate);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format: " + e.getMessage());
        }
        Gym.getInstance().addPerson(this);
    }

    private String generateUniqueKey(String name, String birthDate, Gender gender) {
        return name.toLowerCase() + "|" + birthDate + "|" + gender;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getBalance() {
        int balance = this.balance;
        try {
            this.balance = Gym.getInstance().getBalanceById(this.id);
        }
        catch (Exception e) {
            this.balance = balance;
        }
        return this.balance;
    }

    public int getId() {
        return this.id;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getBirthDate() {
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

    public void deductBalance(int price) {
        this.balance = this.getBalance() - price;
        Gym.getInstance().updatePersonBalance(this.id, this.balance);
    }

    public void addBalance(int amount) {
        this.balance = this.getBalance() + amount;
        Gym.getInstance().updatePersonBalance(this.id, this.balance);
    }
}

