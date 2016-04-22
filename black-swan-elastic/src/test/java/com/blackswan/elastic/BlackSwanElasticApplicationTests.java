package com.blackswan.elastic;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.blackswan.elastic.entity.Post;
import com.blackswan.elastic.entity.Tag;
import com.blackswan.elastic.service.PostService;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackSwanElasticApplication.class)
public class BlackSwanElasticApplicationTests {
	  @Autowired
	    private PostService dftPostService;
	    @Autowired
	    private ElasticsearchTemplate elasticsearchTemplate;
	@Test
	public void contextLoads() {
	}
	  @Before
	    public void before() {
	        elasticsearchTemplate.deleteIndex(Post.class);
	        elasticsearchTemplate.createIndex(Post.class);
	        elasticsearchTemplate.putMapping(Post.class);
	        elasticsearchTemplate.refresh(Post.class, true);
	    }

	   @Test
	    public void testSave() throws Exception {
	        Tag tag = new Tag();
	        tag.setId("1");
	        tag.setName("tech");
	        Tag tag2 = new Tag();
	        tag2.setId("2");
	        tag2.setName("elasticsearch");

	        Post post = new Post();
	        post.setId("1");
	        post.setTitle("Bigining with spring boot application and elasticsearch");
	        post.setTags(Arrays.asList(tag, tag2));
	        dftPostService.save(post);

	       // assertThat(post.getId(), notNullValue());
	        assertThat( dftPostService.findOne(post.getId()) , notNullValue());
	        
	        Post post2 = new Post();
	        post2.setId("2");
	        post2.setTitle("Bigining with spring boot application");
	        post2.setTags(Arrays.asList(tag));
	        dftPostService.save(post2);
	     
	        assertThat( dftPostService.findOne(post2.getId()) , notNullValue());




	    }
	    public void testFindOne() throws Exception {

	    }

	    public void testFindAll() throws Exception {

	    }
	    
	    @Test
	    public void testFindByTagsName() throws Exception {
	        Tag tag = new Tag();
	        tag.setId("1");
	        tag.setName("tech");
	        Tag tag2 = new Tag();
	        tag2.setId("2");
	        tag2.setName("elasticsearch");

	        Post post = new Post();
	        post.setId("1");
	        post.setTitle("Beggining with spring boot application and elasticsearch");
	        post.setTags(Arrays.asList(tag, tag2));
	        dftPostService.save(post);



	        Post post2 = new Post();
	        post2.setId("1");
	        post2.setTitle("Bigining with spring boot application");
	        post2.setTags(Arrays.asList(tag));
	        dftPostService.save(post);

	        Page<Post> posts  = dftPostService.findByTagsName("tech", new PageRequest(0,10));
	        Page<Post> posts2  = dftPostService.findByTagsName("tech", new PageRequest(0,10));
	        Page<Post> posts3  = dftPostService.findByTagsName("maz", new PageRequest(0,10));


	       assertThat(posts.getTotalElements(), is(1L));
	        assertThat(posts2.getTotalElements(), is(1L));
	        assertThat(posts3.getTotalElements(), is(0L));
	    }
	    
}
