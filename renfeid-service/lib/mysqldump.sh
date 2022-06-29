#!/usr/bin/env bash

CRTDIR=$(pwd)

mysqldump -h 127.0.0.1 -P 3306 -uroot -proot renfeid>$CRTDIR/mysqldump.sql