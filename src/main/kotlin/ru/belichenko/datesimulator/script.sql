CREATE TABLE IF NOT EXISTS users (
    id SERIAl PRIMARY KEY,         --номер в системе
    gender ENUM('MALE', 'FEMALE'), --пол очев
    age INT,                       --возраст очев
    firstName TEXT,                --имя очев
    lastName TEXT,                 --фамилия очев
    photoUrl TEXT,                 --фото очев
    login TEXT NOT NULL,           --логин очев
    password TEXT NOT NULL,        --пароль очев
    userId UUID NOT NULL,          --уникальный номер узера
    token TEXT NOT NULL            --уникальный стринг нынешнего доступа
);

CREATE TABLE IF NOT EXISTS reactions (
    id SERIAl PRIMARY KEY,         --номер в системе
    idFrom UUID NOT NULL,          --номер узера, от которого пришла реакция
    idTo UUID NOT NULL,            --номер узера, кому пришла реакция
    reaction BOOLEAN NOT NULL,     --реакция тру или фолс

    FOREIGN KEY (userFrom) REFERENCES users(uuid), --связь id нынешнего с id из users()
    FOREIGN KEY (userTo) REFERENCES users(uuid)    --связь id нынешнего с id из users()
);