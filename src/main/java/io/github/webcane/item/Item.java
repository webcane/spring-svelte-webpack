package io.github.webcane.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author mniedre
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "ITEM")
public class Item implements Serializable, Persistable<Long> {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_gen")
    @SequenceGenerator(name = "item_gen", sequenceName = "item_seq")
    @Column(name = "ITEM_ID", unique = true)
    private Long id;

    @Column(name = "TITLE", unique = true, nullable = false)
    private String title;

    /**
     * Constructor
     *
     * @param title
     */
    public Item(String title) {
        this.title = title;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
