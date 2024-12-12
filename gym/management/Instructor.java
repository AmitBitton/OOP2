package gym.management;

import gym.customers.Gender;
import gym.customers.Person;

public class Instructor extends Person {

    public Instructor(String name, int balance, Gender gender, String birthDate) {
        super(name, balance, gender, birthDate);
    }
}
