# Index Builder

## Introduction

This index builder reads inputs from a [JSON list of objects](https://github.com/yefeiw/coding_exercise/tree/master/IndexBuilder/configs) and output the forward index to the sql database, and the inveretd index to the memcached server.

Usually the index server is run independent of the main ad serching engine for better robustness and cleaner design.

## Steps to run:

### [Setup Memcached](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-memcache-on-ubuntu-14-04)

```
/usr/local/bin/memcached -d -p 11211
telnet 127.0.0.1 11211
```

### [Setup Mysql](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-14-04)

```
mysql -u root -p 

$$$ enter password as root

create database searchad;

use searchad

create table ad (adId int(11),campaignId int(11), keyWords varchar(1024), bitPrice double, price double, thumbnail mediumtext, description mediumtext, brand varchar(100), detail_url mediumtext, category varchar(1024), title varchar(2048), primary key(adId));

create table campaign(campaignId int(11), budget double, primary key(campaignId));

```

### Load Project as Eclipse

load all [jars](https://github.com/yefeiw/coding_exercise/tree/master/IndexBuilder/javalib)

Click run and check outputs from the console.

