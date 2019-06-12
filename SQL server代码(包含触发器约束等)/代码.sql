--�������ݿ���ر�֮��Ĳ���������Լ��
CREATE DATABASE SchoolBookManage

CREATE TABLE book
(
	ͼ����   nvarchar(20)  NOT NULL,--���
	ͼ������   nvarchar(20)  NOT NULL,--����
	���   nvarchar(20)  NOT NULL,--���
	���� nvarchar(10) NOT NULL,--����
	������   nvarchar(20) NOT NULL,--������
	�������� date NOT NULL,--��������
	���� int NOT NULL,--����
	�ڲ��� int NOT NULL,--�ڲ���
	PRIMARY KEY (ͼ����)
)

CREATE TABLE borrowleaderboard
(
	ͼ���� nvarchar(20) NOT NULL,
	���Ĵ��� int NOT NULL,
	PRIMARY KEY (ͼ����),
	FOREIGN KEY (ͼ����) REFERENCES book(ͼ����) 	                    
		ON DELETE CASCADE	
		/*��ɾ��book ���е�Ԫ���������recording��һ��ʱ�ܾ�ɾ��*/
		ON UPDATE CASCADE
      	/*��������*/
)

CREATE TABLE student
(
	���߱�� nvarchar(20) NOT NULL,
	���� nvarchar(20) NOT NULL,
	���� nvarchar(10) NOT NULL,
	�Ա� nvarchar(2) NOT NULL,
	ѧλ nvarchar(10) NOT NULL,
	ѧԺ nvarchar(20) NOT NULL,
	��ϵ�绰 char(11) NOT NULL,
	�ɽ����� int NOT NULL,
	������ int NOT NULL,
	�������� int NOT NULL,
	ͷ�� IMAGE,
	�Ƿ񶳽� bit,
	PRIMARY KEY (���߱��)
)

CREATE TABLE teacher
(
	���߱�� nvarchar(20) NOT NULL,
	���� nvarchar(20) NOT NULL,
	���� nvarchar(10) NOT NULL,
	�Ա� nvarchar(2) NOT NULL,
	ѧԺ nvarchar(20) NOT NULL,
	��ϵ�绰 char(11) NOT NULL,
	�ɽ����� int NOT NULL,
	������ int NOT NULL,
	�������� int NOT NULL,
	ͷ�� IMAGE,
	PRIMARY KEY (���߱��)
)

CREATE TABLE manager
(
	����Ա��� nvarchar(20) NOT NULL,
	���� nvarchar(20) NOT NULL,
	���� nvarchar(10) NOT NULL,
	�Ա� nvarchar(2) NOT NULL,
	��ϵ�绰 char(11) NOT NULL,

	PRIMARY KEY (����Ա���)
)

