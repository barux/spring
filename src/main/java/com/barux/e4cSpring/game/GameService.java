package com.barux.e4cSpring.game;

import com.barux.e4cSpring.config.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository repository;
    private final Mapper<Game, GameDTO> gameMapper;

    public Page<GameDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(gameMapper::mapTo);
    }

    public GameDTO getById(Integer id) {
        return gameMapper.mapTo(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found")));
    }

    public GameDTO create(GameDTO gameDTO) {
        return gameMapper.mapTo(repository.save(gameMapper.mapFrom(gameDTO)));
    }


    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
