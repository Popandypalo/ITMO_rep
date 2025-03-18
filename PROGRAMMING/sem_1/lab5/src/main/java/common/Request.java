package main.java.common;

import java.io.Serializable;
import java.util.Map;

/**
 * Класс запроса от клиента к серверу.
 */
public class Request implements Serializable {
    private String commandName;
    private Map<String, String> arguments;
    private String scriptContent; // для execute_script

    /**
     * Конструктор для команды без скрипта.
     *
     * @param commandName название команды
     * @param arguments   аргументы команды
     */
    public Request(String commandName, Map<String, String> arguments) {
        this.commandName = commandName;
        this.arguments = arguments;
    }

    /**
     * Конструктор для команды со скриптом.
     *
     * @param commandName   название команды
     * @param arguments     аргументы команды
     * @param scriptContent содержимое скрипта
     */
    public Request(String commandName, Map<String, String> arguments, String scriptContent) {
        this.commandName = commandName;
        this.arguments = arguments;
        this.scriptContent = scriptContent;
    }

    // Геттеры и сеттеры

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    public String getScriptContent() {
        return scriptContent;
    }

    public void setScriptContent(String scriptContent) {
        this.scriptContent = scriptContent;
    }

    @Override
    public String toString() {
        return "Request{" +
                "commandName='" + commandName + '\'' +
                ", arguments=" + arguments +
                ", scriptContent='" + scriptContent + '\'' +
                '}';
    }
}

