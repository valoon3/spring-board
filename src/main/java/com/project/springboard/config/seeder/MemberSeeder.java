package com.project.springboard.config.seeder;

import com.project.springboard.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"seeder"})
@Configuration
@RequiredArgsConstructor
public class MemberSeeder implements ApplicationRunner {

    private final MemberService memberService;

    @Override
    public void run(ApplicationArguments args) {
        for(int i = 0; i < 3; i ++) {
            memberService.signup(
                    "user" + ( i + 1 ) + "@test.com",
                    "1234",
                    "user" + i + "nickname",
                    "user" + i + "username"
            );
        }
    }
}
