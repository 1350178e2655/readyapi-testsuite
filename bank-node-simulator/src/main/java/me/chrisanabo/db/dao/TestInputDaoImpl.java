package me.chrisanabo.db.dao;


import me.chrisanabo.db.model.TestInput;
import me.chrisanabo.db.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestInputDaoImpl  implements TestInputDao {

    private final Connection con;

    public TestInputDaoImpl() throws SQLException {
        this.con = ConnectionPool.getInstance("conn-pool").getConnection();
    }


    @Override
    public TestInput getTestInput(int id) throws SQLException {

        String query = "select * from employee where emp_id= ?";

        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        TestInput testInput = new TestInput();
        ResultSet rs = ps.executeQuery();

        boolean exist = false;

        while (rs.next()) {
            exist = true;
        }

        if (exist) {
            return testInput;
        } else {
            return null;
        }
    }

    @Override
    public TestInput getTestInput(String msgId) throws SQLException {
        return null;
    }


}
