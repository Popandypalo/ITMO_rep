package main.java.domain.entity.drink;

import com.google.gson.Gson;
import java.io.FileReader;
import java.util.List;

public class DrinkService {
    private static final Gson gson = new Gson();
    public List<Drink> loadDrinks(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Drink[] drinksArray = gson.fromJson(reader, Drink[].class);
            return List.of(drinksArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
