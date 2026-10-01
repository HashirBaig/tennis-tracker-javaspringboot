package com.hashir.tennistracker.match;

import com.hashir.tennistracker.common.ApiResponse;
import com.hashir.tennistracker.common.Pagination;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ApiResponse<List<MatchResponse>> getAllMatches(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        Page<MatchResponse> result = matchService.getAllMatches(page, limit);
        return ApiResponse.paginated(result.getContent(), Pagination.fromPage(result));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MatchResponse> createMatch(@Valid @RequestBody MatchRequest request) {
        return ApiResponse.success(matchService.createMatch(request));
    }

}