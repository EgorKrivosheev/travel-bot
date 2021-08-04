package by.grodno.krivosheev.travelbot.responses;

import org.springframework.http.HttpStatus;

/**
 * This class it format json the response a server
 */
public class AnswerResponse {
    private final HttpStatus code;
    private final String message;

    public AnswerResponse(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code.value();
    }

    public String getMessage() {
        return message;
    }
}
