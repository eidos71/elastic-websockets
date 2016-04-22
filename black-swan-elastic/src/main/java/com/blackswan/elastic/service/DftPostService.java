package com.blackswan.elastic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.blackswan.elastic.entity.Post;
import com.blackswan.elastic.repository.PostRepository;

@Service
public class DftPostService implements PostService {

	  @Autowired
	    private PostRepository postRepository;

	    @Override
	    public Post save(Post post) {
	        postRepository.save(post);
	        return post;
	    }

	    @Override
	    public Post findOne(String id) {
	        return postRepository.findOne(id);
	    }

	    @Override
	    public Iterable<Post> findAll() {
	        return postRepository.findAll();
	    }

	    @Override
	    public Page<Post> findByTagsName(String tagName, PageRequest pageRequest) {
	        return postRepository.findByTagsName(tagName, pageRequest);
	    }
}
