package com.hh.service;

import java.util.List;

import com.hh.entity.Album;



public interface AlbumService {
	List<Album> getAll();
	void add(Album a);
}
