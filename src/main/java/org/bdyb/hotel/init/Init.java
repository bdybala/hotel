package org.bdyb.hotel.init;

import lombok.extern.slf4j.Slf4j;
import org.bdyb.hotel.domain.User;
import org.bdyb.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Init {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void initAll() {
        initAdmin();
    }

    private void initAdmin() {
        if (!userRepository.existsByEmail("admin")) {
            userRepository.save(User.builder()
                    .id(1L)
                    .email("admin")
                    .name("Admin")
                    .build());
        }
    }
}
