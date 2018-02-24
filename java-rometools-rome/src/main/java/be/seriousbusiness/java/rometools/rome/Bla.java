package be.seriousbusiness.java.rometools.rome;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndPerson;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Bla {
	private static final Logger LOGGER=LoggerFactory.getLogger(Bla.class);
	
	public static void test(){
		SyndFeedInput syndFeedInput=new SyndFeedInput();
		try {
			URL url = new URL("http://www.brusselnieuws.be/nl/hoofdpunten/feed");
			try {
				SyndFeed syndFeed=syndFeedInput.build(new XmlReader(url));
				final String title=syndFeed.getTitle();
				if(title!=null){
					LOGGER.info("Title: {}",title);
				}
				final List<SyndPerson> authors=syndFeed.getAuthors();
				if(authors!=null){
					for(final SyndPerson author : authors){
						LOGGER.info("Author name: {}, email: {}",author.getName(),author.getEmail());
					}
				}
				final List<SyndEntry> entries=syndFeed.getEntries();
				if(entries!=null){
					for(final SyndEntry entry : entries){
						//LOGGER.info("{}",entry);
						LOGGER.info("Title: {}",entry.getTitle());
						final List<SyndPerson> entryAuthors=entry.getAuthors();
						if(authors!=null){
							for(final SyndPerson entryAuthor : entryAuthors){
								LOGGER.info("Author name: {}, email: {}",entryAuthor.getName(),entryAuthor.getEmail());
							}
						}
						entry.getCategories();
						entry.getContents();
						entry.getContributors();
						entry.getDescription();
						entry.getEnclosures();
						
					}
				}
				
			} catch (final IllegalArgumentException e) {
				LOGGER.error("The feed type could not be understood by the parsers",e);
			} catch (final FeedException e) {
				LOGGER.error("The feed could not be parsed",e);
			} catch (final IOException e) {
				LOGGER.error("The URL stream could not be read",e);
			}
		} catch (final MalformedURLException e) {
			LOGGER.error("URL is incorrect",e);
		}
		
		
	}
	
	public static final void main(final String[] args){
		Bla.test();
	}

}
