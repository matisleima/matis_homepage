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
    private static final String DEFAULT_STRING = "";

    private final Sheets sheetsService;

    public WorkRepository() throws IOException {
        sheetsService = initializeSheetsService();
    }

    private Sheets initializeSheetsService() throws IOException {
        InputStream credentialsStream = getClass().getClassLoader().getResourceAsStream("google-service-account.json");
        if (credentialsStream == null) {
            throw new IOException("Resource not found: google-service-account.json");
        }

        GoogleCredentials credentials = ServiceAccountCredentials
                .fromStream(credentialsStream)
                .createScoped(SheetsScopes.SPREADSHEETS_READONLY);

        return new Sheets.Builder(new com.google.api.client.http.javanet.NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("KodukasBackend")
                .build();
    }

    public List<Work> fetchWorkData() throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(SPREADSHEET_ID, RANGE)
                .execute();
        List<List<Object>> rows = response.getValues();

        if (rows == null || rows.isEmpty()) {
            return new ArrayList<>();
        }

        return mapRowsToWorks(rows);
    }

    private List<Work> mapRowsToWorks(List<List<Object>> rows) {
        List<Work> works = new ArrayList<>();
        for (List<Object> row : rows) {
            Work work = new Work();
            work.setName(getStringValue(row, 0));
            work.setMusic(getStringValue(row, 1));
            work.setLyrics(getStringValue(row, 2));
            work.setSamples(getStringValue(row, 3));
            work.setInfo(getStringValue(row, 4));
            work.setLength(getStringValue(row, 5));
            work.setYear(getStringValue(row, 6));
            work.setMonth(getStringValue(row, 7));
            work.setDay(getStringValue(row, 8));
            work.setStyle(getStringValue(row, 9));
            work.setInstruments(getStringValue(row, 10));
            work.setTempo(getStringValue(row, 11));
            work.setMood(getStringValue(row, 12));
            work.setSheet(getBooleanValue(row, 13));
            work.setTags(getStringValue(row, 14));
            work.setArr(getBooleanValue(row, 15));
            work.setCollection(getBooleanValue(row, 16));
            work.setFixedMedia(getBooleanValue(row, 17));
            work.setRecBy(getStringValue(row, 18));
            work.setPremiereBy(getStringValue(row, 19));
            work.setPremiereLoc(getStringValue(row, 20));
            work.setAlternName(getStringValue(row, 21));
            work.setBand(getStringValue(row, 22));
            work.setAuthorRating(getStringValue(row, 23));
            works.add(work);
        }
        return works;
    }

    private String getStringValue(List<Object> row, int index) {
        return index < row.size() && row.get(index) != null ? row.get(index).toString() : DEFAULT_STRING;
    }

    private Boolean getBooleanValue(List<Object> row, int index) {
        if (index >= row.size() || row.get(index) == null) {
            return null; // Return null if the value is missing
        }
        return Boolean.parseBoolean(row.get(index).toString());
    }
}
