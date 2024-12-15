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
    private List<Client> clientsList = new ArrayList<>();
    private String sessionType;
    private int price;

    public Session(LocalDateTime date, Instructor instructor, ForumType forum, String sessionType, int price) {
        this.date = date;
        this.instructor = instructor;
        this.forum = forum;
        this.sessionType = sessionType;
        this.price = price;
    }

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
        return clientsList;
    }

    public int getNumRegistered() { return clientsList.size(); }

    public int getPrice() {
        return this.price;
    }
}
