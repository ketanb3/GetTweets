

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;


public class TweetStream {
	public static void main(String[] args) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true);
        cb.setOAuthConsumerKey("");
        cb.setOAuthConsumerSecret("");
        cb.setOAuthAccessToken("");
        cb.setOAuthAccessTokenSecret("");

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        
       
        
        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrubGeo(long arg0, long arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStatus(Status status) {
               /* User user = status.getUser();
                
                // gets Username
                String username = status.getUser().getScreenName();
                System.out.println(username);
                String profileLocation = user.getLocation();
                System.out.println(profileLocation);
                long tweetId = status.getId(); 
                System.out.println(tweetId);
                String content = status.getText();
                System.out.println(content +"\n");*/
            	User user = status.getUser();
            	   BufferedWriter bufferedWriter = null;
            	   try
            	   {
            	      bufferedWriter = new BufferedWriter(new FileWriter("twitterDumponFile.txt", true));
            	      String username = status.getUser().getScreenName();
            	      bufferedWriter.write(username+"	");

            	      String profileLocation = user.getLocation();
            	      bufferedWriter.write(profileLocation+"	");

            	      String content = status.getText();
            	      bufferedWriter.write(content);
            	      bufferedWriter.newLine();

            	   }
            	   catch (FileNotFoundException ex) 
            	   {
            	      ex.printStackTrace();
            	   } 
            	   catch (IOException ex) 
            	   {
            	      ex.printStackTrace();
            	   } 
            	   finally
            	   {
            	      //Closing the BufferedWriter
            	      try
            	      {
            	         if (bufferedWriter != null) 
            	         {
            	            bufferedWriter.flush();
            	            bufferedWriter.close();
            	         }
            	      } 
            	      catch (IOException ex) 
            	      {
            	         ex.printStackTrace();
            	      }
            	   }
            	
            	
            }

            @Override
            public void onTrackLimitationNotice(int arg0) {
                // TODO Auto-generated method stub

            }

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

        };
        FilterQuery fq = new FilterQuery();
        double[][] location = {{40.7142691},{-74.0059729}};
        String keywords[] = {"ireland"};

        fq.track(keywords);
        fq.locations(location);

        twitterStream.addListener(listener);
        twitterStream.filter(fq); 
        
        

    }
}
