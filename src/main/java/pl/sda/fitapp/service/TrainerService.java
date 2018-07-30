package pl.sda.fitapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.domains.Trainer;
import pl.sda.fitapp.domains.dto.TrainerLoginDto;
import pl.sda.fitapp.repositories.TrainerRepository;

import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;


    public Long registerTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
        return trainer.getId();
    }

    public Optional<Trainer> loginUser(TrainerLoginDto trainerLoginDto) {
        Optional<Trainer> searchedTrainer = trainerRepository.findByEmailAndPassword(trainerLoginDto.getEmail(),trainerLoginDto.getPassword());
        if(searchedTrainer.isPresent()){
            return Optional.of(searchedTrainer).get();
        }
        return Optional.empty();
    }



}
