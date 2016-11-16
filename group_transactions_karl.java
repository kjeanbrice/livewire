 
 
    public static void create_group(Connection connection,
            PreparedStatement prepared_statement,
            int user_id, String group_name, String group_type)
            throws SQLException {

        prepared_statement = connection.prepareStatement(" INSERT INTO group_data"
                + "(user_id,group_name,group_type,creation_date) "
                + "VALUES (?,?,?,?)");

        prepared_statement.setInt(1, user_id);
        prepared_statement.setString(2, group_name);
        prepared_statement.setString(3, group_type);
        prepared_statement.setTimestamp(4,
                new java.sql.Timestamp(new java.util.Date().getTime()));
        prepared_statement.executeUpdate();

        ResultSet result_set = null;
        prepared_statement = connection.prepareStatement(
                "SELECT group_id"
                + " FROM group_data"
                + " WHERE group_data.user_id  = ? and group_data.group_name = ?");
        prepared_statement.setInt(1, user_id);
        prepared_statement.setString(2, group_name);
        result_set = prepared_statement.executeQuery();

        if (result_set.next()) {
            prepared_statement = connection.prepareStatement(" INSERT INTO page_data"
                    + "(page_owner,associated_group,post_count) "
                    + "VALUES (?,?,?)");
            prepared_statement.setInt(1, user_id);
            prepared_statement.setInt(2, result_set.getInt("group_id"));
            prepared_statement.setInt(3, 0);
            prepared_statement.executeUpdate();
        }
    }
    
    

    /*Allows the user to join a group*/
    public static void add_user_to_own_group(Connection connection,
            PreparedStatement prepared_statement,
            int owner_id, String group_name, int user_to_add)
            throws SQLException {

        ResultSet result_set = null;
        prepared_statement = connection.prepareStatement(
                "SELECT group_id"
                + " FROM group_data"
                + " WHERE group_data.user_id  = ? and group_data.group_name = ?");
        prepared_statement.setInt(1, owner_id);
        prepared_statement.setString(2, group_name);
        result_set = prepared_statement.executeQuery();

        if (result_set.next()) {
            prepared_statement = connection.prepareStatement("INSERT INTO group_members(group_id,user_id) "
                    + "VALUES (?,?)");
            prepared_statement.setInt(1, result_set.getInt("group_id"));
            prepared_statement.setInt(2, user_to_add);
            int i = prepared_statement.executeUpdate();
        }
    }

    
    
    /*Allows the owner of a group to create a new post*/
    public static void create_post_in_own_group(Connection connection,
            PreparedStatement prepared_statement,
            int group_id, int user_id, String content) throws SQLException {
        ResultSet result_set = null;
        prepared_statement = connection.prepareStatement("SELECT page_id"
                + " FROM page_data"
                + " WHERE page_data.associated_group  = ?");
        prepared_statement.setInt(1, group_id);
        result_set = prepared_statement.executeQuery();

        if (result_set.next()) {
            prepared_statement = connection.prepareStatement("INSERT INTO "
                    + "post_data(user_id,page_id,content)"
                    + " VALUES (?,?,?)");
            prepared_statement.setInt(1, user_id);
            prepared_statement.setInt(2, result_set.getInt("page_id"));
            prepared_statement.setString(3, content);
            prepared_statement.executeUpdate();

        }
    }

    
    
    /*Allows a user to add a comment to a post*/
    public static void add_comment_to_post(Connection connection,
            PreparedStatement prepared_statement,
            int user_id, int post_id, String content)
            throws SQLException {
        ResultSet resultSet = null;
        prepared_statement = connection.prepareStatement(
                "INSERT INTO comment_data(user_id,post_id,comment_content) "
                + "VALUES (?,?,?)");
        prepared_statement.setInt(1, user_id);
        prepared_statement.setInt(2, post_id);
        prepared_statement.setString(3, content);
        prepared_statement.executeUpdate();

        prepared_statement = connection.prepareStatement("SELECT P.comment_count "
                + "FROM post_data P "
                + "WHERE P.post_id = ?");
        prepared_statement.setInt(1, post_id);

        resultSet = prepared_statement.executeQuery();
        if (resultSet.next()) {
            prepared_statement = connection.prepareStatement("UPDATE post_data "
                    + "SET post_data.comment_count = ? "
                    + "WHERE post_data.post_id  = ?");
            prepared_statement.setInt(1, resultSet.getInt("comment_count") + 1);
            prepared_statement.setInt(2, post_id);
            prepared_statement.executeUpdate();
        }
    }

    
    
    public static void like_comment(Connection connection, PreparedStatement prepared_statement, int comment_id, int user_id) throws SQLException {
        prepared_statement = connection.prepareStatement("INSERT INTO liked_comments(comment_id,user_id) VALUES (?,?)");
        prepared_statement.setInt(1, comment_id);
        prepared_statement.setInt(2, user_id);
        prepared_statement.executeUpdate();

    }

    
    
    public static void unlike_comment(Connection connection,
            PreparedStatement prepared_statement,
            int comment_id, int user_id)
            throws SQLException {
        prepared_statement = connection.prepareStatement("DELETE FROM "
                + "liked_comments WHERE liked_comments.comment_id = ? "
                + "and liked_comments.user_id = ?");
        prepared_statement.setInt(1, comment_id);
        prepared_statement.setInt(2, user_id);
        prepared_statement.executeUpdate();
    }

    
    
    public static void like_post(Connection connection,
            PreparedStatement prepared_statement,
            int post_id, int user_id) throws SQLException {
        prepared_statement = connection.prepareStatement("INSERT INTO liked_posts(post_id,user_id)"
                + " VALUES (?,?)");
        prepared_statement.setInt(1, post_id);
        prepared_statement.setInt(2, user_id);
        prepared_statement.executeUpdate();
    }

    
    
    public static void unlike_post(Connection connection, PreparedStatement prepared_statement,
            int post_id, int user_id)
            throws SQLException {
        prepared_statement = connection.prepareStatement("DELETE FROM "
                + "liked_posts "
                + "WHERE liked_posts.post_id = ? "
                + "and liked_posts.user_id = ?");
        prepared_statement.setInt(1, post_id);
        prepared_statement.setInt(2, user_id);
        prepared_statement.executeUpdate();
    }

    
    public static void remove_user_from_group(Connection connection, PreparedStatement prepared_statement,
            int user_to_remove, int owner_id, String group_name)
            throws SQLException {
        ResultSet result_set = null;
        prepared_statement = connection.prepareStatement("SELECT group_id"
                + " FROM group_data"
                + " WHERE group_data.user_id  = ?"
                + " and group_data.group_name = ?");
        prepared_statement.setInt(1, owner_id);
        prepared_statement.setString(2, group_name);
        result_set = prepared_statement.executeQuery();

        if (result_set.next()) {
            prepared_statement = connection.prepareStatement("DELETE FROM "
                    + "group_members "
                    + "WHERE group_members.user_id = ? "
                    + "and group_members.group_id = ?");
            prepared_statement.setInt(1, user_to_remove);
            prepared_statement.setInt(2, result_set.getInt("group_id"));
            prepared_statement.executeUpdate();
        }
    }

   
    public static void remove_post_from_group(Connection connection,
            PreparedStatement prepared_statement,
             int post_id) throws SQLException {
        prepared_statement = connection.prepareStatement("DELETE "
                + "FROM post_data "
                + "WHERE post_data.post_id = ?");
        prepared_statement.setInt(1, post_id);
        prepared_statement.executeUpdate();
    }

    
    public static void remove_comment_from_group(Connection connection, PreparedStatement prepared_statement,
            int comment_id, int post_id) throws SQLException {

        ResultSet resultSet = null;
        prepared_statement = connection.prepareStatement("DELETE FROM "
                + "comment_data"
                + " WHERE comment_data.comment_id = ? AND"
                + " comment_data.post_id = ? ");
        prepared_statement.setInt(1, comment_id);
        prepared_statement.setInt(2, post_id);
        prepared_statement.executeUpdate();

        prepared_statement = connection.prepareStatement("SELECT P.comment_count "
                + "FROM post_data P "
                + "WHERE P.post_id = ?");
        prepared_statement.setInt(1, post_id);

        resultSet = prepared_statement.executeQuery();
        if (resultSet.next()) {
            prepared_statement = connection.prepareStatement("UPDATE post_data "
                    + "SET post_data.comment_count = ? "
                    + "WHERE post_data.post_id  = ?");
            prepared_statement.setInt(1, resultSet.getInt("comment_count") - 1);
            prepared_statement.setInt(2, post_id);
            prepared_statement.executeUpdate();
        }
    }

     
    public static void modify_post(Connection connection,
            PreparedStatement prepared_statement,
            int post_id, String content)
            throws SQLException {
        prepared_statement = connection.prepareStatement("UPDATE post_data "
                + "SET post_data.content = ? "
                + "WHERE post_data.post_id  = ?");
        prepared_statement.setInt(2, post_id);
        prepared_statement.setString(1, content);
        prepared_statement.executeUpdate();
    }

    public static void modify_comment(Connection connection,
            PreparedStatement prepared_statement,
            int comment_id, int post_id, String content)
            throws SQLException {
        prepared_statement = connection.prepareStatement("UPDATE comment_data "
                + "SET comment_data.comment_content = ?"
                + " WHERE comment_data.comment_id  = ? "
                + "AND comment_data.post_id = ? ");

        prepared_statement.setString(1, content);
        prepared_statement.setInt(2, comment_id);
        prepared_statement.setInt(3, post_id);
        prepared_statement.executeUpdate();
    }

   
    public static void rename_group(Connection connection,
            PreparedStatement prepared_statement,
            String new_group_name,
            int owner_id, String old_group_name)
            throws SQLException {
        ResultSet result_set = null;
        prepared_statement = connection.prepareStatement("SELECT group_id"
                + " FROM group_data"
                + " WHERE group_data.user_id  = ? "
                + " AND group_data.group_name = ?");
        prepared_statement.setInt(1, owner_id);
        prepared_statement.setString(2, old_group_name);
        result_set = prepared_statement.executeQuery();

        if (result_set.next()) {
            prepared_statement = connection.prepareStatement("UPDATE group_data "
                    + "SET group_data.group_name = ? WHERE group_data.group_id  = ?");
            prepared_statement.setString(1, new_group_name);
            prepared_statement.setInt(2, result_set.getInt("group_id"));
            prepared_statement.executeUpdate();
        }
    }

    public static void delete_group(Connection connection, PreparedStatement prepared_statement, int group_id) throws SQLException {
        prepared_statement = connection.prepareStatement("DELETE FROM "
                + "group_data "
                + "WHERE group_data.group_id = ?");
        prepared_statement.setInt(1, group_id);
        prepared_statement.executeUpdate();
    }

    public static void buy_advertised_item(Connection connection,
            PreparedStatement prepared_statement,
            String item_name, int buyer_id, int num_units)
            throws SQLException {

        ResultSet result_set = null;
        prepared_statement = connection.prepareStatement("Select employee_id, "
                + "advertisement_id "
                + "FROM advertisement_data "
                + "WHERE advertisement_data.item_name = ?");
        prepared_statement.setString(1, item_name);
        result_set = prepared_statement.executeQuery();

        if (result_set.next()) {
            int employee_id = result_set.getInt("employee_id");
            int advertisement_id = result_set.getInt("advertisement_id");

            result_set = null;
            prepared_statement = connection.prepareStatement("Select account_number "
                    + "FROM user_data "
                    + "WHERE user_data.user_id = ?");
            prepared_statement.setInt(1, buyer_id);
            result_set = prepared_statement.executeQuery();
            if (result_set.next()) {
                prepared_statement = connection.prepareStatement("INSERT INTO sale_data"
                        + "(ad_id,seller_id,consumer_id,number_of_units,account_number) "
                        + "Values( ?, ?, ?, ?, ?)");
                prepared_statement.setInt(1, advertisement_id);
                prepared_statement.setInt(2, employee_id);
                prepared_statement.setInt(3, buyer_id);
                prepared_statement.setInt(4, num_units);
                prepared_statement.setLong(5, result_set.getLong("account_number"));
                prepared_statement.executeUpdate();
            }
        }
    }