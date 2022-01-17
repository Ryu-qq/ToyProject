package com.ryu.mypptbe.domain.repository;

import com.ryu.mypptbe.domain.user.User;
import com.ryu.mypptbe.oauth.domain.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

        @Autowired UserRepository userRepository;

        @Test
        @Transactional
        public void testUser() throws Exception{
            User user = User.builder()
                    .userId("123124")
                    .email("fbtkdals2@naver.com")
                    .profileImageUrl("imageurlpath")
                    .role(Role.USER)
                    .username("Ryu")
                    .build();

            //Long savedUser = userRepository.save(user);
            //User findUser = userRepository.find(savedUser);

            //Assertions.assertThat(findUser.getUserId()).isEqualTo(user.getUserId());
            //Assertions.assertThat(findUser.getUsername()).isEqualTo(user.getUsername());

        }
}