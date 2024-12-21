package gym.management;

import gym.DateUtils;
import gym.Exception.ClientNotRegisteredException;
import gym.Exception.DuplicateClientException;
import gym.Exception.InstructorNotQualifiedException;
import gym.Exception.InvalidAgeException;
import gym.management.Sessions.*;
import gym.customers.*;

import java.time.LocalDateTime;
import java.util.*;

public class Secretary extends Person {
    private int salary;
    private Person person;

    public Secretary(Person person, int salary) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.salary = salary;
        Gym.getInstance().addAction("A new secretary has started working at the gym: " + this.getName());

    }

    private void checkPermissions() throws NullPointerException {
        if (Gym.getInstance().getSecretary() != this)
            throw new NullPointerException("Error: Former secretaries are not permitted to perform actions");
    }


    public void notify(String message) {
        Gym.getInstance().getNotifyer().notifyClients(message);
        Gym.getInstance().addAction("A message was sent to all gym clients: " + message);
    }

    public void notify(String date, String message) {
        Gym.getInstance().getNotifyer().notifyClientsByDate(message, date);

    }

    public void notify(Session session, String message) {
        if (session != null) {
            Gym.getInstance().getNotifyer().notifyBySession(session, message);
            Gym.getInstance().addAction("A message was sent to everyone registered for session " + session.getSessionType() + " on " + session.getDate() + " : " + message);
        }
    }

    public Instructor hireInstructor(Person person, int salary, ArrayList<String> sessionTypes) {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        Instructor newInstructor = new Instructor(person, salary, sessionTypes);
        if (Gym.getInstance().getInstructors().contains(newInstructor)) {
            return null;
        }
        Gym.getInstance().getInstructors().add(newInstructor);
        Gym.getInstance().addAction("Hired new instructor: " + person.getName() + " with salary per hour: " + salary);
        return newInstructor;
    }

    public Client registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        for (Client c : Gym.getInstance().getClients()) {
            if (c.getId() == person.getId()) {
                // Gym.getInstance().addAction("Error: The client is already registered");
                throw new DuplicateClientException("Error: The client is already registered");
            }
        }
        Client newClient = new Client(person);
        Gym.getInstance().addClient(newClient);
        Gym.getInstance().addAction("Registered new client: " + newClient.getName());
        Gym.getInstance().getNotifyer().subscribe(newClient);
        return newClient;
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }
        if (!Gym.getInstance().getClients().contains(client)) {
            //Gym.getInstance().addAction("Error: Registration is required before attempting to unregister");
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }
        Gym.getInstance().addAction("Unregistered client: " + client.getName());
        Gym.getInstance().getNotifyer().unSubscribe(client);
        Gym.getInstance().removeClient(client);
    }

    public void registerClientToLesson(Client client, Session session) throws ClientNotRegisteredException, DuplicateClientException {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }
        if (session == null) {
            return;
        }
        if (!Gym.getInstance().getClients().contains(client)) {
            //Gym.getInstance().addAction("Error: The client is not registered with the gym and cannot enroll in lessons");
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }
        if (session.isRegisterForSession(client)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }
        ArrayList<String> problems = RegistrationManger.checkIfCanRegister(client, session);
        for (String problem : problems) {
            Gym.getInstance().addAction(problem);
        }
        if (problems.isEmpty()) {
            int sessionPrice = session.getPrice();
            client.addSession(session);
            session.getRegisteredClients().add(client);
            Gym.getInstance().addToGymBalance(sessionPrice);
            client.deductBalance(sessionPrice);
            Gym.getInstance().addAction("Registered client: " + client.getName() + " to session: " + session.getSessionType() + " on " + session.getDate() + " for price: " + session.getPrice());
        }
    }


    public void paySalaries() {
        try {
            checkPermissions();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }

        if (Gym.getInstance().getSecretary() != null) {
            Gym.getInstance().deductGymBalance(this.salary);
            this.addBalance(this.salary);
        }

        //instructors
        int instructorSalary, numOfSessions, allInstructorsSalary = 0;
        for (Instructor instructor : Gym.getInstance().getInstructors()) {
            numOfSessions = 0;
            for (Session session : Gym.getInstance().getSessions()) {
                if (session.getInstructor().equals(instructor)) {
                    numOfSessions = numOfSessions + 1;
                }
            }
            instructorSalary = instructor.getSalaryPerHour() * numOfSessions;
            instructor.addBalance(instructorSalary);
            Gym.getInstance().addPerson(instructor);
            allInstructorsSalary += instructorSalary;
        }
        Gym.getInstance().setGymBalance(Gym.getInstance().getGymBalance() - allInstructorsSalary);
        Gym.getInstance().addAction("Salaries have been paid to all employees");
    }

    public void printActions() {
        for (String action : Gym.getInstance().getActionsHistory()) {
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
            // Gym.getInstance().addAction("Error: Instructor is not qualified to conduct this session type.");
            throw new InstructorNotQualifiedException();
        }
        LocalDateTime stringToTime = DateUtils.timeToString(date);
//        if (!DateUtils.isInTheFuture(stringToTime)) {
//            Gym.getInstance().addAction("Failed registration: Session is not in the future");
//            return null;
//        }
        Session newSession = SessionFactory.createSession(sessionType, stringToTime, forum, instructor);
        Gym.getInstance().addAction("Created new session: " + sessionType + " on " + stringToTime + " with instructor: " + instructor.getName());
        Gym.getInstance().getSessions().add(newSession);
        return newSession;
    }

    public String toString() {
        return ("ID: " + this.getId() + " | " +
                "Name: " + this.getName() + " | " +
                "Gender: " + this.getGender() + " | " +
                "Birthday: " + this.getBirthDate() + " | " +
                "Age: " + this.age + " | " +
                "Balance: " + this.getBalance()) + " | " +
                "Role: Secretary | " +
                "Salary per Month: " + this.salary;
    }
}
