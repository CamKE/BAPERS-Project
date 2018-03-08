-- -----------------------------------------------------
-- Schema BAPERS_data
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BAPERS_data` DEFAULT CHARACTER SET utf8 ;
USE `BAPERS_data` ;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Customer` (
  `account_no` SMALLINT ZEROFILL UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_holder_name` VARCHAR(80) NOT NULL,
  `prefix` VARCHAR(4) NOT NULL,
  `firstname` VARCHAR(35) NOT NULL,
  `lastname` VARCHAR(35) NOT NULL,
  `street_name` VARCHAR(80) NOT NULL,
  `postcode` VARCHAR(8) NOT NULL,
  `city` VARCHAR(60) NOT NULL,
  `phone` VARCHAR(13) NOT NULL,
  `is_suspended` TINYINT(1) NOT NULL DEFAULT 0,
  `in_default` TINYINT(1) NOT NULL DEFAULT 0,
  `is_valued` TINYINT(1) NOT NULL DEFAULT 0,
  `registration_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `building_no` TINYINT NULL,
  PRIMARY KEY (`account_no`),
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Role` (
  `role_id` TINYINT NOT NULL AUTO_INCREMENT,
  `role_description` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`User` (
  `account_no` SMALLINT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(35) NOT NULL,
  `lastname` VARCHAR(35) NOT NULL,
  `password_hash` BINARY(32) NOT NULL,
  `registration_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `Role_role_id` TINYINT NOT NULL,
  PRIMARY KEY (`account_no`),
  UNIQUE INDEX `password_UNIQUE` (`password_hash` ASC),
  INDEX `fk_User_Role1_idx` (`Role_role_id` ASC),
  CONSTRAINT `fk_User_Role1`
    FOREIGN KEY (`Role_role_id`)
    REFERENCES `BAPERS_data`.`Role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`DiscountPlan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`DiscountPlan` (
  `Customer_account_no` SMALLINT UNSIGNED ZEROFILL NOT NULL,
  `User_account_no` SMALLINT UNSIGNED NOT NULL,
  `discount_type` ENUM('Fixed', 'Variable', 'Flexible') NULL,
  PRIMARY KEY (`Customer_account_no`),
  INDEX `fk_DiscountPlan_User1_idx` (`User_account_no` ASC),
  CONSTRAINT `fk_DiscountPlan_CustomerAccount`
    FOREIGN KEY (`Customer_account_no`)
    REFERENCES `BAPERS_data`.`Customer` (`account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DiscountPlan_User1`
    FOREIGN KEY (`User_account_no`)
    REFERENCES `BAPERS_data`.`User` (`account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`FixedDiscount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`FixedDiscount` (
  `discount_rate` TINYINT ZEROFILL NOT NULL,
  `DiscountPlan_Customer_account_no` SMALLINT UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`DiscountPlan_Customer_account_no`),
  CONSTRAINT `fk_FixedDiscount_DiscountPlan1`
    FOREIGN KEY (`DiscountPlan_Customer_account_no`)
    REFERENCES `BAPERS_data`.`DiscountPlan` (`Customer_account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Band`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Band` (
  `upper_bound` DOUBLE(8,2) UNSIGNED ZEROFILL NOT NULL,
  `lower_bound` DOUBLE(8,2) UNSIGNED ZEROFILL NOT NULL,
  `discount_rate` TINYINT UNSIGNED NOT NULL,
  `DiscountPlan_Customer_account_no` SMALLINT UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`upper_bound`, `DiscountPlan_Customer_account_no`, `lower_bound`),
  INDEX `fk_Band_DiscountPlan1_idx` (`DiscountPlan_Customer_account_no` ASC),
  CONSTRAINT `fk_Band_DiscountPlan1`
    FOREIGN KEY (`DiscountPlan_Customer_account_no`)
    REFERENCES `BAPERS_data`.`DiscountPlan` (`Customer_account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Department` (
  `department_code` CHAR(2) NOT NULL,
  `department_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`department_code`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Task` (
  `task_id` TINYINT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(40) NOT NULL,
  `duration_min` TINYINT UNSIGNED NOT NULL,
  `shelf_slot` TINYINT UNSIGNED NOT NULL,
  `price` DOUBLE(6,2) UNSIGNED NOT NULL,
  `Department_department_code` CHAR(2) NOT NULL,
  PRIMARY KEY (`task_id`),
  INDEX `fk_Task_Department1_idx` (`Department_department_code` ASC),
  CONSTRAINT `fk_Task_Department1`
    FOREIGN KEY (`Department_department_code`)
    REFERENCES `BAPERS_data`.`Department` (`department_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Task_DiscountPlans`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Task_DiscountPlans` (
  `Task_task_id` TINYINT UNSIGNED ZEROFILL NOT NULL,
  `DiscountPlan_Customer_account_no` SMALLINT ZEROFILL UNSIGNED NOT NULL,
  PRIMARY KEY (`Task_task_id`, `DiscountPlan_Customer_account_no`),
  INDEX `fk_Task_has_DiscountPlan_DiscountPlan1_idx` (`DiscountPlan_Customer_account_no` ASC),
  INDEX `fk_Task_has_DiscountPlan_Task1_idx` (`Task_task_id` ASC),
  CONSTRAINT `fk_Task_has_DiscountPlan_Task1`
    FOREIGN KEY (`Task_task_id`)
    REFERENCES `BAPERS_data`.`Task` (`task_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Task_has_DiscountPlan_DiscountPlan1`
    FOREIGN KEY (`DiscountPlan_Customer_account_no`)
    REFERENCES `BAPERS_data`.`DiscountPlan` (`Customer_account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`StandardJob`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`StandardJob` (
  `code` VARCHAR(6) NOT NULL,
  `job_description` VARCHAR(45) NOT NULL,
  `price` DOUBLE(5,2) UNSIGNED NOT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`StandardJob_Tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`StandardJob_Tasks` (
  `StandardJob_code` VARCHAR(6) NOT NULL,
  `Task_task_id` TINYINT UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`StandardJob_code`, `Task_task_id`),
  INDEX `fk_StandardJob_has_Task_Task1_idx` (`Task_task_id` ASC),
  INDEX `fk_StandardJob_has_Task_StandardJob1_idx` (`StandardJob_code` ASC),
  CONSTRAINT `fk_StandardJob_has_Task_StandardJob1`
    FOREIGN KEY (`StandardJob_code`)
    REFERENCES `BAPERS_data`.`StandardJob` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_StandardJob_has_Task_Task1`
    FOREIGN KEY (`Task_task_id`)
    REFERENCES `BAPERS_data`.`Task` (`task_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Status` (
  `status_id` INT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `start_time` TIME NULL,
  `finish_time` TIME NULL,
  `status_type` ENUM('Not started', 'In progress', 'Completed') NOT NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Priority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Priority` (
  `priority_description` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`priority_description`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`CompletionTime`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`CompletionTime` (
  `completion_time` TIME NOT NULL,
  `surcharge` TINYINT NULL,
  `Priority_priority_description` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`completion_time`),
  INDEX `fk_CompletionTime_Priority1_idx` (`Priority_priority_description` ASC),
  CONSTRAINT `fk_CompletionTime_Priority1`
    FOREIGN KEY (`Priority_priority_description`)
    REFERENCES `BAPERS_data`.`Priority` (`priority_description`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Deadline`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Deadline` (
  `deadline` DATETIME NOT NULL,
  `date_received` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CompletionTime_completion_time` TIME NOT NULL,
  PRIMARY KEY (`date_received`, `CompletionTime_completion_time`),
  INDEX `fk_Deadline_CompletionTime1_idx` (`CompletionTime_completion_time` ASC),
  CONSTRAINT `fk_Deadline_CompletionTime1`
    FOREIGN KEY (`CompletionTime_completion_time`)
    REFERENCES `BAPERS_data`.`CompletionTime` (`completion_time`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Job`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Job` (
  `job_no` MEDIUMINT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `User_account_no` SMALLINT UNSIGNED ZEROFILL NOT NULL,
  `Customer_account_no` SMALLINT ZEROFILL UNSIGNED NOT NULL,
  `Status_status_id` INT UNSIGNED ZEROFILL NOT NULL,
  `is_collected` TINYINT(1) NOT NULL DEFAULT 0,
  `Deadline_date_received` DATETIME NOT NULL,
  `Deadline_CompletionTime_completion_time` TIME NOT NULL,
  `special_instructions` VARCHAR(45) NULL,
  PRIMARY KEY (`job_no`),
  INDEX `fk_Job_User1_idx` (`User_account_no` ASC),
  INDEX `fk_Job_Customer1_idx` (`Customer_account_no` ASC),
  INDEX `fk_Job_Status1_idx` (`Status_status_id` ASC),
  INDEX `fk_Job_Deadline1_idx` (`Deadline_date_received` ASC, `Deadline_CompletionTime_completion_time` ASC),
  CONSTRAINT `fk_Job_User1`
    FOREIGN KEY (`User_account_no`)
    REFERENCES `BAPERS_data`.`User` (`account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Job_Customer1`
    FOREIGN KEY (`Customer_account_no`)
    REFERENCES `BAPERS_data`.`Customer` (`account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Job_Status1`
    FOREIGN KEY (`Status_status_id`)
    REFERENCES `BAPERS_data`.`Status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Job_Deadline1`
    FOREIGN KEY (`Deadline_date_received` , `Deadline_CompletionTime_completion_time`)
    REFERENCES `BAPERS_data`.`Deadline` (`date_received` , `CompletionTime_completion_time`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Job_StandardJobs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Job_StandardJobs` (
  `Job_job_no` MEDIUMINT UNSIGNED ZEROFILL NOT NULL,
  `StandardJob_code` VARCHAR(6) NOT NULL,
  `Status_status_id` INT UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`Job_job_no`, `StandardJob_code`),
  INDEX `fk_Job_has_StandardJob_StandardJob1_idx` (`StandardJob_code` ASC),
  INDEX `fk_Job_has_StandardJob_Job1_idx` (`Job_job_no` ASC),
  INDEX `fk_Job_StandardJobs_Status1_idx` (`Status_status_id` ASC),
  CONSTRAINT `fk_Job_has_StandardJob_Job1`
    FOREIGN KEY (`Job_job_no`)
    REFERENCES `BAPERS_data`.`Job` (`job_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Job_has_StandardJob_StandardJob1`
    FOREIGN KEY (`StandardJob_code`)
    REFERENCES `BAPERS_data`.`StandardJob` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Job_StandardJobs_Status1`
    FOREIGN KEY (`Status_status_id`)
    REFERENCES `BAPERS_data`.`Status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Job_StandardJobs_Tasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Job_StandardJobs_Tasks` (
  `Job_StandardJobs_Job_job_no` MEDIUMINT UNSIGNED ZEROFILL NOT NULL,
  `Job_StandardJobs_StandardJob_code` VARCHAR(6) NOT NULL,
  `Task_task_id` TINYINT UNSIGNED ZEROFILL NOT NULL,
  `Status_status_id` INT UNSIGNED ZEROFILL NOT NULL,
  `User_account_no` SMALLINT UNSIGNED ZEROFILL NULL,
  PRIMARY KEY (`Job_StandardJobs_Job_job_no`, `Job_StandardJobs_StandardJob_code`, `Task_task_id`),
  INDEX `fk_Job_StandardJobs_has_Task_Task1_idx` (`Task_task_id` ASC),
  INDEX `fk_Job_StandardJobs_has_Task_Job_StandardJobs1_idx` (`Job_StandardJobs_Job_job_no` ASC, `Job_StandardJobs_StandardJob_code` ASC),
  INDEX `fk_Job_StandardJobs_Tasks_Status1_idx` (`Status_status_id` ASC),
  INDEX `fk_Job_StandardJobs_Tasks_User1_idx` (`User_account_no` ASC),
  CONSTRAINT `fk_Job_StandardJobs_has_Task_Job_StandardJobs1`
    FOREIGN KEY (`Job_StandardJobs_Job_job_no` , `Job_StandardJobs_StandardJob_code`)
    REFERENCES `BAPERS_data`.`Job_StandardJobs` (`Job_job_no` , `StandardJob_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Job_StandardJobs_has_Task_Task1`
    FOREIGN KEY (`Task_task_id`)
    REFERENCES `BAPERS_data`.`Task` (`task_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Job_StandardJobs_Tasks_Status1`
    FOREIGN KEY (`Status_status_id`)
    REFERENCES `BAPERS_data`.`Status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Job_StandardJobs_Tasks_User1`
    FOREIGN KEY (`User_account_no`)
    REFERENCES `BAPERS_data`.`User` (`account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Invoice` (
  `Invoice_no` MEDIUMINT UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `Job_job_no` MEDIUMINT UNSIGNED ZEROFILL NOT NULL,
  `total_payable` DOUBLE(8,2) UNSIGNED NOT NULL,
  `date_issued` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `invoice_status` ENUM('Awaiting payment', 'Paid on time', 'Paid late') NOT NULL,
  `invoice_location` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Invoice_no`),
  INDEX `fk_invoice_Job1_idx` (`Job_job_no` ASC),
  CONSTRAINT `fk_invoice_Job1`
    FOREIGN KEY (`Job_job_no`)
    REFERENCES `BAPERS_data`.`Job` (`job_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`ReminderLetter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`ReminderLetter` (
  `Invoice_invoice_no` MEDIUMINT UNSIGNED ZEROFILL NOT NULL,
  `date_issued` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `letter_location` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Invoice_invoice_no`, `date_issued`),
  CONSTRAINT `fk_ReminderLetter_invoice1`
    FOREIGN KEY (`Invoice_invoice_no`)
    REFERENCES `BAPERS_data`.`Invoice` (`Invoice_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Report`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Report` (
  `report_no` INT NOT NULL,
  `date_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `report_type` ENUM('Customer report', 'Staff report', 'Company report') NOT NULL,
  `report_location` VARCHAR(45) NOT NULL,
  `from_date` DATE NOT NULL,
  `to_date` DATE NOT NULL,
  `User_account_no` SMALLINT UNSIGNED ZEROFILL NULL,
  PRIMARY KEY (`report_no`),
  INDEX `fk_Report_User1_idx` (`User_account_no` ASC),
  CONSTRAINT `fk_Report_User1`
    FOREIGN KEY (`User_account_no`)
    REFERENCES `BAPERS_data`.`User` (`account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`CardDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`CardDetails` (
  `last4digits` SMALLINT(4) UNSIGNED NOT NULL,
  `card_type` ENUM('Visa', 'Visa debit', 'Mastercard', 'Amex', 'Maestro') NOT NULL,
  `expiry_date` VARCHAR(45) NOT NULL,
  `Customer_account_no` SMALLINT UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`last4digits`, `expiry_date`),
  INDEX `fk_CardDetails_Customer1_idx` (`Customer_account_no` ASC),
  CONSTRAINT `fk_CardDetails_Customer1`
    FOREIGN KEY (`Customer_account_no`)
    REFERENCES `BAPERS_data`.`Customer` (`account_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`PaymentRecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`PaymentRecord` (
  `payment_no` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `total` DOUBLE(8,2) UNSIGNED NOT NULL,
  `payment_type` ENUM('Cash', 'Card') NOT NULL,
  `payment_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Invoice_invoice_no` MEDIUMINT UNSIGNED ZEROFILL NOT NULL,
  `CardDetails_last4digits` SMALLINT(4) UNSIGNED NOT NULL,
  `CardDetails_expiry_date` VARCHAR(45) NULL,
  PRIMARY KEY (`payment_no`),
  INDEX `fk_PaymentRecord_invoice1_idx` (`Invoice_invoice_no` ASC),
  INDEX `fk_PaymentRecord_CardDetails1_idx` (`CardDetails_last4digits` ASC, `CardDetails_expiry_date` ASC),
  CONSTRAINT `fk_PaymentRecord_invoice1`
    FOREIGN KEY (`Invoice_invoice_no`)
    REFERENCES `BAPERS_data`.`Invoice` (`Invoice_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PaymentRecord_CardDetails1`
    FOREIGN KEY (`CardDetails_last4digits` , `CardDetails_expiry_date`)
    REFERENCES `BAPERS_data`.`CardDetails` (`last4digits` , `expiry_date`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Material` (
  `material_description` VARCHAR(45) NOT NULL,
  `Job_job_no` MEDIUMINT UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`material_description`),
  INDEX `fk_Material_Job1_idx` (`Job_job_no` ASC),
  CONSTRAINT `fk_Material_Job1`
    FOREIGN KEY (`Job_job_no`)
    REFERENCES `BAPERS_data`.`Job` (`job_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`RestoreSettings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`RestoreSettings` (
  `restore_frequency` ENUM('hourly', 'every 12 hours', 'daily', 'weekly', 'monthly', 'every 3 months', 'every 6 months') NOT NULL,
  `restore_mode` ENUM('reminder', 'auto', 'manual') NOT NULL,
  `restore_location` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`restore_mode`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`ReportSettings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`ReportSettings` (
  `report_type` ENUM('summary performance report', 'customer report', 'staff report') NOT NULL,
  `frequency` ENUM('hourly', 'daily', 'weekly', 'monthly', 'yearly') NULL,
  `report_period` ENUM('previous hour', 'previous day', 'previous week', 'previous month', 'previous year') NULL,
  PRIMARY KEY (`report_type`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`BackupSettings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`BackupSettings` (
  `backup_mode` ENUM('manual', 'reminder', 'auto') NOT NULL,
  `backup_frequency` ENUM('hourly', 'every 12 hours', 'daily', 'weekly', 'monthly', 'every 3 months', 'every 6 months') NOT NULL,
  `backup_location` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`backup_mode`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `BAPERS_data`.`Backup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BAPERS_data`.`Backup` (
  `date_created` DATETIME NOT NULL,
  `file_name` VARCHAR(45) NOT NULL,
  `file_location` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`file_name`))
ENGINE = InnoDB;