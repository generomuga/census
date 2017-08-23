package census.com.census.events;

public class DeleteErrorHealthEvent {

    private String message;

    public DeleteErrorHealthEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
