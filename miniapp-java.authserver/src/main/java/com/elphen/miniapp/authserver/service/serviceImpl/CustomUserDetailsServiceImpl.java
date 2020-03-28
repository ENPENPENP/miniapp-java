package com.elphen.miniapp.authserver.service.serviceImpl;

import com.elphen.miniapp.authserver.service.TUserService;
import com.elphen.miniapp.domain.entity.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/*
 * @ClassName CustomUsesrDetalilServiceImpl
 * @Auth Elphen
 * @Description TODO
 **/
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TUserService tUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
