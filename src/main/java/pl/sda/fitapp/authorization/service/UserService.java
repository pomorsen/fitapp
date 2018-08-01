package pl.sda.fitapp.authorization.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.domains.GymUser;
import pl.sda.fitapp.domains.Trainer;
import pl.sda.fitapp.domains.UserType;
import pl.sda.fitapp.domains.dto.LoggedUserDto;
import pl.sda.fitapp.repositories.GymUserRepository;
import pl.sda.fitapp.repositories.TrainerRepository;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private GymUserRepository gymUserRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    private static SecureRandom random = new SecureRandom();

    private static Map<String, LoggedUserDto> rememberedUsers = new HashMap<>();

    public Long isAuthenticUser(String username, String password, UserType userType) {
        if (userType.toString().equalsIgnoreCase("Trainer")) {
            Optional<Trainer> searchTrainer = trainerRepository.findByEmailAndPassword(username, password);
            if (searchTrainer.isPresent()){
                return searchTrainer.get().getId();
            }
        } else if (userType.toString().equalsIgnoreCase("User")){
            Optional<GymUser> searchGymUser = gymUserRepository.findByEmailAndPassword(username,password);
            if(searchGymUser.isPresent()){
                return searchGymUser.get().getId();
            }
        }
        return 0L;

    }

    public static String rememberUser(LoggedUserDto loggedUserDto) {
        String randomId = new BigInteger(130, random).toString(32);
        rememberedUsers.put(randomId, loggedUserDto);
        return randomId;
    }

    public static LoggedUserDto getRememberedUser(String id) {
        return rememberedUsers.get(id);
    }


    public static void removeRememberedUser(String id) {
        rememberedUsers.remove(id);
    }

}
