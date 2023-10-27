CREATE TABLE projectboard (
  bno int NOT NULL AUTO_INCREMENT,
  title varchar(200) NOT NULL,
  writer varchar(100) NOT NULL,
  content text,
  readcount int default 0,
  image_File varchar(500),
  regdate datetime default now(),
  moddate datetime default now(),
  PRIMARY KEY (bno)
);

create table projectcomment(
cno int auto_increment,
bno int not null,
writer varchar(200) not null default "unknown",
content varchar(1000),
regdate datetime default now(),
primary key(cno));

create table projectmember(
id varchar(100),
pwd varchar(100) not null,
email varchar(100) not null,
age int default 0,
regdate datetime default now(),
lastlogin datetime default now(),
primary key(id)
);