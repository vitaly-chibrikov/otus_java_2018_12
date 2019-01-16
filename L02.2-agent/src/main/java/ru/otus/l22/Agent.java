package ru.otus.l22;


import java.lang.instrument.Instrumentation;

/**
 * Created by tully.
 */
public class Agent {
    private static Agent agent;
    private Instrumentation instrumentation;

    private Agent(){}

    static Agent instance(){
        if(agent == null){
            agent = new Agent();
        }
        return agent;
    }

    public static void premain(String args, Instrumentation instrumentation) {
        instance().instrumentation = instrumentation;
        System.out.println("Classes loaded: " + instrumentation.getAllLoadedClasses().length);


        System.out.println("String size: " + instrumentation.getObjectSize(new String(new char[100]))); //"shallow" size.
        System.out.println("int[10] size: " + instrumentation.getObjectSize(new int[10]));
    }

    Instrumentation getInstrumentation() {
        return instrumentation;
    }
}
