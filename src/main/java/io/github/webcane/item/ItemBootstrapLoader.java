package io.github.webcane.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mniedre
 */
@Slf4j
@Component
@Profile("dev")
public class ItemBootstrapLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static List<Item> allItems = new ArrayList<>();

    private ItemRepository itemRepo;

    @Autowired
    public ItemBootstrapLoader(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;

        allItems.add(new Item("item 1"));
        allItems.add(new Item("item 2"));
        allItems.add(new Item("item 3"));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        for (Item a : allItems) {
            Item a2 = itemRepo.save(a);
            log.info("{} added ", a2);
        }
    }
}
