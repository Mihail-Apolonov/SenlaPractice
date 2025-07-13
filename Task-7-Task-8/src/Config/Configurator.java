package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Configurator {
    public static void configure(Object configObject) {
        Class<?> clazz = configObject.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ConfigProperty.class)) {
                ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
                try {
                    String configFileName = annotation.configFileName();
                    String propertyName = annotation.propertyName().isEmpty()
                            ? clazz.getSimpleName() + "." + field.getName()
                            : annotation.propertyName();
                    Class<?> type = annotation.type();

                    Properties prop = new Properties();
                    prop.load(new FileInputStream("src/" + configFileName));

                    String value = prop.getProperty(propertyName);
                    if (value != null) {
                        field.setAccessible(true);
                        field.set(configObject, convertValue(value, field.getType()));
                    }
                } catch (IOException | IllegalAccessException e) {
                    System.out.println("Error configuring field " + field.getName() + ": " + e.getMessage());
                }
            }
        }
    }

    private static Object convertValue(String value, Class<?> targetType) {
        if (targetType == Boolean.class || targetType == boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (targetType == Integer.class || targetType == int.class) {
            return Integer.parseInt(value);
        } else if (targetType == Long.class || targetType == long.class) {
            return Long.parseLong(value);
        } else if (targetType == Double.class || targetType == double.class) {
            return Double.parseDouble(value);
        } else if (targetType == Float.class || targetType == float.class) {
            return Float.parseFloat(value);
        }
        return value;
    }
}
