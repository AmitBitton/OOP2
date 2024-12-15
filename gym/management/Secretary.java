package gym.management;
import gym.DateUtils;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.management.Sessions.*;
import gym.customers.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Secretary extends Person {
    private int salary;
    private Person person;
    private Gym gym= Gym.getInstance();

    public Secretary(Person person, int salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.salary = salary;
        gym.addAction("A new secretary has started working at the gym: "+ this.getName());

    }

    private void checkPermissions() throws NullPointerException {
        if (Gym.getInstance().getSecretary() != this)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
    }

    public void notify(String s) {
    }

    public void notify(String s, String s1) {
    }

    public void notify(Session s, String s1) {
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<String> sessionTypes) {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        Instructor newInstructor = new Instructor(person,salary,sessionTypes);
        if (Gym.getInstance().getInstructors().contains(newInstructor)){
            return null;
        }
        Gym.getInstance().getInstructors().add(newInstructor);
        gym.addAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        return newInstructor;
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        Client newClient = new Client(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        if (gym.getClients().contains(newClient)){
            gym.addAction("Error: The client is already registered");
            throw new DuplicateClientException();
        }
        Gym.getInstance().addClient(newClient);
        gym.addAction("Registered new client: " + newClient.getName());
        return newClient;
    }

    public void unregisterClient(Client client) {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }
        if (!gym.getClients().contains(client)){
            gym.addAction("Error: Registration is required before attempting to unregister");
        }
        gym.addAction("Unregistered client: " + client.getName());
        Gym.getInstance().removeClient(client);
    }

    public void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }
        if (!gym.getClients().contains(client)){
            throw new ClientNotRegisteredException();
        }
        if (!DateUtils.inTheFuture(session.getDate())){
            gym.addAction("Failed registration: Session is not in the future");
        }

        gym.addAction("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
        gym.addAction("Failed registration: Client's gender doesn't match the session's gender requirements");
        gym.addAction("Failed registration: Client doesn't have enough balance");
        gym.addAction("Failed registration: No available spots for session");
        gym.addAction("Registered client: " + client.getName() + "to session: " + session + " on " + session.getDate() + "for price: " + session.getPrice());

    }


    public void paySalaries() {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }
        gym.addAction("Salaries have been paid to all employees");
    }

    public void printActions() {
     for (String action: gym.getActionsHistory()){
         System.out.println(action);
     }
    }

    public Session addSession(String sessionType, String date, ForumType forum, Instructor instructor) throws InstructorNotQualifiedException {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        if (!instructor.getCertifiedClassescertifiedClasses().contains(sessionType)) {
            throw new InstructorNotQualifiedException();
        }
        LocalDateTime dateTime = DateUtils.timeToString(date);
        if (!DateUtils.inTheFuture(dateTime)) {
            gym.addAction("Failed registration: Session is not in the future");
            return null;
        }
        Session newSession = SessionFactory.createSession(sessionType, dateTime, forum, instructor);
        gym.addAction("Created new session: " + sessionType + " on " + dateTime + " with instructor: " + instructor);
        Gym.getInstance().getSessions().add(newSession);
        return newSession;
    }

    public String toString() {
        return ("ID: " + this.getId() + " | " +
                "Name: " + this.getName() + " | " +
                "Gender: " + this.getGender() + " | " +
                "Birthday: " + this.getBirthDate() + " | " +
                "Age: " + this.age + " | " +
                "Balance: " + this.getBalance()) +
                "Role: Secretary | " +
                "Salary per Month: " + this.salary;
    }
}
