package com.example.song.controller;



import org.springframework.beans.factory.annotation.Autowired;
 
import java.util.*;

import com.example.song.service.SongH2Service;
 
import com.example.song.model.Song;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


 
 
 

@RestController
public class SongController{

    @Autowired
    public SongH2Service songService;
     


    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable("songId") int songId){
        return songService.getSongById(songId);
    }


    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable("songId") int songId,@RequestBody Song song){
        return songService.updateSong( songId, song);


    }




     @DeleteMapping("/songs/{songId}")
    public void deleteSong(@PathVariable("songId") int songId){
        songService.deleteSong(songId);
    }

   


    @GetMapping("/songs")
    public ArrayList<Song> getSongs(){
        return songService.getSongs();
    }

     @PostMapping("/songs")
    public Song addSong(@RequestBody Song song){
        return songService.addSong(song);
    }

    


     
    







}