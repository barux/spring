package com.barux.progettoSpring.game;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<GameDTO>> getAll(@PageableDefault(size = Integer.MAX_VALUE, sort = { "id"}, direction = Sort.Direction.ASC)
                                                    Pageable pageable) {
        return new ResponseEntity<>(gameService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(gameService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<GameDTO> create(@Valid @RequestBody GameDTO gameDTO) {
        return new ResponseEntity<>(gameService.save(gameDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> update(@PathVariable Integer id, @Valid @RequestBody GameDTO gameDTO) {
        if(!gameService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(gameService.update(id, gameDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameDTO> updateFields(@PathVariable Integer id, @Valid @RequestBody GameDTO gameDTO) {
        // FIXME la validazione dà errore se tralasci qualche campo, ma non dovrebbe
        if(!gameService.existsById(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(gameService.updateFields(id, gameDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        gameService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
