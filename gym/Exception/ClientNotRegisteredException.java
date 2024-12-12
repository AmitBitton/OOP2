package gym.Exception;

public class ClientNotRegisteredException extends Exception{
    public ClientNotRegisteredException(){
        super("The client is not registered with the gym and cannot enroll in lessons");
    }

}
