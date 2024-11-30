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
               matchesString(work.getYear(), filter.getYear()) &&
               matchesString(work.getMonth(), filter.getMonth()) &&
               matchesString(work.getDay(), filter.getDay()) &&
               matchesString(work.getStyle(), filter.getStyle()) &&
               matchesString(work.getInstruments(), filter.getInstruments()) &&
               matchesString(work.getTempo(), filter.getTempo()) &&
               matchesString(work.getMood(), filter.getMood()) &&
               matchesBoolean(work.getSheet(), filter.getSheet()) &&
               matchesString(work.getTags(), filter.getTags()) &&
               matchesBoolean(work.getArr(), filter.getArr()) &&
               matchesBoolean(work.getCollection(), filter.getCollection()) &&
               matchesBoolean(work.getFixedMedia(), filter.getFixedMedia()) &&
               matchesString(work.getRecBy(), filter.getRecBy()) &&
               matchesString(work.getPremiereBy(), filter.getPremiereBy()) &&
               matchesString(work.getPremiereLoc(), filter.getPremiereLoc()) &&
               matchesString(work.getAlternName(), filter.getAlternName()) &&
               matchesString(work.getBand(), filter.getBand()) &&
               matchesString(work.getAuthorRating(), filter.getAuthorRating());
    }

    private boolean matchesGeneralTerm(Work work, String generalTerm) {
        String lowerTerm = generalTerm.toLowerCase();

        return matchesString(work.getName(), lowerTerm) ||
               matchesString(work.getMusic(), lowerTerm) ||
               matchesString(work.getLyrics(), lowerTerm) ||
               matchesString(work.getSamples(), lowerTerm) ||
               matchesString(work.getInfo(), lowerTerm) ||
               matchesString(work.getLength(), lowerTerm) ||
               matchesString(work.getYear(), lowerTerm) ||
               matchesString(work.getMonth(), lowerTerm) ||
               matchesString(work.getDay(), lowerTerm) ||
               matchesString(work.getStyle(), lowerTerm) ||
               matchesString(work.getInstruments(), lowerTerm) ||
               matchesString(work.getTempo(), lowerTerm) ||
               matchesString(work.getMood(), lowerTerm) ||
               matchesString(work.getTags(), lowerTerm) ||
               matchesString(work.getRecBy(), lowerTerm) ||
               matchesString(work.getPremiereBy(), lowerTerm) ||
               matchesString(work.getPremiereLoc(), lowerTerm) ||
               matchesString(work.getAlternName(), lowerTerm) ||
               matchesString(work.getBand(), lowerTerm) ||
               matchesString(work.getAuthorRating(), lowerTerm);
    }

    private boolean matchesString(String workValue, String filterValue) {
        return filterValue == null || (workValue != null && workValue.toLowerCase().contains(filterValue.toLowerCase()));
    }

    private boolean matchesBoolean(Boolean workValue, Boolean filterValue) {
        return filterValue == null || (workValue != null && workValue.equals(filterValue));
    }
}
