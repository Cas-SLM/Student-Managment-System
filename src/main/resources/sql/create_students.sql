CREATE TABLE IF NOT EXISTS `Students`(
   `id` INTEGER
       NOT NULL
       PRIMARY KEY AUTOINCREMENT,
   `name` TEXT
       NOT NULL,
   `email` VARCHAR(255)
       NOT NULL,
   `date_of_birth` DATE
       NOT NULL
);