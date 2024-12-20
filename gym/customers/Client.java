package gym.customers;

import gym.Exception.InvalidAgeException;
import gym.management.Observer;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.List;

public class Client  extends Person implements Observer {
    private List <Session> sessions;
    private List <String> notifications;
    private List <ForumType> forumTypes;
    private int balance;

    public Client(Person person) throws InvalidAgeException {
        super(person);
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
    public int getBalance(){return this.balance;}
    public void setBalance(int balance){
        this.balance=balance;
    }
    public void addSession(Session session){
        sessions.add(session);
    }
    public void removeSession(Session session){
        sessions.remove(session);
    }

    public String getName() {
        return super.getName();
    }
    @Override
    public void update(String notification){
        notifications.add(this.name + " received notification: " + notification);

    }

    public String getNotifications() {
        return (notifications.toString());
    }
    public  List<ForumType> getforumType(){
     return forumTypes;
    }
    public List<Session> getSessions(){
        return this.sessions;
    }
    public void deductBalance(int price) {
        int updateBalance= this.balance-price;
       this.balance=updateBalance;
    }

    public void addBalance(int amount) {
        balance += amount;
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
