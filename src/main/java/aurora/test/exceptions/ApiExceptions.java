package aurora.test.exceptions;

public class ApiExceptions extends RuntimeException{
    public ApiExceptions(String message){
        super(message);
    }
    public ApiExceptions (String message,Throwable cause){
        super(message,cause);
    }
}
