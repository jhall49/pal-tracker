package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry() {
    }

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.projectId=projectId;
        this.userId=userId;
        this.date = date;
        this.hours=hours;
    }

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id=id;
        this.projectId=projectId;
        this.userId=userId;
        this.date = date;
        this.hours=hours;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals(Object newTimeEntry){
        if (this==newTimeEntry){
            return true;
        }
        if (newTimeEntry==null|| getClass() != newTimeEntry.getClass()){ return false; }
        return (this.toString().equalsIgnoreCase(((TimeEntry)newTimeEntry).toString()));
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MMM-dd");

        return "TimeEntry{id: "+ this.getId()+",projectId: "+this.projectId+",hours: "+this.hours+",date: "+date.format(formatter)+",userId: "+ this.userId+"}";
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.toString());
    }



}
