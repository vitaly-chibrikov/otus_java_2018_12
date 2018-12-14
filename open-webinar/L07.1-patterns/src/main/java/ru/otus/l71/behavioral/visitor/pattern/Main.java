package ru.otus.l71.behavioral.visitor.pattern;

import ru.otus.l71.behavioral.visitor.pattern.services.CarCheckService;
import ru.otus.l71.behavioral.visitor.pattern.services.CarRepairService;
import ru.otus.l71.behavioral.visitor.pattern.services.CarWashService;

/**
 * Created by tully.
 */
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.doService(new CarCheckService());
        car.doService(new CarWashService());
        car.doService(new CarRepairService());
    }
}
