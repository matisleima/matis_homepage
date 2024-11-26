package ee.leima.matis.backend.business.dto;

import lombok.Data;

@Data
public class WorkFilterDTO {

    private String name;           // Column A
    private String music;          // Column B
    private String lyrics;         // Column C
    private String samples;        // Column D
    private String info;           // Column E
    private String length;         // Column F
    private Integer year;          // Column G
    private Integer month;         // Column H
    private Integer day;           // Column I
    private String style;          // Column J
    private String instruments;    // Column K
    private Integer tempo;         // Column L
    private String mood;           // Column M
    private Boolean sheet;         // Column N
    private String tags;           // Column O
    private Boolean arr;           // Column P
    private Boolean collection;    // Column Q
    private Boolean fixedMedia;    // Column R
    private String recBy;          // Column S
    private String premiereBy;     // Column T
    private String premiereLoc;    // Column U
    private String alternName;     // Column V
    private String band;           // Column W
    private Double authorRating;   // Column X
}
