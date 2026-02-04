package spring.rest.test.demo.models;

public class ErrorWithField extends Error {
    public String field;

    public ErrorWithField(String field, String message) {
        super(message);
        this.field = field;
    }
    
}
