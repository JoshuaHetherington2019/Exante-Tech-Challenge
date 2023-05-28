package com.example.verifit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long membershipId;
    @PastOrPresent
    @NotNull
    private LocalDateTime lastAttendanceDate;
    @PositiveOrZero
    private int currentStreak;
    private Boolean discountStatus;

    public Member() {
    }

    public Member(LocalDateTime attendanceRecord, int currentStreak, Boolean discountStatus) {
        this.lastAttendanceDate = attendanceRecord;
        this.currentStreak = currentStreak;
        this.discountStatus = discountStatus;
    }

    public long getMembershipId() {
        return membershipId;
    }

    public LocalDateTime getLastAttendanceDate() {
        return lastAttendanceDate;
    }

    public void setLastAttendanceDate(LocalDateTime lastAttendanceDate) {
        this.lastAttendanceDate = lastAttendanceDate;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public Boolean getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(Boolean discountStatus) {
        this.discountStatus = discountStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "\"membershipId\":" + membershipId +
                ", \"lastAttendanceDate\":" + lastAttendanceDate +
                ", \"currentStreak\":" + currentStreak +
                ", \"discountStatus\":" + discountStatus +
                '}';
    }
}
