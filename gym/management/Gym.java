package gym.management;

import gym.customers.*;
import gym.management.Sessions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gym {
    private static Gym instance;
    private String name; //the gym name
    private Secretary secretary;
    private List<Client> clients = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();
    private List<Secretary> secretaries= new ArrayList<>();
    private int gymBalance;
    private List<String> actionsHistory=new ArrayList<>();
    private Map<Integer,Integer> balanceIDMap=new HashMap<>();
    private Notifyer notifyer= new Notifyer();

    private Gym() {
        this.name="CrossFit";
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
    public void setGymBalance(int balance){
        this.gymBalance=balance;
    }
    public void addToGymBalance(int num){
        setGymBalance(this.gymBalance+num);
    }
    public void addPerson(Person person) {
        balanceIDMap.put(person.getId(), person.getBalance());
    }
    public Map<Integer, Integer> getPeopleIDMap() {
        return balanceIDMap;
    }
    public int getBalanceById(int id) {
        return balanceIDMap.getOrDefault(id, 0);
    }

    public Notifyer getNotifyer(){
        return this.notifyer;
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

    public int getGymBalance() {
        return this.gymBalance;
    }

    public void setSecretary(Person p1, int salary) {
        if (this.secretary != null) {
        secretaries.add(this.secretary);
        }
        this.secretary = new Secretary(p1, salary);
        this.secretaries.add(secretary);
    }

    public Secretary getSecretary() {
        return secretary;
    }

    protected void addClient(Client client) {
        this.clients.add(client);
    }

    protected void removeClient(Client client) {
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
    public void addAction(String action){
        actionsHistory.add(action);
    }

    public List<String> getActionsHistory() {
        return actionsHistory;
    }
    public List<Client>getClients(){
        return this.clients;
    }
    public void updateGymBalance(int newBalance){
        this.gymBalance=newBalance;
    }

}
