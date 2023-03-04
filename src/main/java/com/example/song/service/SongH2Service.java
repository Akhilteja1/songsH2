package com.example.song.service;



import com.example.song.model.Song;
import java.util.*;

import com.example.song.repository.SongRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.song.model.SongRowMapper;


@Service
public class SongH2Service implements SongRepository{


    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Song> getSongs(){
        List<Song> songList = db.query("select * from PLAYLIST",new SongRowMapper() );
        ArrayList<Song> songs = new ArrayList<>(songList);
        return songs;

    }

    @Override
    public Song getSongById(int songId){
        try{
            Song song = db.queryForObject("select * from PLAYLIST where songId = ? ",new SongRowMapper(),songId);

        return song;

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
    }

    @Override
    public Song addSong(Song song){
        db.update("insert into PLAYLIST(songName,lyricist,singer,musicDirector) values(?,?,?,?)",song.getSongName(),song.getLyricist(),song.getSinger(),song.getMusicDirector());
        Song savedSong =db.queryForObject("select * from PLAYLIST where songName = ? and lyricist = ? and singer = ? and musicDirector= ? ",new SongRowMapper(),song.getSongName(),song.getLyricist(),song.getSinger(),song.getMusicDirector());
        return savedSong;
    }


     @Override
    public Song updateSong(int songId, Song song){

        try{
            Song checkSong = db.queryForObject("select * from PLAYLIST where songId = ? ",new SongRowMapper(),songId);
            if(checkSong != null){
            
            db.update("update PLAYLIST set singer = ? where songId = ? ",song.getSinger(),songId);
             
            }
            return getSongById(songId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }






         
            
            
        }


        
     @Override
    public void deleteSong(int songId){
            Song song = db.queryForObject("select * from PLAYLIST where SONGid =?",new SongRowMapper(),songId);
            if(song != null){
                db.update("delete from PLAYLIST where songId = ?",songId);
            throw new ResponseStatusException(HttpStatus.OK);

            }else{
                 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            }

              






            

        }      

     

              






            

        










}