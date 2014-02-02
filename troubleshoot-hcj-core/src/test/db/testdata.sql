DROP TABLE IF EXISTS user_codes;
CREATE TABLE user_codes (
  id int NOT NULL IDENTITY,
  user_name VARCHAR(200) NOT NULL,
  code varchar(100) NOT NULL,
  type_id int NOT NULL,
  options varchar(200)
 );
 
INSERT INTO user_codes (id, user_name, code, type_id) VALUES 
	(1, 'test', 'AAAAAAAA', 1);