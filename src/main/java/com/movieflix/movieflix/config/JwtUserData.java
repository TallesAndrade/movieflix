package com.movieflix.movieflix.config;

import lombok.Builder;

@Builder
public record JwtUserData(Long id,
                          String name,
                          String email) {
}
