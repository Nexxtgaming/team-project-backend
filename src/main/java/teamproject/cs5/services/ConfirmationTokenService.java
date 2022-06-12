package teamproject.cs5.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teamproject.cs5.models.ConfirmationToken;
import teamproject.cs5.models.User;
import teamproject.cs5.repository.ConfirmationTokenRepository;

import java.time.LocalDateTime;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserService userService;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenService, UserService userService) {
        this.confirmationTokenRepository = confirmationTokenService;
        this.userService = userService;
    }
    public void save(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }
    public String validateToken(String token, LocalDateTime confirmedAt){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> {throw new ResponseStatusException(HttpStatus.NOT_FOUND,"invalid token");
                });
        if(confirmedAt.isBefore(confirmationToken.getExpiresAt())){
            User user = confirmationToken.getUser();
            user.setVerified(true);
            confirmationToken.setConfirmedAt(confirmedAt);
            userService.save(user);
            confirmationTokenRepository.save(confirmationToken);
            return "Confirmed";
        }else {
            throw new IllegalStateException("token expired");
        }
    }
}
