package io.github.webcane.item;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * @author mniedre
 */
@Data
@NoArgsConstructor
public class ItemForm {

    @Size(max = 255)
    private String title;
}
