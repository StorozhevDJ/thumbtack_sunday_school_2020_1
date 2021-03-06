package net.thumbtack.school.hospital.database.model;

import java.time.LocalTime;
import java.util.Objects;

public class TicketSchedule {
    private int id;
    private int scheduleId;
    private String ticket;
    private LocalTime timeStart;
    private LocalTime timeEnd;
    private Patient patient;
    private ScheduleType scheduleType;

    public TicketSchedule () {}

    public TicketSchedule(int id, int scheduleId, String ticket, LocalTime timeStart, LocalTime timeEnd, Patient patient, ScheduleType scheduleType) {
        setId(id);
        setScheduleId(scheduleId);
        setTicket(ticket);
        setTimeStart(timeStart);
        setTimeEnd(timeEnd);
        setPatient(patient);
        setScheduleType(scheduleType);
    }

    public TicketSchedule(int scheduleId, String ticket, LocalTime timeStart, LocalTime timeEnd, Patient patient, ScheduleType scheduleType) {
        this(0, scheduleId, ticket, timeStart, timeEnd, patient, scheduleType);
    }

    public TicketSchedule(String ticket, LocalTime timeStart, LocalTime timeEnd, Patient patient, ScheduleType scheduleType) {
        this(0, 0, ticket, timeStart, timeEnd, patient, scheduleType);
    }


    public TicketSchedule(LocalTime timeStart, LocalTime timeEnd,ScheduleType scheduleType) {
        this();
        setTimeStart(timeStart);
        setTimeEnd(timeEnd);
        setScheduleType(scheduleType);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketSchedule)) return false;
        TicketSchedule that = (TicketSchedule) o;
        return getId() == that.getId() &&
                getScheduleId() == that.getScheduleId() &&
                Objects.equals(getTicket(), that.getTicket()) &&
                Objects.equals(getTimeStart(), that.getTimeStart()) &&
                Objects.equals(getTimeEnd(), that.getTimeEnd()) &&
                Objects.equals(getPatient(), that.getPatient()) &&
                getScheduleType() == that.getScheduleType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getScheduleId(), getTicket(), getTimeStart(), getTimeEnd(), getPatient(), getScheduleType());
    }
}
