package com.example.mib2_integrate.config;
import com.example.mib2_integrate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import java.util.Optional;

@Configuration
public class SecurityConfigurationBasicAuth {

    @Autowired
    UserRepository userRepository;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        Optional<com.example.mib2_integrate.entity.User> username = userRepository.findByFullName("mib2_integration_center");
        UserDetails user1 = User.builder()
                .username(username.get().getUsername())
                .password(username.get().getPassword())
                .roles("INTEGRATION")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .cors().and()
                    .authorizeHttpRequests((authz) -> authz
                            .antMatchers("/api/mib2/**").hasRole("INTEGRATION")
                            .anyRequest().authenticated()
                    )
                    .httpBasic(Customizer.withDefaults());
            return http.build();
    }
}