package com.tobiasjohansson.emusicms.controllers;

import com.tobiasjohansson.emusicms.models.Genre;
import com.tobiasjohansson.emusicms.services.GenreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/music/api/v1/genres")
public class GenreController {

    @Autowired
    private GenreServices genreServices;

    @PostMapping("/add")
    public ResponseEntity<Genre> saveCustomer(@RequestBody Genre genre) {

        return new ResponseEntity<>(genreServices.saveGenre(genre), HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public List<Genre> getAllGenres() {
        return genreServices.getAllGenres();
    }

    @PutMapping("/update")
    public ResponseEntity<Genre> updateGenre(@PathVariable("id") int id, @RequestBody Genre genre) {

        return ResponseEntity.ok(genreServices.updateGenre(genre, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable("id") int id) {
        genreServices.deleteGenre(id);

        return new ResponseEntity<String>("Genre deleted!", HttpStatus.OK);
    }

    @GetMapping("/searchbyname/{genre}")
    public List<Genre> findByGenre(@PathVariable("genre") String genre){
        genre = genre.replace("-", " ").replace("+", " ").replace("%", " ");
        return genreServices.findByGenre(genre);
    }
}
