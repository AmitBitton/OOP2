package gym.management;
import gym.DateUtils;
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
    private List<String> actionsHistory;

    public Secretary(Person person, int salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.salary = salary;
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
        System.out.println("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        return newInstructor;
    }

    public Client registerClient(Person person) throws InvalidAgeException {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        Client newClient = new Client(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        Gym.getInstance().addClient(newClient);
        System.out.println("Registered new client: " + newClient.getName());
        return newClient;
    }

    public void unregisterClient(Client client) {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }
        System.out.println("Unregistered client: " + client.getName());
        Gym.getInstance().removeClient(client);
    }

    public void registerClientToLesson(Client client, Session session) {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }
        if (!DateUtils.inTheFuture(session.getDate())){
            System.out.println("Failed registration: Session is not in the future");
        }

        System.out.println("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
        System.out.println("Failed registration: Client's gender doesn't match the session's gender requirements");
        System.out.println("Failed registration: Client doesn't have enough balance");
        System.out.println("Failed registration: No available spots for session");
        System.out.println("Registered client: " + client.getName() + "to session: " + session + " on " + session.getDate() + "for price: " + session.getPrice());

    }
    private boolean canRegister(Client client, Session session){
    if (session.getSessionType().contains(ForumType.Seniors)&&client.getAge()<65)
    }

    public void paySalaries() {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }

        System.out.println("Salaries have been paid to all employees");
    }

    public void printActions() {
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
            System.out.println("Failed registration: Session is not in the future");
            return null;
        }
        Session newSession = SessionFactory.createSession(sessionType, dateTime, forum, instructor);
        System.out.println("Created new session: " + sessionType + " on " + dateTime + " with instructor: " + instructor);
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
