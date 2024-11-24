package ee.leima.matis.backend.util;

import lombok.Getter;

@Getter
public enum SheetColumns {
    NAME("A"),
    MUSIC("B"),
    LYRICS("C"),
    SAMPLES("D"),
    INFO("E"),
    LENGTH("F"),
    YEAR("G"),
    MONTH("H"),
    DAY("I"),
    STYLE("J"),
    INSTRUMENTS("K"),
    TEMPO("L"),
    MOOD("M"),
    SHEET("N"),
    TAGS("O"),
    ARR("P"),
    COLLECTION("Q"),
    FIXED_MEDIA("R"),
    REC_BY("S"),
    PREMIERE_BY("T"),
    PREMIERE_LOC("U"),
    ALTERN_NAME("V"),
    BAND("W"),
    AUTHOR_RATING("X");

    private final String column;

    SheetColumns(String column) {
        this.column = column;
    }
}

