package com.barux.progettoSpring.game;

import com.barux.progettoSpring.config.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameMapper implements Mapper<Game, GameDTO> {

    private final ModelMapper modelMapper;
        @Override
        public GameDTO mapTo(Game game) {
            return modelMapper.map(game, GameDTO.class);
        }

        @Override
        public Game mapFrom(GameDTO gameDTO) {
            return modelMapper.map(gameDTO, Game.class);
        }
}
