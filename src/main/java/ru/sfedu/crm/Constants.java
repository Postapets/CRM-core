package ru.sfedu.crm;

public class Constants {

    //for labs
    //path to hibernate config file
    public static final String DEFAULT_HIBERNATE_PATH = "HIBERNATE.CONFIG.PATH";

    //configs
    public static final String DEFAULT_CONFIG_PATH = "./src/main/resources/environment.properties";

    //sql
    public static final String SQL_ALL_SCHEMAS = "SELECT schema_name FROM information_schema.schemata";




    //
    //
    //
    //csv
    public static final String PATH_CSV = "PATH.CSV";
    public static final String EXT_CSV = "EXT.CSV";
    public static final String DPCSV = "DPCSV";

    //xml
    public static final String PATH_XML = "PATH.XML";
    public static final String EXT_XML = "EXT.XML";
    public static final String DPXML = "DPXML";

    //mdb
    public static final String MG_DATABASE = "MG.DATABASE";
    public static final String MG_HOST = "MG.HOST";
    public static final String MG_PORT = "MG.PORT";
    public static final String MG_OBJECT = "object";
    public static final String MG_TIME = "time";
    public static final String MG_DATA_TYPE = "dataType";

    //H2 db
    public static final String DPDB = "DPDB";
    public static final String EXT_DB = "EXT.DB";
    public static final String DB_DRIVER = "DB.DRIVER";
    public static final String DB_URL = "DB.URL";
    public static final String DB_USER = "DB.USER";
    public static final String DB_PASSWORD = "DB.PASSWORD";

    //queries
    public static final String SELECT_ALL_FROM = "select * from ";
    public static final String DROP_ALL_TABLES = "drop table if exists feedback, request, requestcategory, worker, client, user";


    //queries for entity User
    public static final String CREATE_TABLE_USER = "create table if not exists user" +
            "(id bigint primary key" +
            ",name varchar(70)" +
            ",birthDate bigint" +
            ",phoneNumber varchar(15))";
    public static final String INSERT_USER = "insert into user(id,name,birthDate,phoneNumber) values(?, ?, ?, ?)";
    public static final String DELETE_FROM_USER_BY_ID = "delete from user where id in (%d)";
    public static final String UPDATE_USER_BY_ID = "update user set " +
            " id = %d" +
            ",name = '%s'" +
            ",birthDate = %d" +
            ",phoneNumber = '%s'" +
            " where id in (%d)";


    //queries for entity Client
    public static final String CREATE_TABLE_CLIENT = "create table if not exists client" +
            "(id bigint primary key" +
            ",name varchar(70)" +
            ",birthDate bigint" +
            ",phoneNumber varchar(15)" +
            ",inn bigint" +
            ",status varchar(25))";
    public static final String INSERT_CLIENT = "insert into client(id,name,birthDate,phoneNumber,inn,status) values(?, ?, ?, ?, ?, ?)";
    public static final String DELETE_FROM_CLIENT_BY_ID = "delete from client where id in (%d)";
    public static final String UPDATE_CLIENT_BY_ID = "update client set " +
            " id = %d" +
            ",name = '%s'" +
            ",birthDate = %d" +
            ",phoneNumber = '%s'" +
            ",inn = %d" +
            ",status = '%s'" +
            " where id in (%d)";


    //queries for entity Worker
    public static final String CREATE_TABLE_WORKER = "create table if not exists worker" +
            "(id bigint primary key" +
            ",name varchar(70)" +
            ",birthDate bigint" +
            ",phoneNumber varchar(15)" +
            ",position varchar(50)" +
            ",employmentDate bigint" +
            ",leaveDate bigint)";
    public static final String INSERT_WORKER = "insert into worker(id,name,birthDate,phoneNumber,position,employmentDate,leaveDate) values(?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_FROM_WORKER_BY_ID = "delete from worker where id in (%d)";
    public static final String UPDATE_WORKER_BY_ID = "update worker set " +
            " id = %d" +
            ",name = '%s'" +
            ",birthDate = %d" +
            ",phoneNumber = '%s'" +
            ",position = '%s'" +
            ",employmentDate = %d" +
            ",leaveDate = %d" +
            " where id in (%d)";


