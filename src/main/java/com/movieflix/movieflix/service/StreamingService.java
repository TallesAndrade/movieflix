package com.movieflix.movieflix.service;

import com.movieflix.movieflix.controller.request.StreamingRequest;
import com.movieflix.movieflix.controller.response.StreamingResponse;
import com.movieflix.movieflix.entity.Streaming;
import com.movieflix.movieflix.exceptions.StreamingNotFoundException;
import com.movieflix.movieflix.mapper.StreamingMapper;
import com.movieflix.movieflix.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamingService {
    private final StreamingRepository  repository;

    public StreamingService(StreamingRepository repository) {
        this.repository = repository;
    }

    public StreamingResponse saveStreaming(StreamingRequest streamingRequest) {
        Streaming streaming = repository.save(StreamingMapper.toStreaming(streamingRequest));
        return StreamingMapper.toStreamingResponse(streaming);
    }

    public List<StreamingResponse> findAll() {
        List<Streaming> streamings = repository.findAll();
        return streamings.stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
    }

    public StreamingResponse findById(Long id) {
        return StreamingMapper.toStreamingResponse(getStreamingOrThrow(id));
    }

    public void deleteStreamingById(Long id) {
        repository.delete(getStreamingOrThrow(id));
    }

    private Streaming getStreamingOrThrow(Long id){
        return  repository.findById(id)
                .orElseThrow(StreamingNotFoundException::new);
    }
}