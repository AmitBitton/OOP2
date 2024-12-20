package gym.management;

import gym.DateUtils;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Notifyer extends Subject{

  public void notifyClientsByDate (String message, String date){
    LocalDate realDate =DateUtils.stringToDate(date);
    clientSet.stream()
            .filter(client -> client.getSessions().stream()
                    .anyMatch(session -> session.getDate().toLocalDate().equals(realDate)))
            .distinct() // Ensures each client is included only once
            .forEach(client -> client.update(message));
    Gym.getInstance().addAction("A message was sent to everyone registered for a session on "+ realDate + " : "+ message);
  }

  public void notifyBySession(Session session, String message){
    for (Client client:session.getRegisteredClients()) {
      client.update(message);

    }
  }
  }
