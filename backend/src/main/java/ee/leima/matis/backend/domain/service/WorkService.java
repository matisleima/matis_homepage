package ee.leima.matis.backend.domain.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ee.leima.matis.backend.business.dto.WorkFilterDTO;
import ee.leima.matis.backend.domain.entity.Work;
import ee.leima.matis.backend.domain.repository.WorkRepository;
import jakarta.annotation.Resource;

@Service
public class WorkService {

    @Resource
    private WorkRepository workRepository;

    public List<Work> getWorks(WorkFilterDTO filter) {
        try {
            List<Work> allWorks = workRepository.fetchWorkData();
            return filterWorks(filter, null, allWorks);
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch work data from Google Spreadsheets.", e);
        }
    }

    public List<Work> getWorks(String generalTerm) {
        try {
            List<Work> allWorks = workRepository.fetchWorkData();
            return filterWorks(null, generalTerm, allWorks);
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch work data from Google Spreadsheets.", e);
        }
    }

    private List<Work> filterWorks(WorkFilterDTO filter, String generalTerm, List<Work> allWorks) {
        return allWorks.stream()
                .filter(work -> (filter == null || matchesFilter(work, filter)) &&
                                (generalTerm == null || matchesGeneralTerm(work, generalTerm)))
                .collect(Collectors.toList());
    }    

    private boolean matchesFilter(Work work, WorkFilterDTO filter) {
        return matchesString(work.getName(), filter.getName()) &&
               matchesString(work.getMusic(), filter.getMusic()) &&
               matchesString(work.getLyrics(), filter.getLyrics()) &&
               matchesString(work.getSamples(), filter.getSamples()) &&
               matchesString(work.getInfo(), filter.getInfo()) &&
               matchesString(work.getLength(), filter.getLength()) &&
               matchesInt(work.getYear(), filter.getYear()) &&
               matchesInt(work.getMonth(), filter.getMonth()) &&
               matchesInt(work.getDay(), filter.getDay()) &&
               matchesString(work.getStyle(), filter.getStyle()) &&
               matchesString(work.getInstruments(), filter.getInstruments()) &&
               matchesInt(work.getTempo(), filter.getTempo()) &&
               matchesString(work.getMood(), filter.getMood()) &&
               matchesExact(work.getSheet(), filter.getSheet()) &&
               matchesString(work.getTags(), filter.getTags()) &&
               matchesExact(work.getArr(), filter.getArr()) &&
               matchesExact(work.getCollection(), filter.getCollection()) &&
               matchesExact(work.getFixedMedia(), filter.getFixedMedia()) &&
               matchesString(work.getRecBy(), filter.getRecBy()) &&
               matchesString(work.getPremiereBy(), filter.getPremiereBy()) &&
               matchesString(work.getPremiereLoc(), filter.getPremiereLoc()) &&
               matchesString(work.getAlternName(), filter.getAlternName()) &&
               matchesString(work.getBand(), filter.getBand()) &&
               matchesDouble(work.getAuthorRating(), filter.getAuthorRating());
    }

    private boolean matchesGeneralTerm(Work work, String generalTerm) {
        String lowerTerm = generalTerm.toLowerCase();
    
        return matchesString(work.getName(), lowerTerm) ||
               matchesString(work.getMusic(), lowerTerm) ||
               matchesString(work.getLyrics(), lowerTerm) ||
               matchesString(work.getSamples(), lowerTerm) ||
               matchesString(work.getInfo(), lowerTerm) ||
               matchesString(work.getLength(), lowerTerm) ||
               (work.getYear() != 0 && String.valueOf(work.getYear()).contains(lowerTerm)) ||
               (work.getMonth() != 0 && String.valueOf(work.getMonth()).contains(lowerTerm)) ||
               (work.getDay() != 0 && String.valueOf(work.getDay()).contains(lowerTerm)) ||
               matchesString(work.getStyle(), lowerTerm) ||
               matchesString(work.getInstruments(), lowerTerm) ||
               (work.getTempo() != 0 && String.valueOf(work.getTempo()).contains(lowerTerm)) ||
               matchesString(work.getMood(), lowerTerm) ||
               matchesString(work.getTags(), lowerTerm) ||
               matchesString(work.getRecBy(), lowerTerm) ||
               matchesString(work.getPremiereBy(), lowerTerm) ||
               matchesString(work.getPremiereLoc(), lowerTerm) ||
               matchesString(work.getAlternName(), lowerTerm) ||
               matchesString(work.getBand(), lowerTerm) ||
               (work.getAuthorRating() != 0 && String.valueOf(work.getAuthorRating()).contains(lowerTerm));
    }
    
    private boolean matchesString(String workValue, String filterValue) {
        return filterValue == null || (workValue != null && workValue.toLowerCase().contains(filterValue.toLowerCase()));
    }
    
    private boolean matchesInt(Integer workValue, Integer filterValue) {
        return filterValue == null || workValue.equals(filterValue);
    }
    
    private boolean matchesExact(Object workValue, Object filterValue) {
        return filterValue == null || workValue.equals(filterValue);
    }
    
    private boolean matchesDouble(Double workValue, Double filterValue) {
        return filterValue == null || workValue.equals(filterValue);
    }
    
}
