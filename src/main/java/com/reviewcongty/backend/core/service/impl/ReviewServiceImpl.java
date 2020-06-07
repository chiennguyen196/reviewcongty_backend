package com.reviewcongty.backend.core.service.impl;

import com.reviewcongty.backend.api.request.ReplyRequest;
import com.reviewcongty.backend.api.request.ReviewRequest;
import com.reviewcongty.backend.core.dao.entity.Company;
import com.reviewcongty.backend.core.dao.entity.Reaction;
import com.reviewcongty.backend.core.dao.entity.Reply;
import com.reviewcongty.backend.core.dao.entity.Review;
import com.reviewcongty.backend.core.dao.repo.CompanyRepository;
import com.reviewcongty.backend.core.dao.repo.ReviewRepository;
import com.reviewcongty.backend.core.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public Page<Review> findByCompanyId(String companyId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Order.desc("created")));
        return reviewRepository.findByCompanyId(companyId, pageable);
    }

    @Override
    public List<Review> getRecentlyReviews(int limit) {
        Slice<Review> reviewSlice = reviewRepository.findAll(PageRequest.of(0, limit, Sort.by(Sort.Order.desc("created"))));
        return reviewSlice.getContent();
    }

    @Override
    // TODO: update the statistical when create new review
    public Review create(String companyId, ReviewRequest request) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found company with id: " + companyId));

        Review review = new Review();

        review.setName(request.getName());
        review.setPosition(request.getPosition());
        review.setContent(request.getContent());
        review.setRating(request.getRating());

        review.setCompanyId(company.getId());
        review.setCompanyName(company.getName());

        return reviewRepository.save(review);
    }

    @Override
    public Review reply(String reviewId, ReplyRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found review with id: " + reviewId));
        Reply reply = new Reply();
        reply.setName(request.getName());
        reply.setContent(request.getContent());

        switch (request.getReaction()) {
            case Reaction.LIKE:
                reply.setReaction(Reaction.LIKE);
                increaseLike(review);
                break;
            case Reaction.DISLIKE:
                reply.setReaction(Reaction.DISLIKE);
                increaseDislike(review);
                break;
            case Reaction.SHOULD_DELETE:
                reply.setReaction(Reaction.SHOULD_DELETE);
                increaseDeleteRequests(review);
                break;
            default:
                reply.setReaction(null);
        }

        reply.setCreated(new Date());

        addNewReply(review, reply);

        return reviewRepository.save(review);

    }

    private void increaseLike(Review review) {
        review.setNumLikes(review.getNumLikes() + 1);
    }

    private void increaseDislike(Review review) {
        review.setNumDislikes(review.getNumDislikes() + 1);
    }

    private void increaseDeleteRequests(Review review) {
        review.setNumDeleteRequests(review.getNumDeleteRequests() + 1);
    }

    private void addNewReply(Review review, Reply reply) {
        List<Reply> replies = review.getReplies();
        List<Reply> newReplies = new ArrayList<>();
        newReplies.add(reply);
        newReplies.addAll(replies);
        review.setReplies(newReplies);
    }
}
