package net.thumbtack.school.hospital.dto.response;

/*{
[
"ticket": “номер талона”,
"room": ”номер кабинета”,
"date": "дата",
"time": "время",
// для случая приема у одного врача
"doctorId": идентификатор врача,
"firstName": "имя врача",
"lastName": "фамилия врача",
"patronymic": "отчество врача",
"speciality": "специальность врача",
// для случая приема на врачебной комиссии
[
{
"doctorId": идентификатор врача,
"firstName": "имя врача",
"lastName": "фамилия врача",
"patronymic": "отчество врача",
"speciality": "специальность врача",
}
...
]
...
]
}*/

public class GetTicketDtoResponse {

    private String ticket;
    private String room;
    private String date;
    private String time;

    private int doctorId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String speciality;


    public GetTicketDtoResponse(String ticket, String room, String date, String time, int doctorId, String firstName,
                                String lastName, String patronymic, String speciality) {
        super();
        this.ticket = ticket;
        this.room = room;
        this.date = date;
        this.time = time;
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.speciality = speciality;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }


}
