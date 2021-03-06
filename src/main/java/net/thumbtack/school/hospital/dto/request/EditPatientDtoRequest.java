package net.thumbtack.school.hospital.dto.request;

/*{
"firstName": "имя",
"lastName": "фамилия",
"patronymic": "отчество", // необязателен
"email": "адрес",
"address": "домашний адрес, одной строкой",
"phone": "номер",
"oldPassword": "прежний пароль",
"newPassword": "новый пароль"
}*/

import net.thumbtack.school.hospital.dto.validation.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EditPatientDtoRequest {

    @FirstName
    private String firstName;
    @LastName
    private String lastName;
    @Patronymic
    private String patronymic;
    @Email
    private String email;
    @NotNull
    private String address;
    @Phone
    private String phone;
    private String oldPassword;
    @Password
    private String newPassword;

    public EditPatientDtoRequest() {
    }

    public EditPatientDtoRequest(String firstName, String lastName, String patronymic, String email, String address,
                                 String phone, String oldPassword, String newPassword) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
