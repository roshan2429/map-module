package com.example.mapmodule.repository;

import com.example.mapmodule.model.User;
import com.google.cloud.spring.data.spanner.repository.SpannerRepository;

public interface Userrepository extends SpannerRepository<User, String> {
}


