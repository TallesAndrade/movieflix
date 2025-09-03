package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.controller.docs.StreamingControllerDocs;
import com.movieflix.movieflix.controller.request.StreamingRequest;
import com.movieflix.movieflix.controller.response.StreamingResponse;
import com.movieflix.movieflix.service.StreamingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController implements StreamingControllerDocs {
    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreamings() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(streamingService.findAll());
    }

    @Override
    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@Valid @RequestBody StreamingRequest streamingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(streamingService.saveStreaming(streamingRequest));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreamingById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(streamingService.findById(id));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        streamingService.deleteStreamingById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}