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
	pass varchar(8) not null,
	adminFlg boolean
);

INSERT INTO user_info(id, user_id, mail, pass, adminFlg) VALUES
(111,"taro","taro@gmail.com","123",false),
(999,"user","user","user",false),
(555,"admin","admin","admin",true);

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

INSERT INTO dish_info (author, registered_date, updated_date, dish_name, dish_info, ing, proc_1, cooking_time, image_file_path, image_file_name, publicity) VALUES
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1),
("test",now(),now(),"test bread","test","test/test,test/test","testtesttest",10,"./images","Test.jpg",1);
INSERT INTO dish_info (id,author, registered_date, updated_date, dish_name, dish_info, ing, proc_1, proc_2, proc_3, cooking_time, image_file_path, image_file_name, publicity) VALUES
(101,"user",now(),now(),"パンケーキ","バターとはちみつで","パンケーキミックス/200g,牛乳/ 150cc,卵/ 1個,バター/ 適量,はちみつ/ 適量","パンケーキミックス、牛乳、卵を混ぜ合わせる","熱したフライパンに油を薄くひいて1を入れる","弱火で両面を焼き、盛り付ける。バターとはちみつをお好みで加える",15,"./images","pancake.jpg",1),
(102,"user",now(),now(),"青椒肉絲","ピーマンたっぷり","牛肉/100g,ピーマン/ 4～5個,タケノコ/ 100g,醤油/ 大さじ1,砂糖/ 大さじ1,刻みニンニク/ 大さじ1,鶏ガラスープの素/ 少々","牛肉、ピーマン、タケノコを細長く切る","醤油、砂糖、ニンニク、鶏ガラスープの素を合わせておく","1を炒めて火が通ったら2の合わせ調味料を絡める",20,"./images","chinjiao.jpg",1),
(103,"user",now(),now(),"たらこパスタ","簡単パスタ","パスタ/200g,たらこ/適量,バター/適量,刻み海苔/適量,塩/適量","たっぷりのお湯を沸騰させ塩を加えてパスタを茹でる","茹で上がったらバターをひいたフライパンにパスタとたらこを加えよくあえる","盛り付けて刻み海苔をトッピングして完成",20,"./images","tarako_pasta.jpg",1),
(104,"taro",now(),now(),"レタス炒飯","レタスたっぷりの炒飯","ご飯 / 200g,生卵 / 1個,肉 / 20g,レタス / 2枚","フライパンを熱して油を回す","生卵を割り入れ、しばらくしたら温めたご飯を加えよく混ぜながら炒める","材料と調味料を加えて味を整える",15,"./images","friedrice.jpg",1);
INSERT INTO dish_info (id, author, registered_date, updated_date, dish_name, dish_info, ing, proc_1, proc_2, proc_3, proc_4, cooking_time, image_file_path, image_file_name, publicity) VALUES
(105,"user",now(),now(),"チヂミ","ニラと玉ねぎのチヂミ","小麦粉 / 50g,片栗粉 / 50g,水 / 100cc,ニラ / 1束,玉ねぎ / 半個,鶏ガラスープのもと / 適量","材料をボールに混ぜる","熱したフライパンにゴマ油をしく","片面を2分ずつ焼く","一口サイズに切って盛り付け",25,"./images","jijimi.jpg",1),
(106,"user",now(),now(),"オニオンスープ","玉ねぎの甘味","玉ねぎ/1個,ベーコン/ 30g,塩コショウ/ 適量,水/400cc","玉ねぎはみじん切りに、ベーコンは食べやすい大きさに切る","玉ねぎとベーコンをよく炒める","2に水を加えて塩コショウで味をととのえる","10分ほど煮込んで完成",30,"./images","onion_soup.jpg",1);
INSERT INTO dish_info (id, author, registered_date, updated_date, dish_name, dish_info, ing, proc_1, proc_2, cooking_time, image_file_path, image_file_name, publicity) VALUES
(107,"user",now(),now(),"野菜ラーメン","シンプルなおうちラーメン","お好みのインスタントラーメン/一袋,お好みの野菜/ 適量,お好みの肉/ 適量,お好みの調味料/ 適量","野菜、肉、調味料を炒める","できあがったインスタントラーメンに1をのせて完成",15,"./images","ramen.jpg",1),
(108,"user",now(),now(),"じゃがいも炒め","中華風","じゃがいも/3～4個,鶏肉/ 50g,スナック豆/ 30g,黒酢/ 大さじ0.5,醤油/ 大さじ1,豆鼓入りラー油/ 大さじ1","じゃがいもは細切りにする","材料を炒めて合わせ調味料を加えて完成",20,"./images","potato.jpg",1),
(109,"user",now(),now(),"コブサラダ","ハニーマスタードドレッシング仕立て","レタス/100g,アボカド/ 半個,トマト/ 半個,ブラックオリーブ/ 20g,ベーコン/ 20g,はちみつ/ 大さじ2,マスタード/ 大さじ2,パルメザンチーズ/ 適量","材料は食べやすい大きさに切り、ベーコンはよく炒める","調味料でドレッシングを作り盛り付けて完成",25,"./images","cobb.jpg",1),
(110,"user",now(),now(),"肉じゃが","出汁のきいた肉じゃが","牛肉/100g,じゃがいも/ 2～3個,にんじん/ 1本,しらたき/ 一袋,醤油/ 100cc,みりん/ 100cc,酒/ 100cc,かつおだし/ 200cc","材料を食べやすい大きさに切って炒める","火が通ったら調味料とかつおだしを加えて水分が飛ぶまで煮詰める",30,"./images","nikujaga.jpg",1);

drop table if exists tag_info;

create table tag_info(
	id int primary key auto_increment,
	tag_name varchar(30) not null unique
);

INSERT INTO tag_info(tag_name) VALUES
("ランチ"),("中華"),("ご飯"),("レタス"),("韓国"),
("粉もの"),("ニラ"),("ディナー"),("じゃがいも"),("簡単"),
("サラダ"),("アボカド"),("トマト"),("和食"),("煮物"),
("パスタ"),("朝食"),("おやつ"),("スープ"),("玉ねぎ"),
("ピーマン");

drop table if exists tag_map;

create table tag_map(
	id int primary key auto_increment,
	dish_id  int not null,
	tag_id int not null
);

INSERT INTO tag_map(dish_id, tag_id) VALUES
(101,17),(101,18),(101,6),(102,1),(102,2),(102,8),(102,21),(103,16),(103,10),(104,1),(104,2),(104,3),(104,4),(105,1),(105,5),(105,6),(105,7),
(106,20),(106,8),(106,19),(107,1),(107,10),(108,9),(108,8),(108,2),(109,11),(109,12),(109,13),(109,8),(110,14),(110,15),(110,8);