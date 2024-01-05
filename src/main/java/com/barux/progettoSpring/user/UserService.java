package com.barux.progettoSpring.user;

import com.barux.progettoSpring.config.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final Mapper<User, UserDTO> mapper;
    public UserDTO me() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = repository.findByEmail(authentication.getName());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return mapper.mapTo(user.get());
    }

    public Page<UserDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::mapTo);

    }
}
