package ru.otus.l111.base.dataSets;

import javax.persistence.*;

/**
 * Created by tully.
 */
@Entity
public class EmptyDataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
