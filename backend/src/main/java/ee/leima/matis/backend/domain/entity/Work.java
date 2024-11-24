package ee.leima.matis.backend.domain.entity;

import lombok.Data;

@Data
public class Work {

    private String name;
    private String music;
    private String lyrics;
    private String samples;
    private String info;
    private String length; //time?
    private int year;
    private int month;
    private int day;
    private String style;
    private String instruments;
    private int tempo;
    private String mood;
    private Boolean sheet;
    private String tags;
    private Boolean arr;
    private Boolean collection;
    private Boolean fixedMedia;
    private String recBy;
    private String premiereBy;
    private String premiereLoc;
    private String alternName;
    private String band;
    private double authorRating;

}
