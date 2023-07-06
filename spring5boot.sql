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