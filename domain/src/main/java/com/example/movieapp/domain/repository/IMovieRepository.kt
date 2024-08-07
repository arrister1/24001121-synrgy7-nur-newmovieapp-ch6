package com.example.movieapp.domain.repository

import com.example.movieapp.common.Resource
import com.example.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
}