package com.plaidcamp.mealogram.domain.user;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plaidcamp.mealogram.domain.BaseEntity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class UserMaster extends BaseEntity implements UserDetails {

    @Column(unique = true)
    @NotNull
    private String email;

    //@JsonIgnore
    @Column
    private String password;

    @Column
    private Integer cntAccount;

    @Column
    @NotNull
    private String administrate;

    @Column
    private LocalDateTime lastLoginDate;

    @Column
    private Integer pwerrcnt;

    @Column
    private String pwinitcode;

    @Column
    private String phone;

    @Column
    private String userClass;

    @Column
    private String birthday;

    @Column
    private String facebookKey;

    @Column
    private String googleKey;

    @ElementCollection(fetch = FetchType.EAGER)
    //@Builder.Default
    private List<String> roles = new ArrayList<>();

//    @OneToMany(mappedBy = "user_account")
//    private final Set<UserAccount> userAccounts = new HashSet<UserAccount>(); // account 개발 후 재활성화 예정

    @PrePersist
    void hashPassword() {
        if (password == null || password.isEmpty()) {
            throw new DataIntegrityViolationException("Empty password");
        }
        password = new BCryptPasswordEncoder().encode(this.password);
    }

    public boolean comparePassword(String password) {
        return new BCryptPasswordEncoder().matches(password, this.password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : administrate.split(","))
            roles.add(new SimpleGrantedAuthority(role));
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserMaster{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cntAccount=" + cntAccount +
                ", administrate=" + administrate +
                ", lastLoginDate=" + lastLoginDate +
                ", pwerrcnt=" + pwerrcnt +
                ", pwinitcode='" + pwinitcode + '\'' +
                ", phone='" + phone + '\'' +
                ", userClass='" + userClass + '\'' +
                ", facebookKey='" + facebookKey + '\'' +
                ", googleKey='" + googleKey + '\'' +
                ", roles=" + roles +
                '}';
    }
}
