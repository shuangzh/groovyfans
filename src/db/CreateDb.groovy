package db

import groovy.sql.Sql

/**
 * Created by admin on 2017/5/26.
 */

import static db.HsqldbServer.*

class CreateDb {
    static ddls = [
            '''
            Create table cookbook(
             ID integer primary key,
             Title varchar(100),
             author varchar(100),
             year integer
            )
            ''',
            '''
            CREATE TABLE CHAPTER(
            ID INTEGER PRIMARY KEY,
            BOOK_ID INTEGER,
            TITLE VARCHAR(100))
            ''',
            '''
            CREATE TABLE RECIPE(
            ID INTEGER PRIMARY KEY,
            CHAPTER_ID INTEGER,
            TITLE VARCHAR(100),
            DESCRIPTION CLOB,
            IMAGE BLOB)
            ''',
            '''
            CREATE TABLE INGREDIENT(
            ID INTEGER PRIMARY KEY,
            RECIPE_ID INTEGER,
            NAME VARCHAR(100),
            AMOUNT DOUBLE,
            UNITS VARCHAR(20))
            '''
    ]

    static Sql sql

    static  create(){
        sql = Sql.newInstance(dbSettings)
        ddls.each { ddl -> sql.execute(ddl) }
        println "schema created successfully"
    }

    static init(){
        def cookbooks = [
                [ id: 5,
                  title: '30-minute-meals',
                  author: 'Jamie Oliver',
                  year: 2010],
                [ id: 6,
                  title: 'Ministry of food',
                  author: 'Jamie Oliver',
                  year: 2005],
                [ id: 7,
                  title: 'Vegan food',
                  author: 'Mr. Spock',
                  year: 2105]
        ]

        cookbooks.each { it ->  sql.execute("INSERT INTO cookbook VALUES (:id, :title, :author, :year)",it)  }

        println "initial insert data success"
        println "query cookbook table"
        sql.eachRow("SELECT  * FROM COOKBOOK") { it -> printf '%-5s%-30s%-10s\n' , it.id, it.title, it.year  }

    }

    static main(args){
        startServer()       // 启动数据库
        create()            // 创建schema
        init()              // 初始化数据

    }

}
