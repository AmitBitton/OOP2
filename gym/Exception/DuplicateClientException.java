package gym.Exception;

public class DuplicateClientException extends Exception {
    public DuplicateClientException(){
        super("The client is already registered");
    }
}
