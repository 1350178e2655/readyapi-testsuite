package me.chrisanabo.db.dao;

import me.chrisanabo.db.model.Employee;
import me.chrisanabo.db.model.TestInput;

import java.sql.SQLException;

public interface TestInputDao {

    public TestInput getTestInput(int id) throws SQLException;

    public TestInput getTestInput(String msgId) throws SQLException;


}
