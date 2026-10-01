package com.hashir.tennistracker.match;

import com.hashir.tennistracker.player.Player;
import com.hashir.tennistracker.player.PlayerNotFoundException;
import com.hashir.tennistracker.player.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    public MatchService(MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public MatchResponse createMatch(MatchRequest request) {
        String playerOneName = request.playerOne().trim();
        String playerTwoName = request.playerTwo().trim();

        if (playerOneName.equalsIgnoreCase(playerTwoName)) {
            throw new IllegalArgumentException("A player cannot play against themselves");
        }

        long gamesOne = request.gamesWonByPlayerOne();
        long gamesTwo = request.gamesWonByPlayerTwo();
        if (gamesOne + gamesTwo > request.numberOfGames()) {
            throw new IllegalArgumentException("Games won cannot exceed the total number of games");
        }

        Player playerOne = playerRepository.findByPlayerName(playerOneName)
                .orElseThrow(() -> new PlayerNotFoundException(playerOneName));
        Player playerTwo = playerRepository.findByPlayerName(playerTwoName)
                .orElseThrow(() -> new PlayerNotFoundException(playerTwoName));

        Match match = new Match();
        match.setPlayerOne(playerOne);
        match.setPlayerTwo(playerTwo);
        match.setNumberOfSets(request.numberOfSets());
        match.setNumberOfGames(request.numberOfGames());
        match.setGamesWonByPlayerOne(gamesOne);
        match.setGamesWonByPlayerTwo(gamesTwo);

        return MatchResponse.fromEntity(matchRepository.save(match));
    }

    @Transactional(readOnly = true)
    public List<MatchResponse> getAllMatches() {
        return matchRepository.findAllWithPlayers().stream()
                .map(MatchResponse::fromEntity)
                .toList();
    }
}