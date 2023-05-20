package com.example.demo.manager.login.bo;

import com.example.demo.manager.login.dto.AdminDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author GaZ
 * @date 2022/3/10 9:05
 * @Description
 */
public class AdminUserDetails implements UserDetails {

    //后台用户
    private AdminDto adminDto;

    public AdminUserDetails(AdminDto adminDto) {
        this.adminDto = adminDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return adminDto.getMenuList().stream()
                .map(menu ->new SimpleGrantedAuthority(menu.getId()+":"+menu.getMenuName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return adminDto.getPassword();
    }

    @Override
    public String getUsername() {
        return adminDto.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return adminDto.getStatus().equals(1);
    }
}
