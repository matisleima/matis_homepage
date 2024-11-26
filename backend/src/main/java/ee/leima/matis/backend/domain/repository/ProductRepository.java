package ee.leima.matis.backend.domain.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import ee.leima.matis.backend.domain.entity.Product;

@Repository
public class ProductRepository {

    private static final String SPREADSHEET_ID = "18p3F3x13jNz1xrvqQujQH4zjCyq7aHaeCyQq9m377CQ"; // Replace with actual
                                                                                                 // spreadsheet ID

    private static final String RANGE = "products!A2:G";

    private final Sheets sheetsService; // Google Sheets API client

    // Constructor initializes the Sheets API client
    public ProductRepository() throws IOException {
        sheetsService = initializeSheetsService();
    }

    // Initialize the Sheets API client using the service account JSON file
    private Sheets initializeSheetsService() throws IOException {
        GsonFactory gsonFactory = GsonFactory.getDefaultInstance();

        // Load credentials from resources folder
        InputStream credentialsStream = ProductRepository.class.getClassLoader()
                .getResourceAsStream("google-service-account.json");
        if (credentialsStream == null) {
            throw new IOException("Resource not found: google-service-account.json");
        }

        ServiceAccountCredentials credentials = (ServiceAccountCredentials) GoogleCredentials
                .fromStream(credentialsStream)
                .createScoped(SheetsScopes.SPREADSHEETS_READONLY);

        return new Sheets.Builder(new com.google.api.client.http.javanet.NetHttpTransport(),
                gsonFactory,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("KodukasBackend")
                .build();
    }

    // Fetches data from the spreadsheet and maps it to a list of Product objects
    public List<Product> fetchProductData() throws IOException {
        List<Product> products = new ArrayList<>();

        // Fetch data from Google Sheets
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();
        List<List<Object>> values = response.getValues();

        // If no data, return empty list
        if (values == null || values.isEmpty()) {
            return products;
        }

        // Process each row (skip header if needed)
        for (List<Object> row : values) {

            Product product = new Product();
            product.setName(getStringValue(row, 0)); // Column A
            product.setType(getStringValue(row, 1)); // Column B
            product.setUnit(getStringValue(row, 2)); // Column C
            product.setDescription(getStringValue(row, 3)); // Column D
            product.setPrice(getStringValue(row, 4)); // Column E
            product.setInStock(getBooleanValue(row, 5)); // Column F
            product.setTags(getStringValue(row, 6)); // Column G

            products.add(product);
        }

        return products;
    }

    // Helper method to safely extract a String value from a row
    private String getStringValue(List<Object> row, int index) {
        return index < row.size() ? row.get(index).toString() : null; // Null if index exceeds row size
    }

    // Helper method to safely extract a Boolean value from a row
    private Boolean getBooleanValue(List<Object> row, int index) {
        return index < row.size() ? Boolean.parseBoolean(row.get(index).toString()) : null; // Null if missing
    }
}
