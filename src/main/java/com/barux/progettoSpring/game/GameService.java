package com.barux.progettoSpring.game;

import com.barux.progettoSpring.config.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final Mapper<Game, GameDTO> gameMapper;

    public Page<GameDTO> getAll(Pageable pageable) {
        return gameRepository.findAll(pageable)
                .map(gameMapper::mapTo);
    }

    public GameDTO getById(Integer id) {
        return gameMapper.mapTo(gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found")));
    }

    public GameDTO save(GameDTO gameDTO) {
        return gameMapper.mapTo(gameRepository.save(gameMapper.mapFrom(gameDTO)));
    }


    public void delete(Integer id) {
        gameRepository.deleteById(id);
    }

    public GameDTO update(Integer id, GameDTO gameDTO) {
        gameDTO.setId(id);
        return gameMapper.mapTo(gameRepository.save(gameMapper.mapFrom(gameDTO)));
    }

    public GameDTO updateFields(Integer id, GameDTO gameDTO) {
        gameDTO.setId(id);
        return gameRepository.findById(id).map(
                existingBook -> {
                    Optional.ofNullable(gameDTO.getName()).ifPresent(existingBook::setName);
                    Optional.ofNullable(gameDTO.getDescription()).ifPresent(existingBook::setDescription);
                    Optional.ofNullable(gameDTO.getReleaseDate()).ifPresent(existingBook::setReleaseDate);
                    Optional.ofNullable(gameDTO.getPrice()).ifPresent(existingBook::setPrice);
                    Optional.ofNullable(gameDTO.getCover()).ifPresent(existingBook::setCover);
                    Optional.ofNullable(gameDTO.getGenre()).ifPresent(existingBook::setGenre);
                    Optional.ofNullable(gameDTO.getPlatform()).ifPresent(existingBook::setPlatform);
                    return gameMapper.mapTo(gameRepository.save(existingBook));
                }
        ).orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public boolean existsById(Integer id) {
        return gameRepository.existsById(id);
    }
}
