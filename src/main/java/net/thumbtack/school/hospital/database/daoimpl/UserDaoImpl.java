package net.thumbtack.school.hospital.database.daoimpl;

import net.thumbtack.school.hospital.database.dao.UserDao;
import net.thumbtack.school.hospital.database.mappers.UserMapper;
import net.thumbtack.school.hospital.database.model.Session;
import net.thumbtack.school.hospital.database.model.User;
import net.thumbtack.school.hospital.serverexception.ServerError;
import net.thumbtack.school.hospital.serverexception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private UserMapper userMapper;

    public UserDaoImpl() {
    }

    @Autowired
    public UserDaoImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void update(User user) throws ServerException {
        LOGGER.debug("DAO get User by login \"{}\"", user.getLogin());
        try {
            userMapper.update(user);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get User by login {} {}", user.getLogin(), ex);
            throw new ServerException(ServerError.LOGIN_OR_PASSWORD_INVALID);
        }
    }

    @Override
    public User getByLogin(String login, String password) throws ServerException {
        LOGGER.debug("DAO get User by login \"{}\"", login);
        try {
            return userMapper.getByLogin(login, password);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get User by login {} {}", login, ex);
            throw new ServerException(ServerError.LOGIN_OR_PASSWORD_INVALID);
        }
    }

    @Override
    public User getByToken(String token) throws ServerException {
        LOGGER.debug("DAO get User by token {}", token);
        try {
            return userMapper.getByToken(token);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't get User by token {} {}", token, ex);
            throw new ServerException(ServerError.TOKEN_INVALID);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logIn(User user) throws ServerException {
        LOGGER.debug("DAO LogIn user \"{}\" ", user.getLogin());
        try {
            userMapper.insertToken(user);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't LogIn user {} {} ", user.getLogin(), ex);
            throw new ServerException(ServerError.LOGIN_OR_PASSWORD_INVALID);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void logOut(Session token) throws ServerException {
        LOGGER.debug("DAO LogOut user with token {} ", token);
        try {
            userMapper.deleteToken(token);
        } catch (DataAccessException ex) {
            LOGGER.info("Can't LogOut user with token {} {} ", token, ex);
            throw new ServerException(ServerError.TOKEN_INVALID);
        }
    }

}
