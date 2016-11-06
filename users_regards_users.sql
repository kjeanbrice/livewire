INSERT INTO `group_members` (`group_id`,`user_id`,`join_date`) VALUES ('$group_id', '$user_id', CURDATE()); -- Join group
DELETE FROM `group_members` WHERE `user_id`='$user_id' AND `group_id`='$group_id'; -- Leave group

