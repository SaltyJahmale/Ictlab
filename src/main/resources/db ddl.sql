create table appuser
(
	id bigint not null
		constraint appuser_pkey
			primary key,
	email varchar(50) not null,
	enabled boolean not null,
	firstname varchar(50) not null,
	lastpasswordresetdate timestamp not null,
	lastname varchar(50) not null,
	password varchar(255) not null,
	username varchar(50) not null
		constraint uk_418sr20kh207kb22viuyno1
			unique
)
;

create table authority
(
	id bigint not null
		constraint authority_pkey
			primary key,
	name varchar(50) not null
)
;

create table appuser_authority
(
	user_id bigint not null
		constraint fk455lni71enywmyinrbphjqjie
			references appuser,
	authority_id bigint not null
		constraint fke6stopqi0mkv7ms9wlinq7b98
			references authority
)
;

create table class
(
	id bigint not null
		constraint class_pkey
			primary key,
	classname varchar(255) not null
		constraint uk_egp98xs01fho4b2n4hwp68k0a
			unique
)
;

create table message
(
	id bigint not null
		constraint message_pkey
			primary key,
	senddate timestamp not null,
	msg varchar(255) not null,
	state varchar(255) not null
)
;

create table message_appuser
(
	user_id bigint not null
		constraint fkf23em2obdr8iq55cvwuux25t0
			references appuser,
	message_id bigint not null
		constraint fko90hodoatrk3dlddvddlu7315
			references message
)
;

create table reservation
(
	id bigint not null
		constraint reservation_pkey
			primary key,
	enddate timestamp not null,
	startdate timestamp not null,
	title varchar(255) not null
)
;

create table reservation_appuser
(
	reservation_id bigint not null
		constraint fki66fpc38u8se85221ouif2ay4
			references appuser,
	user_id bigint not null
		constraint fkmm3ffvt2jmwspjmscscpcl7c
			references reservation
)
;

create table room
(
	id bigint not null
		constraint room_pkey
			primary key,
	floornumber varchar(255) not null,
	roomnumber varchar(255) not null
)
;

create table reservation_room
(
	reservation_id bigint not null
		constraint fk1p8c0gdgl9nnw8g3mavqchw2j
			references reservation,
	room_id bigint not null
		constraint fke368pj2d58a8xx37fk8p6k1jl
			references room
)
;

create table schoolschedule
(
	id bigint not null
		constraint schoolschedule_pkey
			primary key,
	enddate timestamp not null,
	startdate timestamp not null,
	title varchar(255) not null
)
;

create table schoolschedule_class
(
	schoolschedule_id bigint not null
		constraint fknwdoi9v1gdf9ojrfnsnrd7leh
			references schoolschedule,
	class_id bigint not null
		constraint fkng45umk5dyamrkd8lult53v6
			references class
)
;

create table schoolschedule_room
(
	schoolschedule_id bigint not null
		constraint fki6c5ut0e04bveaga6ctrnsss1
			references schoolschedule,
	room_id bigint not null
		constraint fksv6u8gw4jrn3qv1whiti31o84
			references room
)
;

create table school_schedule_teacher
(
	school_schedule_id bigint not null
		constraint fk1cg27itx50x12gw8vry8x5i5y
			references schoolschedule,
	teacher varchar(255)
)
;

create table sensordata
(
	id bigint not null
		constraint sensordata_pkey
			primary key,
	localdatetime timestamp,
	name varchar(255),
	sensordataenum varchar(255),
	sensorinputdata double precision,
	sensorlocation varchar(255),
	sensormeasurement varchar(255)
)
;

create table sensornode
(
	name varchar(255) not null
		constraint sensornode_pkey
			primary key,
	connectionstatus varchar(255),
	ip varchar(255),
	port varchar(255),
	sensordataenum varchar(255),
	sensornodestatus varchar(255),
	topic varchar(255)
		constraint uk_1qf52h3ysffvso56560ofy9ew
			unique
)
;

