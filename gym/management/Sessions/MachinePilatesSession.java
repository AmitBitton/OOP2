package gym.management.Sessions;

import gym.management.Instructor;

import java.time.LocalDateTime;

public class MachinePilatesSession extends Session {
    private static final int maxParticipants = 10;

    public MachinePilatesSession(LocalDateTime date, Instructor instructor, ForumType forumType) {
        super(date, instructor, forumType, SessionType.MachinePilates, 80);
    }
}
