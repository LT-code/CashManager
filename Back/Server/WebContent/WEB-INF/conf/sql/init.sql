-- Parse::SQL::Dia    version 0.25                              
-- Documentation      http://search.cpan.org/dist/Parse-Dia-SQL/
-- Environment        Perl 5.030000, /usr/bin/perl              
-- Architecture       x86_64-linux-thread-multi                 
-- Target Database    oracle                                    
-- Input file         ../bdd.dia                                
-- Generated at       Tue Oct 22 17:12:41 2019                  
-- Typemap for oracle not found in input file                   

-- get_constraints_drop 

-- get_permissions_drop 

-- get_view_drop

-- get_schema_drop
drop table Cart;
drop table Artice;
drop table CartArticle;
drop table Payment;
drop table PaymentType;
drop table Setting;
drop table Log;
drop table Machine;

-- get_smallpackage_pre_sql 

-- get_schema_create
create table Cart (
   idCart    INT AUTO_INCREMENT PRIMARY KEY not null,
   idMachine VARCHAR(100)                   not null,
   idSetting INT                            not null,
   FOREIGN KEY (idMachine) REFERENCES Machine(idMachine)
)   ;

create table Artice (
   code VARCHAR(30) PRIMARY KEY not null,
   name VARCHAR(30)             not null
)   ;

create table CartArticle (
   articleCode VARCHAR(30) not null,
   quantity    INT         not null,
   idCart      INT         not null,
   FOREIGN KEY (articleCode) REFERENCES Article(code),
   FOREIGN KEY (idCart) REFERENCES Cart(idCart)    
)   ;

create table Payment (
   idPayment INT AUTO_INCREMENT PRIMARY KEY not null,
   idCart    INT                            not null,
   type      INT                            not null,
   status    VARCHAR(10)                    not null,
   FOREIGN KEY (idMachine) REFERENCES Machine(idMachine),
   FOREIGN KEY (type) REFERENCES PaymentType(idType)
)   ;

create table PaymentType (
   idType INT AUTO_INCREMENT PRIMARY KEY not null,
   name   VARCHAR(20)                    not null
)   ;

create table Setting (
   idSetting       INT AUTO_INCREMENT PRIMARY KEY not null,
   connectionDelay INT                            not null,
   maxAttemps      INT                               not null
)   ;

create table Log (
   idLog     INT AUTO_INCREMENT PRIMARY KEY not null,
   idMachine VARCHAR(100)                   not null,
   name      VARCHAR(30)                    not null,
   type      VARCHAR(10)                    not null,
   code      VARCHAR(10)                    not null,
   FOREIGN KEY (idMachine) REFERENCES Machine(idMachine)
)   ;

create table Machine (
   idMachine VARCHAR(100) PRIMARY KEY not null,
   idSetting INT                      not null,
   isAdmin   BOOLEAN                  not null,
   password  VARCHAR(30)              not null,
   FOREIGN KEY (idSetting) REFERENCES Setting(idSetting)
)   ;

-- get_view_create

-- get_permissions_create

-- get_inserts

-- get_smallpackage_post_sql

-- get_associations_create
