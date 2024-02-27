package hu.movieproject.movieproject.services;

import hu.movieproject.movieproject.models.Movie;
import hu.movieproject.movieproject.models.Review;
import hu.movieproject.movieproject.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(String body, String imdbId){

        //Creating the new Review object, and inserting it to the database
        Review review = reviewRepository.insert(new Review(body));

        //Update the Movie's ReviewId-s array, and inserting the Review's Id to it
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}
