// import java.io.*;
// import java.util.*;

public class Review {
    public float starRating;
    public String review;

    public Review(float starRating, String comment) {
        this.starRating = starRating;
        this.review = comment;
    }

    // 별점 입력
    public void setRating(float rating) {
        this.starRating = rating;
    }
}