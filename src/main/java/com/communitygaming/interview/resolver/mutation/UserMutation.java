package com.communitygaming.interview.resolver.mutation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.communitygaming.interview.payload.request.SignupRequest;
import com.communitygaming.interview.payload.response.MessageResponse;
import com.communitygaming.interview.service.UserService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

    private final UserService userService;

    public MessageResponse registerUser(final SignupRequest signupRequest) {
        final MessageResponse messageResponse = userService.registerUser(signupRequest);

        return messageResponse;
    }
}
