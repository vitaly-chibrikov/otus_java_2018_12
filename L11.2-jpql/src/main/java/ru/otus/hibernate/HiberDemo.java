package ru.otus.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import ru.otus.hibernate.model.Person;
import ru.otus.hibernate.model.Phone;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HiberDemo {
    private static final String URL = "jdbc:postgresql://localhost:5432/otus?user=otus_user&password=otus_password";
    private final SessionFactory sessionFactory;

    public static void main(String[] args) {
        final HiberDemo demo = new HiberDemo();

//        demo.entityExample();
//        demo.leakageExample();
//        demo.fetchExample();
//        demo.JPQLexample();
        demo.nativeExample();
    }

    private HiberDemo() {
        final Configuration configuration = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect")
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create");

        final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        final Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Phone.class)
                .getMetadataBuilder()
                .build();

        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    private void entityExample() {
        try (final Session session = sessionFactory.openSession()) {
            final Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            session.persist(person);

            session.close();

            System.out.println(person);

            final Person selected = session.load(Person.class, person.getId());
            System.out.println("selected:" + selected);
            System.out.println(">>> updating >>>");

            final Transaction transaction = session.getTransaction();
            transaction.begin();
            person.setAddress("moved street");
            transaction.commit();

            final Person updated = session.load(Person.class, person.getId());
            System.out.println("updated:" + updated);

            session.detach(updated);

            System.out.println(">>> updating detached>>>");

            final Transaction transactionDetached = session.getTransaction();
            transactionDetached.begin();
            updated.setAddress("moved street NOT CHANGED");
            transactionDetached.commit();

            final Person notUpdated = session.load(Person.class, person.getId());
            System.out.println("notUpdated:" + notUpdated);
        }
    }

    private void leakageExample() {
        try (final Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.getTransaction();
            transaction.begin();

            final Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");
            session.persist(person);
            System.out.println(person);

            transaction.commit();

            session.detach(person);
            deepInIn(person);

            final Person selected = session.load(Person.class, person.getId());
            System.out.println("selected:" + selected);
        }
    }

    private void deepInIn(Person person) {
        final Person john = person;
        john.setName("john");
        System.out.println("john:" + john);
    }

    private void fetchExample() {
        final long personId;
        try (final Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.getTransaction();
            transaction.begin();

            final Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            final List<Phone> listPhone = new ArrayList<>();
            for (int idx = 0; idx < 10; idx++) {
                listPhone.add(new Phone("+" + idx, person));
            }
            person.setPhones(listPhone);

            System.out.println("persist...");
            session.save(person);
            personId = person.getId();

            System.out.println("commit...");
            transaction.commit();
        }
        try (final Session session = sessionFactory.openSession()) {
            final Phone selectedPhone = session.load(Phone.class, 3L);
            System.out.println("selectedPhone:" + selectedPhone);

            final Person selected = session.load(Person.class, personId);
            System.out.println("selected person:" + selected.getName());
//            System.out.println(selected.getPhones());
        }
    }

    private void JPQLexample() {
        try (final Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.getTransaction();
            transaction.begin();

            final Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            final List<Phone> listPhone = new ArrayList<>();
            for (int idx = 0; idx < 10; idx++) {
                listPhone.add(new Phone("+" + idx, person));
            }
            person.setPhones(listPhone);
            session.save(person);
            System.out.println("commit...");
            transaction.commit();
        }

        final EntityManager entityManager = sessionFactory.createEntityManager();

        System.out.println("select phone list:");

        final List<Phone> selectedPhones = entityManager.createQuery(
                "select p from Phone p where p.id > :phoneId", Phone.class)
                .setParameter("phoneId", 9L)
                .getResultList();

        System.out.println(selectedPhones);


        final Person person = entityManager
                .createNamedQuery("get_person_by_name", Person.class)
                .setParameter("name", "Ivan")
                .getSingleResult();

        System.out.println("selected person:" + person.getNickName());


        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Person> criteria = builder.createQuery(Person.class);
        final Root<Person> root = criteria.from(Person.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("name"), "Ivan"));

        final Person personCriteria = entityManager.createQuery(criteria).getSingleResult();
        System.out.println("selected personCriteria:" + personCriteria.getNickName());
    }

    private void nativeExample() {
        try (final Session session = sessionFactory.openSession()) {
            final Transaction transaction = session.getTransaction();
            transaction.begin();

            final Person person = new Person();
            person.setName("Ivan");
            person.setNickName("Durak");
            person.setAddress("derv str");

            final List<Phone> listPhone = new ArrayList<>();
            for (int idx = 0; idx < 10; idx++) {
                listPhone.add(new Phone("+" + idx, person));
            }
            person.setPhones(listPhone);
            session.save(person);
            System.out.println("commit...");
            transaction.commit();
        }

        try (final Session session = sessionFactory.openSession()) {
            final String name = session.doReturningWork(connection -> {
                try (final PreparedStatement ps = connection.prepareStatement("SELECT name FROM person WHERE id = ?")) {
                    ps.setLong(1, 1L);
                    try (final ResultSet rs = ps.executeQuery()) {
                        rs.next();
                        return rs.getString("name");
                    }
                }
            });
            System.out.println("sqL name:" + name);
        }
    }
}

