
 create table blog (
       blog_id bigserial not null,
        createat varchar(255),
        details text,
        isactive varchar(255),
        title varchar(255),
        updateat varchar(255),
        username varchar(255),
        primary key (blog_id)
    );

    create table bloguser (
       user_id  bigserial not null,
        isactive varchar(255) not null,
        password varchar(255) not null,
        role varchar(255) not null,
        username varchar(255) not null,
        primary key (user_id)
    );

    create table comment (
       comment_id bigserial not null,
        blog_id int4,
        description varchar(255),
        username varchar(255),
        primary key (comment_id)
    );

    alter table bloguser
       add constraint UK_hge9my4wnd94peoyc1f9bb2ja unique (username)