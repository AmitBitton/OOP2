package gym.management.Sessions;

import gym.management.Instructor;

import java.time.LocalDateTime;

public class PilatesSession extends Session {
    private static final int maxParticipants = 30;


    public PilatesSession(LocalDateTime date, Instructor instructor, ForumType forumType) {
        super(date, instructor, forumType, SessionType.Pilates, 60);
    }
}
