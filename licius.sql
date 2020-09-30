create database catalog_management;


CREATE SCHEMA IF NOT EXISTS `catalog_management` DEFAULT CHARACTER SET utf8 ;
USE `catalog_management` ;

-- -----------------------------------------------------
-- Table `catalog_management`.`catagory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog_management`.`catagory` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `enabled` BIT(1) NULL,
  `parent_Id` VARCHAR(45) NULL,
  `level` INT NULL,
  `img_link` LONGTEXT NULL,
  `returnable` BIT(1) NULL,
  `created_at` DATETIME(3) NULL,
  `update_at` DATETIME(3) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `catalog_management`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog_management`.`products` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(512) NULL,
  `description` VARCHAR(2048) NULL,
  `catagory_id` BIGINT(20) NULL,
  `sku` VARCHAR(45) NULL,
  `img_link` LONGTEXT NULL,
  `enabled` BIT(1) NULL,
  `created_at` DATETIME(3) NULL,
  `update_at` DATETIME(3) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_1_idx` (`catagory_id` ASC),
  UNIQUE INDEX `sku_UNIQUE` (`sku` ASC),
  CONSTRAINT `fk_products_catagory`
    FOREIGN KEY (`catagory_id`)
    REFERENCES `catalog_management`.`catagory` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `catalog_management`.`combo_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog_management`.`combo_products` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `discription` LONGTEXT NULL,
  `selling_price` DECIMAL(10,2) NULL,
  `created_at` DATETIME(3) NULL,
  `update_at` DATETIME(3) NULL,
  `enabled` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_combo_products_1_idx` (`selling_price` ASC),
  CONSTRAINT `fk_combo_products_1`
    FOREIGN KEY (`selling_price`)
    REFERENCES `catalog_management`.`products` (`sku`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `catalog_management`.`combo_product_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog_management`.`combo_product_list` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `combo_id` BIGINT(20) NULL,
  `product_sku` VARCHAR(45) NULL,
  `mrp` DECIMAL(10,2) NULL,
  `created_at` DATETIME(3) NULL,
  `update_at` DATETIME(3) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_table1_1_idx` (`combo_id` ASC),
  INDEX `fk_combo_product_list_1_idx` (`product_sku` ASC),
  CONSTRAINT `fk_table1_1`
    FOREIGN KEY (`combo_id`)
    REFERENCES `catalog_management`.`combo_products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_combo_product_list_1`
    FOREIGN KEY (`product_sku`)
    REFERENCES `catalog_management`.`products` (`sku`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `catalog_management`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog_management`.`orders` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `order_number` VARCHAR(45) NULL,
  `buyer_name` VARCHAR(45) NULL,
  `buyer_ph_number` VARCHAR(45) NULL,
  `total_amount` DECIMAL(10,2) NULL,
  `final_amount` DECIMAL(10,2) NULL,
  `discount_amount` DECIMAL(10,2) NULL,
  `status` VARCHAR(45) NULL,
  `created_at` DATETIME(3) NULL,
  `update_at` DATETIME(3) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `catalog_management`.`line_items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `catalog_management`.`line_items` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `order_id` BIGINT(20) NULL,
  `product_sku` VARCHAR(45) NULL,
  `mrp` VARCHAR(45) NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_line_items_1_idx` (`order_id` ASC),
  CONSTRAINT `fk_line_items_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `catalog_management`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

