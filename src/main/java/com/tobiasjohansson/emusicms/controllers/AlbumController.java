package com.tobiasjohansson.emusicms.controllers;


import com.tobiasjohansson.emusicms.models.Album;
import com.tobiasjohansson.emusicms.repositories.AlbumRepository;
import com.tobiasjohansson.emusicms.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/music")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumRepository albumRepository;

    public AlbumController() {
    }

    // Album By Track name
    @GetMapping("/album/albumbytrackname/{trackName}")
    public Album getAlbumByTrackName(@PathVariable("trackName") String trackName){
        return albumService.findAlbumByTrackName(trackName);
    }
    // GET ALBUM BY ARTIST NAME
    @GetMapping("/album/albumsbyartistname/{artistName}")
    public List<Album> getAlbumsByArtistName(@PathVariable("artistName") String artistName) {
        return albumService.findAlbumsByArtistName(artistName);
    }
    // GET ALBUM BY ALBUM TITLE
    @GetMapping("/album/searchbyname/{albumTitle}")
    public Album getAlbumByTitle(@PathVariable("albumTitle") String albumTitle){
        return albumService.getAlbumByTitle(albumTitle);
    }
    // GET ALL ALBUMS
    @GetMapping("/album/getall")
    public List<Album> getAllAlbums(){
        return albumService.getAllAlbums();
    }
    // GET ALBUM BY ID todo: ResponseEntity <----------------------
    @GetMapping("/album/getbyid/{id}")
    public Optional<Album> albumById(@PathVariable("id") int id){
        return albumService.albumById(id);
    }

    // POST, CREATE/SAVE NEW ALBUM
    @PostMapping("/album/add")
    public Album saveAlbum(Album newAlbum){
        return albumService.saveAlbum(newAlbum);
    }

    // UPDATE

    // DELETE ALBUM
    // returnerar en sträng som bekräftelse todo: kan ändras <-----------
    @DeleteMapping("/album/delete/{id}")
    public String deleteAlbum(@PathVariable("id") int id){
        return albumService.deleteAlbum(id);
    }

    @PutMapping("/album/update/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable("id") int id, @RequestBody Album album){
        return ResponseEntity.ok(albumService.updateAlbum(album, id));
    }
}
