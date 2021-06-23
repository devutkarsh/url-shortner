package com.url.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class URLInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int urlid;
	private String url;
	
	@Column(name = "urlnew", unique=true)
	private String urlnew;
	private int hits;
	
	public int getUrlid() {
		return urlid;
	}
	public void setUrlid(int urlid) {
		this.urlid = urlid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlnew() {
		return urlnew;
	}
	public void setUrlnew(String urlnew) {
		this.urlnew = urlnew;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}

	
}
