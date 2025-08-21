package com.movieflix.movieflix.controller;

import com.movieflix.movieflix.controller.request.StreamingRequest;
import com.movieflix.movieflix.controller.response.StreamingResponse;
import com.movieflix.movieflix.service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {
    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAllStreamings() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(streamingService.findAll());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest streamingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(streamingService.saveStreaming(streamingRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreamingById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(streamingService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        streamingService.deleteStreamingById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}