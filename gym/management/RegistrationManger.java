package gym.management;

import gym.DateUtils;
import gym.customers.Client;
import gym.customers.Gender;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;

import java.util.ArrayList;

public class RegistrationManger {
    public static String checkForumType(Client client, Session session) {
        ForumType forumType = session.getForum();
        if (forumType == (ForumType.All)) {
            return null;
        }
        if (forumType == (ForumType.Seniors) && !client.getforumType().contains(ForumType.Seniors)) {
            return "Failed registration: Client doesn't meet the age requirements for this session (Seniors)";
        }
        if (forumType == ForumType.Female && client.getGender() != Gender.Female) {
            return "Failed registration: Client's gender doesn't match the session's gender requirements";
        }
        if (forumType == ForumType.Male && client.getGender() != Gender.Male) {
            return "Failed registration: Client's gender doesn't match the session's gender requirements";
        }
        return null;
    }

    public static String checkEnoughBalance(Client client, Session session) {
        if (client.getBalance() < session.getPrice()) {
            return "Failed registration: Client doesn't have enough balance";
        }
        return null;
    }

    public static ArrayList<String> checkIfCanRegister(Client client, Session session) {
        ArrayList<String> problems = new ArrayList<>();
        if (!session.canRegister()) {
            problems.add("Failed registration: No available spots for session");
        }
        if (!DateUtils.isInTheFuture(session.getDate()) ){
            problems.add("Failed registration: Session is not in the future");
        }
        String isAppropriateForumType = checkForumType(client, session);
        if (isAppropriateForumType != null) {
            problems.add(isAppropriateForumType);
        }
        String hasEnoughBalance = checkEnoughBalance(client, session);
        if (hasEnoughBalance != null) {
            problems.add("Failed registration: Client doesn't have enough balance");
        }
        return problems;

    }


}
