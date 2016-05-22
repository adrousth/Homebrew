package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 *         5/21/2016
 */
public class Results {
    private boolean success;
    private List<String> messages;
    private String type;

    public Results() {
        messages = new ArrayList<>();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
