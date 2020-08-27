package com.example.dateexam.jpa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class IntervalDate {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date dateToCheck;

    @Column
    private boolean inInterval;

    @Column
    private long numberOfdays;

    public IntervalDate() {
    }

    public IntervalDate(Date dateToCheck, boolean inInterval, long numberOfdays) {

        this.dateToCheck = dateToCheck;
        this.inInterval = inInterval;
        this.numberOfdays = numberOfdays;
    }



    public Date getDateToCheck() {
        return dateToCheck;
    }

    public void setDateToCheck(Date dateToCheck) {
        this.dateToCheck = dateToCheck;
    }

    public boolean isInInterval() {
        return inInterval;
    }

    public void setInInterval(boolean inInterval) {
        this.inInterval = inInterval;
    }

    public long getNumberOfdays() {
        return numberOfdays;
    }

    public void setNumberOfdays(int numberOfdays) {
        this.numberOfdays = numberOfdays;
    }
}