CREATE TABLE recording
(
	���߱�� nvarchar(20) NOT NULL,
	ͼ���� nvarchar(20) NOT NULL,
	����ʱ�� datetime NOT NULL,
	������� nvarchar(4),--��ʦ��ѧ��
	׼������ bit NOT NULL,
	Ӧ��ʱ�� datetime,
	����ʱ�� datetime,
	�Ƿ����� bit NOT NULL,

	PRIMARY KEY (���߱��,ͼ����,����ʱ��), 				
    /*FOREIGN KEY (���߱��) REFERENCES student(���߱��) 
		ON DELETE NO ACTION
		/*��ɾ��student ���е�Ԫ���������recording��һ��ʱ�ܾ�ɾ��*/
        ON UPDATE CASCADE,    
		/*��������*/
	FOREIGN KEY (���߱��) REFERENCES teacher(���߱��)
		ON DELETE NO ACTION
		/*��ɾ��student ���е�Ԫ���������recording��һ��ʱ�ܾ�ɾ��*/
        ON UPDATE CASCADE,    
		/*��������*/*/
    FOREIGN KEY (ͼ����) REFERENCES book(ͼ����) 	                    
		ON DELETE NO ACTION 	
		/*��ɾ��book ���е�Ԫ���������recording��һ��ʱ�ܾ�ɾ��*/
		ON UPDATE CASCADE
      	/*��������*/
);
CREATE TABLE studentrecording
(
	���߱�� nvarchar(20) NOT NULL,
	ͼ���� nvarchar(20) NOT NULL,
	����ʱ�� datetime NOT NULL,
	׼������ bit NOT NULL,
	Ӧ��ʱ�� datetime,
	����ʱ�� datetime,
	�Ƿ����� bit NOT NULL,

	PRIMARY KEY (���߱��,ͼ����,����ʱ��),
    FOREIGN KEY (���߱��) REFERENCES student(���߱��) 
		ON DELETE NO ACTION
		/*��ɾ��student ���е�Ԫ���������recording��һ��ʱ�ܾ�ɾ��*/
        ON UPDATE CASCADE,
		/*��������*/
    FOREIGN KEY (ͼ����) REFERENCES book(ͼ����) 	                    
		ON DELETE NO ACTION 	
		/*��ɾ��book ���е�Ԫ���������recording��һ��ʱ�ܾ�ɾ��*/
		ON UPDATE CASCADE
);
CREATE TABLE teacherrecording
(
	���߱�� nvarchar(20) NOT NULL,
	ͼ���� nvarchar(20) NOT NULL,
	����ʱ�� datetime NOT NULL,
	׼������ bit NOT NULL,
	Ӧ��ʱ�� datetime,
	����ʱ�� datetime,
	�Ƿ����� bit NOT NULL,

	PRIMARY KEY (���߱��,ͼ����,����ʱ��), 				
	FOREIGN KEY (���߱��) REFERENCES teacher(���߱��)
		ON DELETE NO ACTION
		/*��ɾ��teacher ���е�Ԫ���������recording��һ��ʱ�ܾ�ɾ��*/
        ON UPDATE CASCADE,    
		/*��������*/
    FOREIGN KEY (ͼ����) REFERENCES book(ͼ����) 	                    
		ON DELETE NO ACTION 	
		/*��ɾ��book ���е�Ԫ���������recording��һ��ʱ�ܾ�ɾ��*/
		ON UPDATE CASCADE
      	/*��������*/
);

CREATE TABLE studentfine
(
	���߱�� nvarchar(20) NOT NULL,
	ͼ���� nvarchar(20) NOT NULL,
	����ʱ�� datetime NOT NULL,
	���� smallint ,
	���� float,
	�Ƿ�ɷ� bit,

	PRIMARY KEY (���߱��,ͼ����),
	FOREIGN KEY (���߱��,ͼ����,����ʱ��) REFERENCES studentrecording(���߱��,ͼ����,����ʱ��) 
		ON DELETE NO ACTION
        ON UPDATE CASCADE, 
)
CREATE TABLE teacherfine
(
	���߱�� nvarchar(20) NOT NULL,
	ͼ���� nvarchar(20) NOT NULL,
	����ʱ�� datetime NOT NULL,
	���� smallint ,
	���� float,
	�Ƿ�ɷ� bit,

	PRIMARY KEY (���߱��,ͼ����),
	FOREIGN KEY (���߱��,ͼ����,����ʱ��) REFERENCES teacherrecording(���߱��,ͼ����,����ʱ��) 
		ON DELETE NO ACTION
        ON UPDATE CASCADE, 
)
CREATE TABLE seat
(
	����¥�� smallint NOT NULL,
	��� smallint NOT NULL,
	���߱�� nvarchar(20) 

	PRIMARY KEY (����¥��,���),
	FOREIGN KEY (���߱��) REFERENCES student(���߱��) 
		ON DELETE NO ACTION
		/*��ɾ��reader ���е�Ԫ���������seat��һ��ʱ�ܾ�ɾ��*/
        ON UPDATE CASCADE,    
		/*��������*/
)
/*��������*/
--�������������ֱ�ʵ�ֽ���ͻ���ʱ�Զ�����ͼ����Ϣ���ڲ�����,������,��deleted��inserted����
CREATE TRIGGER studentreturn_trigger
ON studentrecording
FOR UPDATE
AS
	DECLARE @booknumber nvarchar(20),@readerNumber nvarchar(20),@borrowTime datetime,@returnTime datetime,@shouldReturnTime datetime,@renew bit
	select @renew =deleted.�Ƿ�����
	from deleted

	select @returnTime = inserted.����ʱ��
	from inserted
		
	SELECT @booknumber = deleted.ͼ����,@readerNumber=deleted.���߱��,@shouldReturnTime=deleted.Ӧ��ʱ��,@borrowTime=deleted.����ʱ��
	FROM deleted

	if(@returnTime is not null)--����
	begin
		--�����
		UPDATE book
		SET �ڲ���=�ڲ���+1
		WHERE ͼ����=@booknumber
		--readyReturn��0
		UPDATE studentrecording
		SET ׼������ = 0
		WHERE ���߱��=@readerNumber and ͼ����=@booknumber
		--���߸���
		UPDATE student
		set �ɽ�����=�ɽ�����+1,������=������-1
		WHERE ���߱��=@readerNumber

		--���㷣��
		IF(DATEDIFF ( DAY , @shouldReturnTime , @returnTime )>0)
		BEGIN
			declare @overDate int,@money float
			set @overDate=DATEDIFF ( DAY , @shouldReturnTime , @returnTime )
			set @money=@overDate*0.5
			insert into studentfine values(@readerNumber,@booknumber,@borrowTime,@overDate,@money,0)
			--�����˻�
			UPDATE student
			set �Ƿ񶳽�=1
			where ���߱��=@readerNumber
		END	
	end
	if(@returnTime is null and @renew = 0)--û�����
	Begin
		select @renew = inserted.�Ƿ�����
		from inserted
		if(@renew = 1)--����
		begin
 			UPDATE studentrecording
			set Ӧ��ʱ��=DATEADD(day,30,@shouldReturnTime)--datetime���Ͳ�����
			where ���߱��=@readerNumber and ͼ����=@booknumber
		end
	end

