package census.com.census.events;

public class DeleteErrorFamilyEvent {
    private String message;

    public DeleteErrorFamilyEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
