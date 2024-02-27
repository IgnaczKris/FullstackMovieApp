package hu.movieproject.movieproject.controllers;

import hu.movieproject.movieproject.models.Review;
import hu.movieproject.movieproject.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@RestController
@RequestMapping("/reviews")
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){
        return new ResponseEntity<Review>(reviewService.createReview(
                payload.get("body"),
                payload.get("imdbId")),
                HttpStatus.CREATED
        );


    }
}
