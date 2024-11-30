package ee.leima.matis.backend.domain.entity;

import lombok.Data;

@Data
public class Work {

    private String name;
    private String music;
    private String lyrics;
    private String samples;
    private String info;
    private String length; // Time as a string
    private String year; // Changed to string
    private String month; // Changed to string
    private String day; // Changed to string
    private String style;
    private String instruments;
    private String tempo; // Changed to string
    private String mood;
    private Boolean sheet; // Kept as boolean
    private String tags;
    private Boolean arr; // Kept as boolean
    private Boolean collection; // Kept as boolean
    private Boolean fixedMedia; // Kept as boolean
    private String recBy;
    private String premiereBy;
    private String premiereLoc;
    private String alternName;
    private String band;
    private String authorRating; // Changed to string
}
