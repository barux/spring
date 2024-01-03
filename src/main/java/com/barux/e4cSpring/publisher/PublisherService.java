package com.barux.e4cSpring.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository repository;
    private final PublisherMapper publisherMapper;

    public Page<PublisherDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(publisherMapper::mapTo);
    }

    public PublisherDTO getById(Integer id) {
        return publisherMapper.mapTo(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found")));
    }

    public PublisherDTO create(PublisherDTO publisherDTO) {
        return publisherMapper.mapTo(repository.save(publisherMapper.mapFrom(publisherDTO)));
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }


    public PublisherDTO update(Integer id, PublisherDTO publisherDTO) {
        publisherDTO.setId(id);
        return publisherMapper.mapTo(repository.save(publisherMapper.mapFrom(publisherDTO)));
    }

    public PublisherDTO updateFields(Integer id, PublisherDTO publisherDTO) {
        publisherDTO.setId(id);
        return repository.findById(id).map(
                existingPublisher -> {
                    Optional.ofNullable(publisherDTO.getName()).ifPresent(existingPublisher::setName);
                    Optional.ofNullable(publisherDTO.getFoundedAt()).ifPresent(existingPublisher::setFoundedAt);
                    return publisherMapper.mapTo(repository.save(existingPublisher));
                }
        ).orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
}
