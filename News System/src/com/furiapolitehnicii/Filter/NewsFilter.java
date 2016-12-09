package com.furiapolitehnicii.Filter;

import java.util.ArrayList;
import java.util.List;

import com.furiapolitehnicii.Event.Event;

public class NewsFilter extends Filter {

	private String author;
	private String domain;
	private List<String> subdomains = new ArrayList<String>();
	private String informationSource;
	private String firstPublication;
	private String lastModification;

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

	public void setFirstPublication(String firstPublication) {
		this.firstPublication = firstPublication;
	}

	public void setLastModification(String lastModification) {
		this.lastModification = lastModification;
	}

	private static boolean hasSameType(String type1, String type2) {
		if (type1 == null)
			return true;
		else if (type1.toString().equals(type2.toString()))
			return true;
		return false;
	}

	private boolean isInSubdomains(List<String> subdomains) {
		if (this.subdomains == null)
			return true;
		for (String subdomain : this.subdomains) {
			if (!subdomains.contains(subdomain))
				return false;
		}
		return true;
	}

	@Override
	public boolean verify(Event event) {
		String author = event.getNews().getAuthor();
		String domain = event.getNews().getDomain();
		String informationSource = event.getNews().getInformationSource();
		String firstPublication = event.getNews().getFirstPublication();
		String lastModification = event.getNews().getLastModification();
		List<String> subdomains = event.getNews().getSubdomains();

		return hasSameType(this.author, author) && hasSameType(this.domain, domain)
				&& hasSameType(this.firstPublication, firstPublication)
				&& hasSameType(this.lastModification, lastModification)
				&& hasSameType(this.informationSource, informationSource) && isInSubdomains(subdomains);
	}

}
