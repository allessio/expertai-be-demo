-- =========================================================================
-- DDL Expert AI DEMO
-- =========================================================================

drop table if exists content_entity;
drop table if exists contents;
drop table if exists entities;
drop table if exists entity_types;
drop table if exists knowledge_properties;
drop table if exists knowledge;


CREATE TABLE contents (
    content_id			int NOT NULL AUTO_INCREMENT,
    content				varchar(10000) NOT NULL,
    created_at 			TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ws_checked_at 		TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    content_language	varchar(2),
    ws_checker_version	varchar(255),
    ws_checker_success	boolean,
    
    PRIMARY KEY (content_id)
);

CREATE TABLE entity_types (
    entity_type			varchar(3) NOT NULL,
    description			varchar (100) NOT NULL,
    
    PRIMARY KEY (entity_type)
);

CREATE TABLE knowledge (
    syncon				int NOT NULL AUTO_INCREMENT,
    label				varchar (100) NOT NULL,
    
    PRIMARY KEY (syncon)
);

CREATE TABLE knowledge_properties (
	property_id			int NOT NULL AUTO_INCREMENT,
	property_type		varchar (100) NOT NULL,
	property_value		varchar (255) NOT NULL,
	syncon				int NOT NULL,
    
    PRIMARY KEY (property_id),
    FOREIGN KEY (syncon) REFERENCES knowledge(syncon)
);

CREATE TABLE entities (
    entity_id			int NOT NULL AUTO_INCREMENT,
    entity_type			varchar(3) NOT NULL,
    lemma				varchar(255) NOT NULL,
    syncon				int NOT NULL,
    
    PRIMARY KEY (entity_id),
    FOREIGN KEY (entity_type) REFERENCES entity_types(entity_type),
    FOREIGN KEY (syncon) REFERENCES knowledge(syncon)
);

CREATE TABLE content_entity (
    content_entity_id	int NOT NULL AUTO_INCREMENT,
    content_id			int NOT NULL,
    entity_id			int NOT NULL,
    start_position		int NOT NULL,
    end_position		int NOT NULL,
    
    PRIMARY KEY (content_entity_id),
    FOREIGN KEY (content_id) REFERENCES contents(content_id),
    FOREIGN KEY (entity_id) REFERENCES entities(entity_id)
);