CREATE TRIGGER teacherreturn_trigger
ON teacherrecording
FOR UPDATE
AS
	DECLARE @booknumber nvarchar(20),@readerNumber nvarchar(20),@borrowTime datetime,@returnTime datetime,@shouldReturnTime datetime,@renew bit
	select @renew =deleted.�Ƿ�����
	from deleted

	select @returnTime = inserted.����ʱ��
	from inserted
		
	SELECT @booknumber = deleted.ͼ����,@readerNumber=deleted.���߱��,@shouldReturnTime=deleted.Ӧ��ʱ��,@borrowTime=deleted.����ʱ��
	FROM deleted

	if(@returnTime is not null)--����
	begin
		--�����
		UPDATE book
		SET �ڲ���=�ڲ���+1
		WHERE ͼ����=@booknumber
		--readyReturn��0
		UPDATE teacherrecording
		SET ׼������ = 0
		WHERE ���߱��=@readerNumber and ͼ����=@booknumber
		--���߸���
		UPDATE teacher
		set �ɽ�����=�ɽ�����+1,������=������-1
		WHERE ���߱��=@readerNumber
		--���㷣��
		IF(DATEDIFF ( DAY , @shouldReturnTime , @returnTime )>0)
		BEGIN
			declare @overDate int,@money float
			set @overDate=DATEDIFF ( DAY , @shouldReturnTime , @returnTime )
			set @money=@overDate*0.5
			insert into teacherfine values(@readerNumber,@booknumber,@borrowTime,@overDate,@money,0)
			--�����˻�
			UPDATE teacher
			set �Ƿ񶳽�=1
			where ���߱��=@readerNumber
		END	
	end
	if(@returnTime is null and @renew = 0)--û�����
	Begin
		select @renew = inserted.�Ƿ�����
		from inserted
		if(@renew = 1)--����
		begin
 			UPDATE teacherrecording
			set Ӧ��ʱ��=DATEADD(day,30,@shouldReturnTime)--datetime���Ͳ�����
			where ���߱��=@readerNumber and ͼ����=@booknumber
		end
	end

