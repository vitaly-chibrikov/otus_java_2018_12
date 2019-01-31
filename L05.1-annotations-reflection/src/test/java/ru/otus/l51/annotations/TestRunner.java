package ru.otus.l51.annotations;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class TestRunner {
    public static void main(String[] args) {
        run(AnnotationsTest.class);
    }

    private static void run(Class<?> testClass) {
        final LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectClass(testClass)
                )
                .build();

        final Launcher launcher = LauncherFactory.create();
        launcher.execute(request);

//        JUnitCore junit = new JUnitCore();
//        junit.run(testClass.class);
    }
}
