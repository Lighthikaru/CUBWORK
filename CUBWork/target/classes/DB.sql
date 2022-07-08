CREATE TABLE Currency (
                          code CHAR(3)  NOT NULL PRIMARY KEY ,
                          chinese_name CHAR(4) ,
                          UPDATETIME TIMESTAMP,
                          RATE CHAR(50)
);

insert into Currency(UPDATETIME,code,chinese_name,RATE)values ( '2022-07-07 00:00:00','USD','美金','21,757.4486');
insert into Currency(UPDATETIME,code,chinese_name,RATE)values ( '2022-07-07 00:00:00','GBP','英鎊','18,180.3500');
insert into Currency(UPDATETIME,code,chinese_name,RATE)values ( '2022-07-07 00:00:00','EUR','歐元','21,194.9315');
