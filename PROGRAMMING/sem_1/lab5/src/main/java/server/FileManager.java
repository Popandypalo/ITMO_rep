package main.java.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;

import main.java.server.exceptions.FileReadException;
import main.java.server.exceptions.FileWriteException;
import main.models.Product;
import java.io.*;
import java.util.HashSet;

/**
 * Класс для работы с файлом коллекции.
 */
public class FileManager {
    private final String fileName;
    private final ObjectMapper mapper;

    public FileManager(String fileName) {
        this.fileName = fileName;
        this.mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Сохраняет коллекцию в файл.
     */
    public void saveCollection(HashSet<Product> collection) throws FileWriteException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            mapper.writeValue(fos, collection);
            System.out.println("Коллекция сохранена.");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка сохранения: " + e.getMessage());
        }
    }

    /**
     * Загружает коллекцию из файла.
     */
    public HashSet<Product> loadCollection() throws FileReadException {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Файл не найден: " + fileName + ". Коллекция будет пустой.");
            return new HashSet<>();
        }
    
        if (file.length() == 0) {
            System.out.println("Файл пустой. Коллекция будет пустой.");
            return new HashSet<>();
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            HashSet<Product> loaded = mapper.readValue(reader, new TypeReference<HashSet<Product>>() {});
            System.out.println("Коллекция успешно загружена. Элементов: " + loaded.size());
            return loaded;
        } catch (JsonMappingException e) {
            System.out.println("Ошибка разбора JSON: Некорректный формат данных. Подробности: " + e.getMessage());
            System.out.println("Коллекция будет пустой.");
            return new HashSet<>();
        } catch (IOException e) {
            throw new FileReadException("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
