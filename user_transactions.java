public static void joinGroup(Connection connection, PreparedStatement prepared_statement,int userId, int groupId, java.sql.Date date) throws SQLException{
	prepared_statement = connection.prepareStatement("INSERT INTO group_members(group_id,user_id,join_date) VALUES (?,?,?)");
	prepared_statement.setInt(1,groupId);
	prepared_statement.setInt(2,userId);
	prepared_statement.setDate(3,date);
	prepared_statement.executeUpdate();
}

public static void leaveGroup(Connection connection, PreparedStatement prepared_statement,int userId, int groupId) throws SQLException{
	prepared_statement = connection.prepareStatement("DELETE FROM group_members WHERE user_id=? AND group_id=?");
	prepared_statement.setInt(1,groupId);
	prepared_statement.setInt(2,userId);
	prepared_statement.executeUpdate();
}

public static void writePost(Connection connection, PreparedStatement prepared_statement,int postId, int userId, String content, int pageId) throws SQLException{
	prepared_statement = connection.prepareStatement("INSERT INTO post_data(post_id,user_id,content,comment_count,page_id) VALUES (?,?,?,"+0+",?)");
	prepared_statement.setInt(1,postId);
	prepared_statement.setInt(2,userId);
	prepared_statement.setString(3,content);
	prepared_statement.setInt(4,0);
	prepared_statement.setInt(5,pageId);
	prepared_statement.executeUpdate();
	
	prepared_statement = connection.prepareStatement("UPDATE page_data SET post_count = post_count + 1 WHERE page_id=?");
	prepared_statement.setInt(1,pageId);
	prepared_statement.executeUpdate();
}

public static void deletePost(Connection connection, PreparedStatement prepared_statement,int postId) throws SQLException{
	prepared_statement = connection.prepareStatement("DELETE FROM post_data WHERE post_id=?");
	prepared_statement.setInt(1,postId);
	prepared_statement.executeUpdate();
	
	prepared_statement = connection.prepareStatement("UPDATE page_data SET post_count = post_count + 1 WHERE page_id=?");
	prepared_statement.setInt(1,pageId);
	prepared_statement.executeUpdate();
}

public static void modifyPost(Connection connection, PreparedStatement prepared_statement,int postId, int userId, String content, int pageId) throws SQLException{
	prepared_statement = connection.prepareStatement("UPDATE post_data SET content=? WHERE user_id=? AND post_id=? AND page_id=?");
	prepared_statement.setString(1,content);
	prepared_statement.setInt(2,post_id);
	prepared_statement.setInt(3,pageId);
	prepared_statement.executeUpdate();
}

public static void commentOnPost(Connection connection, PreparedStatement prepared_statement,int commentId, int postId, int userId, String content, java.sql.Date date) throws SQLException{
	prepared_statement = connection.prepareStatement("INSERT INTO comment_data(comment_id,post_id,user_id,comment_content,creation_date) VALUES (?,?,?,?,?)");
	prepared_statement.setInt(1,postId);
	prepared_statement.setInt(2,commentId);
	prepared_statement.setInt(3,userId);	
	prepared_statement.setString(4,content);
	prepared_statement.setDate(5,date);
	prepared_statement.executeUpdate();
	
	prepared_statement = connection.prepareStatement("UPDATE post_data SET comment_count = comment_count + 1 WHERE post_id=?");
	prepared_statement.setInt(1,postId);
	prepared_statement.executeUpdate();
}

public static void deleteCommentOnPost(Connection connection, PreparedStatement prepared_statement,int commentId, int postId, int userId) throws SQLException{
	prepared_statement = connection.prepareStatement("DELETE FROM comment_data WHERE post_id=? AND user_id=? AND comment_id=?");
	prepared_statement.setInt(1,postId);
	prepared_statement.setInt(2,userId);
	prepared_statement.setInt(3,commentId);	
	prepared_statement.executeUpdate();
	
	prepared_statement = connection.prepareStatement("UPDATE post_data SET comment_count = comment_count - 1 WHERE post_id=?");
	prepared_statement.setInt(1,postId);
	prepared_statement.executeUpdate();
}

public static void modifyComment(Connection connection, PreparedStatement prepared_statement,int postId, int userId, String content, int pageId) throws SQLException{
	prepared_statement = connection.prepareStatement("UPDATE comment_data SET comment_content=? WHERE user_id=? AND comment_id=? AND post_id=?");
	prepared_statement.setString(1,content);
	prepared_statement.setInt(2,comment_id);
	prepared_statement.setInt(3,postId);
	prepared_statement.executeUpdate();
}

public static void likePost(Connection connection, PreparedStatement prepared_statement,int postId, int userId) throws SQLException{
	prepared_statement = connection.prepareStatement("INSERT INTO liked_posts(post_id,user_id) VALUES (?,?)");
	prepared_statement.setInt(1,postId);
	prepared_statement.setInt(2,userId);
	prepared_statement.executeUpdate();
}

public static void unlikePost(Connection connection, PreparedStatement prepared_statement,int postId, int userId) throws SQLException{
	prepared_statement = connection.prepareStatement("DELETE FROM liked_posts WHERE post_id=? AND user_id=?);
	prepared_statement.setInt(1,postId);
	prepared_statement.setInt(2,userId);
	prepared_statement.executeUpdate();
}

public static void likeComment(Connection connection, PreparedStatement prepared_statement,int commentId, int userId) throws SQLException{
	prepared_statement = connection.prepareStatement("INSERT INTO liked_comments(comment_id,user_id) VALUES (?,?)");
	prepared_statement.setInt(1,commentId);
	prepared_statement.setInt(2,userId);
	prepared_statement.executeUpdate();
}

public static void unlikeComment(Connection connection, PreparedStatement prepared_statement,int commentId, int userId) throws SQLException{
	prepared_statement = connection.prepareStatement("DELETE FROM liked_comments WHERE comment_id=? AND user_id=?);
	prepared_statement.setInt(1,commentId);
	prepared_statement.setInt(2,userId);
	prepared_statement.executeUpdate();
}


