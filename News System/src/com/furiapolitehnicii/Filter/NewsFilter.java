package com.furiapolitehnicii.Filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.furiapolitehnicii.Event.Event;

public class NewsFilter extends Filter {

	private String author;
	private String domain;
	private List<String> subdomains = new ArrayList<String>();
	private String informationSource;
	private Date firstPublication;
	private Date lastModification;

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setSubdomains(List<String> subdomains) {
		this.subdomains = subdomains;
	}

	public void setInformationSource(String informationSource) {
		this.informationSource = informationSource;
	}

	public void setFirstPublication(Date firstPublication) {
		this.firstPublication = firstPublication;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}

	private static boolean hasSameType(String type1, String type2) {
		if (type1 == null)
			return true;
		else if (type1.equals(type2))
			return true;
		return false;
	}

	@Override
	public boolean verify(Event event) {
		String author = event.getNews().getAuthor();
		String domain = event.getNews().getDomain();
		String informationSource = event.getNews().getInformationSource();
		Date firstPublication = event.getNews().getFirstPublication();
		Date lastModification = event.getNews().getLastModification();

		return hasSameType(this.author, author) && hasSameType(this.domain, domain)
				&& hasSameType(this.firstPublication.toString(), firstPublication.toString())
				&& hasSameType(this.lastModification.toString(), lastModification.toString())
				&& hasSameType(this.informationSource, informationSource);
	}

}
