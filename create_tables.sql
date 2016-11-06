CREATE TABLE user_data(
	user_id INT NOT NULL AUTO_INCREMENT,
	last_name VARCHAR(30),
	first_name VARCHAR(30),
	address VARCHAR(50),
	city VARCHAR(50),
	state CHAR(2),
	zip_code INT,
	telephone BIGINT,
	email VARCHAR(50),
	account_number BIGINT NOT NULL,
	creation_date TIMESTAMP,
	credit_card BIGINT DEFAULT NULL,
	rating INT,
	PRIMARY KEY(user_id),
	UNIQUE(telephone),
	UNIQUE(email),
	UNIQUE(account_number)
);

CREATE TABLE user_preferences(
	user_id INT NOT NULL,
	preference VARCHAR(511),
	FOREIGN KEY(user_id) REFERENCES user_data(user_id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE group_data(
	group_id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	group_name VARCHAR(50) NOT NULL,
	group_type VARCHAR(50) NOT NULL,
	creation_date TIMESTAMP NOT NULL,
	PRIMARY KEY(group_id),
	FOREIGN KEY(user_id) REFERENCES user_data(user_id)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE group_members(
	group_id INT NOT NULL,
	user_id INT NOT NULL,
	join_date TIMESTAMP NOT NULL,
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

CREATE TABLE post_data(
	post_id INT NOT NULL UNIQUE,
	user_id	 INT NOT NULL,
	dateOfPost TIMESTAMP,
	content LONGTEXT,
	comment_count INT,
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
	message_date TIMESTAMP, 
	message_subject VARCHAR(249),
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
	employee_id INT NOT NULL AUTO_INCREMENT,
	social BIGINT NOT NULL,
	lastName VARCHAR(50) NOT NULL,
	firstName VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
	city VARCHAR(50) NOT NULL,
	state VARCHAR(2) NOT NULL,
	zipcode INT NOT NULL,
	telephone BIGINT NOT NULL,
	startDate TIMESTAMP,
	hourly DECIMAL(4,2) NOT NULL,
    PRIMARY KEY (employee_id),
	UNIQUE (social)
);

CREATE TABLE advertisement_data(
	advertisement_id INT NOT NULL AUTO_INCREMENT,
	employee_id INT NOT NULL,
	date_of_ad TIMESTAMP NOT NULL,
	type_of_ad VARCHAR(50) NOT NULL,
	company VARCHAR(50) NOT NULL,
	item_name VARCHAR(50) NOT NULL,
	content LONGTEXT NOT NULL,
	unit_price DECIMAL(4,2) NOT NULL,
	num_available INTEGER NOT NULL,
	PRIMARY KEY(advertisement_id),
	FOREIGN KEY(employee_id) REFERENCES employee_data(employee_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

CREATE TABLE sale_data(
	transaction_id INT NOT NULL AUTO_INCREMENT,
	ad_id INT NOT NULL,
	seller_id INT NOT NULL,
	consumer_id INT NOT NULL,
	transaction_date TIMESTAMP NOT NULL,
	number_of_units INT NOT NULL,
	account_number BIGINT NOT NULL, 
	PRIMARY KEY(transaction_id),
	FOREIGN KEY(seller_id) REFERENCES user_data(user_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(consumer_id) REFERENCES user_data(user_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(ad_id) REFERENCES advertisement_data(advertisement_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE,
	FOREIGN KEY(account_number) REFERENCES user_data(account_number)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);
