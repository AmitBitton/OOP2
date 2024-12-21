package gym.customers;

import gym.Exception.InvalidAgeException;
import gym.management.Gym;
import gym.management.Observer;
import gym.management.Secretary;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person implements Observer {
    private List<Session> sessions;
    private List<String> notifications;
    private List<ForumType> forumTypes;
    private Person person;

    public Client(Person person) throws InvalidAgeException {
        super(person);
        this.person = person;
        if (this.age < 18) {   //check if the person can be a client in the gym
            throw new InvalidAgeException();
        }
        this.sessions = new ArrayList<>(); // the sessions that the client register
        this.notifications = new ArrayList<>(); // all the messages the client ever get from the gym
        this.forumTypes = new ArrayList<>();
        this.forumTypes.add(ForumType.All);
        if (this.age >= 65) {
            this.forumTypes.add(ForumType.Seniors);
        }
        if (this.getGender().equals(Gender.Female)) {
            this.forumTypes.add(ForumType.Female);
        } else this.forumTypes.add(ForumType.Male);
    }

    public int getBalance() {
        return Gym.getInstance().getBalanceById(this.id);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public void update(String notification) {
        notifications.add(notification);

    }

    public String getNotifications() {
        return (notifications.toString());
    }

    public List<ForumType> getforumType() {
        return forumTypes;
    }

    public List<Session> getSessions() {
        return this.sessions;
    }

    public String toString() {
        return ("ID: " + this.getId() + " | " +
                "Name: " + this.getName() + " | " +
                "Gender: " + this.getGender() + " | " +
                "Birthday: " + this.getBirthDate() + " | " +
                "Age: " + this.age + " | " +
                "Balance: " + super.getBalance());
    }

}
