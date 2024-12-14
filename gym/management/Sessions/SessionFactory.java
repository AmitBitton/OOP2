package gym.management.Sessions;

import gym.management.Instructor;

import java.time.LocalDateTime;

public class SessionFactory {
    public static Session createSession(String sessionType, LocalDateTime date, ForumType forumType, Instructor instructor) {
        Session session = null;
        if (sessionType.equals(SessionType.Pilates)) {
          session= new PilatesSession(date, instructor, forumType);}
        else if (sessionType.equals(SessionType.ThaiBoxing)) {
            session = new ThaiBoxingSession(date, instructor, forumType);
        } else if (sessionType.equals(SessionType.Ninja)) {
            session= new NinjaSession(date, instructor, forumType);
        }
        else if (sessionType.equals(SessionType.MachinePilates)){
            session= new MachinePilatesSession(date, instructor, forumType);
        }
        return session;
    }

}
