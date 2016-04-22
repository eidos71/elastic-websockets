package com.blackswan.elastic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.blackswan.elastic.entity.Post;



public interface PostService {
	Post save (Post post);
	  Post findOne(String id);
	  Iterable<Post> findAll();
	  Page<Post> findByTagsName(String tagName, PageRequest pageRequest);
}
