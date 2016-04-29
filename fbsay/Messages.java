package voicefb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.restfb.Connection;
import com.restfb.types.CategorizedFacebookType;
import com.restfb.types.Comment;
import com.restfb.types.Conversation;
import com.restfb.types.Likes;
import com.restfb.types.Message;
import com.restfb.types.Post;


public class Messages {
	
	public static List<Comment> latestPostComments = new ArrayList<Comment>();
	public static List<CategorizedFacebookType> latestPostLikes = new ArrayList<CategorizedFacebookType>();
	public static Post latestPost = null;
	
	public static void main(String[] args) throws IOException {
		
		Connection<Post> myFeed = FbManager.facebookClient.fetchConnection("me/feed", Post.class);
		Post p = myFeed.getData().get(0);
		File likesFile = new File("likes.txt");
		File commentsFile = new File("comments.txt");
		BufferedWriter likesWriter = new BufferedWriter(new FileWriter(likesFile));
		BufferedWriter commentsWriter = new BufferedWriter(new FileWriter(commentsFile));
		Connection<Comment> allComments = FbManager.facebookClient.fetchConnection(p.getId()+"/comments", Comment.class);
		latestPostComments = allComments.getData();
		commentsWriter.write("You now have " + latestPostComments.size() + " comments. Your latest comments are as follows.");
		commentsWriter.newLine();
		for (Comment c : latestPostComments) {
			String message = c.getFrom().getName() + " says " + c.getMessage();
			System.out.println(message);
			commentsWriter.write(message);
			commentsWriter.newLine();
		}
		commentsWriter.flush();
		Connection<CategorizedFacebookType> allLikes = FbManager.facebookClient.fetchConnection(p.getId()+"/likes", CategorizedFacebookType.class);
		latestPostLikes = allLikes.getData();
		likesWriter.write("You now have " + latestPostLikes.size() + " likes. Your latest likers are as follows.");
		likesWriter.newLine();
		for (CategorizedFacebookType l : latestPostLikes) {
			System.out.println(l.getName());
			likesWriter.write(l.getName());
			likesWriter.newLine();
		}
		likesWriter.flush();
//		
//		Timer timer = new Timer();
//		timer.scheduleAtFixedRate(new TimerTask() {
//			
//			@Override
//			public void run() {
//				
//				System.out.println(p.getMessage());
//				Connection<CategorizedFacebookType> allComments = FbManager.facebookClient.fetchConnection(p.getId()+"/likes", CategorizedFacebookType.class);
//				//System.out.println(allComments.getData().size());
//				for (CategorizedFacebookType l : allComments.getData()) {
//					System.out.println(l.getName());
//				}
////				System.out.println(myFeed.getData().get(0).getLikes().getData().get(0).getName());
////				System.out.println(p.getLikesCount());
////				System.out.println(myFeed.getData().get(0).getComments().getData().get(0).getMessage());
////				for (Post p : myFeed.getData()) {
////					System.out.println(p.getMessage());
////				}
////				Connection<Conversation> conversations = FbManager.facebookClient.fetchConnection("me/conversations", Conversation.class);
////
////				for(List<Conversation> conversationPage : conversations) {
////				    for(Conversation conversation : conversationPage) {
////				        System.out.println(conversation);
////				        System.out.println(conversation.getUnreadCount());
////
////				        Message lastMessage = null;
////				        for (Message message : conversation.getMessages())
////				        {
////				            System.out.println("Message text = " + message.getMessage());
////				            System.out.println("Message unread = " + message.getUnread());
////				            System.out.println("Message from = " + message.getFrom().getName());
////				            System.out.println("Message to = " + message.getTo().get(0).getName());
////				            System.out.println("Message unseen = " + message.getUnseen());
////				            lastMessage = message;
////				        }
////				    }    
////				}
//				
//				
//			}
//		}, 0, 5000);
	}
}
