package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.ReviewDTO;
import org.example.testhaibazo.model.Review;
import org.example.testhaibazo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO> getReviewById(Long id) {
        return reviewRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDto) {
        Review review = convertToEntity(reviewDto);
        return convertToDto(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDto) {
        return reviewRepository.findById(id).map(review -> {
            review.setRating(reviewDto.getRating());
            review.setComment(reviewDto.getComment());
            return convertToDto(reviewRepository.save(review));
        }).orElse(null);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private ReviewDTO convertToDto(Review review) {
        return new ReviewDTO(review.getId(), review.getRating(), review.getComment(), review.getProduct().getId(), review.getUser().getId());
    }

    private Review convertToEntity(ReviewDTO reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        return review;
    }
}
