package ru.otus.l061.ehcache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;
import java.lang.management.ManagementFactory;

/**
 * Created by tully.
 */
public class EhcacheMain {

    private final CacheManager cacheManager;

    private EhcacheMain() {
        CachingProvider provider = Caching.getCachingProvider();
        cacheManager = provider.getCacheManager();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());
        org.apache.log4j.BasicConfigurator.configure();
        new EhcacheMain().run();
    }

    private void run() throws InterruptedException {
        Configuration<Integer, String> configuration =
                new MutableConfiguration<Integer, String>().setTypes(Integer.class, String.class);

        Cache<Integer, String> cache = cacheManager.createCache("jCache", configuration);
        cacheManager.enableStatistics("jCache", true);
        cacheManager.enableManagement("jCache", true);

        int steps = 200;

        new Thread(() -> {
            for (int i = 0; i < steps; i++) {
                cache.put(i, "String of: " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(5000);

        for (int i = 0; i < steps; i++) {
            String value = cache.get(i);
            System.out.println("Value: " + value);
            Thread.sleep(900);

        }


        cacheManager.close();
    }
}
