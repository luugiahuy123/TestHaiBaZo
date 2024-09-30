package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDTO> getAllReviews();

    Optional<ReviewDTO> getReviewById(Long id);

    ReviewDTO createReview(ReviewDTO reviewDto);

    ReviewDTO updateReview(Long id, ReviewDTO reviewDto);

    void deleteReview(Long id);
}
