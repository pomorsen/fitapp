package pl.sda.fitapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.fitapp.domains.Trainer;
import pl.sda.fitapp.domains.dto.TrainerEditDto;
import pl.sda.fitapp.domains.dto.TrainerLoginDto;
import pl.sda.fitapp.repositories.TrainerRepository;

import java.util.List;
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

    public Optional<Trainer> getTrainer(Long id) {
        if (trainerRepository.findById(id).isPresent()) {
            Trainer trainer = trainerRepository.findById(id).get();

            return Optional.of(trainer);
        }
        return Optional.empty();
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> list = trainerRepository.findAll();

        return list;
    }

    public Optional<Trainer> editTrainer(Long id, TrainerEditDto editDto) {
        Optional<Trainer> foundTrainer = trainerRepository.findById(id);

        if (trainerRepository.findById(id).isPresent()) {
            Trainer trainer = foundTrainer.get();

            if (trainer != null) {

                if (editDto.getPassword() != null) {
                    trainer.setPassword(editDto.getPassword());
                }
                if (editDto.getName() != null) {
                    trainer.setName(editDto.getName());
                }
                if (editDto.getSurname() != null) {
                    trainer.setPassword(editDto.getSurname());
                }

                trainer = trainerRepository.save(trainer);

                return Optional.of(trainer);
            }
        }
        return Optional.empty();
    }

    public Optional<Trainer> deleteTrainer(Long id) {
        if (id != null) {
            Optional<Trainer> foundTrainer = trainerRepository.findById(id);
            if (foundTrainer.isPresent()) {
                Trainer trainer = foundTrainer.get();

                trainerRepository.delete(trainer);

                return Optional.of(trainer);
            }
        }

        return Optional.empty();
    }

}
