CREATE TABLE IF NOT EXISTS `activation_key` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_key` VARCHAR(75) NOT NULL,
  `user_email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_activation_key_user_email FOREIGN KEY(user_email) REFERENCES `user`(email) );