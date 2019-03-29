package ru.otus.l111.main;

import ru.otus.l111.base.DBService;
import ru.otus.l111.base.dataSets.UserDataSet;
import ru.otus.l111.dbService.DBServiceImpl;

import java.util.List;


public class MainXML {
    public static void main(String[] args) {
        DBService dbService = new DBServiceImpl();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);


        dbService.save(new UserDataSet("tully"));
        dbService.save(new UserDataSet("sully"));

        UserDataSet dataSet = dbService.read(1);
        System.out.println(dataSet);

        dataSet = dbService.readByName("sully");
        System.out.println(dataSet);

        List<UserDataSet> dataSets = dbService.readAll();
        for (UserDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }

        dbService.shutdown();
    }
}
