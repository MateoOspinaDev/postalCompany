package com.workshop.postal.Security.services;

import com.workshop.postal.Security.User.Role;
import com.workshop.postal.Security.User.SecurityUser;
import com.workshop.postal.Security.User.SecurityUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements IUserDetailsService {

    private final SecurityUserRepository securityUserRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final RolRepository rolRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<SecurityUser> userOptional = securityUserRepository
                        .findByEmail(username);

                if (userOptional.isEmpty()) {
                    throw new UsernameNotFoundException("El usuario con el nombre de usuario/correo electrónico " + username + " no existe");
                }

                SecurityUser securityUser = userOptional.get();

                return User.builder()
                        .username(securityUser.getUsername())
                        .password(securityUser.getPassword())
                        .roles(Role.USER.name())
                        .build();
            }
        };
    }

//    @Override
//    public UserClient saveUsuario(UserClient user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userClientRepository.save(user);
//    }
//
//    @Override
//    public Rol saveRol(Rol role) {
//        return rolRepository.save(role);
//    }
//
//    @Override
//    public void addRolToUsuario(String username, String roleName) {
//        UserClient user = userClientRepository.findByUsername(username);
//        Rol role = rolRepository.findByName(roleName);
//        user.getRoles().add(role);
//    }
//
//    @Override
//    public UserClient getUsuario(String username) {
//        return userClientRepository.findByUsername(username);
//    }
//
//    @Override
//    public List<UserClient> getUsuarios() {
//        return userClientRepository.findAll();
//    }
//
//    @Override
//    public boolean existsByUsername(String username) {
//        return userClientRepository.existsByUsername(username);
//    }
}