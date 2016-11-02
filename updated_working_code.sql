CREATE TABLE user_data(
	user_id INT NOT NULL AUTO_INCREMENT,
    last_name VARCHAR(30),
    first_name VARCHAR(30),
    address VARCHAR(50),
    city VARCHAR(30),
    state CHAR(2),
    zip_code INT,
    telephone INT,
    email VARCHAR(50),
    account_number INT NOT NULL,
    creation_date DATE,
	credit_card BIGINT DEFAULT NULL,
    rating INT,
    PRIMARY KEY(user_id),
    UNIQUE(telephone),
	UNIQUE(email),
    UNIQUE(account_number)
);

CREATE TABLE user_preferences(
	user_id INT NOT NULL,
    preference VARCHAR(20),
    FOREIGN KEY(user_id) REFERENCES user_data(user_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE group_data(
	group_id		INT 	NOT NULL AUTO_INCREMENT,
    user_id			INT		NOT NULL,
    group_name		VARCHAR(50)	NOT NULL,
    group_type		VARCHAR(50) NOT NULL,
    creation_date	TIMESTAMP	NOT NULL,
    
    PRIMARY KEY(group_id),
    FOREIGN KEY(user_id) REFERENCES user_data(user_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE group_members(
	group_id	INT		NOT NULL,
    user_id		INT		NOT NULL,
    join_date	TIMESTAMP	NOT NULL,
    PRIMARY KEY(group_id,user_id),
	FOREIGN KEY(user_id) REFERENCES user_data(user_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	FOREIGN KEY(group_id) REFERENCES group_data(group_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE page_data(
	page_id INT NOT NULL AUTO_INCREMENT,
    page_owner INT,
    associated_group INT,
    post_count INT,
    PRIMARY KEY(page_id),
    FOREIGN KEY(page_owner) REFERENCES user_data(user_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	FOREIGN KEY(associated_group) REFERENCES group_data(group_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE post_data
(
	post_id INTEGER NOT NULL UNIQUE,
	user_id	 INTEGER NOT NULL,
	dateOfPost DATE,
	content LONGTEXT,
	commentCount Integer,
	PRIMARY KEY(post_id),
	FOREIGN KEY(user_id) REFERENCES user_data(user_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE comment_data(
	comment_id INT NOT NULL AUTO_INCREMENT,
    post_id INT NOT NULL,
    user_id INT NOT NULL,
    comment_content VARCHAR(250) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    PRIMARY KEY(comment_id),
    FOREIGN KEY(user_id) REFERENCES user_data(user_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	FOREIGN KEY(post_id) REFERENCES post_data(post_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE messages_data(
	message_id INT NOT NULL AUTO_INCREMENT,
    message_date DATE, 
    message_subject VARCHAR(50),
    message_content VARCHAR(511), -- 511 because 512 will add an aditional byte and decrease efficiency
    message_sender INT NOT NULL,
    message_receiver INT NOT NULL,
    PRIMARY KEY(message_id),
    FOREIGN KEY(message_sender) REFERENCES user_data(user_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	FOREIGN KEY(message_receiver) REFERENCES user_data(user_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE employee_data(
	social INT NOT NULL,
	lastName VARCHAR(50) NOT NULL,
	firstName VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	state VARCHAR(50) NOT NULL,
	zipcode INT NOT NULL,
	telephone CHAR(10) NOT NULL,
	startDate DATE,
	hourly INT NOT NULL,
	PRIMARY KEY (social)
);

CREATE TABLE advertisement_data(
	advertisement_id INT NOT NULL AUTO_INCREMENT,
	employee_id	 INT NOT NULL,
	date_of_ad DATE NOT NULL,
	type_of_ad DATE NOT NULL,
	company VARCHAR(50) NOT NULL,
	item_name VARCHAR(50) NOT NULL,
	content LONGTEXT NOT NULL,
	unit_price INTEGER NOT NULL,
	num_available INTEGER NOT NULL,
	PRIMARY KEY(advertisement_id),
	FOREIGN KEY(employee_id) REFERENCES employee_data(social)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE sale_data(
	transaction_id INT NOT NULL AUTO_INCREMENT,
	ad_id INT NOT NULL,
    seller_id INT NOT NULL,
    consumer_id INT NOT NULL,
    transaction_date DATE NOT NULL,
    number_of_units INT NOT NULL,
    account_number INT NOT NULL, 
    PRIMARY KEY(transaction_id),
    FOREIGN KEY(seller_id) REFERENCES user_data(user_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE,
	FOREIGN KEY(consumer_id) REFERENCES user_data(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	FOREIGN KEY(ad_id) REFERENCES advertisement_data(advertisement_id)
		ON DELETE CASCADE
        ON UPDATE CASCADE
);