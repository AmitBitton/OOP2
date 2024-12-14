package gym.customers;

import gym.Exception.InvalidAgeException;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private List <Session> sessions;
    private List <String> notifications;
    private List <ForumType> forumTypes;

    public Client(String name, int balance, Gender gender, String birthDate) throws InvalidAgeException {
        super(name, balance, gender, birthDate);
        if (this.age<18){   //check if the person can be a client in the gym
            throw new InvalidAgeException();
        }
        this.sessions=new ArrayList<>(); // the sessions that the client register
        this.notifications=new ArrayList<>(); // all the messages the client ever get from the gym
        this.forumTypes= new ArrayList<>();
        this.forumTypes.add(ForumType.All);
        if (this.age>=65) {
            this.forumTypes.add(ForumType.Seniors);
        }
        if (this.getGender().equals(Gender.Female)){
            this.forumTypes.add(ForumType.Female);
        }
        else this.forumTypes.add(ForumType.Male);
    }

    public String getName() {
        return super.getName();
    }

    public String getNotifications() {
        return (notifications.toString());
    }
    public  List<ForumType> getforumType(){
     return forumTypes;
    }
    public String toString(){
         return ("ID: " + this.getId() + " | " +
                "Name: " + this.getName() + " | " +
                "Gender: " + this.getGender() + " | " +
                "Birthday: " + this.getBirthDate() + " | " +
                "Age: " + this.age + " | " +
                "Balance: " + this.getBalance());
    }

}
