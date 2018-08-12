package com.weissenrieder.oauthtest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Objects;
import java.util.Set;

/**
 * Created by Rihards Gladisevs on 12.08.2018.
 */
@Getter
@Setter
public class User extends DefaultOidcUser {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;

    public User(Set<GrantedAuthority> authorities, OidcIdToken idToken) {
        super(authorities, idToken, "sub");
        this.id = idToken.getSubject();
        this.firstName = idToken.getGivenName();
        this.lastName = idToken.getFamilyName();
        this.username = idToken.getPreferredUsername();
        this.email = idToken.getEmail();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getFirstName(), getLastName(), getUsername(), getEmail());
    }
}
