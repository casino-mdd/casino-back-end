/*
Created		4/27/2019
Modified		5/1/2019
Project		
Model			
Company		
Author		
Version		
Database		PostgreSQL 8.1 
*/


/* Create Tables */


Create table  Person 
(
	 id_person  Serial NOT NULL,
	 age  Integer NOT NULL,
	 gender  Varchar NOT NULL Check (gender IN ('F','M')),
	 name  Varchar NOT NULL,
	 surname  Varchar NOT NULL,
	 email  Varchar NOT NULL UNIQUE,
	 phone  Numeric(10,0) NOT NULL,
	 identification_number  Varchar NOT NULL UNIQUE,
	 created_At  Timestamp NOT NULL,
	 updated_at  Timestamp NOT NULL,
 primary key ( id_person )
) Without Oids;


Create table  Client 
(
	 id_client  Serial NOT NULL,
	 id_person  Integer NOT NULL,
	 created_At  Timestamp NOT NULL,
	 updated_at  Timestamp NOT NULL,
 primary key ( id_client )
) Without Oids;


Create table  Employee 
(
	 id_employee  Serial NOT NULL,
	 id_person  Integer NOT NULL,
	 position  Varchar NOT NULL,
	 admition_date  Timestamp NOT NULL,
	 updated_At  Timestamp NOT NULL,
	 created_at  Timestamp NOT NULL,
	 id_office  Integer NOT NULL,
 primary key ( id_employee )
) Without Oids;


Create table  Office 
(
	 id_office  Serial NOT NULL,
	 address  Varchar NOT NULL,
	 city  Varchar NOT NULL,
	 name  Varchar NOT NULL,
	 updated_at  Timestamp NOT NULL,
	 created_at  Timestamp NOT NULL,
 primary key ( id_office )
) Without Oids;


Create table  User_account 
(
	 id_user_account  Serial NOT NULL,
	 id_employee  Integer NOT NULL,
	 username  Varchar NOT NULL UNIQUE,
	 password  Varchar NOT NULL,
	 updated_At  Timestamp NOT NULL,
	 created_At  Timestamp NOT NULL,
	 is_active  Boolean NOT NULL Default true,
	 profile Varchar NOT NULL,
 primary key ( id_user_account )
) Without Oids;


Create table  Sale 
(
	 id_sale  Serial NOT NULL,
	 id_employee  Integer NOT NULL,
	 id_office  Integer NOT NULL,
	 created_at  Timestamp NOT NULL,
	 token  Integer NOT NULL,
	 cost  Double precision NOT NULL,
	 payment_method  Varchar NOT NULL Check (payment_method in ('Efectivo', 'Tarjeta credito', 'Tarjeta debito')),
	 id_client  Integer NOT NULL,
 primary key ( id_sale )
) Without Oids;


Create table  Point 
(
	 id_point  Serial NOT NULL,
	 total_points  Integer NOT NULL,
	 exp_date  Timestamp NOT NULL,
	 created_at  Timestamp NOT NULL,
	 id_sale  Integer NOT NULL,
 primary key ( id_point )
) Without Oids;


Create table  Reward 
(
	 id_reward  Serial NOT NULL,
	 id_office  Integer NOT NULL,
	 name  Varchar NOT NULL,
	 points_need  Integer NOT NULL,
	 is_available  Boolean NOT NULL,
 primary key ( id_reward )
) Without Oids;


Create table  Exchange 
(
	 id_exchange  Serial NOT NULL,
	 id_client  Integer NOT NULL,
	 created_at  Timestamp NOT NULL,
	 id_reward  Integer NOT NULL,
	 id_employee  Integer NOT NULL,
 primary key ( id_exchange )
) Without Oids;


/* Create Tab 'Others' for Selected Tables */


/* Create Indexes */


/* Create Foreign Keys */

Alter table  Client  add  foreign key ( id_person ) references  Person  ( id_person ) on update restrict on delete restrict;

Alter table  Employee  add  foreign key ( id_person ) references  Person  ( id_person ) on update restrict on delete restrict;

Alter table  Exchange  add  foreign key ( id_client ) references  Client  ( id_client ) on update restrict on delete restrict;

Alter table  Sale  add  foreign key ( id_client ) references  Client  ( id_client ) on update restrict on delete restrict;

Alter table  User_account  add  foreign key ( id_employee ) references  Employee  ( id_employee ) on update restrict on delete restrict;

Alter table  Sale  add  foreign key ( id_employee ) references  Employee  ( id_employee ) on update restrict on delete restrict;

Alter table  Exchange  add  foreign key ( id_employee ) references  Employee  ( id_employee ) on update restrict on delete restrict;

Alter table  Employee  add  foreign key ( id_office ) references  Office  ( id_office ) on update restrict on delete restrict;

Alter table  Sale  add  foreign key ( id_office ) references  Office  ( id_office ) on update restrict on delete restrict;

Alter table  Reward  add  foreign key ( id_office ) references  Office  ( id_office ) on update restrict on delete restrict;

Alter table  Point  add  foreign key ( id_sale ) references  Sale  ( id_sale ) on update restrict on delete restrict;

Alter table  Exchange  add  foreign key ( id_reward ) references  Reward  ( id_reward ) on update restrict on delete restrict;


