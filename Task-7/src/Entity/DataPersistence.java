package Entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;

public class DataPersistence {
    private static final String DATA_FILE = "src/autoservice_data.json";
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static void saveData(AutoServiceAdmin admin) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(DATA_FILE), admin);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }

    public static AutoServiceAdmin loadData() {
        try {
            File file = new File(DATA_FILE);
            if (file.exists()) {
                return mapper.readValue(file, AutoServiceAdmin.class);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
        }
        return new AutoServiceAdmin();
    }
}