CREATE TRIGGER studentborrow_trigger--INSERT INTO recording(���߱��,ͼ����) VALUES (?, ?)
ON  studentrecording
FOR INSERT
AS
	DECLARE @booknumber nvarchar(20),@readerNumber nvarchar(20),@readerCategory nvarchar(4)
	SELECT @booknumber = inserted.ͼ����,@readerNumber=inserted.���߱��
	FROM inserted

	select @readerCategory=student.ѧλ
	from student
	where student.���߱��=@readerNumber

	UPDATE book
	SET �ڲ���=�ڲ���-1
	WHERE ͼ����=@booknumber;

	--�������а�
	if((select count(*)
		from borrowleaderboard
		where borrowleaderboard.ͼ����=@booknumber
		))>0
	begin
		UPDATE borrowleaderboard
		set ���Ĵ���=���Ĵ���+1
		where borrowleaderboard.ͼ����=@booknumber
	end
	else
	begin
		INSERT INTO borrowleaderboard VALUES (@booknumber,'1')
	end
	--����ѧ����,����Ӧ������
	IF(@readerCategory = '������')
		begin
			--����ѧ����
			UPDATE student
			set �ɽ�����=�ɽ�����-1,������=������+1
			WHERE ���߱��=@readerNumber
			--����Ӧ�����ڣ�������30
			UPDATE studentrecording
			set Ӧ��ʱ��=DATEADD(day,30,GETDATE())
			WHERE ���߱��=@readerNumber and ͼ����=@booknumber
		end
	IF(@readerCategory = '˶ʿ' or @readerCategory = '��ʿ')
		begin
			--����ѧ����
			UPDATE student
			set �ɽ�����=�ɽ�����-1,������=������+1
			WHERE ���߱��=@readerNumber
			--����Ӧ�����ڣ�˶��60
			UPDATE studentrecording
			set Ӧ��ʱ��=DATEADD(day,60,GETDATE())
			WHERE ���߱��=@readerNumber and ͼ����=@booknumber
		end


CREATE TRIGGER teacherborrow_trigger--INSERT INTO recording(���߱��,ͼ����) VALUES (?, ?)
ON  teacherrecording
FOR INSERT
AS
	DECLARE @booknumber nvarchar(20),@readerNumber nvarchar(20)
	SELECT @booknumber = inserted.ͼ����,@readerNumber=inserted.���߱��
	FROM inserted

	UPDATE book
	SET �ڲ���=�ڲ���-1
	WHERE ͼ����=@booknumber;

	--�������а�
	if((select count(*)
		from borrowleaderboard
		where borrowleaderboard.ͼ����=@booknumber
		))>0
	begin
		UPDATE borrowleaderboard
		set ���Ĵ���=���Ĵ���+1
		where borrowleaderboard.ͼ����=@booknumber
	end
	else
	begin
		INSERT INTO borrowleaderboard VALUES (@booknumber,'1')
	end

	UPDATE teacher
	set �ɽ�����=�ɽ�����-1,������=������+1
	WHERE ���߱��=@readerNumber
	--����Ӧ�����ڣ���ʦ180��
	UPDATE teacherrecording
	set Ӧ��ʱ��=DATEADD(day,180,GETDATE())
	WHERE ���߱��=@readerNumber and ͼ����=@booknumber

drop TRIGGER teacherreturn_trigger
drop TRIGGER studentreturn_trigger

drop TRIGGER borrow_trigger

--ʹ�ô��������ɷ���
CREATE TRIGGER paystudentfine_trigger
ON studentfine
FOR UPDATE
AS
	DECLARE @readerNumber nvarchar(20)
	SELECT @readerNumber=inserted.���߱��
	FROM inserted

	update student
	set �Ƿ񶳽�=0
	where student.���߱��=@readerNumber

Update studentfine set �Ƿ�ɷ�=1 where ���߱��='201700800370'

CREATE TRIGGER payteacherfine_trigger
ON teacherfine
FOR UPDATE
AS
	DECLARE @readerNumber nvarchar(20)
	SELECT @readerNumber=inserted.���߱��
	FROM inserted

	update teacher
	set �Ƿ񶳽�=0
	where teacher.���߱��=@readerNumber

--������ͼ��ѯ����ͼ�����š��������������ڲ���
CREATE VIEW book_view (ͼ����,ͼ������,����,�ڲ���)
as
SELECT ͼ����,ͼ������,����,�ڲ���
FROM book

select * from book_view

--������ͼ�鿴���а�
CREATE VIEW leaderboard_view (ͼ����,ͼ������,���,����,������,���Ĵ���)
as
SELECT TOP 100 PERCENT book.ͼ����,ͼ������,���,����,������,���Ĵ���
FROM book,borrowleaderboard
where book.ͼ����=borrowleaderboard.ͼ����
order by ���Ĵ���

