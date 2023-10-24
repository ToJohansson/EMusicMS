package com.tobiasjohansson.emusicms.controllers;

import com.tobiasjohansson.emusicms.models.Track;
import com.tobiasjohansson.emusicms.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/music")
public class TrackController {

    @Autowired
    private TrackService trackService;

    public TrackController() {
    }

    // search track by name
    @GetMapping("/track/gettrackbyname/{trackName}")
    public Track getTrackByName(@PathVariable("trackName") String trackName){
        return trackService.getTrackByName(trackName);
    }
    // GET ALL TRACKS
    @GetMapping("/track/getall")
    public List<Track> getAllAllTracks(){
        return trackService.getAllTracks();
    }
    // GET TRACKS BY ID todo: ResponseEntity <----------------------
    @GetMapping("/track/getbyid/{id}")
    public Optional<Track> trackById(@PathVariable("id") int id){
        return trackService.getTrackById(id);
    }

    // POST, CREATE/SAVE NEW TRACK
    @PostMapping("/track/add")
    public Track saveTrack(Track newTrack){
        return trackService.saveTrack(newTrack);
    }

    // UPDATE

    // DELETE TRACK
    // returnerar en sträng som bekräftelse todo: kan ändras <-----------
    @DeleteMapping("/track/delete/{id}")
    public String deleteTrack(@PathVariable("id") int id){
        return trackService.deleteTrack(id);
    }

    @GetMapping("/track/bygenre/{genreName}")
    public List<Track> findTrackByGenre(@PathVariable("genreName")String genreName){
        genreName = genreName.replace("-", " ").replace("+", " ").replace("%", " ");
        return trackService.findTrackByGenre(genreName);
    }

    @GetMapping("/track/byalbum/{albumName}")
    public List<Track> findAlbumbyTrack(@PathVariable("albumName")String albumName){
        albumName = albumName.replace("-", " ").replace("+", " ").replace("%", " ");
        return trackService.findTrackByAlbum(albumName);
    }

    @PutMapping("/track/updateTracks/{id}")
    public ResponseEntity<Track> updateTracks(@PathVariable("id")int id, @RequestBody Track track) {
        return ResponseEntity.ok(trackService.updateTracks(track, id));
    }
}

