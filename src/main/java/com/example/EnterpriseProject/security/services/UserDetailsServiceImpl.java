package com.example.EnterpriseProject.security.services;

import com.example.EnterpriseProject.entity.User;
import com.example.EnterpriseProject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional //It will help to rollback over initial state if there are any errors happening during the process
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        if (identifier.isBlank()) {
            throw new UsernameNotFoundException("Identifier cannot be null or empty");
        }

        User user = userRepository.findByCardId(identifier)
                .or(() -> userRepository.findByUsername(identifier))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with cardId or username: " + identifier));

        return UserDetailsImpl.build(user);
    }
}
