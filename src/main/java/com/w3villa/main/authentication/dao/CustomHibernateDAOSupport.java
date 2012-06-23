package com.w3villa.main.authentication.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

@Component
public abstract class CustomHibernateDAOSupport extends HibernateDaoSupport{
	
}
