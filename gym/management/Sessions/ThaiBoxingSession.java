package gym.management.Sessions;

import gym.management.Instructor;

import java.time.LocalDateTime;

public class ThaiBoxingSession extends Session {
    private static final int maxParticipants = 20;

    public ThaiBoxingSession(LocalDateTime date, Instructor instructor, ForumType forumType) {
        super(date, instructor, forumType, SessionType.ThaiBoxing, 100);
    }
}
