package gym.management;

import gym.management.Sessions.*;
import gym.customers.*;

import java.util.ArrayList;

public class Secretary {

    public Secretary(Person p1, int i) {
    }
private void checkPermissions () throws NullPointerException{
    if (Gym.getInstance().getSecretary()!= this)
        throw new NullPointerException ("Error: Former secretaries are not permitted to perform actions");
}
    public void notify(String s) {

    }

    public void notify(String s, String s1) {
    }

    public void notify(Session s, String s1) {
    }

    public Instructor hireInstructor(Person p6, int i, ArrayList<Object> objects) {
    }

    public Client registerClient(Person p3) {
    }

    public void unregisterClient(Client c2) {
    }

    public void registerClientToLesson(Client c7, Session s5) {
    }

    public void paySalaries() {
    }

    public void printActions() {
    }

    public Session addSession(Object pilates, String s, Object all, Instructor i2) {
    }
}
