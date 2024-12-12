package gym.customers;

import gym.Exception.InvalidAgeException;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private List <Session> sessions;
    private List <String> notifications;
    public Client(String name, int balance, Gender gender, String birthDate) throws InvalidAgeException {
        super(name, balance, gender, birthDate);
        if (this.age<18){   //check if the person can be a client in the gym
            throw new InvalidAgeException();
        }
        this.sessions=new ArrayList<>(); // the sessions that the client register
        this.notifications=new ArrayList<>(); // all the messages the client ever get from the gym
    }

    public String getName() {
        return super.getName();
    }

    public String getNotifications() {
        return (notifications.toString());
    }
}
