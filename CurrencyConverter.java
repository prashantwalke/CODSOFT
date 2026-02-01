import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private static double fetchRate(String base, String target) throws Exception {
        String apiUrl = "https://api.exchangerate.host/latest?base=" + base + "&symbols=" + target;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        String key = "\"" + target + "\":";
        int index = response.indexOf(key);

        if (index == -1) {
            throw new RuntimeException("Invalid currency code");
        }

        int start = index + key.length();
        int end = response.indexOf("}", start);

        return Double.parseDouble(response.substring(start, end));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter base currency: ");
            String base = scanner.next().toUpperCase();

            System.out.print("Enter target currency: ");
            String target = scanner.next().toUpperCase();

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();

            if (amount <= 0) {
                System.out.println("Invalid amount");
                return;
            }

            double rate = fetchRate(base, target);
            double result = amount * rate;

            System.out.println("Converted Amount: " +
                    String.format("%.2f", result) + " " + target);

        } catch (Exception e) {
            System.out.println("Conversion failed");
        } finally {
            scanner.close();
        }
    }
}

