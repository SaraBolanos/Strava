package es.deusto.sd.strava.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.UserChallenge;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;
import es.deusto.sd.strava.repository.ChallengeRepository;
import es.deusto.sd.strava.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private UserRepository userRepository;

    // Crear un nuevo desafío
    public Challenge createChallenge(String name, String startDate, String endDate, float target, TargetType targetType, Sport sport, Long creatorId) {
        // Buscar el creador (User) por su ID
        User creator = userRepository.findById(creatorId)
            .orElseThrow(() -> new RuntimeException("Creador no encontrado"));

        // Convertir las fechas de String a LocalDate
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        // Crear un nuevo objeto Challenge
        Challenge challenge = new Challenge(name, start, end, target, targetType, sport, creator);

        // Guardar el challenge en la base de datos
        return challengeRepository.save(challenge);  // El ID se generará automáticamente aquí
    }


    // Obtener todos los desafíos activos filtrados por fecha y deporte
    public List<Challenge> getAllChallenges(String dateString, Sport sport) {
        LocalDate today = LocalDate.now();

        // Filtrar los desafíos activos
        List<Challenge> activeChallenges = challengeRepository.findAll().stream()
            .filter(challenge -> {
                LocalDate start = challenge.getStartDate();
                LocalDate end = challenge.getEndDate();
                return (today.isEqual(start) || today.isAfter(start)) && (today.isEqual(end) || today.isBefore(end));
            })
            .collect(Collectors.toList());

        if (sport != null && dateString != null) {
            // Filtrar por deporte y fecha
            LocalDate date = LocalDate.parse(dateString);
            return activeChallenges.stream()
                .filter(challenge -> sport.equals(challenge.getSport()))
                .filter(challenge -> {
                    LocalDate start = challenge.getStartDate();
                    LocalDate end = challenge.getEndDate();
                    return (date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end));
                })
                .collect(Collectors.toList());
        }

        if (sport != null) {
            // Filtrar solo por deporte
            return activeChallenges.stream()
                .filter(challenge -> sport.equals(challenge.getSport()))
                .collect(Collectors.toList());
        }

        if (dateString != null) {
            // Filtrar solo por fecha
            LocalDate date = LocalDate.parse(dateString);
            return activeChallenges.stream()
                .filter(challenge -> {
                    LocalDate start = challenge.getStartDate();
                    LocalDate end = challenge.getEndDate();
                    return (date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end));
                })
                .collect(Collectors.toList());
        }

        return activeChallenges; // Filtrar solo por los desafíos activos
    }

    // Obtener los desafíos aceptados por un usuario
    public List<Challenge> getAcceptedChallenges(User user) {
        return user.getChallenges().stream()
            .map(UserChallenge::getChallenge) // Obtener el Challenge de cada UserChallenge
            .collect(Collectors.toList());
    }

    // Obtener los desafíos no terminados de un usuario
    public List<Challenge> getUnfinishedChallenges(User user) {
        return user.getChallenges().stream()
            .filter(userChallenge -> !userChallenge.getChallenge().isFinished()) // Filtrar los que no están terminados
            .map(UserChallenge::getChallenge)
            .collect(Collectors.toList());
    }

    // Aceptar un desafío
    public Challenge acceptChallenge(long id, User user) {
        Optional<Challenge> challengeToAccept = Optional.of(challengeRepository.findById(id));

        challengeToAccept.ifPresent(challenge -> user.addChallenge(new UserChallenge(challenge)));

        return challengeToAccept.orElse(null); // Devolver el desafío si se acepta, o null si no se encuentra
    }

    // Obtener el porcentaje de logro de un desafío de un usuario
    public float getPercentageOfAchievement(long id, User user) {
        Optional<UserChallenge> userChallengeToCheck = user.getChallenges().stream()
            .filter(userChallenge -> userChallenge.getChallenge().getId() == id)
            .findFirst();

        return userChallengeToCheck.map(userChallenge -> 
            (userChallenge.getProgress() / userChallenge.getChallenge().getTarget()) * 100
        ).orElse(0f); // Devolver el porcentaje de logro o 0 si no se encuentra
    }
}
