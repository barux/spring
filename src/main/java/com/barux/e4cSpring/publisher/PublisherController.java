package com.barux.e4cSpring.publisher;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
@Tag(name = "Publisher")
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping("/")
    public ResponseEntity<Page<PublisherDTO>> getAll(Pageable pageable) {
        return new ResponseEntity<>(publisherService.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getById(Integer id) {
        return new ResponseEntity<>(publisherService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<PublisherDTO> create(PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherService.create(publisherDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO> update(Integer id, PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherService.update(id, publisherDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PublisherDTO> updateFields(Integer id, PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherService.updateFields(id, publisherDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Integer id) {
        publisherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
