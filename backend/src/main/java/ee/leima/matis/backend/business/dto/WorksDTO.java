package ee.leima.matis.backend.business.dto;

import lombok.Data;

@Data
public class WorksDTO {

    private String name;           // Column A
    private String music;          // Column B
    private String lyrics;         // Column C
    private String samples;        // Column D
    private String info;           // Column E
    private String length;         // Column F
    private String year;           // Column G (Changed to String)
    private String month;          // Column H (Changed to String)
    private String day;            // Column I (Changed to String)
    private String style;          // Column J
    private String instruments;    // Column K
    private String tempo;          // Column L (Changed to String)
    private String mood;           // Column M
    private Boolean sheet;         // Column N (Remains Boolean)
    private String tags;           // Column O
    private Boolean arr;           // Column P (Remains Boolean)
    private Boolean collection;    // Column Q (Remains Boolean)
    private Boolean fixedMedia;    // Column R (Remains Boolean)
    private String recBy;          // Column S
    private String premiereBy;     // Column T
    private String premiereLoc;    // Column U
    private String alternName;     // Column V
    private String band;           // Column W
    private String authorRating;   // Column X (Changed to String)
}
