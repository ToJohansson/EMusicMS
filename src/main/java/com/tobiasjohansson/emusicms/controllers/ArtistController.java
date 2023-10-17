package com.tobiasjohansson.emusicms.controllers;

import com.tobiasjohansson.emusicms.models.Artist;
import com.tobiasjohansson.emusicms.models.Track;
import com.tobiasjohansson.emusicms.services.ArtistServices;
import com.tobiasjohansson.emusicms.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/music/api/v1")
public class ArtistController {

    @Autowired
    private ArtistServices artistServices;

    @Autowired
    private TrackService trackService;

    public ArtistController(ArtistServices artistServices) {
        this.artistServices = artistServices;
    }

    //get all artists
    @GetMapping("/getall")
    public List<Artist> getAllAllArtists(){
        return artistServices.getAllArtists();
    }

    //get artist by id
    @GetMapping("/getbyid/{id}")
    public Optional<Artist> artistById(@PathVariable("id") int id){
        return artistServices.getArtistById(id);
    }

    //get artists by genre-id
    @GetMapping ("/getartistsbygenre/{id}")

    public List<Artist> getArtistsByGenreId(@PathVariable("id") int id){ return artistServices.getArtistsByGenre(id); }

    @GetMapping("/bygenre/{genreName}")
    public List<Artist> getArtistsByGenreName (@PathVariable("genreName") String genreName){
        return artistServices.getArtistsByGenreName(genreName);
    }

    //add/create artist
    @PostMapping("/add")
    public Artist saveArtist(Artist newArtist){
        return artistServices.createArtist(newArtist);
    }

    //update artist
    @PutMapping("/update/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable("id")int id, @RequestBody Artist artist) {
        return ResponseEntity.ok(artistServices.updateArtist(artist, id));
    }
    //delete artist

    @DeleteMapping("/delete/{id}")
    public void deleteArtist(@PathVariable("id") int id){
        artistServices.deleteArtist(id);
    }

    @GetMapping("/searchbyname/{artist}")
    public List<Artist> findByName(@PathVariable("artist") String artist){
        artist = artist.replace("-", " ").replace("+", " ").replace("%", " ");
        return artistServices.getArtistsByName(artist);
    }

    @GetMapping("/findtracks/{artistName}")
    public List<Track> findTrackByArtist(@PathVariable("artistName")String artistName){
        artistName = artistName.replace("-", " ").replace("+", " ").replace("%", " ");
        return trackService.getTrackByArtist(artistName);
    }


}
