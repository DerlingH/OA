package com.hh.dao;

import java.util.List;

import com.hh.entity.Album;



public interface AlbumDao {
	List<Album> selectAll();
	void insert(Album album);
}
