package ee.leima.matis.backend.domain.repository;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import ee.leima.matis.backend.domain.entity.Work;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkRepository {

    private static final String SPREADSHEET_ID = "18p3F3x13jNz1xrvqQujQH4zjCyq7aHaeCyQq9m377CQ";

    private static final String RANGE = "compositions!A2:X";

    private final Sheets sheetsService; // Google Sheets API client

    // Constructor initializes the Sheets API client
    public WorkRepository() throws IOException {
        sheetsService = initializeSheetsService();
    }

    // Initialize the Sheets API client using the service account JSON file
    private Sheets initializeSheetsService() throws IOException {
        GsonFactory gsonFactory = GsonFactory.getDefaultInstance();

        // Load credentials from resources folder
        InputStream credentialsStream = WorkRepository.class.getClassLoader()
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

    // Fetches data from the spreadsheet and maps it to a list of Work objects
    public List<Work> fetchWorkData() throws IOException {
        List<Work> works = new ArrayList<>();

        // Fetch data from Google Sheets
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();
        List<List<Object>> values = response.getValues();

        // If no data, return empty list
        if (values == null || values.isEmpty()) {
            return works;
        }

        // Process each row (skip header if needed)
        for (List<Object> row : values) {
            // if (row.size() < 23)
            //     continue; // Ensure row has enough columns

            Work work = new Work();
            work.setName(getStringValue(row, 0)); // Column A
            work.setMusic(getStringValue(row, 1)); // Column B
            work.setLyrics(getStringValue(row, 2)); // Column C
            work.setSamples(getStringValue(row, 3)); // Column D
            work.setInfo(getStringValue(row, 4)); // Column E
            work.setLength(getStringValue(row, 5)); // Column F
            work.setYear(getIntValue(row, 6)); // Column G
            work.setMonth(getIntValue(row, 7)); // Column H
            work.setDay(getIntValue(row, 8)); // Column I
            work.setStyle(getStringValue(row, 9)); // Column J
            work.setInstruments(getStringValue(row, 10)); // Column K
            work.setTempo(getIntValue(row, 11)); // Column L
            work.setMood(getStringValue(row, 12)); // Column M
            work.setSheet(getBooleanValue(row, 13)); // Column N
            work.setTags(getStringValue(row, 14)); // Column O
            work.setArr(getBooleanValue(row, 15)); // Column P
            work.setCollection(getBooleanValue(row, 16)); // Column Q
            work.setFixedMedia(getBooleanValue(row, 17)); // Column R
            work.setRecBy(getStringValue(row, 18)); // Column S
            work.setPremiereBy(getStringValue(row, 19)); // Column T
            work.setPremiereLoc(getStringValue(row, 20)); // Column U
            work.setAlternName(getStringValue(row, 21)); // Column V
            work.setBand(getStringValue(row, 22)); // Column W
            work.setAuthorRating(getDoubleValue(row, 23)); // Column X

            works.add(work);
        }

        return works;
    }

    // Helper method to safely extract a String value from a row
    private String getStringValue(List<Object> row, int index) {
        return index < row.size() ? row.get(index).toString() : null; // Null if index exceeds row size
    }

    // Helper method to safely extract an int value from a row
    private int getIntValue(List<Object> row, int index) {
        // Check if the index is out of bounds or the value is null/empty
        if (index >= row.size() || row.get(index) == null || row.get(index).toString().isEmpty()) {
            return 0; // Default value for empty or invalid integers
        }
        try {
            return Integer.parseInt(row.get(index).toString());
        } catch (NumberFormatException e) {
            return 0; // Default value if parsing fails
        }
    }

    // Helper method to safely extract a Boolean value from a row
    private Boolean getBooleanValue(List<Object> row, int index) {
        return index < row.size() ? Boolean.parseBoolean(row.get(index).toString()) : null; // Null if missing
    }

    // Helper method to safely extract a double value from a row
    private double getDoubleValue(List<Object> row, int index) {
        return index < row.size() ? Double.parseDouble(row.get(index).toString()) : 0.0; // Default 0.0 for missing
                                                                                         // values
    }
}
