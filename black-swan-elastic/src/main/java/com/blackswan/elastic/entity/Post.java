package com.blackswan.elastic.entity;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 
 * @author blackswam
 *
 */
@Document(indexName = "indexpost", type = "post")
public class Post {
	@Id
	private String id;
	
	private String title;//
	@Field(type = FieldType.Nested)
	private List<Tag> tags;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		
		
		return "Post [id=" + id + ", title=" + title + ", tags=" + Arrays.toString(tags.toArray()) + "]";

		    }
		 
	}
