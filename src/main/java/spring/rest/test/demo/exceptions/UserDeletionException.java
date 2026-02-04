package spring.rest.test.demo.exceptions;

public class UserDeletionException extends RuntimeException {
    public UserDeletionException() {
        super("Failed to delete user");
    }
    
}
