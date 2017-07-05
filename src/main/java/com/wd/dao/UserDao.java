package com.wd.dao;

import com.wd.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by root on 17-3-11.
 */
public interface UserDao extends JpaRepository<User,Long>{

}
