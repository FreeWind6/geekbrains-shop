package ru.geekbrains.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.shop.persistence.entities.Shopuser;
import ru.geekbrains.shop.persistence.entities.enums.Role;
import ru.geekbrains.shop.persistence.repositories.ShopuserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopuserService implements UserDetailsService {

    private final ShopuserRepository shopuserRepository;

    public Optional<Shopuser> findByPhone(String phone) {
        return shopuserRepository.findByPhone(phone);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Shopuser shopuser;
        Optional<Shopuser> shopuserOptional = shopuserRepository.findByPhone(username);
        if (shopuserOptional.isEmpty()) {
            throw new UsernameNotFoundException("Invalid username or password!");
        } else {
            shopuser = shopuserOptional.get();
        }
        return new User(shopuser.getPhone(), shopuser.getPassword(), mapRoleToAuthorities(shopuser.getRole()));

    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Role role) {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles.stream().map(userRole -> new SimpleGrantedAuthority((userRole.name()))).collect(Collectors.toList());
    }

}
