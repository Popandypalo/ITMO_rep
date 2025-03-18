package main.java.common;

import java.io.Serializable;

/**
 * Класс ответа от сервера клиенту.
 */
public class Response implements Serializable {
    private String status;
    private String message;

    /**
     * Конструктор для ответа сервера.
     *
     * @param status  статус (например, OK, ERROR)
     * @param message сообщение ответа
     */
    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Геттеры и сеттеры

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
