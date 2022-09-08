package me.chrisanabo.db;

import me.chrisanabo.db.dao.EmployeeDao;
import me.chrisanabo.db.dao.EmployeeDaoImpl;
import me.chrisanabo.db.model.Employee;
import me.chrisanabo.db.service.GenerateResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Application {

    public static void main(String[] args) throws SQLException, ExecutionException,
            InterruptedException, TimeoutException {

        GenerateResponse generateResponse = new GenerateResponse("Id");
      //    generateResponse.constructReply("").thenSendResponse();

    }
}