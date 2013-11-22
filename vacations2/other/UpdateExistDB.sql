-- добавляем поля в таблицу Employees
ALTER TABLE Employees ADD 
	  Admin bit NOT NULL DEFAULT 0,
	  Login nvarchar(50) NOT NULL DEFAULT '' ;
GO

-- изменяем строковый тип в колонках на нормальный 
ALTER TABLE Employees ALTER COLUMN Name nvarchar(50)
ALTER TABLE Employees ALTER COLUMN Position nvarchar(50)
ALTER TABLE Employees ALTER COLUMN Telephone nvarchar(50)
ALTER TABLE Employees ALTER COLUMN TabNumber nvarchar(50)
ALTER TABLE Employees ALTER COLUMN Position nvarchar(50)

ALTER TABLE Vacations ALTER COLUMN Reason nvarchar(50)
ALTER TABLE Vacations ALTER COLUMN Note nvarchar(50)
ALTER TABLE Vacations ALTER COLUMN audit_user nvarchar(50)
GO

-- убераем пробелы
UPDATE Employees
SET Name = RTRIM(Name),
    Position = RTRIM(Position),
    Telephone = RTRIM(Telephone),
    TabNumber = RTRIM(TabNumber)    
    
UPDATE Vacations
SET Reason = RTRIM(Reason),
    Note = RTRIM(Note),
    audit_user = RTRIM(audit_user)
    
