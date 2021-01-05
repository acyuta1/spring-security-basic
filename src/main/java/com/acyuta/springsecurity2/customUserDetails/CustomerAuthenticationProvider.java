//package com.acyuta.springsecurity2.customUserDetails;
//
//import com.acyuta.springsecurity2.domain.service.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//
///**
// * Alternative, using authentication provided without using userdetails, userdetailsservice etc.
// */
//@Component
//public class CustomerAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        var userName = authentication.getName();
//        var password = authentication.getCredentials().toString();
//
//        var customer = customerRepository.findByEmail(userName);
//        if(customer.isPresent()){
//            if(passwordEncoder.encode(password).equals(customer.get().getPwd())) {
//                var authorities = new ArrayList<SimpleGrantedAuthority>();
//                authorities.add(new SimpleGrantedAuthority(customer.get().getRole()));
//                return new UsernamePasswordAuthenticationToken(userName, password, authorities);
//            }
//            throw new BadCredentialsException("Invalid credentials");
//        }
//        throw new BadCredentialsException("Invalid credentials");
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
