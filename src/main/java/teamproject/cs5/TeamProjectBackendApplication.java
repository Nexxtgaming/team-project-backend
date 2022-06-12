package teamproject.cs5;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import teamproject.cs5.models.ERole;
import teamproject.cs5.models.Role;
import teamproject.cs5.repository.RoleRepository;

@SpringBootApplication
public class TeamProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamProjectBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner run(RoleRepository roleRepository ){
        return args -> {
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
            roleRepository.save(new Role(ERole.ROLE_HELPER));
            roleRepository.save(new Role(ERole.ROLE_REFUGEE));
            roleRepository.save(new Role(ERole.ROLE_USER));
        };

    }
}
