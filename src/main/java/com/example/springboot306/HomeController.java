package com.example.springboot306;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @RequestMapping("/")

    public String index(Model model){
        // first let's create a director
        Director director = new Director();

        director.setName("glen matin");
        director.setGenre("comedy");

        //now let us create a movie
        Movie movie = new Movie();
        movie.setTitle("the star battle");
        movie.setYear(1997);
        movie.setDescription("a movie about mars invasion");

        //add movie to empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        movie = new Movie();
        movie.setTitle("battle of mars");
        movie.setYear(2001);
        movie.setDescription("this is a movie about mars");
        movies.add(movie);

        movie = new Movie();
        movie.setTitle("trip to venus");
        movie.setYear(2010);
        movie.setDescription("this movie is about trip to venus");
        movies.add(movie);

        // add the list of movies to the director's movie list
        director.setMovies(movies);
        //save the director to the database
        directorRepository.save(director);

        // move directors from database to template
        model.addAttribute("directors", directorRepository.findAll());
        return "index";
    }
}
