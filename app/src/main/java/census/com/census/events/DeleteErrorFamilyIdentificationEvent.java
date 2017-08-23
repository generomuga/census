package census.com.census.events;

public class DeleteErrorFamilyIdentificationEvent {

    private String message;

    public DeleteErrorFamilyIdentificationEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}