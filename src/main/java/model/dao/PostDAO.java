package model.dao;

import javax.naming.*;
import javax.sql.*;

import java.sql.*;
import java.util.*;

class PostDAO {
	private DataSource ds;
	PostDAO() {
		try{
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/TestDB");
		}catch(NamingException ne){
		}
	}
}
