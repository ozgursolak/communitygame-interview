package com.communitygaming.interview.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.communitygaming.interview.payload.request.LoginRequest;
import com.communitygaming.interview.payload.response.JwtResponse;
import com.communitygaming.interview.security.jwt.JwtUtils;
import com.communitygaming.interview.security.service.UserDetailsImpl;
import com.communitygaming.interview.model.ERole;
import com.communitygaming.interview.model.Role;
import com.communitygaming.interview.model.User;
import com.communitygaming.interview.payload.request.SignupRequest;
import com.communitygaming.interview.payload.response.MessageResponse;
import com.communitygaming.interview.repository.RoleRepository;
import com.communitygaming.interview.repository.UserRepository;
import com.communitygaming.interview.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Override
    public JwtResponse authenticateUser(final LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword())
        );

        final SecurityContext context = SecurityContextHolder.getContext();

        context.setAuthentication(authentication);

        final String jwt = jwtUtils.generateJwtToken(authentication);
        final UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        final JwtResponse jwtResponse = new JwtResponse(
            jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles
        );

        return jwtResponse;
    }

    @Override
    public MessageResponse registerUser(final SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        final User user = new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword())
        );

        final Set<String> strRoles = signUpRequest.getRoles();
        final Set<Role> roles = new HashSet<>();

        if (CollectionUtils.isNotEmpty(strRoles)) {
            final Role userRole = getUserRole(ERole.ROLE_USER);

            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        final Role adminRole = getUserRole(ERole.ROLE_ADMIN);

                        roles.add(adminRole);

                        break;
                    case "mod":
                        final Role modRole = getUserRole(ERole.ROLE_MODERATOR);

                        roles.add(modRole);

                        break;
                    default:
                        final Role userRole = getUserRole(ERole.ROLE_USER);

                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

    private Role getUserRole(final ERole role) {
        return roleRepository
            .findByName(role)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }
}
