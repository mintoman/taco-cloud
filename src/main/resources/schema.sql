create table if not exists Taco_Order (
      id bigint not null,
      delivery_Name varchar(50) not null,
      delivery_Street varchar(50) not null,
      delivery_City varchar(50) not null,
      delivery_State varchar(20) not null,
      delivery_Zip varchar(10) not null,
      cc_number varchar(16) not null,
      cc_expiration varchar(5) not null,
      cc_cvv varchar(3) not null,
      placed_at timestamp not null,
      primary key (id)
 );

 create table if not exists Taco (
       id bigint not null,
       name varchar(50) not null,
       created_at timestamp not null,
       primary key (id)
 );

 create table if not exists Ingredient (
     id varchar(4) not null,
     name varchar(25) not null,
     type enum ('CHEESE','PROTEIN','SAUCE','VEGGIES','WRAP'),
     primary key (id)
 );

 --alter table Taco
     --add foreign key (taco_order_id) references Taco_Order(id);

 create table if not exists Taco_Ingredients (
     taco_id bigint not null,
     ingredient_id varchar(4) not null
 );

 alter table Taco_Ingredients
     add foreign key (taco_id) references Taco(id);
 alter table Taco_Ingredients
     add foreign key (ingredient_id) references Ingredient(id);

 create table if not exists Taco_Order_Tacos (
     taco_order_id bigint not null,
     taco_id bigint not null
 );

 alter table Taco_Order_Tacos
     add foreign key (taco_order_id) references Taco_Order(id);
 alter table Taco_Order_Tacos
     add foreign key (taco_id) references Taco(id);

 create sequence IF NOT EXISTS taco_order_seq start with 1 increment by 50;
 create sequence IF NOT EXISTS taco_seq start with 1 increment by 50;