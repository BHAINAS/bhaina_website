package com.bhainas.backend.Service;

import com.bhainas.backend.model.CustomUserDetails;
import com.bhainas.backend.model.UserData;
import com.bhainas.backend.repository.UserRepository;
import com.bhainas.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return new CustomUserDetails(userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Invalid Email")));
    }
    public void registerUser(UserData ud)
    {
        if(!userRepository.existsByEmail(ud.getEmail()))
        {
            User u = new User(ud);
            userRepository.save(u);
        }
    }
    
}
