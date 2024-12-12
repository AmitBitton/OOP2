package gym.Exception;

public class InstructorNotQualifiedException extends Exception {
    public InstructorNotQualifiedException(){
        super("Error: Instructor is not qualified to conduct this session type.");
    }
}
