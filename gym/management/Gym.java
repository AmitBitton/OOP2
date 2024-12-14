package gym.management;

import gym.customers.*;
import gym.management.Sessions.*;

import java.util.ArrayList;
import java.util.List;

public class Gym {
    private static Gym instance;
    private String name; //the gym name
    private Secretary secretary;
    private List<Client> clients = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();
    private int gymBalance;

    private Gym() {
    }

    public static Gym getInstance() {
        if (instance == null) {
            instance = new Gym();
        }
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gym Name: ").append(name).append("\n");
        sb.append("Gym Secretary: ").append(secretary.toString()).append("\n");
        sb.append("Gym Balance: ").append(getGymBalance()).append("\n");
        sb.append("\nClients Data:\n");
        for (Client client : clients) {
            sb.append(client.toString()).append("\n");
        }
        sb.append("\nEmployees Data:\n");
        for (Instructor instructor : instructors) {
            sb.append(instructor.toString()).append("\n");
        }
        sb.append("\nSessions Data:\n");
        for (Session session : sessions) {
            sb.append(session.toString()).append("\n");
        }
        return sb.toString();
    }

    private int getGymBalance() {
        return this.gymBalance;
    }

    public void setSecretary(Person p1, int salary) {
        if (this.secretary != null) {
            //לחסום את המזכירה הישנה.
        }
        this.secretary = new Secretary(p1, salary);
        System.out.println("A new secretary has started working at the gym: " + this.getSecretary().getName());
    }

    public Secretary getSecretary() {
        return secretary;
    }

    protected void addClient(Client client) {
        this.clients.add(client);
    }

    public void removeClient(Client client) {
        if (clients.contains(client)) {
            this.clients.remove(client);
        }
    }
    public List<Session> getSessions(){
        return this.sessions;
    }
    public List<Instructor> getInstructors(){
        return this.instructors;
    }
}
