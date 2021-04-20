DROP DATABASE IF EXISTS sample_db_v003;
CREATE DATABASE sample_db_v003;
USE sample_db_v003;

----------------------------------------------------------------------------------
-- TABLES
----------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_USER;
CREATE TABLE SB_USER
(
  user_id Binary(16) NOT NULL,
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
  user_protect_item Varchar(200) DEFAULT 'SLMORA',
  user_active_status Int DEFAULT '0',
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

INSERT INTO SB_USER
(user_id,
 user_email,
 user_avatar_name,
 user_first_name,
 user_last_name,
 user_full_name,
 user_title,
 user_sex,
 user_birthday,
 user_rank_point,
 user_reg_date_time,
 user_reg_ip,
 user_rank,
 user_nic,
 user_nationality,
 user_passport_no,
 user_contact_no_01,
 user_contact_no_02,
 user_mobile,
 user_fax_no,
 user_speciality,
 user_flag,
 user_is_email_verified,
 user_is_mobile_verified,
 user_note,
 user_protect_item,
 user_active_status,
 raw_last_update_date_time,
 raw_last_update_log_id,
 update_user_account_id,
 raw_show_status,
 raw_update_status,
 raw_delete_status,
 raw_active_status,
 extra_01,
 extra_02,
 extra_03,
 user_category_id)
VALUES
(1,
'probisys@boswingroup.com',
'Probisys',
'Probisys',
'Probisys',
'Probisys Probisys',
'System',
'Male',
'2018-05-20',
0,
'2018-05-20 00:00:01',
'0.0.0.0',
0,
'111111111V',
'Sri Lanka',
NULL,
'0768250515',
'0711233000',
'0768250515',
'0768250515',
1,
'super',
1,
1,
'This is super user related details',
'PROBISYS$MORA',
1,
'2018-05-20 00:00:01',
1,
1,
1,
1,
1,
1,
NULL,
NULL,
NULL,
1);

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_IMAGE;
CREATE TABLE SB_IMAGE
(
  image_id Int NOT NULL AUTO_INCREMENT,
  image_note Text,
  image_salt Text,
  image_salt_code Int,
  image_is_salted Int,
  image_description Varchar(200),
  image_name Varchar(250),
  image_path Text,
  image_blob MEDIUMBLOB,
  image_code Int,
  image_height Int,
  image_width Int,
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
  image_cat_id Int,
  image_type_id Int,
 PRIMARY KEY (image_id)
)
;

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_USER_CATEGORY;
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

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_FILE;
CREATE TABLE SB_FILE
(
  file_id Int NOT NULL AUTO_INCREMENT,
  file_note Text,
  file_description Varchar(200),
  file_name Varchar(250),
  file_key Text,
  file_path Text,
  file_clob BLOB,
  file_code Int,
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
  file_cat_id Int,
  file_type_id Int,
 PRIMARY KEY (file_id)
)
;

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_ITEM_01;
CREATE TABLE SB_ITEM_01
(
  item_01_id Binary(16) NOT NULL,
  item_01_id_name Varchar(32),
  item_01_description Varchar(250),
  item_01_name Varchar(200) NOT NULL,
  item_01_qty_on_hand Int NOT NULL DEFAULT 0,
  item_01_measure_unit Char(5),
  item_01_unit_price Decimal(15,2) DEFAULT 0,
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
  item_01_category Varchar(250),
 PRIMARY KEY (item_01_id)
)
;

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_ITEM_CATEGORY_01;
CREATE TABLE SB_ITEM_CATEGORY_01
(
  item_category_01_id Binary(16) NOT NULL,
  item_category_01_id_name Varchar(32),
  item_category_01_name Varchar(150),
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
 PRIMARY KEY (item_category_01_id)
)
;

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_USER_01;
CREATE TABLE SB_USER_01
(
  user_01_id Binary(16) NOT NULL,
  user_01_id_name Varchar(100),
  user_01_first_name Varchar(100),
  user_01_last_name Varchar(100),
  user_01_full_name Text,
  user_01_user_name Varchar(100),
  user_01_email Varchar(100),
  user_01_address Text,
  user_01_country Varchar(100),
  user_01_state Varchar(100),
  user_01_zip Varchar(100),
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
 PRIMARY KEY (user_01_id)
)
;

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_ITEM_02;
CREATE TABLE SB_ITEM_02
(
  item_02_id Binary(16) NOT NULL,
  item_02_id_name Varchar(32),
  item_02_description Varchar(250),
  item_02_name Varchar(200) NOT NULL,
  item_02_qty_on_hand Int NOT NULL DEFAULT 0,
  item_02_measure_unit Char(5),
  item_02_unit_price Decimal(15,2) DEFAULT 0,
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
  item_02_category Varchar(250),
 PRIMARY KEY (item_02_id)
)
;

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_ITEM_03;
CREATE TABLE SB_ITEM_03
(
  item_03_id Binary(16) NOT NULL,
  item_03_id_name Varchar(32),
  item_03_description Varchar(250),
  item_03_name Varchar(200) NOT NULL,
  item_03_qty_on_hand Int NOT NULL DEFAULT 0,
  item_03_measure_unit Char(5),
  item_03_unit_price Decimal(15,2) DEFAULT 0,
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
  item_03_category Varchar(250),
 PRIMARY KEY (item_03_id)
)
;

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_CUSTOMER_01;
CREATE TABLE SB_CUSTOMER_01
(
  customer_01_id Binary(16) NOT NULL,
  customer_01_email Varchar(200),
  customer_01_sex Char(10),
  customer_01_first_name Varchar(100),
  customer_01_last_name Varchar(100),
  customer_01_nic Char(50),
  customer_01_mobile Varchar(20),
  customer_01_birthday Date,
  customer_01_address Text,
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
 PRIMARY KEY (customer_01_id)
)
;

------------------------------------------------------------------------------------------------------------------------

DROP TABLE IF EXISTS SB_EMPLOYEE_01;
CREATE TABLE SB_EMPLOYEE_01
(
  employee_01_id Binary(16) NOT NULL,
  employee_01_email Varchar(200),
  employee_01_sex Char(10),
  employee_01_first_name Varchar(100),
  employee_01_last_name Varchar(100),
  employee_01_nic Char(50),
  employee_01_mobile Varchar(20),
  employee_01_birthday Date,
  employee_01_address Text,
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
 PRIMARY KEY (employee_01_id)
)
;

DROP TABLE IF EXISTS SB_SUPPLIER_01;
CREATE TABLE SB_SUPPLIER_01
(
  supplier_01_id Binary(16) NOT NULL,
  supplier_01_email Varchar(200),
  supplier_01_owner_sex Char(10),
  supplier_01_owner_name Varchar(100),
  supplier_01_owner_birthday Date,
  supplier_01_owner_mobile Varchar(20),
  supplier_01_owner_address Text,
  supplier_01_business_name Varchar(100),
  supplier_01_brn Char(50),
  supplier_01_business_mobile Varchar(20),
  supplier_01_business_address Text,
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
 PRIMARY KEY (supplier_01_id)
)
;

DROP TABLE IF EXISTS SB_CUSTOMER_ORDER_01;
CREATE TABLE SB_CUSTOMER_ORDER_01
(
  customer_order_01_id Binary(16) NOT NULL,
  customer_order_01_invoice_number Varchar(20),
  customer_order_01_date_time Datetime NOT NULL,
  customer_order_01_total Decimal(15,2) DEFAULT 0,
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
 PRIMARY KEY (customer_order_01_id)
)
;

----------------------------------------------------------------------------------
-- FUNCTIONS
----------------------------------------------------------------------------------

SET GLOBAL log_bin_trust_function_creators = 1;

CREATE DEFINER=`root`@`localhost` FUNCTION `get_ordered_uuid`(`uuid` binary(36)) RETURNS binary(16)
BEGIN
	RETURN UNHEX(CONCAT(SUBSTR(uuid, 15, 4),SUBSTR(uuid, 10, 4),SUBSTR(uuid, 1, 8),SUBSTR(uuid, 20, 4),SUBSTR(uuid, 25)));
END

select get_ordered_uuid(uuid())

------------------------------------------------------------------------------------------------------------------------

CREATE DEFINER=`root`@`localhost` FUNCTION `getUnixTimestampInMilliseconds`() RETURNS bigint
BEGIN
  DECLARE milliseconds BIGINT DEFAULT 0;

  SELECT CONV(
	  CONCAT(
		  SUBSTRING(uid,16,3),
		  SUBSTRING(uid,10,4),
		  SUBSTRING(uid,1,8)),
	  16,10)
	  DIV 10000
	  - (141427 * 24 * 60 * 60 * 1000) INTO milliseconds
  FROM (SELECT UUID() uid) AS alias;

  RETURN milliseconds;
END

select getUnixTimestampInMilliseconds()

------------------------------------------------------------------------------------------------------------------------

CREATE DEFINER=`root`@`localhost` PROCEDURE `add_user_category`(category_name varchar(255), is_default Int)
BEGIN

DECLARE v1_id int DEFAULT 0;

  /*CUSTOM SQLEXCEPTION*/
  DECLARE EXIT HANDLER FOR SQLEXCEPTION
  BEGIN
    ROLLBACK;  /* rollback any changes made in the transaction*/
    RESIGNAL;  /* raise again the sql exception to the caller*/
  END;

  START TRANSACTION;

	INSERT INTO SB_USER_CATEGORY (
		user_category_name,
		user_category_code,
		user_category_is_default,
		raw_last_update_date_time,
		raw_last_update_log_id,
		update_user_account_id,
		raw_show_status,
		raw_update_status,
		raw_delete_status,
		raw_active_status)
	VALUES (category_name, 1, is_default, now(), 1, 1, 1, 1, 1, 1);

	SET v1_id = LAST_INSERT_ID();

	IF (v1_id = 0 OR v1_id IS NULL) THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = "SB_USER_CATEGORY details are wrong.!", MYSQL_ERRNO = 300;
	END IF;

--   ROLLBACK;
  COMMIT;
	SELECT v1_id;
-- SELECT 'ok';
/*  COMMIT;*/
END

call add_user_category("Test Call", 1)

------------------------------------------------------------------------------------------------------------------------

CREATE DEFINER=`root`@`localhost` FUNCTION `UuidToBin`(`uuid` binary(36)) RETURNS binary(16)
BEGIN
	RETURN
	UNHEX(
		CONCAT(
			SUBSTR(uuid, 15, 4),
			SUBSTR(uuid, 10, 4),
			SUBSTR(uuid, 1, 8),
			SUBSTR(uuid, 20, 4),
			SUBSTR(uuid, 25)));
END

CREATE DEFINER=`root`@`localhost` FUNCTION `UuidFromBin`(`bin` binary(16)) RETURNS binary(36)
BEGIN
	RETURN
	CONCAT_WS('-',
		HEX(SUBSTR(bin, 5, 4)),
		HEX(SUBSTR(bin, 3, 2)),
		HEX(SUBSTR(bin, 1, 2)),
		HEX(SUBSTR(bin, 9, 2)),
		HEX(SUBSTR(bin, 11)));
END

---------------------
-- Special
---------------------

SHOW VARIABLES LIKE '%timeout%';

SHOW VARIABLES LIKE '%connect%';