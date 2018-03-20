CREATE TABLE IF NOT EXISTS `flash_cards_db`.`card` (
  `id` INT(11) NOT NULL,
  `term` VARCHAR(100) NOT NULL,
  `definition` VARCHAR(200) NOT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `image` VARCHAR(500) NULL DEFAULT NULL,
  `set_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `set_id` (`set_id` ASC),
  CONSTRAINT `card_ibfk_1`
    FOREIGN KEY (`set_id`)
    REFERENCES `flash_cards_db`.`set` (`id`))