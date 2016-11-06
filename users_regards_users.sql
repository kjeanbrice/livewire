/* 	
	Written in terms of PHP
	PHP statements used '$variable' inputs
    We obtain these variables from the PHP side, 
    these are the SQL statements we use
*/

/* Join Group*/
INSERT INTO `group_members` (`group_id`,`user_id`,`join_date`) VALUES ('$group_id', '$user_id', CURDATE());
/*--------------*/
/*Leave Group*/
DELETE FROM `group_members` WHERE `user_id`='$user_id' AND `group_id`='$group_id'; 
/*--------------*/
/*Write Post*/
INSERT INTO `post_data` (`post_id`,`user_id`,`content`,`comment_count`,`page_id`) VALUES ('$post_id', '$user_id', '$content', '0', '$page_id');
UPDATE `page_data` SET post_count = post_count + 1 WHERE `page_id`='$page_id';
/*--------------*/



