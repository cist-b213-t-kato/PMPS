DROP TABLE activity;
DROP TABLE bulletin;
DROP TABLE comment;
DROP TABLE projectdetail;
DROP TABLE schedule;
DROP TABLE useraccount;
DROP TABLE userpost;

CREATE TABLE activity
(
  projectid serial NOT NULL,
  projectname text NOT NULL,
  leader text NOT NULL,
  term text NOT NULL,
  outline text NOT NULL,
  link text NOT NULL,
  grade integer NOT NULL,
  year integer NOT NULL,
  CONSTRAINT activity_pkey PRIMARY KEY (projectid)
);

CREATE TABLE bulletin
(
  bulletinid serial  NOT NULL,
  subject varchar(256) NOT NULL,
  maintext varchar(256) NOT NULL,
  userid  varchar(8) NOT NULL,
  CONSTRAINT bulletin_pkey PRIMARY KEY (bulletinid)
);

CREATE TABLE comment
(
  projectid integer NOT NULL,
  commenttext text NOT NULL,
  username text NOT NULL,
  date date NOT NULL
);

CREATE TABLE projectdetail
(
   projectid serial NOT NULL, 
   outline text NOT NULL, 
   achievement text NOT NULL, 
   impression text NOT NULL
);

create table schedule(
	scheduleid serial NOT NULL,
	content text NOT NULL, --tkato追加
	starttime timestamp NOT NULL,
	endtime timestamp NOT NULL,
	userid varchar(8),
	CONSTRAINT schedule_pkey PRIMARY KEY (scheduleid)
);

CREATE TABLE useraccount
(
  userid character varying(8) NOT NULL,
  password character varying(256) NOT NULL,
  username text NOT NULL,
  grade integer NOT NULL,
 PRIMARY KEY (userid)
);

CREATE TABLE userpost
(
  bulletinid integer NOT NULL,
  userid varchar(8) NOT NULL,
  read boolean NOT NULL
);

--データの例--------------------------------------------------------------------------------
insert into activity(projectname,leader,term,outline,link,grade,year) 
values('PMPS','上杉','秋学期','プロメン専用ポータルサイトの作成','https://google.com',3,2017);

insert into activity(projectname,leader,term,outline,link,grade,year) 
values('IoT','惣伊田','春学期','CO2センサーを用いた部屋の混雑状況の可視化','https://google.com',3,2017);

insert into activity(projectname,leader,term,outline,link,grade,year) 
values('地図アプリ','矢田','春学期','googleマップAPIを使って地図を自作','https://google.com',2,2016);

insert into activity(projectname,leader,term,outline,link,grade,year) 
values('ゲーム','矢田','秋学期','Unityで2Dのゲームを作成','https://google.com',2,2016);
-------------------------------------------------------------------------------------------

insert into bulletin(subject,maintext,userid) values('[悲報]ねむいんご','ねむいからねむいんだ','b2150220');

INSERT INTO comment VALUES (0,'すごい！','矢田修弘','2018-03-01');

----データの例（serialでIDをつけてるのでactivityテーブルとの関連に注意）------------
INSERT INTO projectdetail (outline, achievement, impression) 
values ('プロジェクトメンバー用のポータルサイトの作成を作成した。','連絡の閲覧及び掲示機能、活動履歴の閲覧及び追加機能、スケジュールの閲覧及び追加機能を実装した。','エラー処理をするとなお良い。');

INSERT INTO projectdetail (outline, achievement, impression) 
values ('CO2センサーで二酸化炭素濃度を測り、その数値をもとに部屋の混雑状況を可視化する。','CO2センサーで部屋の二酸化炭素濃度を測ることができた。','データの扱いで難航した。');

INSERT INTO projectdetail (outline, achievement, impression) 
values ('JavaFXでgooglemapAPIを使用して地図アプリのインターフェースを自作した。','検索機能とルート探索機能（徒歩）を実装することができた。','位置情報が取得できればもっとよかったと思う。');

INSERT INTO projectdetail (outline, achievement, impression) 
values ('Unityを使って2D横スクロールアクションゲームをつくる。','やばいもんができた。','途中で飽きた。');
--------------------------------------------------------------------------------

insert into schedule(content,starttime,endtime,userid) values('EIGO','2018-03-05 13:13:11','2018-03-05 15:43:32','b2150220');

insert into useraccount(userid,password,username,grade) values('b2150220','123','admin',3);
insert into useraccount(userid,password,username,grade) values('b2160220','123','user',2);

insert into userpost(bulletinid,userid,read) values(1,'b2150220',false);
