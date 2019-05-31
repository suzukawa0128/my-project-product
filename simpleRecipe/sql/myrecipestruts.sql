set names utf8;
set foreign_key_checks=0;

drop database if exists myrecipestruts;
create database if not exists myrecipestruts;
use myrecipestruts;

drop table if exists user_info;

create table user_info(
	id int primary key auto_increment,
	user_id varchar(30) not null unique,
	mail varchar(100) not null unique,
	pass varchar(8) not null
);

INSERT INTO user_info(id, user_id, mail, pass) VALUES(111,"taro","taro@gmail.com","123"), (999,"user","user","user");

drop table if exists dish_info;

create table dish_info(
	id int primary key auto_increment,
	author varchar(30) not null,
	registered_date datetime not null,
	updated_date datetime not null,
	dish_name varchar(30) not null, -- authorに対してはunique
	dish_info varchar(60),
	ing varchar(255),
	proc_1 varchar(60),
	proc_2 varchar(60),
	proc_3 varchar(60),
	proc_4 varchar(60),
	proc_5 varchar(60),
	proc_6 varchar(60),
	proc_7 varchar(60),
	proc_8 varchar(60),
	proc_9 varchar(60),
	proc_10 varchar(60),
	cooking_time int,
	image_file_path varchar(50),
	image_file_name varchar(100),
	publicity boolean
);

INSERT INTO dish_info (author, registered_date, updated_date, dish_name, dish_info, ing, proc_1, proc_2, proc_3, cooking_time, image_file_path, image_file_name, publicity) VALUES
("taro",now(),now(),"レタス炒飯","レタスたっぷりの炒飯","ご飯 / 200g,生卵 / 1個,肉 / 20g,レタス / 2枚","フライパンを熱して油を回す","生卵を割り入れ、しばらくしたら温めたご飯を加えよく混ぜながら炒める","材料と調味料を加えて味を整える",15,"./images","friedrice.jpg",1);
INSERT INTO dish_info (author, registered_date, updated_date, dish_name, dish_info, ing, proc_1, proc_2, proc_3, proc_4, cooking_time, image_file_path, image_file_name, publicity) VALUES
("user",now(),now(),"チヂミ","ニラと玉ねぎのチヂミ","小麦粉 / 50g,片栗粉 / 50g,水 / 100cc,ニラ / 1束,玉ねぎ / 半個,鶏ガラスープのもと / 適量","材料をボールに混ぜる","熱したフライパンにゴマ油をしく","片面を2分ずつ焼く","一口サイズに切って盛り付け",25,"./images","jijimi.jpg",1);
INSERT INTO dish_info (author, registered_date, updated_date, dish_name, dish_info, image_file_path, image_file_name, publicity) VALUES
("user",now(),now(),"パンケーキ","バターとはちみつで","./images","pancake.jpg",1),
("user",now(),now(),"青椒肉絲","ピーマンたっぷり","./images","chinjiao.jpg",1),
("user",now(),now(),"オニオンスープ","玉ねぎの甘味","./images","onion_soup.jpg",1),
("user",now(),now(),"たらこパスタ","簡単パスタ","./images","tarako_pasta.jpg",1),
("user",now(),now(),"野菜ラーメン","シンプルなおうちラーメン","./images","ramen.jpg",0),
("user",now(),now(),"じゃがいも炒め","中華風","./images","potato.jpg",0),
("user",now(),now(),"コブサラダ","ハニーマスタードドレッシング仕立て","./images","cobb.jpg",0),
("user",now(),now(),"肉じゃが","出汁のきいた肉じゃが","./images","nikujaga.jpg",0);

drop table if exists tag_info;

create table tag_info(
	id int primary key auto_increment,
	tag_name varchar(30) not null unique
);

INSERT INTO tag_info(tag_name) VALUES
("ランチ"),("中華"),("ご飯"),("レタス"),("韓国"),("粉もの"),("ニラ");

drop table if exists tag_map;

create table tag_map(
	id int primary key auto_increment,
	dish_id  int not null,
	tag_id int not null
);

INSERT INTO tag_map(dish_id, tag_id) VALUES
(1,1),(1,2),(1,3),(1,4),(2,1),(2,5),(2,6),(2,7);