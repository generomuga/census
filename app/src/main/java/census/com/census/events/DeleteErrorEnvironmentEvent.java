package census.com.census.events;

public class DeleteErrorEnvironmentEvent {

    private String message;

    public DeleteErrorEnvironmentEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
