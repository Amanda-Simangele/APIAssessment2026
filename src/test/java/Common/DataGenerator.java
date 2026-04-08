package Common;

import com.github.javafaker.Faker;

public class DataGenerator {

    // single Faker instance
    private static final Faker faker = new Faker();

    // public static variables (generated at class load) for direct use in payloads/tests
    public static final String name = faker.name().firstName();
    public static final String surname = faker.name().lastName();
    public static final String email = generateEmail();

    // keep helper private to keep the public API as variables only
    private static String generateEmail() {
        String base = (name + "." + surname).toLowerCase().replaceAll("[^a-z0-9.]", "");
        // append a short timestamp to reduce collisions
        String suffix = String.valueOf(System.currentTimeMillis() % 10000);
        return base + suffix + "@example.com";
    }

}

