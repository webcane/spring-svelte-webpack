package io.github.webcane.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author mniedre
 */
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * find all Items like projections
     *
     * @param type class of projection
     * @param <T>  type of the address projection
     * @return projection's list
     */
    @Query("select i from Item i")
    <T> List<T> findAll(Class<T> type);
}
