package net.thumbtack.school.hospital.database.dao;

import net.thumbtack.school.hospital.database.model.Admin;

public interface AdminDao {
    /**
     * Add new administrator
     *
     * @param admin - new admin
     * @return inserted admin
     */
    Admin insert(Admin admin);

    /**
     * Get admin info by token from JAVASESSIONID cookie
     *
     * @param token
     * @return admin account
     */
    Admin getByToken(String token);

    /**
     * Get admin info by User ID
     *
     * @param id
     * @return
     */
    Admin getByUserId(int id);
}