    //queries for entity Request
    public static final String CREATE_TABLE_REQUEST = "create table if not exists request" +
            "(id bigint primary key" +
            ",clientId bigint" +
            ",date bigint" +
            ",category bigint" +
            ",status varchar(25)" +
            ",description varchar(250)" +
            ",dismissingReason varchar(250))";
    public static final String INSERT_REQUEST = "insert into request(id,clientId,date,category,status,description,dismissingReason) values(?, ?, ?, ?, ?, ?, ?)";
    public static final String DELETE_FROM_REQUEST_BY_ID = "delete from request where id in (%d)";
    public static final String UPDATE_REQUEST_BY_ID = "update request set " +
            " id = %d" +
            ",clientId = %d" +
            ",date = %d" +
            ",category = %d" +
            ",status = '%s'" +
            ",description = '%s'" +
            ",dismissingReason = '%s'" +
            " where id in (%d)";


    //queries for entity Feedback
    public static final String CREATE_TABLE_FEEDBACK = "create table if not exists feedback" +
            "(id bigint primary key" +
            ",request bigint" +
            ",rate varchar(25)" +
            ",message varchar(250))";
    public static final String INSERT_FEEDBACK  = "insert into feedback(id,request,rate,message) values(?, ?, ?, ?)";
    public static final String DELETE_FROM_FEEDBACK_BY_ID = "delete from feedback where id in (%d)";
    public static final String UPDATE_FEEDBACK_BY_ID = "update feedback set " +
            " id = %d" +
            ",request = %d" +
            ",rate = '%s'" +
            ",message = '%s'" +
            " where id in (%d)";

    //queries for entity RequestCategory
    public static final String CREATE_TABLE_REQUEST_CATEGORY = "create table if not exists requestcategory" +
            "(id bigint primary key" +
            ",categoryName varchar(30))";
    public static final String INSERT_CATEGORY = "insert into requestcategory(id,categoryName) values(?, ?)";
    public static final String DELETE_FROM_CATEGORY_BY_ID = "delete from requestcategory where id in (%d)";
    public static final String UPDATE_CATEGORY_BY_ID = "update requestcategory set " +
            " id = %d" +
            ",categoryName = '%s'" +
            " where id in (%d)";

    //defaults
    public static final String DEFAULT_USERNAME = "John Smith";
    public static final long   DEFAULT_BIRTH_DATE = 817662118;
    public static final String DEFAULT_PHONE_NUMBER = "+78005553535";
    public static final long   DEFAULT_INN = 1234567890;
    public static final String DEFAULT_POSITION = "Chief";
    public static final long   DEFAULT_EMPLOYMENT_DATE = 1607126400;
    public static final long   DEFAULT_LEAVE_DATE = 9999999999L;
    public static final String DEFAULT_CATEGORY_NAME = "Repair";

    //methods
    public static final String EDIT_CATEGORY = "editcategory";
    public static final String CREATE_REQUEST = "createrequest";
    public static final String VIEW_CLIENT_REQUESTS = "viewclientrequests";
    public static final String CREATE_FEEDBACK_COMMENT = "createfeedbackcomment";
    public static final String PROCESS_REQUEST = "processrequest";
    public static final String VIEW_ALL_FEEDBACK_COMMENTS = "viewallfeedbackcomments";
    public static final String VIEW_ALL_REQUESTS = "viewallrequests";

    //exceptions
    public static final String EXC_BOOL_IS_FALSE = "Boolean is false";
    public static final String EXC_OBJ_IS_NULL = "Object is null";
    public static final String EXC_OBJ_IS_NOT_NULL = "Obj is not null";
    public static final String EXC_EMPTY_LIST = "Empty list";
    public static final String EXECUTED = "Command executed";
    public static final String ILLEGAL_ARGUMENTS = "Illegal arguments!";

}
