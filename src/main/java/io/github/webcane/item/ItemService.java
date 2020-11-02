package io.github.webcane.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mniedre
 */
@Service
public class ItemService {

    private ItemRepository itemRepo;

    @Autowired
    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public List<ItemView> findAll() {
        return itemRepo.findAll(ItemView.class);
    }

    @Transactional
    public boolean addItem(ItemForm request) {
        Item a = new Item(request.getTitle());
        a = itemRepo.save(a);
        return (a != null);
    }
}
