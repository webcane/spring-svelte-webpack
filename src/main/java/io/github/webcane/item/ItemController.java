package io.github.webcane.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author mniedre
 */
@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService svc;

    @Autowired
    protected ItemController(ItemService service) {
        this.svc = service;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public ResponseEntity<List<ItemView>> findAllItems() {
        List<ItemView> result = svc.findAll();
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> postItem(@Valid @RequestBody ItemForm request) {
        try {
            this.svc.addItem(request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            log.warn("Unable to add item {}. {}", request, ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
