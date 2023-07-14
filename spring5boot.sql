-- member
create table member2 (
    mno     int             auto_increment,
    userid  varchar(18)     unique,
    passwd  varchar(18)     not null,
    name    varchar(10)     not null,
    email   varchar(50)     unique,
    zipcode char(7)         not null,
    add1    varchar(100)    not null,
    add2    varchar(100)    not null,
    phone   char(13)        not null,
    regdate datetime        default current_timestamp,
    primary key (mno)
);

insert into member2 (userid, passwd, name, email, zipcode, add1, add2, phone)
values ('abc123', '987xyz', 'abc123', 'abc123@987xyz', '123-456', '서울 관악구', '블라블라', '123-4567-8910');

select * from member2;

-- board
create table board2 (
    bno         int             auto_increment,
    title       varchar(100)    not null,
    userid      varchar(18)     not null,
    regdate     datetime        default current_timestamp,
    thumbs      int             default 0,
    views       int             default 0,
    contents    text            not null,
    ipaddr      varchar(15)     not null,
    primary key (bno)
    -- foreign key (userid) references member2(userid)
);

alter table board2 add constraint fkuid foreign key(userid) references member2(userid);

insert into board2 (title, userid, contents, ipaddr)
values ('3 값이 필요한데, 1을(를) 가져왔습니다.', 'abc123', '3 값이 필요한데, 1을(를) 가져왔습니다.', '127.815.21.10');
insert into board2 (title, userid, contents, ipaddr)
values ('빌드가 731ms에서 성공적으로 완료되었습니다! (1분 전)', 'abc123a', '빌드가 731ms에서 성공적으로 완료되었습니다! (1분 전)', '168.15.12.21');
insert into board2 (title, userid, contents, ipaddr)
values ('모든 파일이 최신 상태입니다!', 'abc123c', '모든 파일이 최신 상태입니다!', '115.92.164.155');

select count(userid) cnt, ceil(count(userid) / 25) from board2;

-- pds
create table pds (
    pno         int             auto_increment,
    title       varchar(100)    not null,
    userid      varchar(18)     not null,
    regdate     datetime        default current_timestamp,
    thumbs      int             default 0,
    views       int             default 0,
    contents    text            not null,
    ipaddr      varchar(15)     not null,
    primary key (pno)
);

create table pdsattach (
    pano        int             auto_increment,
    pno         int             not null,       -- 게시글 번호
    fname       varchar(200)    not null,       -- 게시글 제목 (uuid 포함)
    ftype       varchar(3)      not null,       -- 게시글 타입
    fsize       float           not null,       -- 게시글 크기
    fdown       int             default 0,       -- 다운로드
    primary key (pano)
);

alter table pds add constraint fkpuid foreign key(userid) references member2(userid);
alter table pdsattach add constraint fkpno foreign key(pno) references pds(pno);