CREATE TABLE IF NOT EXISTS Users (id bigint IDENTITY, UserName varchar(100),
password varchar(100)
);
//
CREATE TABLE IF NOT EXISTS Disks (id bigint IDENTITY, DiskName varchar(100),
UserId bigint,
FOREIGN KEY (UserId) REFERENCES Users(id)
);
//
CREATE TABLE IF NOT EXISTS TakenItems (id bigint IDENTITY, DiskId bigint,
UserId bigint,
FOREIGN KEY (DiskId) REFERENCES Disks(id),
FOREIGN KEY (UserId) REFERENCES Users(id)
);