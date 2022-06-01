package teamproject.cs5.services;

import org.springframework.stereotype.Service;
import teamproject.cs5.models.ERole;
import teamproject.cs5.models.User;
import teamproject.cs5.repository.UserRepository;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isRole(Long id, ERole roleName){
        AtomicBoolean result = new AtomicBoolean(false);
        User user = userRepository.getById(id);
        user.getRoles().forEach(role -> {
            if (role.getName().equals(roleName)){
                result.set(true);
            }
        });
        return result.get();
    }
}
