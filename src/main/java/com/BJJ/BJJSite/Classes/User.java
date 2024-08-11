package com.BJJ.BJJSite.Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.BJJ.BJJSite.Interfaces.UserUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a User.
 */
@Entity
@Table(name = "users")
public class User implements UserUtils {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    protected Long userId;

    @Column(nullable = false)
    protected String fullName;

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

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    private String phone;
    private String address;
    private String sex;
    private String dob;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentOption> paymentOptions;

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
        isAccountNonExpired = UserBuilder.isAccountNonExpired;
        isAccountNonLocked = UserBuilder.isAccountNonLocked;
        isCredentialsNonExpired = UserBuilder.isCredentialsNonExpired;
        isEnabled = UserBuilder.isEnabled;
        paymentOptions = UserBuilder.paymentOptions;
    }

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
        private boolean isAccountNonExpired;
        private boolean isAccountNonLocked;
        private boolean isCredentialsNonExpired;
        private boolean isEnabled;
        public List<PaymentOption> paymentOptions = new ArrayList<>();

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

        public T isAccountNonExpired(boolean value) {
            this.isAccountNonExpired = value;
            return self();
        }

        public T isAccountNonLocked(boolean value) {
            this.isAccountNonLocked = value;
            return self();
        }

        public T isCredentialsNonExpired(boolean value) {
            this.isCredentialsNonExpired = value;
            return self();
        }

        public T isEnabled(boolean value) {
            this.isEnabled = value;
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
     * For testing purpose only.
     * 
     * @param id
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
     * @throws IllegalArgumentException if the username is null or exceeds 10
     *                                  characters.
     */
    public void setUsername(String username) {
        if (username != null && username.length() <= 10) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Username must be fewer than 10 characters");
        }
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
     * @throws IllegalArgumentException if the password is null or exceeds 10
     *                                  characters.
     */
    public void setPassword(String password) {
        if (password != null && password.length() <= 10) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Password must be fewer than 10 characters");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
            // ADD EMAIL ERROR CHECKING
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

    public boolean getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public boolean getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
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

    /*
     * @Override
     * public Collection<? extends GrantedAuthority> getAuthorities() {
     * return List.of();
     * }
     */

}
