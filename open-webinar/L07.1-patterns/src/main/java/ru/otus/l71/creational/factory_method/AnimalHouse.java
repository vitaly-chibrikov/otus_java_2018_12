package ru.otus.l71.creational.factory_method;

/**
 * Created by tully.
 * <p>
 * Framework in the Factory method pattern.
 */
public abstract class AnimalHouse {
    private final Animal animal;

    protected AnimalHouse() {
        //build a house from parts
        //add a bowl
        //add toys

        //create an animal
        animal = getAnimal();
        animal.makeSound();
    }

    // protected abstract Part[] getParts();
    // protected abstract Bowl getBowl();
    // protected abstract Toy[] getToys();

    protected abstract Animal getAnimal();
}
