package gym.management.Sessions;

import gym.Exception.DuplicateClientException;
import gym.Exception.InvalidAgeException;
import gym.customers.Client;
import gym.management.Instructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Session {
    private LocalDateTime date;
    private Instructor instructor;
    private ForumType forum;
    private List<Client> clientsList;
    private String sessionType;
    private int price;

    public Session(LocalDateTime date, Instructor instructor, ForumType forum, String sessionType, int price) {
        this.date = date;
        this.instructor = instructor;
        this.forum = forum;
        this.sessionType = sessionType;
        this.price = price;
        this.clientsList=new ArrayList<>();

    }
    public abstract int getMaxParticipants();

        public LocalDateTime getDate() {
        return this.date;
    }

    public String getSessionType() {
        return sessionType;
    }

    public ForumType getForum() {
        return forum;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Client> getRegisteredClients() {

        if (clientsList==null){
            clientsList= new ArrayList<>();
        }
        return clientsList;
    }

    public int getNumRegistered() { return clientsList.size(); }
    public boolean canRegister(){
        return getNumRegistered()<getMaxParticipants();
    }

    public int getPrice() {
        return this.price;
    }
    public String toString(){
        return ("Session Type: " + this.sessionType + " | " +
                "Date: " + this.date + " | " +
                "Forum: " + this.forum + " | " +
                "Instructor: " + this.instructor.getName() + " | " +
                "Participants: " + this.clientsList.size()+"/"+this.getMaxParticipants() );
    }

}
