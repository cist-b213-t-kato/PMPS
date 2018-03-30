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
values('PMPS','上杉将史','秋学期','プロメン専用ポータルサイトの作成','https://drive.google.com/drive/folders/1DDwG4yxHynhND28xHhGVqNCY8d2i6g4A?usp=sharing',3,2017);

insert into activity(projectname,leader,term,outline,link,grade,year)
values('IoT','上杉将史','春学期','CO2センサーを用いた部屋の混雑状況の可視化','https://drive.google.com/drive/folders/0B5-QhbGJR3-YY3lGdnBLM2R4Vlk?usp=sharing',3,2017);

insert into activity(projectname,leader,term,outline,link,grade,year)
values('地図アプリ','上杉将史','春学期','googleマップAPIを使って地図を自作','https://drive.google.com/drive/folders/0B8HCX3kixLDNejFpYW0yX3VNQk0?usp=sharing',2,2016);

insert into activity(projectname,leader,term,outline,link,grade,year)
values('ゲーム','矢田修弘','春学期','Unityで2Dのゲームを作成','https://google.com',3,2017);

insert into activity(projectname,leader,term,outline,link,grade,year)
values('もぐらたたき','阿部晃大','春学期','JavaFxを用いたもぐらたたき','https://drive.google.com/drive/folders/0B8HCX3kixLDNajc0WVFpUm1rdU0?usp=sharing',2,2016);

insert into activity(projectname,leader,term,outline,link,grade,year)
values('BlackJack','米田司','春学期','カードゲーム','https://drive.google.com/drive/folders/0B8HCX3kixLDNWEdiYVgtWFpWalU?usp=sharing',2,2016);

insert into activity(projectname,leader,term,outline,link,grade,year)
values('SLMS(一部機能)','阿部晃大','秋学期','予襲復讐管理システム','https://drive.google.com/drive/folders/0B8HCX3kixLDNUWNxRDdFeHVraGc?usp=sharing',2,2016);


-------------------------------------------------------------------------------------------


----データの例（serialでIDをつけてるのでactivityテーブルとの関連に注意）------------
INSERT INTO projectdetail (outline, achievement, impression)
values ('プロジェクトメンバー用のポータルサイトの作成を作成した。','連絡の閲覧及び掲示機能、活動履歴の閲覧及び追加機能、スケジュールの閲覧及び追加機能を実装した。',
'エラー処理が甘いので今後はエラー処理に時間を掛けたいところ。');

INSERT INTO projectdetail (outline, achievement, impression)
values ('CO2センサーで二酸化炭素濃度を測り、その数値をもとに部屋の混雑状況を可視化する。','CO2センサーで部屋の二酸化炭素濃度を測ることができた。',
'技術不足でwicketやservletでデータを取得できなかった');

INSERT INTO projectdetail (outline, achievement, impression)
values ('JavaFXでgooglemapAPIを使用して地図アプリのインターフェースを自作した。','検索機能とルート探索機能（徒歩）を実装することができた。',
'位置情報が取得できればもっとよかったと思う。また、読み込み速度が遅いので、改善が求められる。');

INSERT INTO projectdetail (outline, achievement, impression)
values ('Unityを使って2D横スクロールアクションゲームをつくる。','やばいもんができた。','途中で飽きた。');

INSERT INTO projectdetail (outline, achievement, impression)
values('JavaFxを用いたもぐらたたきの作成','ゲームを一通りプレイすることができ、スコアを表示できた',
'もぐらのヴァリエーションや縛りプレイ、難易度の導入など改良ができそう。');

INSERT INTO projectdetail (outline, achievement, impression)
values('JavaFxを用いたBlackJackの作成','あるときにデータが消えてしまい、ネットに落ちているソースを採用した','バックアップをとることが大事であることがわかった。');

INSERT INTO projectdetail (outline, achievement, impression)
values('予襲復讐管理システムの教員側の機能の作成','トップページ、講義や学生ごとのデータを表示するページの作成を行った。','役割分担をきっちりとし、期日までに作業を行えた。');

----------------------------ユーザーアカウント----------------------------------------------------

insert into useraccount(userid,password,username,grade) values('b2150220','123','上杉将史',3);
insert into useraccount(userid,password,username,grade) values('b2160220','123','坂上田村麻呂',2);
insert into useraccount(userid,password,username,grade) values('b2151640','123','矢田修弘',3);
insert into useraccount(userid,password,username,grade) values('b2150940','123','惣伊田隼平',3);
insert into useraccount(userid,password,username,grade) values('b2160000','123','大塩平八郎',2);
insert into useraccount(userid,password,username,grade) values('b2169999','123','柳生但馬守宗矩',2);

------------------------------------掲示と受取人のポスト、bulletinidが紐づいているので注意----------------------------------------------------------------
insert into bulletin(subject,maintext,userid) values('4/12の活動について','G201にて今後の活動について話します。','b2150220');
insert into bulletin(subject,maintext,userid) values('e306の利用について','研究棟3階にあるe306の利用方法についての文章','b2150220');


insert into userpost(bulletinid,userid,read) values(1,'b2160220',false);
insert into userpost(bulletinid,userid,read) values(1,'b2160000',false);
insert into userpost(bulletinid,userid,read) values(1,'b2160999',false);
insert into userpost(bulletinid,userid,read) values(1,'b2150220',false);
insert into userpost(bulletinid,userid,read) values(1,'b2161640',false);
insert into userpost(bulletinid,userid,read) values(1,'b2160940',false);

insert into userpost(bulletinid,userid,read) values(2,'b2160220',false);
insert into userpost(bulletinid,userid,read) values(2,'b2160000',false);
insert into userpost(bulletinid,userid,read) values(2,'b2160999',false);
insert into userpost(bulletinid,userid,read) values(2,'b2150220',false);
insert into userpost(bulletinid,userid,read) values(2,'b2161640',false);
insert into userpost(bulletinid,userid,read) values(2,'b2160940',false);

------------------------------------コメント、projectidが紐づいているので注意------------------------------------------------------------------------------
INSERT INTO comment VALUES (1,'とてもよい活動ですね！','坂上田村麻呂','2018-03-01');
INSERT INTO comment VALUES (1,'ありがとうございます!','上杉将史','2018-03-02');
INSERT INTO comment VALUES (2,'すごい！','大塩平八郎','2018-03-03');
INSERT INTO comment VALUES (4,'とてもよき活動なり','柳生但馬守宗矩','2018-03-01');
INSERT INTO comment VALUES (4,'ありがとうございます！','矢田修弘','2018-03-01');

------------------------------------スケジュール------------------------------------------------------------------------------
insert into schedule(content,starttime,endtime,userid) values('発表練習','2018-03-31 7:00:00','2018-03-31 12:00:00','b2150220');
insert into schedule(content,starttime,endtime,userid) values('2年生プロメン発表会','2018-03-31 13:00:00','2018-03-31 15:00:00','b2150220');
insert into schedule(content,starttime,endtime,userid) values('3年生プロメン発表会','2018-03-31 15:00:01','2018-03-31 17:00:00','b2150220');
insert into schedule(content,starttime,endtime,userid) values('お疲れ様会','2018-04-01 1:13:11','2018-04-01 23:43:32','b2150220');



