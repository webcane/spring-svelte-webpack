package io.github.webcane.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author mniedre
 */
@Slf4j
@Profile("dev")
@RestController
public class PrincipalController {

    @GetMapping
    @RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public Principal user(@AuthenticationPrincipal Principal principal) {
        return principal;
    }
}
