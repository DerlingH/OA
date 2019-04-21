package com.hh.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hh.dao.AlbumDao;
import com.hh.entity.Album;
import com.hh.service.AlbumService;


@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
	@Autowired
	private AlbumDao albumDao;
	@Override
	public List<Album> getAll() {
		return albumDao.selectAll();
	}
	@Override
	public void add(Album a) {
		albumDao.insert(a);
	}
}
