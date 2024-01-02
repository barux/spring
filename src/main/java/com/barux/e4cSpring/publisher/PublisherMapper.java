package com.barux.e4cSpring.publisher;

import com.barux.e4cSpring.config.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublisherMapper implements Mapper<Publisher, PublisherDTO> {
    private final ModelMapper modelMapper;

    @Override
    public PublisherDTO mapTo(Publisher publisher) {
        return modelMapper.map(publisher, PublisherDTO.class);
    }

    @Override
    public Publisher mapFrom(PublisherDTO publisherDTO) {
        return modelMapper.map(publisherDTO, Publisher.class);
    }
}