select * from leaderboard_view

drop  VIEW leaderboard_view
--�����洢���̲�ѯָ�����߽���ͼ������
CREATE PROCEDURE studentborrow @readernumber nvarchar(20)
AS 
	SELECT studentrecording.���߱��,����,book.ͼ����,book.ͼ������,studentrecording.Ӧ��ʱ��
	FROM student,studentrecording,book
	WHERE student.���߱�� = studentrecording.���߱�� AND student.���߱��=@readernumber and studentrecording.ͼ����=book.ͼ���� and studentrecording.����ʱ�� is null

CREATE PROCEDURE teacherborrow @readernumber nvarchar(20)
AS 
	SELECT teacherrecording.���߱��,����,book.ͼ����,book.ͼ������,teacherrecording.Ӧ��ʱ��
	FROM teacher,teacherrecording,book
	WHERE teacher.���߱�� = teacherrecording.���߱�� AND teacher.���߱��=@readernumber and teacherrecording.ͼ����=book.ͼ���� and teacherrecording.����ʱ�� is null


--Ĭ��ѧ���ɽ�����Ϊ20
ALTER TABLE student
ADD CONSTRAINT borrow1 DEFAULT 20 FOR �ɽ�����
--Ĭ����ʦ�ɽ�����Ϊ30
ALTER TABLE teacher
ADD CONSTRAINT borrow2 DEFAULT 30 FOR �ɽ�����

--Ĭ�Ͻ�����Ϊ0
ALTER TABLE student
ADD CONSTRAINT borrowed1 DEFAULT 0 FOR ������

ALTER TABLE teacher
ADD CONSTRAINT borrowed2 DEFAULT 0 FOR ������
--Ĭ�Ͻ���ʱ��Ϊ��ǰʱ��
ALTER TABLE studentrecording
ADD CONSTRAINT borrowtime1 DEFAULT GETDATE() FOR ����ʱ��

ALTER TABLE teacherrecording
ADD CONSTRAINT borrowtime2 DEFAULT GETDATE() FOR ����ʱ��
--Ĭ�϶���Ϊ0
ALTER TABLE student
ADD CONSTRAINT studentfreeze DEFAULT 0 FOR �Ƿ񶳽�

ALTER TABLE teacher
ADD CONSTRAINT teacherfreeze DEFAULT 0 FOR �Ƿ񶳽�
--Ĭ��׼������Ϊ0
ALTER TABLE studentrecording
ADD CONSTRAINT readyreturn1 DEFAULT 0 FOR ׼������

ALTER TABLE teacherrecording
ADD CONSTRAINT readyreturn2 DEFAULT 0 FOR ׼������
--Ĭ���Ƿ�����Ϊ0
ALTER TABLE studentrecording
ADD CONSTRAINT renew1 DEFAULT 0 FOR �Ƿ�����

ALTER TABLE teacherrecording
ADD CONSTRAINT renew2 DEFAULT 0 FOR �Ƿ�����


--���������ڲ�����������
CREATE TRIGGER booknumber_trigger
ON  book
FOR INSERT
AS
	DECLARE @exist int,@total int
	SELECT @exist = inserted.�ڲ���,
		   @total = inserted.�ڲ���
	FROM inserted
	IF(@exist is NULL)--�ڲ���Ĭ��Ϊ����
	BEGIN
		DECLARE @number nchar(20)
		SELECT @number = inserted.ͼ����
		FROM inserted

		UPDATE book
		SET �ڲ��� = @total
		WHERE ͼ����=@number
	END
	IF(@exist is not NULL and @exist <> @total)--�ڲ�����������������
	BEGIN
		PRINT '�ڲ���Ӧ������һ��'
		ROLLBACK
	END
	


CREATE PROCEDURE setseat 
   @floor AS smallint,@number AS smallint
AS
while @floor<=5
BEGIN
	while @number<=50
	BEGIN
		INSERT INTO seat  VALUES (@floor, @number,null)
		set @number = @number+1
	END
	set @floor = @floor+1
	set @number = 1
END

exec setseat 1,1
