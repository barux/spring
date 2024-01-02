package com.barux.e4cSpring.game;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Tag(name = "Game")
public class GameController {
    private final GameService gameService;

    @GetMapping("/")
    public ResponseEntity<Page<GameDTO>> getAll(Pageable pageable) {
        return new ResponseEntity<>(gameService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getById(Integer id) {
        return new ResponseEntity<>(gameService.getById(id), HttpStatus.OK);
    }

//    @GetMapping{"/?publisherId={publisherId}"}
//    public ResponseEntity<Page<GameDTO>> getByPublisherId(Integer publisherId) {
//        return new ResponseEntity<>(gameService.getByPublisherId(publisherId), HttpStatus.OK);
//    }

    @PostMapping("/")
    public ResponseEntity<GameDTO> create(GameDTO gameDTO) {
        return new ResponseEntity<>(gameService.create(gameDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> update(Integer id, GameDTO gameDTO) {
        return new ResponseEntity<>(gameService.update(id, gameDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameDTO> updateFields(Integer id, GameDTO gameDTO) {
        return new ResponseEntity<>(gameService.updateFields(id, gameDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Integer id) {
        gameService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
