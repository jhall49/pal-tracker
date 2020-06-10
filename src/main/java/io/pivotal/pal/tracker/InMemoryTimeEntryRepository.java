package io.pivotal.pal.tracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    Map<Long,TimeEntry> timeEntryMap = new HashMap<Long,TimeEntry>();
    long maxId=0;
    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        this.maxId++;
        timeEntry.setId(maxId);
        this.timeEntryMap.put(maxId,timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {return timeEntryMap.get(id);}

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntryMap.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry newTimeEntry) {
        TimeEntry timeEntry=find(id);
        if (timeEntry!=null) {
            timeEntry.setProjectId(newTimeEntry.getProjectId());
            timeEntry.setHours(newTimeEntry.getHours());
            timeEntry.setDate(newTimeEntry.getDate());
            timeEntry.setUserId(newTimeEntry.getUserId());
        }
        timeEntryMap.replace(id,timeEntry);
        return timeEntry;
    }

    @Override
    public void delete(Long timeEntryId) {
        timeEntryMap.remove(timeEntryId);
    }
}
