package com.workshop.postal.Security.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService{
    UserDetailsService userDetailsService();
//    UserClient saveUsuario(UserClient user);
//    Rol saveRol(Rol role);
//    void addRolToUsuario(String username, String roleName);
//    UserClient getUsuario(String username);
//    List<UserClient> getUsuarios();
//    boolean existsByUsername(String username);
}
