package com.BJJ.BJJSite.Classes;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.BJJ.BJJSite.Enums.PayBasis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Integer id;

    @Column(nullable = false)
    @Builder.Default
    protected String firstname = "test";

    @Column(nullable = false)
    @Builder.Default
    protected String lastname = "user";

    @Column(nullable = false)
    @Builder.Default
    protected String username = "defaultUser";

    @Column(nullable = false)
    @Builder.Default
    protected String password = "Applesauce1";

    @Column(unique = true, length = 100, nullable = false)
    @Builder.Default
    protected String email = "test@user.com";

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false)
    @Builder.Default
    private boolean accountNonExpired = true;

    @Column(nullable = false)
    @Builder.Default
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    @Builder.Default
    private boolean credentialsNonExpired = true;

    @Column(nullable = false)
    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private String phone = "NO_PHONE";

    @Builder.Default
    private String address = "NO_ADDRESS";

    @Builder.Default
    private String sex = "DEFAULT_SEX";

    @Builder.Default
    private String dob = "DEFAULT_DOB";

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;

    @Builder.Default
    private String socialSecurityNumber = "000-00-0000";

    @Builder.Default
    private String position = "DEFAULT_POSITION";

    @Builder.Default
    private double payrate = 0.0;

    @Builder.Default
    private PayBasis paybasis = PayBasis.VOLUNTEER;

    @Builder.Default
    private double baseEarnings = 0.0;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
}
