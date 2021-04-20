DROP DATABASE IF EXISTS SAMPLE_DB_V001;
CREATE DATABASE SAMPLE_DB_V001;
USE SAMPLE_DB_V001;

CREATE TABLE SB_USER_CATEGORY
(
  user_category_id Int NOT NULL AUTO_INCREMENT,
  user_category_name Varchar(150),
  user_category_code Int,
  user_category_is_default Int,
  user_category_note Text,
  raw_last_update_date_time Datetime NOT NULL,
  raw_last_update_log_id Int NOT NULL,
  update_user_account_id Int NOT NULL,
  raw_show_status Int NOT NULL,
  raw_update_status Int NOT NULL,
  raw_delete_status Int NOT NULL,
  raw_active_status Int NOT NULL,
  extra_01 Text,
  extra_02 Text,
  extra_03 Text,
 PRIMARY KEY (user_category_id)
)
;

CREATE TABLE SB_USER
(
  user_id Int NOT NULL AUTO_INCREMENT,
  user_email Varchar(200),
  user_avatar_name Varchar(100),
  user_first_name Varchar(100),
  user_last_name Varchar(100),
  user_full_name Text,
  user_title Char(10),
  user_sex Char(10),
  user_birthday Date,
  user_rank_point Int DEFAULT '0',
  user_reg_date_time Datetime,
  user_reg_ip Varchar(100) DEFAULT '0.0.0.0',
  user_rank Int DEFAULT '0',
  user_nic Char(50),
  user_nationality Varchar(100),
  user_passport_no Varchar(100),
  user_contact_no_01 Varchar(20),
  user_contact_no_02 Varchar(20),
  user_mobile Varchar(20),
  user_fax_no Varchar(20),
  user_speciality Int DEFAULT '0',
  user_flag Varchar(100),
  user_is_email_verified Int DEFAULT '0',
  user_is_mobile_verified Int DEFAULT '0',
  user_note Text,
  user_protect_item Varchar(200) DEFAULT 'PROBISYS$MORA',
  raw_last_update_date_time Datetime NOT NULL,
  raw_last_update_log_id Int NOT NULL,
  update_user_account_id Int NOT NULL,
  raw_show_status Int NOT NULL,
  raw_update_status Int NOT NULL,
  raw_delete_status Int NOT NULL,
  raw_active_status Int NOT NULL,
  extra_01 Text,
  extra_02 Text,
  extra_03 Text,
  user_category_id Int,
 PRIMARY KEY (user_id)
)
;

CREATE INDEX ix_user_a_user_category ON SB_USER (user_category_id)
;

ALTER TABLE SB_USER ADD CONSTRAINT r_user_a_user_category FOREIGN KEY (user_category_id) REFERENCES SB_USER_CATEGORY (user_category_id) ON DELETE SET NULL ON UPDATE CASCADE
;