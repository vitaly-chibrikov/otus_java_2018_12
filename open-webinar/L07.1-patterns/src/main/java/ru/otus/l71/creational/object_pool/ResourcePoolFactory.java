package ru.otus.l71.creational.object_pool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tully.
 */
public class ResourcePoolFactory implements ResourceFactory {
    private Queue<Resource> pool = new LinkedList<>();

    @Override
    public Resource get() {
        if (pool.isEmpty()) {
            return new PoolResource(new RealResource());
        } else {
            return pool.poll();
        }
    }

    public class PoolResource implements Resource {
        private final Resource resource;

        PoolResource(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void print() {
            resource.print();
        }

        @Override
        public void close() {
            pool.add(this);
        }
    }
}
