package DI;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DependencyInjector {
    private static final Map<Class<?>, Object> instances = new HashMap<>();

    public static void injectDependencies(Object target) {
        Class<?> targetClass = target.getClass();
        Field[] fields = targetClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                Inject injectAnnotation = field.getAnnotation(Inject.class);
                Class<?> dependencyType = injectAnnotation.implementation() != void.class
                        ? injectAnnotation.implementation()
                        : field.getType();

                try {
                    Object dependencyInstance = getOrCreateInstance(dependencyType);
                    field.setAccessible(true);
                    field.set(target, dependencyInstance);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Failed to inject dependency", e);
                }
            }
        }
    }

    public static Object getOrCreateInstance(Class<?> clazz) {
        if (!instances.containsKey(clazz)) {
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                instances.put(clazz, instance);
                injectDependencies(instance);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create instance of " + clazz.getName(), e);
            }
        }
        return instances.get(clazz);
    }

    public static void registerInstance(Class<?> clazz, Object instance) {
        instances.put(clazz, instance);
    }

    public static <T> T createInstance(Class<T> clazz) {
        try {
            // 1. Создаем экземпляр через конструктор по умолчанию
            T instance = clazz.getDeclaredConstructor().newInstance();

            // 2. Внедряем зависимости
            injectDependencies(instance);

            return instance;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Класс " + clazz.getName() + " должен иметь конструктор по умолчанию", e);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать экземпляр " + clazz.getName(), e);
        }
    }
}
