package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository,
                               MeterRegistry meterRegistry) {
        this.timeEntryRepository=timeEntryRepository;
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry=timeEntryRepository.create(timeEntryToCreate);
        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());
        if (null!=timeEntry) {
            return new ResponseEntity<>(timeEntry, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId) {
        TimeEntry timeEntry=timeEntryRepository.find(timeEntryId);
        if (timeEntry != null) {
            actionCounter.increment();
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable Long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry=timeEntryRepository.update(timeEntryId, expected);
        if (null!=timeEntry) {
            actionCounter.increment();
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable Long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        actionCounter.increment();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
