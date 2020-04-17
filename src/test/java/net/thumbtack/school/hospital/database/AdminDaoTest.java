package net.thumbtack.school.hospital.database;

import static org.junit.jupiter.api.Assertions.*;

import net.thumbtack.school.hospital.database.model.Session;
import net.thumbtack.school.hospital.database.model.UserType;
import net.thumbtack.school.hospital.serverexception.ServerException;
import org.junit.jupiter.api.Test;

import net.thumbtack.school.hospital.database.model.Admin;
import net.thumbtack.school.hospital.database.model.User;


public class AdminDaoTest extends DatabasePrepare {

    @Test
    public void testAddAdminGetByUserId() {
        User user = new User("Вася", "Петечкин", "Васильевич", "testAdmin", "password", null);
        Admin admin = new Admin(user, "SecondAdmin");
        adminDao.insert(admin);
        // Check double add
        assertThrows(RuntimeException.class, () -> adminDao.insert(admin));
        assertAll(
                () -> assertNotEquals(0, admin.getId()),
                () -> assertEquals(admin.getUser().getId(), user.getId()),
                () -> assertEquals(UserType.ADMINISTRATOR, admin.getUser().getType()),
                () -> assertEquals(2, adminDao.getCount())
        );

        Admin getAdmin = adminDao.getByUserId(user.getId());
        assertAll(
                () -> assertNotEquals(0, getAdmin.getId(), "admin id = 0"),
                () -> assertEquals(getAdmin.getUser().getId(), user.getId(), "Admin and user IDs is different"),
                () -> assertEquals("SecondAdmin", getAdmin.getPosition(), "Admin position is not SecondAdmin"),
                () -> assertEquals(UserType.ADMINISTRATOR, getAdmin.getUser().getType(), "User type is not admin"),
                () -> assertEquals("Вася", getAdmin.getUser().getFirstName(), "User firstName is not Вася"),
                () -> assertEquals("Петечкин", getAdmin.getUser().getLastName(), "User lastname is not Петечкин"),
                () -> assertEquals("Васильевич", getAdmin.getUser().getPatronymic(), "User patronymic is not Васильевич"),
                () -> assertEquals("testAdmin", getAdmin.getUser().getLogin(), "User login is not testAdmin"),
                () -> assertNull(getAdmin.getUser().getPassword(), "User password is not null")
        );
    }

    @Test
    public void testGetDefaultAdmin() {
        User user = null;
        try {
            user = userDao.getByLogin("admin", "admin");
        } catch (ServerException e) {
            fail();
        }
        Admin admin = adminDao.getByUserId(user.getId());
        assertNotEquals(0, admin.getId());
        assertNotEquals(0, admin.getUser().getId());
        assertNotEquals(0, admin.getUser().getId());
    }

    @Test
    public void testGetByToken() {
        try {
            userDao.logIn(new User("admin", "admin", new Session("token")));
        } catch (ServerException e) {
            fail();
        }

        Admin admin = adminDao.getByToken(new Session("token"));
        assertAll(
                () -> assertEquals("Superadmin", admin.getPosition(), "Admin position is not Superadmin"),
                () -> assertEquals(UserType.ADMINISTRATOR, admin.getUser().getType(), "User type is not admin"),
                () -> assertEquals("FirstNameAdmin", admin.getUser().getFirstName(), "User firstName is not FirstNameAdmin"),
                () -> assertEquals("lastNameAdmin", admin.getUser().getLastName(), "User lastname is not lastNameAdmin"),
                () -> assertNull(admin.getUser().getPatronymic(), "User patronymic is not null"),
                () -> assertEquals("admin", admin.getUser().getLogin(), "User login is not Admin"),
                () -> assertNull(admin.getUser().getPassword(), "User password is not null")
        );

    }

}
