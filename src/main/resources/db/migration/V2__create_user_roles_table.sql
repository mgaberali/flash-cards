CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_email` VARCHAR(100) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_user_email FOREIGN KEY(user_email) REFERENCES `user`(email) );