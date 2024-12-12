package gym.management;

import gym.customers.Client;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Notifyer {
    private static Notifyer instance;
    private Map<Session, ArrayList<Client>> map= new HashMap<>();
    private Notifyer(){

    }
    public static Notifyer getInstance(){
        if (instance==null){
            instance=new Notifyer();
        }
        return instance;
    }

}
