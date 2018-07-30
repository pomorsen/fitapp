package pl.sda.fitapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.domains.GymUser;
import pl.sda.fitapp.domains.dto.GymUserLoginDto;
import pl.sda.fitapp.repositories.GymUserRepository;

import java.util.Optional;

@Service
public class GymUserService {

    @Autowired
    private GymUserRepository gymUserRepository;

    public Long registerUser(GymUser gymUser) {
        gymUserRepository.save(gymUser);
        return gymUser.getId();
    }

    public Optional<GymUser> loginUser(GymUserLoginDto gymUserLoginDto) {
        Optional<GymUser> searchedGymUser = gymUserRepository.findByEmailAndPassword(gymUserLoginDto.getEmail(),gymUserLoginDto.getPassword());
        if(searchedGymUser.isPresent()){
            return Optional.of(searchedGymUser).get();
        }
        return Optional.empty();
    }


}
