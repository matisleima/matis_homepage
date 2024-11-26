package ee.leima.matis.backend.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ee.leima.matis.backend.business.dto.WorkFilterDTO;
import ee.leima.matis.backend.business.dto.WorkResponse;
import ee.leima.matis.backend.business.service.WorksService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

@RestController
public class WorksController {

    @Resource
    private WorksService worksService;

    @GetMapping("/get-works")
    @Operation(summary = "This returns a list of works optionally filtered by parameters")
    public WorkResponse getWorks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String music,
            @RequestParam(required = false) String lyrics,
            @RequestParam(required = false) String samples,
            @RequestParam(required = false) String info,
            @RequestParam(required = false) String length,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer day,
            @RequestParam(required = false) String style,
            @RequestParam(required = false) String instruments,
            @RequestParam(required = false) Integer tempo,
            @RequestParam(required = false) String mood,
            @RequestParam(required = false) Boolean sheet,
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) Boolean arr,
            @RequestParam(required = false) Boolean collection,
            @RequestParam(required = false) Boolean fixedMedia,
            @RequestParam(required = false) String recBy,
            @RequestParam(required = false) String premiereBy,
            @RequestParam(required = false) String premiereLoc,
            @RequestParam(required = false) String alternName,
            @RequestParam(required = false) String band,
            @RequestParam(required = false) Double authorRating) {

        WorkFilterDTO filter = new WorkFilterDTO();
        filter.setName(name);
        filter.setMusic(music);
        filter.setLyrics(lyrics);
        filter.setSamples(samples);
        filter.setInfo(info);
        filter.setLength(length);
        filter.setYear(year);
        filter.setMonth(month);
        filter.setDay(day);
        filter.setStyle(style);
        filter.setInstruments(instruments);
        filter.setTempo(tempo);
        filter.setMood(mood);
        filter.setSheet(sheet);
        filter.setTags(tags);
        filter.setArr(arr);
        filter.setCollection(collection);
        filter.setFixedMedia(fixedMedia);
        filter.setRecBy(recBy);
        filter.setPremiereBy(premiereBy);
        filter.setPremiereLoc(premiereLoc);
        filter.setAlternName(alternName);
        filter.setBand(band);
        filter.setAuthorRating(authorRating);

        return worksService.getWorks(filter);
    }

    @GetMapping("/get-works-general")
    @Operation(summary = "This returns a list of works filtered by a general term")
    public WorkResponse getWorksGeneralSearch(@RequestParam(required = false) String generalTerm) {
        return worksService.getWorks(generalTerm);
    }

    @PostMapping("/add-works")
    @Operation(summary = "This adds a new entry to the list of works")
    public String addWorks() {
        return "This adds a new entry to the list of works";
    }
}
