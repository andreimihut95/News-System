package com.furiapolitehnicii.News;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class News {

	private String title;
	private String author;
	private String domain;
	private String content;
	private List<String> subdomains;
	private String informationSource;
	private String firstPublication;
	private String lastModification;
	private int numberOfViews;
	private boolean isDeleted;

	public News(String title, String author, String domain, List<String> subdomains, String informationSource,
			String content) {
		this.title = title;
		this.author = author;
		this.domain = domain;
		this.subdomains = subdomains;
		this.informationSource = informationSource;
		this.firstPublication = getCurrentDate();
		this.lastModification = getCurrentDate();
		this.content = content;
		this.isDeleted = false;
		this.numberOfViews = 0;
	}
	private static String getCurrentDate()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String today = simpleDateFormat.format(now);
		return today;
	}
	public String toString() {
		StringBuffer toString = new StringBuffer();
		toString.append("Title: " + title);
		toString.append(System.lineSeparator());
		toString.append("Author: " + author);
		toString.append(System.lineSeparator());
		toString.append("Domain: " + domain);
		toString.append(System.lineSeparator());
		toString.append("Subdomains: " + subdomains);
		toString.append(System.lineSeparator());
		toString.append("Information source: " + informationSource);
		toString.append(System.lineSeparator());
		toString.append(("First publication: " + firstPublication));
		toString.append(System.lineSeparator());
		toString.append("Last modification: " + lastModification);
		toString.append(System.lineSeparator());
		toString.append("Content: " + content);
		toString.append(System.lineSeparator());
		return toString.toString();
	}

	public boolean isDeleted() {
		return this.isDeleted;
	}

	public void delete() {
		this.isDeleted = true;
	}

	public void incrementNumberOfViews() {
		numberOfViews++;
	}

	public int getNumberOfViews() {
		return numberOfViews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		lastModification = getCurrentDate();
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
		lastModification = getCurrentDate();
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
		lastModification = getCurrentDate();
	}

	public List<String> getSubdomains() {
		return subdomains;
	}

	public void setSubdomains(List<String> subdomains) {
		this.subdomains = subdomains;
		lastModification = getCurrentDate();
	}

	public String getInformationSource() {
		return informationSource;
	}

	public void setInformationSource(String informationSource) {
		this.informationSource = informationSource;
		lastModification = getCurrentDate();
	}

	public String getFirstPublication() {
		return firstPublication;
	}

	public String getLastModification() {
		return lastModification;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		lastModification = getCurrentDate();
	}
}
