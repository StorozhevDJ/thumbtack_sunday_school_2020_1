package net.thumbtack.school.hospital.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import net.thumbtack.school.hospital.database.dao.AdminDao;
import net.thumbtack.school.hospital.database.dao.CommonDao;
import net.thumbtack.school.hospital.database.dao.DoctorDao;
import net.thumbtack.school.hospital.database.dao.PatientDao;
import net.thumbtack.school.hospital.database.dao.ScheduleDao;
import net.thumbtack.school.hospital.database.dao.UserDao;
import net.thumbtack.school.hospital.database.daoimpl.AdminDaoImpl;
import net.thumbtack.school.hospital.database.daoimpl.CommonDaoImpl;
import net.thumbtack.school.hospital.database.daoimpl.DoctorDaoImpl;
import net.thumbtack.school.hospital.database.daoimpl.PatientDaoImpl;
import net.thumbtack.school.hospital.database.daoimpl.ScheduleDaoImpl;
import net.thumbtack.school.hospital.database.daoimpl.UserDaoImpl;
import net.thumbtack.school.hospital.database.model.Admin;
import net.thumbtack.school.hospital.database.model.Doctor;
import net.thumbtack.school.hospital.database.model.Patient;
import net.thumbtack.school.hospital.database.model.User;
import net.thumbtack.school.hospital.database.utils.MyBatisUtils;

public class DatabaseTest {

    private CommonDao commonDao = new CommonDaoImpl();
    protected UserDao userDao = new UserDaoImpl();
    protected AdminDao adminDao = new AdminDaoImpl();
    protected DoctorDao doctorDao = new DoctorDaoImpl();
    protected PatientDao patientDao = new PatientDaoImpl();
    protected ScheduleDao scheduleDao = new ScheduleDaoImpl();

    @BeforeAll()
    public static void setUp() {
        boolean initSqlSessionFactory = MyBatisUtils.initSqlSessionFactory();
        if (!initSqlSessionFactory) {
            throw new RuntimeException("Can't create connection, stop");
        }
    }

    @BeforeEach
    public void clearDB() {
        commonDao.clear();
        User user = new User("FirstNameAdmin", "lastNameAdmin", null, "admin", "admin", null);
        adminDao.insert(new Admin(user, "Superadmin"));

        doctorDao.insert(new Doctor(new User("FirstNameDoc", "lastNameDoc", null, "doc", "doctor", null), "spec", "1"));

        patientDao.insert(new Patient(
                new User("FirstNamePatient", "lastNamePatient", "partronymicPatient", "patientLogin", "passwordPatient", null),
                "patient@mail",
                "addrPatient",
                "+79001112233"));
    }

}
