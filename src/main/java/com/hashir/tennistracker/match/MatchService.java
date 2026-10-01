package com.hashir.tennistracker.match;

import com.hashir.tennistracker.player.Player;
import com.hashir.tennistracker.player.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Player playerOne = findOrCreatePlayer(playerOneName);
        Player playerTwo = findOrCreatePlayer(playerTwoName);

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
    public Page<MatchResponse> getAllMatches(int page, int limit) {
        int safePage = Math.max(page, 1);
        int safeLimit = Math.clamp(limit, 1, 100);
        Pageable pageable = PageRequest.of(safePage - 1, safeLimit);
        return matchRepository.findAllWithPlayers(pageable).map(MatchResponse::fromEntity);
    }

    private Player findOrCreatePlayer(String name) {
        return playerRepository.findByPlayerName(name)
                .orElseGet(() -> {
                    Player player = new Player();
                    player.setPlayerName(name);
                    return playerRepository.save(player);
                });
    }
}