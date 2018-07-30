package pl.sda.fitapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.domains.GymUser;
import pl.sda.fitapp.domains.dto.GymUserEditDto;
import pl.sda.fitapp.domains.dto.GymUserLoginDto;
import pl.sda.fitapp.repositories.GymUserRepository;

import java.util.List;
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

    public Optional<GymUser> getGymUser(Long id) {
        if (gymUserRepository.findById(id).isPresent()) {
            GymUser gymUser = gymUserRepository.findById(id).get();

            return Optional.of(gymUser);
        }
        return Optional.empty();
    }

    public List<GymUser> getAllGymUser() {
        List<GymUser> list = gymUserRepository.findAll();

        return list;
    }

    public Optional<GymUser> editGymUser(Long id, GymUserEditDto editDto) {
        Optional<GymUser> foundGymUser = gymUserRepository.findById(id);

        if (gymUserRepository.findById(id).isPresent()) {
            GymUser gymUser = foundGymUser.get();

            if (gymUser != null) {

                if (editDto.getPassword() != null) {
                    gymUser.setPassword(editDto.getPassword());
                }
                if (editDto.getName() != null) {
                    gymUser.setName(editDto.getName());
                }
                if (editDto.getSurname() != null) {
                    gymUser.setPassword(editDto.getSurname());
                }

                gymUser = gymUserRepository.save(gymUser);

                return Optional.of(gymUser);
            }
        }
        return Optional.empty();
    }

    public Optional<GymUser> deleteGymUser(Long id) {
        if (id != null) {
            Optional<GymUser> foundUser = gymUserRepository.findById(id);
            if (foundUser.isPresent()) {
                GymUser gymUser = foundUser.get();

                gymUserRepository.delete(gymUser);

                return Optional.of(gymUser);
            }
        }

        return Optional.empty();
    }
}
