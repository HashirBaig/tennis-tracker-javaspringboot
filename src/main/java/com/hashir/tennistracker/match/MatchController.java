package com.hashir.tennistracker.match;

import com.hashir.tennistracker.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ApiResponse<List<MatchResponse>> getAllMatches() {
        return ApiResponse.success(matchService.getAllMatches());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MatchResponse> createMatch(@Valid @RequestBody MatchRequest request) {
        return ApiResponse.success(matchService.createMatch(request));
    }

}