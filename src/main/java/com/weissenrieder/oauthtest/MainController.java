package com.weissenrieder.oauthtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Rihards Gladisevs on 09.08.2018.
 */
@Controller
public class MainController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) principal;
            OAuth2AuthorizedClient authorizedClient =
                    this.authorizedClientService.loadAuthorizedClient(
                            authentication.getAuthorizedClientRegistrationId(),
                            authentication.getName());
            model.addAttribute("userName", authentication.getName());
            model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        } else {
            model.addAttribute("userName", principal.getName());
        }
        return "index";
    }

    @GetMapping("/users/info")
    public String userInfo(Model model, OAuth2AuthenticationToken authentication) {
        OAuth2User principal = authentication.getPrincipal();
        Map<String, Object> authenticationAttributes = principal.getAttributes();
        model.addAttribute("authenticationAttributes", authenticationAttributes);
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        model.addAttribute("grantedAuthorities", authorities);
        return "user-info";
    }
}
