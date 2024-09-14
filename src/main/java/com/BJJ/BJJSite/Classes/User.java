package com.BJJ.BJJSite.Classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a User.
 * 
 * The User class implements the Spring Security `UserDetails` interface and is an entity
 * that includes various attributes such as username, password, email, and more. It also
 * tracks roles (authorities) and payment options associated with the user.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Long userId;

    @Column(nullable = false)
    protected String fullName;

    @Column(nullable = false)
    protected String username;

    @Column(nullable = false)
    protected String password;

    @Column(unique = true, length = 100, nullable = false)
    protected String email;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private String phone;
    private String address;
    private String sex;
    private String dob;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentOption> paymentOptions;

    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Protected constructor to be called by the UserBuilder.
     * 
     * @param UserBuilder The builder used to construct the User instance.
     */
    protected User(UserBuilder<?> UserBuilder) {
        fullName = UserBuilder.fullName;
        phone = UserBuilder.phone;
        username = UserBuilder.username;
        password = UserBuilder.password;
        email = UserBuilder.email;
        address = UserBuilder.address;
        sex = UserBuilder.sex;
        dob = UserBuilder.dob;
        createdAt = UserBuilder.createdAt;
        updatedAt = UserBuilder.updatedAt;
        accountNonExpired = UserBuilder.accountNonExpired;
        accountNonLocked = UserBuilder.accountNonLocked;
        credentialsNonExpired = UserBuilder.credentialsNonExpired;
        enabled = UserBuilder.enabled;
        paymentOptions = UserBuilder.paymentOptions;
        role = UserBuilder.role;
    }

    /**
     * Builder class for creating a User instance.
     * 
     * This builder pattern allows for flexible and customizable creation of User objects.
     */
    public static class UserBuilder<T extends UserBuilder<T>> {

        private String fullName = "DEFAULT_NAME";
        private String phone = "NO EMAIL ON FILE";
        private String username = "NO FIRST NAME ON FILE";
        protected String password = "NO LAST NAME ON FILE";
        protected String email = "NO PHONE NUMBER ON FILE";
        private String address = "NO ADDRESS ON FILE";
        private String sex = "DEFAULT";
        private String dob = "DEFAULT";
        private Date createdAt;
        private Date updatedAt;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;
        private List<PaymentOption> paymentOptions = new ArrayList<>();
        private Role role;

        /**
         * Default constructor for UserBuilder.
         */
        public UserBuilder() {
        }

        public T fullName(String value) {
            this.fullName = value;
            return self();
        }

        public T phone(String value) {
            this.phone = value;
            return self();
        }

        public T username(String value) {
            this.username = value;
            return self();
        }

        public T password(String value) {
            this.password = value;
            return self();
        }

        public T email(String value) {
            this.email = value;
            return self();
        }

        public T address(String value) {
            this.address = value;
            return self();
        }

        public T sex(String value) {
            this.sex = value;
            return self();
        }

        public T dob(String value) {
            this.dob = value;
            return self();
        }

        public T createdAt(Date value) {
            this.createdAt = value;
            return self();
        }

        public T updatedAt(Date value) {
            this.updatedAt = value;
            return self();
        }

        public T accountNonExpired(boolean value) {
            this.accountNonExpired = value;
            return self();
        }

        public T accountNonLocked(boolean value) {
            this.accountNonLocked = value;
            return self();
        }

        public T credentialsNonExpired(boolean value) {
            this.credentialsNonExpired = value;
            return self();
        }

        public T enabled(boolean value) {
            this.enabled = value;
            return self();
        }

        public T paymentOptions(List<PaymentOption> paymentOptions) {
            this.paymentOptions = paymentOptions;
            return self();
        }

        @SuppressWarnings("unchecked")
        public T addPaymentOption(PaymentOption paymentOption) {
            paymentOptions.add(paymentOption);
            return (T) this;
        }

        @SuppressWarnings("unchecked")
        protected T self() {
            return (T) this;
        }

        public T role(Role value) {
            this.role = value;
            return self();
        }

        public User buildUser() {
            return new User(this);
        }
    }

    /**
     * Retrieves the ID of the User.
     * 
     * @return The ID of the User.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the User.
     * 
     * This method is primarily intended for testing purposes.
     * 
     * @param id The ID to set.
     */
    public void setId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName != null) {
            this.fullName = fullName;
        } else {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("A Phone number is necessary");
        }
    }

    /**
     * Retrieves the username of the User.
     * 
     * @return The username of the User.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the User.
     * 
     * @param username The username of the User.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password of the User.
     * 
     * @return The password of the User.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the User.
     * 
     * @param Password The password of the User.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email error");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<PaymentOption> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<PaymentOption> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public void addPaymentOption(PaymentOption paymentOption) {
        paymentOptions.add(paymentOption);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

}
