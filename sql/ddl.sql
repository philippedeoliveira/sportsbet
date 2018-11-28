create table users (username varchar(42), fullName varchar(200), password varchar(255), enabled boolean not null, buId integer, primary key (username)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table games (id integer(11) AUTO_INCREMENT, gameDate datetime, receiver varchar(42), foreigner varchar(42), receiverScore integer(11), foreignerScore integer(11), round varchar(255), roundNumber integer(11), primary key (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table userbets (better varchar(42), gameId integer(11), receiverScore integer(11), foreignerScore integer(11),
   primary key (better, gameId),
   foreign key (better) references users(username),
   foreign key (gameId) references games(id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null,
    foreign key (username) references users(username)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
create table authorities (
      username varchar(50) not null,
      authority varchar(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);
create table businessUnit (id integer primary key, label varchar(100), location varchar(100));
alter table users add constraint foreign key (buId) references businessUnit(id);

create table businessUnitCodeToId(id integer, buCode varchar(42), primary key(id, buCode));
create table winner(winningTeam varchar(42));