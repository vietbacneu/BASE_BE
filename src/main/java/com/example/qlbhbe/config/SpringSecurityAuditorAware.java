package com.example.qlbhbe.config;

import com.example.qlbhbe.entity.User;
import com.example.qlbhbe.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    private static final Logger logger = LoggerFactory.getLogger(SpringSecurityAuditorAware.class);

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            logger.warn("No authentication in security context. Now return admin as user for auditing.");
            return getAdmin();
        } else {
            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                // Authentication principal was set in JwtAuthenticationFilter
                UsernamePasswordAuthenticationToken usernamePasswordToken =
                        ((UsernamePasswordAuthenticationToken) authentication);
                CustomUserDetails userMinDTO = (CustomUserDetails) usernamePasswordToken.getPrincipal();
                User user = new User();
                user.setId(userMinDTO.getUser().getId());
                user.setUsername(userMinDTO.getUsername());
                return Optional.of(user.getUsername());
            } else {
                return getAdmin();
            }
        }
    }

    private Optional<String> getAdmin() {
        User admin = new User();
        admin.setId(1L);
        admin.setUsername("admin");
        return Optional.of(admin.getUsername());
    }
}
