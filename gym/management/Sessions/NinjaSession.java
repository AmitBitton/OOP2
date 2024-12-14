package gym.management.Sessions;

import gym.management.Instructor;

import java.time.LocalDateTime;

public class NinjaSession extends Session {
    private static final int maxParticipants = 5;

    public NinjaSession(LocalDateTime date, Instructor instructor, ForumType forumType) {
        super(date, instructor, forumType, SessionType.Ninja, 150);
    }
}
